
package SchoolManagment.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Simple utility that returns a live JDBC connection.
 */
public class DBConnection {
	private static final String URL =
			  "jdbc:mysql://localhost:3309/managment_db"
			  + "?allowPublicKeyRetrieval=true"
			  + "&useSSL=false"
			  + "&serverTimezone=UTC";
    private static final String USER = "root";         
    private static final String PASS = "Dorigr04!";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

