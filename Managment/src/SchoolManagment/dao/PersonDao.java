
package SchoolManagment.dao;

import SchoolManagment.model.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD operations for the 'person' table in MySQL.
 */
public class PersonDao {

    /**
     * Inserts a new Person into the database.
     * On success, sets the generated ID back into the Person object.
     */
    public void insert(Person p) throws SQLException {
        String sql = "INSERT INTO person(fname,lname) VALUES(?,?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getFname());
            ps.setString(2, p.getLname());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    p.setId(rs.getInt(1));
                }
            }
        }
    }

    /**
     * Searches for Persons whose last name starts with the given prefix.
     * Returns an empty list if no matches.
     */
    public List<Person> searchByLname(String lname) throws SQLException {
        String sql = "SELECT * FROM person WHERE lname LIKE ?";
        List<Person> out = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, lname + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    out.add(new Person(
                        rs.getInt("id"),
                        rs.getString("fname"),
                        rs.getString("lname")
                    ));
                }
            }
        }
        return out;
    }

    /**
     * Updates the first and last name of an existing Person by ID.
     */
    public void update(Person p) throws SQLException {
        String sql = "UPDATE person SET fname = ?, lname = ? WHERE id = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getFname());
            ps.setString(2, p.getLname());
            ps.setInt(3, p.getId());
            ps.executeUpdate();
        }
    }

    /**
     * Deletes the Person row with the given ID.
     */
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM person WHERE id = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
