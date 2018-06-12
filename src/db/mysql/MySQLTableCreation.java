package db.mysql;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class MySQLTableCreation {
    // Run this as Java application to reset db schema.
    public static void main(String[] args) {
        try {
            Connection conn = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(MySQLDBUtil.URL);

            if (conn == null) {
                return;
            }
            Statement stmt = conn.createStatement();
            String sql = "DROP TABLE IF EXISTS persons;";
            stmt.executeUpdate(sql);

            // Step 3. Create new tables. sort by id or timestamp
            sql = "CREATE TABLE persons " + "(first_name VARCHAR(255) NOT NULL, " + " last_name VARCHAR(255) NOT NULL, "
                    + "address1 VARCHAR(255) NOT NULL, " + "address2 VARCHAR(255), " + "city VARCHAR(255) NOT NULL, "
                    + "state VARCHAR(255) NOT NULL, " + "zipCode INT NOT NULL," + "country VARCHAR(255) NOT NULL, "
                    + "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"
                    + "id INT NOT NULL AUTO_INCREMENT UNIQUE KEY," + " PRIMARY KEY (first_name, last_name, address1))";
            stmt.executeUpdate(sql);


            sql = "DROP TABLE IF EXISTS admins;";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE admins " + "(user VARCHAR(255) NOT NULL, " + " password VARCHAR(255) NOT NULL, "
                    + " PRIMARY KEY (user))";
            stmt.executeUpdate(sql);
            

            sql = "INSERT INTO admins " + "(user, password)" + " VALUES (\"admin\", \"admin\" )";
            System.out.println("INSERT INTO admin:\n" + sql);
            stmt.executeUpdate(sql);
            

            // Step 4: insert data to test
            // Create 2 fake users
          /*
            sql = "INSERT INTO persons " + "(first_name, last_name, address1, address2, city, state, zipCode, country)"
                    + " VALUES (\"Yue\", \"Xu\", \"northwood\", \"str\", \"Ann Arbor\", \"MI\", \"48188\", \"US\" )";
            System.out.println("INSERT INTO persons:\n" + sql);
            stmt.executeUpdate(sql);

            sql = "INSERT INTO persons "
                    + "(first_name, last_name, address1, address2, city, state, zipCode, country)"
                    + " VALUES (\"Dou\", \"Mao\", \"northwood\", \"str\", \"Ann Arbor\", \"MI\", \"48188\", \"US\" )";
            System.out.println("INSERT INTO persons:\n" + sql);
            stmt.executeUpdate(sql);
          */


            System.out.println("Import is done successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
