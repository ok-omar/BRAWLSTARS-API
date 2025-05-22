package model.DAO.mysql;

import model.DAO.DBConnection;
import model.classes.Gadget;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MySQLGadgetsDAO {
    // Create
    /**
     * Insert a gadget to the database
     * @param g the gadget insert to the database
     */
    public static void Create(List<Gadget> gadgets, int brawler_id) {
        Connection con = DBConnection.getConnection();
        String st = "INSERT INTO Gadgets (gadget_id, name, brawler_id) VALUES (?, ?, ?)";
        try {
            // insert the gadgets
            PreparedStatement ps = con.prepareStatement(st);
            for (Gadget g : gadgets) {
                ps.setInt(1, g.getId());
                ps.setString(2, g.getName());
                ps.setInt(3, brawler_id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
