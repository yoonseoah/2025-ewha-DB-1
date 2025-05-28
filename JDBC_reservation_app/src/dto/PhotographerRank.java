package dto;
public class PhotographerRank {
    private int photographerId;
    private String name;
    private int reservationCount;
    private int rank;

    // Getters and setters
    public int getPhotographerId() {
        return photographerId;
    }

    public void setPhotographerId(int photographerId) {
        this.photographerId = photographerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReservationCount() {
        return reservationCount;
    }

    public void setReservationCount(int reservationCount) {
        this.reservationCount = reservationCount;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}