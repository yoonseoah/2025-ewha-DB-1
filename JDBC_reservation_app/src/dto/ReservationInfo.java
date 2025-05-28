package dto;

import java.time.LocalDateTime;

public class ReservationInfo {
	private int reservationId;
    private String userName;
    private String studioName;
    private String photographerName;
    private String colorName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public ReservationInfo(int reservationId, String userName, String studioName,
                           String photographerName, String colorName,
                           LocalDateTime startTime, LocalDateTime endTime) {
        this.reservationId = reservationId;
        this.userName = userName;
        this.studioName = studioName;
        this.photographerName = photographerName;
        this.colorName = colorName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }

    public String getPhotographerName() {
        return photographerName;
    }

    public void setPhotographerName(String photographerName) {
        this.photographerName = photographerName;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

}
