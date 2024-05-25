import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationManager {
    private Connection connection;

    public ReservationManager() {
        connection = DatabaseManager.getConnection();
    }

    public void addReservation(Reservation reservation) {
        String query = "INSERT INTO reservations (reservation_id, guest_id, room_number, check_in_date, check_out_date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, reservation.getReservationId());
            pstmt.setInt(2, reservation.getGuestId());
            pstmt.setInt(3, reservation.getRoomNumber());
            pstmt.setDate(4, reservation.getCheckInDate());
            pstmt.setDate(5, reservation.getCheckOutDate());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Reservation getReservation(int reservationId) {
        Reservation reservation = null;
        String query = "SELECT * FROM reservations WHERE reservation_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, reservationId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    reservation = new Reservation(rs.getInt("reservation_id"), rs.getInt("guest_id"), rs.getInt("room_number"),
                            rs.getDate("check_in_date"), rs.getDate("check_out_date"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservation;
    }

    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservations";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Reservation reservation = new Reservation(rs.getInt("reservation_id"), rs.getInt("guest_id"), rs.getInt("room_number"),
                        rs.getDate("check_in_date"), rs.getDate("check_out_date"));
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }
}
