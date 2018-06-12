package db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import db.DBConnection;
import vo.Person;
import vo.Person.PersonBuilder;

public class MySQLConnection implements DBConnection {
    private Connection conn;

    public MySQLConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(MySQLDBUtil.URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean verifyLogin(String user, String password) {
        if (conn == null) {
            return false;
        }
        try {
            String sql = "SELECT user from admins WHERE user = ? and password = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, user);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean isUserExist(String firstName, String lastName) {
        if (conn == null) {
            return false;
        }
        try {
            String sql = "SELECT id from persons WHERE first_name = ? and last_name = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    
    @Override
    public Person getPerson(String firstName, String lastName) {
        if (conn == null) {
            return null;
        }
        try {
            String sql = "SELECT first_name, last_name, address1, address2, city, state, zipCode, country, date FROM persons"
                        + " WHERE first_name = ? AND last_name = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            ResultSet rs = statement.executeQuery();
            PersonBuilder builder = new PersonBuilder();
            
            if (!rs.next()) {
                builder.setFirstName(rs.getString("first_name"));
                builder.setLastName(rs.getString("last_name"));
                builder.setAddress1(rs.getString("address1"));
                builder.setAddress2(rs.getString("address2"));
                builder.setCity(rs.getString("city"));
                builder.setState(rs.getString("state"));
                builder.setZipCode(rs.getInt("zipCode"));
                builder.setCountry(rs.getString("country"));
                builder.setTimestamp(rs.getTimestamp("date"));
                Person person = builder.build();
                return person;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }        
        return null;
    }

    

    @Override
    public List<Person> getPersons() {
        if (conn == null) {
            return new ArrayList<>();
        }
        ArrayList<Person> list = new ArrayList<Person>();
        try {
            String sql = "SELECT * FROM persons";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            PersonBuilder builder = new PersonBuilder();
            
            while (rs.next()) {
                builder.setFirstName(rs.getString("first_name"));
                builder.setLastName(rs.getString("last_name"));
                builder.setAddress1(rs.getString("address1"));
                builder.setAddress2(rs.getString("address2"));
                builder.setCity(rs.getString("city"));
                builder.setState(rs.getString("state"));
                builder.setZipCode(rs.getInt("zipCode"));
                builder.setCountry(rs.getString("country"));
                builder.setTimestamp(rs.getTimestamp("date"));
                Person person = builder.build();
                list.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return list;
    }

    @Override
    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // SQL injection
    @Override
    public void addPerson(Person person) {
        if (conn == null) {
            return;
        }
        try {
//            "INSERT INTO persons " + "(first_name, last_name, address1, address2, city, state, zipCode, country)"
//                    + " VALUES (\"Yue\", \"Xu\", \"northwood\", \"str\", \"Ann Arbor\", \"MI\", \"48188\", \"US\" )";
            
            String sql = "INSERT INTO persons (first_name, last_name, address1, address2, city, state, zipCode, country) "
                        + "VALUES (?,?,?,?,?,?,?,?)"; 
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getLastName());
            statement.setString(3, person.getAddress1());
            statement.setString(4, person.getAddress2());
            statement.setString(5, person.getCity());
            statement.setString(6, person.getState());
            statement.setInt(7, person.getZipCode());
            statement.setString(8, person.getCountry());            
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
