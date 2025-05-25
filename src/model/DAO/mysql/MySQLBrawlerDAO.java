package model.DAO.mysql;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.DAO.DBConnection;
import model.classes.Brawler;
import model.classes.*;
import view.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLBrawlerDAO {
    // Create
    /**
     * Insert a brawler to the database
     * @param b the brawler insert to the database
     */
    public static void Create(Brawler b){
        Connection con = DBConnection.getConnection();
        String st = "INSERT OR REPLACE INTO Brawlers (brawler_id, name, rarity, class) VALUES (?, ?, ?, ?)";
        try {
            // Crear el brawler
            PreparedStatement ps = con.prepareStatement(st);
            ps.setInt(1, b.getId());
            ps.setString(2, b.getName().toUpperCase());


            if (b.getRarity() != null) {
                ps.setString(3, b.getRarity().getName().toUpperCase());
            } else {
                ps.setString(3, null);
            }


            if (b.getBrawlerClass() != null) {
                ps.setString(4, b.getBrawlerClass().getName().toUpperCase());
            } else {
                ps.setString(4, null);
            }
            ps.executeUpdate();

            MySQLGadgetsDAO.Create(b.getGadgets(), b.getId());
            MySQLStarPowersDAO.Create(b.getStarPowers(), b.getId());

            System.out.println("Inserted " + b.getName() + " successfully!");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    // Read

    /**
     * Get a brawler by its ID
     * @param brawler_id
     * @return The brawler
     */
    public static Brawler Read(int brawler_id){
        Connection con = DBConnection.getConnection();
        String st = "SELECT * from Brawlers WHERE brawler_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(st);
            ps.setInt(1, brawler_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");

                Rarity rarity = new Rarity();
                rarity.setName(rs.getString("rarity"));


                BrawlerClass brawlerClass = new BrawlerClass();
                brawlerClass.setName(rs.getString("class"));


                String updateDate = rs.getString("last_modified");
                List<Gadget> gadgets = getGadgetsByBrawlerId(brawler_id);
                List<StarPower> starPowers = getStarPowersByBrawlerId(brawler_id);
                return new Brawler(brawler_id, name, starPowers, gadgets, brawlerClass, rarity, formateDate(updateDate));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Get all brawlers from the database
     * @return List of brawlers
     */
    public static List<Brawler> readAll() {
        List<Brawler> brawlers = new ArrayList<>();
        Connection con = DBConnection.getConnection();
        String st = "SELECT * from Brawlers";

        try {
            PreparedStatement ps = con.prepareStatement(st);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int brawler_id = rs.getInt("brawler_id");
                String name = rs.getString("name");

                Rarity rarity = new Rarity();
                rarity.setName(rs.getString("rarity"));


                BrawlerClass brawlerClass = new BrawlerClass();
                brawlerClass.setName(rs.getString("class"));


                String updateDate = rs.getString("last_modified");
                List<Gadget> gadgets = getGadgetsByBrawlerId(brawler_id);
                List<StarPower> starPowers = getStarPowersByBrawlerId(brawler_id);
                brawlers.add(new Brawler(brawler_id, name, starPowers, gadgets, brawlerClass, rarity, formateDate(updateDate)));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return brawlers;

    }

    // Update
    public static void Update(Brawler b){
        Connection con = DBConnection.getConnection();
        String st = "UPDATE Brawlers SET name = ?, rarity = ?, class = ?, last_modified = ? WHERE brawler_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(st);
            ps.setString(1, b.getName().toUpperCase());
            if (b.getRarity() != null){
            ps.setString(2, b.getRarity().getName().toUpperCase());
            } else {
                ps.setString(2, null);
            }

            if (b.getBrawlerClass() != null){
                ps.setString(3, b.getBrawlerClass().getName().toUpperCase());
            } else {
                ps.setString(3, null);

            }
            ps.setString(4, formateDate(b.getUpdateDate()));
            ps.setInt(5, b.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    // Delete
    public static void Delete(int brawler_id){
        Connection con = DBConnection.getConnection();
        String st = "DELETE FROM Brawlers WHERE brawler_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(st);
            ps.setInt(1, brawler_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void DeleteAll(){
        Connection con = DBConnection.getConnection();
        String st = "DELETE FROM Brawlers";
        try {
            PreparedStatement ps = con.prepareStatement(st);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    // Helper functions
    /**
     * Get the gatdets of a brawler by its ID
     * @param brawlerId
     * @return List of gadgets
     * @throws SQLException
     */
    public static List<Gadget> getGadgetsByBrawlerId(int brawlerId) throws SQLException {
        List<Gadget> gadgets = new ArrayList<>();
        String st = "SELECT * FROM Gadgets WHERE brawler_id = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(st);
        ps.setInt(1, brawlerId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Gadget gadget = new Gadget(rs.getInt("gadget_id"), rs.getString("name"));
            gadgets.add(gadget);
        }

        return gadgets;
    }

    /**
     * Get the star powers of a brawler by its ID
     * @param brawlerId
     * @return List of star powers
     * @throws SQLException
     */
    public static List<StarPower> getStarPowersByBrawlerId(int brawlerId) throws SQLException {
        List<StarPower> starPowers = new ArrayList<>();
        String st = "SELECT * FROM StarPowers WHERE brawler_id = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(st);
        ps.setInt(1, brawlerId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            StarPower sp = new StarPower(rs.getInt("starpower_id"), rs.getString("name"));
            starPowers.add(sp);
        }

        return starPowers;
    }


    /**
     * Format the date to a string with the format yyyy-MM-dd HH:mm:ss for compatibility with the database
     * @param date
     * @return String with the date formatted
     */
    public static String formateDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return date.format(formatter);
    }

    public static LocalDateTime formateDate(String dateString) {
        if (dateString == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateString, formatter);
    }


}
