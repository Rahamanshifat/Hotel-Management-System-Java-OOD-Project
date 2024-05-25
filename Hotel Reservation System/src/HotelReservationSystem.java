import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class HotelReservationSystem {
    private static RoomManager roomManager = new RoomManager();
    private static GuestManager guestManager = new GuestManager();
    private static ReservationManager reservationManager = new ReservationManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Guest");
            System.out.println("2. View Guests");
            System.out.println("3. Add Reservation");
            System.out.println("4. View Reservations");
            System.out.println("5. Check Room Availability");
            System.out.println("6. Update Room Availability");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addGuest(scanner);
                    break;
                case 2:
                    viewGuests();
                    break;
                case 3:
                    addReservation(scanner);
                    break;
                case 4:
                    viewReservations();
                    break;
                case 5:
                    checkRoomAvailability(scanner);
                    break;
                case 6:
                    updateRoomAvailability(scanner);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addGuest(Scanner scanner) {
        System.out.print("Enter guest ID: ");
        int guestId = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter guest name: ");
        String name = scanner.nextLine();
        System.out.print("Enter guest email: ");
        String email = scanner.nextLine();
        Guest guest = new Guest(guestId, name, email);
        guestManager.addGuest(guest);
        System.out.println("Guest added successfully!");
    }

    private static void viewGuests() {
        List<Guest> guests = guestManager.getAllGuests();
        if (guests.isEmpty()) {
            System.out.println("No guests available.");
        } else {
            for (Guest guest : guests) {
                System.out.println(guest);
            }
        }
    }

    private static void addReservation(Scanner scanner) {
        System.out.print("Enter reservation ID: ");
        int reservationId = scanner.nextInt();
        System.out.print("Enter guest ID: ");
        int guestId = scanner.nextInt();
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        System.out.print("Enter check-in date (yyyy-mm-dd): ");
        Date checkInDate = Date.valueOf(scanner.next());
        System.out.print("Enter check-out date (yyyy-mm-dd): ");
        Date checkOutDate = Date.valueOf(scanner.next());
        Reservation reservation = new Reservation(reservationId, guestId, roomNumber, checkInDate, checkOutDate);
        reservationManager.addReservation(reservation);
        System.out.println("Reservation added successfully!");
    }

    private static void viewReservations() {
        List<Reservation> reservations = reservationManager.getAllReservations();
        if (reservations.isEmpty()) {
            System.out.println("No reservations available.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }

    private static void checkRoomAvailability(Scanner scanner) {
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        Room room = roomManager.getRoom(roomNumber);
        if (room != null) {
            System.out.println(room.isAvailable() ? "Room is available." : "Room is not available.");
        } else {
            System.out.println("Room not found.");
        }
    }

    private static void updateRoomAvailability(Scanner scanner) {
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        System.out.print("Enter new availability (true/false): ");
        boolean isAvailable = scanner.nextBoolean();
        roomManager.updateRoomAvailability(roomNumber, isAvailable);
        System.out.println("Room availability updated successfully!");
    }
}
