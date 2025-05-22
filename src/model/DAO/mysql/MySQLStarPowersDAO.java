package model.DAO.mysql;

import model.DAO.DBConnection;
import model.classes.StarPower;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
public class MySQLStarPowersDAO {
    // Create
    /**
     * Insert star powers to the database
     * @param starPowers List of starpowers to insert
     * @param brawler_id ID of the brawler that the starpowers belong to
     */
    public static void Create(List<StarPower> starPowers, int brawler_id) {
        Connection con = DBConnection.getConnection();
        String st = "INSERT INTO StarPowers (starpower_id, name, brawler_id) VALUES (?, ?, ?)";
        try {
            // insert the star powers
            PreparedStatement ps = con.prepareStatement(st);
            for (StarPower sp : starPowers) {
                ps.setInt(1, sp.getId());
                ps.setString(2, sp.getName());
                ps.setInt(3, brawler_id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
