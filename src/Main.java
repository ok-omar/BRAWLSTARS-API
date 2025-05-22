import com.google.gson.Gson;
import controller.BrawlersController;
import model.DAO.DBConnection;
import view.View;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize the database connection
        DBConnection.openCon();

        // The Menu
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            View.printMainMenu();
            option = scanner.nextInt();
            scanner.nextLine(); // consume newline
            View.separate();

            switch (option) {
                case 1:
                    BrawlersController.listBrawlersFromDatabase();
                    break;
                case 2:
                    BrawlersController.listBrawlersFromEndpoint();
                    break;
                case 3:
                    BrawlersController.listBrawlersFromJson();
                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:
                    BrawlersController.copyAllBrawlers(scanner);
                    break;
                case 7:
                    BrawlersController.copyMissingBrawlers(scanner);
                    break;
                case 0:
                    DBConnection.closeCon();
                    View.separate();
                    break;
                default:

                    System.out.println("Opció no vàlida! Introdueix un número entre 0 i 7.");
            }
        } while (option != 0);

        scanner.close();
    }


}
