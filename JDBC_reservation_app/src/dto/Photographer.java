package dto;

public class Photographer {
	private int photographerId;
    private int studioId;
    private String name;
    private String phone;
    private int years;
    private int reservationCount;

    public Photographer(int photographerId, int studioId, String name, String phone, int years, int reservationCount) {
        this.photographerId = photographerId;
        this.studioId = studioId;
        this.name = name;
        this.phone = phone;
        this.years = years;
        this.reservationCount = reservationCount;
    }

    public int getPhotographerId() { return photographerId; }
    public void setPhotographerId(int photographerId) { this.photographerId = photographerId; }

    public int getStudioId() { return studioId; }
    public void setStudioId(int studioId) { this.studioId = studioId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public int getYears() { return years; }
    public void setYears(int years) { this.years = years; }

    public int getReservationCount() { return reservationCount; }
    public void setReservationCount(int reservationCount) { this.reservationCount = reservationCount; }

}
