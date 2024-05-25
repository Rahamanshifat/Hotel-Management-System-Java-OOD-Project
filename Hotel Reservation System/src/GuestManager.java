import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestManager {
    private Connection connection;

    public GuestManager() {
        connection = DatabaseManager.getConnection();
    }

    public void addGuest(Guest guest) {
        String query = "INSERT INTO guests (guest_id, name, email) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, guest.getGuestId());
            pstmt.setString(2, guest.getName());
            pstmt.setString(3, guest.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Guest getGuest(int guestId) {
        Guest guest = null;
        String query = "SELECT * FROM guests WHERE guest_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, guestId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    guest = new Guest(rs.getInt("guest_id"), rs.getString("name"), rs.getString("email"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guest;
    }

    public List<Guest> getAllGuests() {
        List<Guest> guests = new ArrayList<>();
        String query = "SELECT * FROM guests";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Guest guest = new Guest(rs.getInt("guest_id"), rs.getString("name"), rs.getString("email"));
                guests.add(guest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guests;
    }
}
