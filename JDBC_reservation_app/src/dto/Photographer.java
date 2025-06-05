package dto;

public class Photographer {
    private int photographerId;
    private int studioId;
    private String name;
    private String phone;
    private int years;
    private int reservationCount; // ← 추가된 필드

    // 기본 생성자
    public Photographer(int photographerId, int studioId, String name, String phone, int years) {
        this.photographerId = photographerId;
        this.studioId = studioId;
        this.name = name;
        this.phone = phone;
        this.years = years;
    }

    // reservationCount 포함된 생성자
    public Photographer(int photographerId, int studioId, String name, String phone, int years, int reservationCount) {
        this(photographerId, studioId, name, phone, years);
        this.reservationCount = reservationCount;
    }

    // Getter & Setter
    public int getPhotographerId() {
        return photographerId;
    }

    public void setPhotographerId(int photographerId) {
        this.photographerId = photographerId;
    }

    public int getStudioId() {
        return studioId;
    }

    public void setStudioId(int studioId) {
        this.studioId = studioId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getReservationCount() {
        return reservationCount;
    }

    public void setReservationCount(int reservationCount) {
        this.reservationCount = reservationCount;
    }

    @Override
    public String toString() {
        return "Photographer{" +
                "photographerId=" + photographerId +
                ", studioId=" + studioId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", years=" + years +
                ", reservationCount=" + reservationCount +
                '}';
    }
}
