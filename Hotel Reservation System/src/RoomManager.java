import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomManager {
    private Connection connection;

    public RoomManager() {
        connection = DatabaseManager.getConnection();
    }

    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM rooms";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Room room = new Room(rs.getInt("room_number"), rs.getString("type"), rs.getBoolean("is_available"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public Room getRoom(int roomNumber) {
        Room room = null;
        String query = "SELECT * FROM rooms WHERE room_number = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, roomNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    room = new Room(rs.getInt("room_number"), rs.getString("type"), rs.getBoolean("is_available"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    public void updateRoomAvailability(int roomNumber, boolean isAvailable) {
        String query = "UPDATE rooms SET is_available = ? WHERE room_number = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setBoolean(1, isAvailable);
            pstmt.setInt(2, roomNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
