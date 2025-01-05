import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.testng.Assert.*;

public class MembershipIntegrationTest {

    private Connection connection;

    @BeforeMethod
    public void setUp() throws SQLException {
        // Establish a connection before each test
        connection = DatabaseConnection.getInstance().getConnection();

        // Clear the test data before each test
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM user_form")) {
            stmt.executeUpdate();
        }
    }

    @AfterMethod
    public void tearDown() throws SQLException {
        // Close the connection after each test
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    public void testSimpleMembershipSave() throws SQLException {
        // Create and save SimpleMembership details
        Membership membership = new SimpleMembership();
        membership.saveMembershipDetails("001", "Male", "Morning", 25);

        // Verify the data in the database
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM user_form WHERE User_ID = ?")) {
            stmt.setString(1, "001");
            ResultSet rs = stmt.executeQuery();

            assertTrue(rs.next(), "Data for User_ID 001 should exist in the database.");
            assertEquals("Male", rs.getString("Gender"));
            assertEquals("Simple", rs.getString("Membership"));
            assertEquals("Morning", rs.getString("Gym_Time"));
            assertEquals(25, rs.getInt("Age"));
        }
    }

    @Test
    public void testGoldenMembershipSave() throws SQLException {
        // Create and save GoldenMembership details
        Membership membership = new GoldenMembership();
        membership.saveMembershipDetails("002", "Female", "Evening", 30);

        // Verify the data in the database
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM user_form WHERE User_ID = ?")) {
            stmt.setString(1, "002");
            ResultSet rs = stmt.executeQuery();

            assertTrue(rs.next(), "Data for User_ID 002 should exist in the database.");
            assertEquals("Female", rs.getString("Gender"));
            assertEquals("Golden", rs.getString("Membership"));
            assertEquals("Evening", rs.getString("Gym_Time"));
            assertEquals(30, rs.getInt("Age"));
        }
    }

    @Test
    public void testJubileeMembershipSave() throws SQLException {
        // Create and save JubileeMembership details
        Membership membership = new JubileeMembership();
        membership.saveMembershipDetails("003", "Other", "Afternoon", 35);

        // Verify the data in the database
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM user_form WHERE User_ID = ?")) {
            stmt.setString(1, "003");
            ResultSet rs = stmt.executeQuery();

            assertTrue(rs.next(), "Data for User_ID 003 should exist in the database.");
            assertEquals("Other", rs.getString("Gender"));
            assertEquals("Jubilee", rs.getString("Membership"));
            assertEquals("Afternoon", rs.getString("Gym_Time"));
            assertEquals(35, rs.getInt("Age"));
        }
    }


}
