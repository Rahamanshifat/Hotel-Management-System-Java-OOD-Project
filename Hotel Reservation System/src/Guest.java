public class Guest {
    private int guestId;
    private String name;
    private String email;

    public Guest(int guestId, String name, String email) {
        this.guestId = guestId;
        this.name = name;
        this.email = email;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Guest ID: " + guestId + ", Name: " + name + ", Email: " + email;
    }
}
