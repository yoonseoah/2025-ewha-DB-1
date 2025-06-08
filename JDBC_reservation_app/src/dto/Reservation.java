package dto;

public class Reservation {
    private int reservationId;
    private int userId;
    private int studioId;
    private int photographerId;
    private int backgroundId;
    private String startTime;
    private String endTime;

    public Reservation(int reservationId, int userId, int studioId, int photographerId, int backgroundId, String startTime, String endTime) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.studioId = studioId;
        this.photographerId = photographerId;
        this.backgroundId = backgroundId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getReservationId() { return reservationId; }
    public void setReservationId(int reservationId) { this.reservationId = reservationId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getStudioId() { return studioId; }
    public void setStudioId(int studioId) { this.studioId = studioId; }

    public int getPhotographerId() { return photographerId; }
    public void setPhotographerId(int photographerId) { this.photographerId = photographerId; }

    public int getBackgroundId() { return backgroundId; }
    public void setBackgroundId(int backgroundId) { this.backgroundId = backgroundId; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    @Override
    public String toString() {
        return "예약 ID: " + reservationId + ", 사진관 ID: " + studioId + ", 작가 ID: " + photographerId +
                ", 배경 ID: " + backgroundId + ", 시작: " + startTime + ", 종료: " + endTime;
    }
}
