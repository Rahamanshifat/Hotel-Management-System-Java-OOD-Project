import java.sql.Date;

public class Reservation {
    private int reservationId;
    private int guestId;
    private int roomNumber;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(int reservationId, int guestId, int roomNumber, Date checkInDate, Date checkOutDate) {
        this.reservationId = reservationId;
        this.guestId = guestId;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "Reservation ID: " + reservationId + ", Guest ID: " + guestId + ", Room Number: " + roomNumber +
                ", Check-In Date: " + checkInDate + ", Check-Out Date: " + checkOutDate;
    }
}
