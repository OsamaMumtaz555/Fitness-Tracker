import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.*;
interface Membership {
    void saveMembershipDetails(String id, String gender, String gymTime, int age);
}
class SimpleMembership implements Membership {
    @Override
    public void saveMembershipDetails(String id, String gender, String gymTime, int age) {
        try {
            Connection con = DatabaseConnection.getInstance().getConnection();
            String query = "INSERT INTO user_form (User_ID, Gender, Membership, Gym_Time, Age) VALUES (?, ?, 'Simple', ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setString(1, id);
                pstmt.setString(2, gender);
                pstmt.setString(3, gymTime);
                pstmt.setInt(4, age);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null,"Simple Membership details saved!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error saving Simple Membership: " + e.getMessage());
        }
    }
}
class GoldenMembership implements Membership {
    @Override
    public void saveMembershipDetails(String id, String gender, String gymTime, int age) {
        try {
            Connection con = DatabaseConnection.getInstance().getConnection();
            String query = "INSERT INTO user_form (User_ID, Gender, Membership, Gym_Time, Age) VALUES (?, ?, 'Golden', ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setString(1, id);
                pstmt.setString(2, gender);
                pstmt.setString(3, gymTime);
                pstmt.setInt(4, age);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null,"Golden Membership details saved!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error saving Golden Membership: " + e.getMessage());
        }
    }
}
class JubileeMembership implements Membership {
    @Override
    public void saveMembershipDetails(String id, String gender, String gymTime, int age) {
        try {
            Connection con = DatabaseConnection.getInstance().getConnection();
            String query = "INSERT INTO user_form (User_ID, Gender, Membership, Gym_Time, Age) VALUES (?, ?, 'Jubilee', ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setString(1, id);
                pstmt.setString(2, gender);
                pstmt.setString(3, gymTime);
                pstmt.setInt(4, age);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null,"Jubilee Membership details saved!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error saving Jubilee Membership: " + e.getMessage());
        }
    }
}
public class MembershipFactory {
    public static Membership getMembership(String membershipType) {
        switch (membershipType) {
            case "Simple":
                return new SimpleMembership();
            case "Golden":
                return new GoldenMembership();
            case "Jubilee":
                return new JubileeMembership();
            default:
                throw new IllegalArgumentException("Invalid Membership Type");
        }
    }
}

