package controller;

import model.DAO.mysql.MySQLBrawlerDAO;
import model.api.BrawlersWrapper;
import model.api.brawlify.BrawlifyEndpoint;
import model.api.official.OfficialEndpoint;
import model.api.official.creds;
import model.classes.Brawler;
import view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BrawlersController {
    // Create
    /**
     * 5
     * Copy the missing brawlers from the JSON file and the endpoint to the database
     * @param scanner Scanner to read user input
     */
    public static void copyMissingBrawlers(Scanner scanner){
        List<Integer> validInput = new ArrayList<>();

        // Get the brawlers from the databse
        List<Brawler> dbBrawlers = MySQLBrawlerDAO.readAll();
        // Get the brawlers from the JSON file
        OfficialEndpoint.fetchAndSaveBrawlersJson(0);
        String jsonPath = creds.getOfficialJsonPath();
        List<Brawler> jsonBrawlers = null;
        try {
            jsonBrawlers = BrawlersWrapper.fromOfficialJsonFile(jsonPath).getOfficialBrawlers();
        } catch (IOException e) {
            System.out.printf("Error reading JSON file %s : %s", jsonPath, e.getMessage());
        }
        // Get the brawlers from the endpoint
        String fetchedBrawlify = BrawlifyEndpoint.fetchBrawlify();
        List<Brawler> endpointBrawlers = BrawlersWrapper.fromUnofficialJsonString(fetchedBrawlify).getUnofficialBrawlers();
        // remove the brawlers that are in the database from the brawlers from the JSON file
        List<Brawler> jsonRemainingBrawlers = null;
        if (jsonBrawlers != null){
            jsonRemainingBrawlers = new ArrayList<>(jsonBrawlers);
            jsonRemainingBrawlers.removeAll(dbBrawlers);
            // Check if the there are remaining brawlers
            if (!jsonRemainingBrawlers.isEmpty()){
                validInput.add(1);
                // Print the official api brawlers that are missing in the database
                System.out.println("**************************************************************************");
                System.out.println("Brawlstars API brawlers that are missing in the database:");
                System.out.println("**************************************************************************");
                View.printBrawlersCompact(jsonRemainingBrawlers);
            }
        }
        // Check if brawlers from the endpoint are in the database
        List<Brawler> endpointRemainingBrawlers = null;
        if (endpointBrawlers != null){
            endpointRemainingBrawlers = new ArrayList<>(endpointBrawlers);
            endpointRemainingBrawlers.removeAll(dbBrawlers);
            if (!endpointRemainingBrawlers.isEmpty()){
                validInput.add(2);
                // Print the unofficial api brawlers that are missing in the database
                System.out.println("**************************************************************************");
                System.out.println("Brawlify API brawlers that are missing in the database:");
                System.out.println("**************************************************************************");
                View.printBrawlersCompact(endpointRemainingBrawlers);
            }
        }
        // Ask if the user wants to insert the missing brawlers in the database
        if (validInput.isEmpty()){
            System.out.println("The database is up to date! No brawlers are missing from Brawlstars API nor Brawlify API.");
            return;
        } else {
            if (validInput.contains(1)) System.out.println("Missing brawlers from Brawlstars API: " + jsonRemainingBrawlers.size());
            if (validInput.contains(2)) System.out.println("Missing brawlers from Brawlify API:   " + endpointRemainingBrawlers.size());
            System.out.println("Which brawlers do you want to insert in the database?");
            System.out.println("0. None (Exit)");
            System.out.println("1. Brawlers from the Brawlstars API (JSON file)");
            System.out.println("2. Brawlers from the Brawlify API (Endpoint)");

            int option;
            do {
                System.out.printf("Select an option: ");
                option = scanner.nextInt();
                scanner.nextLine();
                if (option == 1 && !validInput.contains(1)) System.out.println("All the brawlers from the Brawlstars API (JSON file) are already in the database");
                else if (option == 2 && !validInput.contains(2)) System.out.println("All the brawlers from the Brawlify API (Endpoint) are already in the database");
                else if (option != 1 && option != 2 && option != 0) System.out.println("Invalid option. Please try again.");
            } while (!validInput.contains(option) && option != 0);

            if (option == 1){
                for (Brawler b : jsonRemainingBrawlers){
                    MySQLBrawlerDAO.Create(b);
                }
            } else if (option == 2) {
                for (Brawler b : endpointRemainingBrawlers){
                    MySQLBrawlerDAO.Create(b);
                }
            } else {
                System.out.println("No changes have been made to the database.");
            }
        }

    }

    /**
     * Add all the brawlers to the database (delets the whole database)
     * @param scanner
     */
    public static void copyAllBrawlers(Scanner scanner){
        // Get the brawlers from the JSON file
        String jsonPath = creds.getOfficialJsonPath();
        List<Brawler> jsonBrawlers = null;
        try {
            jsonBrawlers = BrawlersWrapper.fromOfficialJsonFile(jsonPath).getOfficialBrawlers();
        } catch (IOException e) {
            System.out.printf("Error reading JSON file %s : %s%n", jsonPath, e.getMessage());
        }
        // Get the brawlers from the endpoint
        String fetchedBrawlify = BrawlifyEndpoint.fetchBrawlify();
        List<Brawler> endpointBrawlers = BrawlersWrapper.fromUnofficialJsonString(fetchedBrawlify).getUnofficialBrawlers();
        // Ask if the user wants to insert the missing brawlers in the database

        System.out.println("Brawlers from Brawlstars API: " + jsonBrawlers.size());
        System.out.println("Brawlers from Brawlify API:   " + endpointBrawlers.size());
        System.out.println("Which brawlers do you want to insert in the database?");
        System.out.println("0. None (Exit)");
        System.out.println("1. Brawlers from the Brawlstars API (JSON file)");
        System.out.println("2. Brawlers from the Brawlify API (Endpoint)");
        System.out.printf("Select an option: ");
        int option;
        do {
            option = scanner.nextInt();
            scanner.nextLine();
            if (option != 1 && option != 2 && option != 0) System.out.println("Opció no vàlida. Intenta-ho de nou.");
        } while (option != 1 && option != 2 && option != 0);

        // Delete all brawlers
        MySQLBrawlerDAO.DeleteAll();

        // Insert all brawlers in the database
        if (option == 1){
            for (Brawler b : jsonBrawlers){
                MySQLBrawlerDAO.Create(b);
            }
        } else if (option == 2) {
            for (Brawler b : endpointBrawlers){
                MySQLBrawlerDAO.Create(b);
            }
        } else {
            System.out.println("Exiting...");
            System.out.println("No changes have been made to the database.");
        }

    }


    // Read

    /**
     * 6
     * List all brawlers from the JSON file (Brawlstars)
     */
    public static void listBrawlersFromJson() {
        OfficialEndpoint.fetchAndSaveBrawlersJson(1);
        String jsonPath = creds.getOfficialJsonPath();
        List<Brawler> brawlers = null;
        try {
            brawlers = BrawlersWrapper.fromOfficialJsonFile(jsonPath).getOfficialBrawlers();
            View.printBrawlers(brawlers);

        } catch (IOException e) {
            System.out.printf("Error reading JSON file %s : %s", jsonPath, e.getMessage());
        }

    }

    /**
     * 1
     * List all brawlers from the database
     */
    public static void listBrawlersFromDatabase() {
        List <Brawler> brawlers = MySQLBrawlerDAO.readAll();
        if (brawlers.isEmpty()) {
            System.out.println("No hi ha brawlers a la base de dades");
        } else {
            View.printBrawlers(brawlers);
        }

    }
    /**
     * 2
     * Show the content of the endpoint (Brawlify)
     */
    public static void listBrawlersFromEndpoint() {
        String fetchedBrawlify = BrawlifyEndpoint.fetchBrawlify();
        List<Brawler> brawlers = BrawlersWrapper.fromUnofficialJsonString(fetchedBrawlify).getUnofficialBrawlers();
        if (brawlers.isEmpty()) {
            System.out.println("No hi ha brawlers a l'endpoint");
        } else {
            View.printBrawlers(brawlers);
        }
    }


    // Update
    public static void updateBrawlerFromJson() {
        // Ask for the brawler ID

        // Search for the brawler in the JSON file

        // Ask if the user wants to update the brawler in the database

        // Update the brawler in the database, adding the updateDate

    }

    public static void updateBrawlerFromEndpoint() {
        // Ask for the brawler ID

        // Call the endpoint https://api.brawlify.com/v1/brawlers/{id}

        // Ask if the user wants to update the brawler in the database

        // Update the brawler in the database, adding the updateDate

    }



}
