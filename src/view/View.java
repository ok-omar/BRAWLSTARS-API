package view;
import model.classes.*;

import java.util.List;

public class View {
    public static void printMainMenu(){
        System.out.println("========= BRAWLERS MENU =============================================");
        System.out.println("1. Llistar els Brawlers de la base de dades");
        System.out.println("2. Llistar els Brawlers de Brawlify API (ENDPOINT)");
        System.out.println("3. Llistar els Brawlers de Brawlstars API (JSON)");
        System.out.println("4. Cercar brawler amb id segons Brawlify API (ENDPOINT) y modificar brawler");
        System.out.println("5. Cercar brawler amb id segons el Brawlstars API (JSON) y modificar brawler");
        System.out.println("6. Afegir tots els brawlers de l'API / JSON a la base de dades (Còpia total de les dades)");
        System.out.println("7. Afegir Brawlers que falten de l'API / JSON a la base de dades (Còpia parcial de les dades)");
        System.out.println("0. Sortir");
        System.out.print("Escull una opció: ");
    }

    public static void printBrawler(Brawler b){
        System.out.println("ID: " + b.getId());
        System.out.println("Nom: " + b.getName());
        if (b.getBrawlerClass().getName() != null) {
            System.out.println("Class:  " + b.getBrawlerClass().getName().toUpperCase());
        }
        if (b.getRarity().getName() != null) {
            System.out.println("Rarity: " + b.getRarity().getName().toUpperCase());
        }

        System.out.println("Gadgets: ");
        printGadgets(b.getGadgets());
        System.out.println("Star Powers: ");
        printStarPowers(b.getStarPowers());

        if (b.getUpdateDate() != null) {
            System.out.println("Data d'actualització: " + b.getUpdateDate());
        }


    }

    public static void printBrawlers(List<Brawler> brawlers){
        for (Brawler b : brawlers){
            printBrawler(b);
            separate();
        }
    }

    public static void printBrawlerCompact(Brawler b){
        System.out.printf("ID: %d           Nom: %s%n", b.getId(), b.getName().toUpperCase());
        if (b.getBrawlerClass() != null) {
            System.out.printf("Class:  %s%n", b.getBrawlerClass().getName().toUpperCase());
        }
        if (b.getRarity() != null) {
            System.out.printf("Rarity: %s%n", b.getRarity().getName().toUpperCase());
        }
    }

    public static void printBrawlersCompact(List<Brawler> brawlers){
        for (Brawler b : brawlers){
            printBrawlerCompact(b);
            separate();
        }
    }

    public static void printGadgets(List<Gadget> gadgets){
        for (Gadget g : gadgets){
            System.out.printf("    Id: %d           Name: %s%n", g.getId(), g.getName().toUpperCase());
        }
    }

    public static void printStarPowers(List<StarPower> starPowers){
        for (StarPower sp : starPowers){
            System.out.printf("    Id: %d           Name: %s%n", sp.getId(), sp.getName().toUpperCase());
        }
    }

    public static void separate(){
        System.out.println("=====================================================================");
    }
}
