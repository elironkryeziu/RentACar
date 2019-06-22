package Connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionClass {

    public Connection connection;

    public Connection getConnection() {
        try {
            String dbName = "rent_a_car";
            String user = "root";
            String password = "1234";

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, user, password);

            Class.forName("com.mysql.cj.jdbc.Driver");
            return connection;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
