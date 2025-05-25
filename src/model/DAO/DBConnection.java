package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Variable de la connexió
    private static Connection connection = null;

    /**
     * Funció per connectar a la BBDD escalada.db
     * @return Connexió amb la BBDD
     */
    public static Connection openCon() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:brawlers.db");
            System.out.println("[  ✔  ] Connexió Establerta correctament!");
        } catch (SQLException e) {
            System.out.println("Error: No s'ha pogut establir la connexió");
            System.out.println(e.getMessage());
        }
        return connection;
    }

    /**
     * Funció per tancar la connexió
     * @param con La connexió
     */
    public static void closeCon(Connection con) {
        if (con != null) {
            try {
                con.close();
                System.out.println("[  ✘  ] Connexió tancada");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void closeCon(int n) {
        if (connection != null) {
            try {
                connection.close();
                if (n != 0){
                System.out.println("[  ✔  ] Connexió tancada correctament");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Getter de la connexió.
     * @return la connexió
     */
    public static Connection getConnection() {
        return connection;
    }
}

