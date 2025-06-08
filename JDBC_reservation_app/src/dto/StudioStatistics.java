package dto;

public class StudioStatistics {
    private int studioId;
    private int totalReservations;

    // Getters and setters
    public int getStudioId() {
        return studioId;
    }

    public void setStudioId(int studioId) {
        this.studioId = studioId;
    }

    public int getTotalReservations() {
        return totalReservations;
    }

    public void setTotalReservations(int totalReservations) {
        this.totalReservations = totalReservations;
    }

    @Override
    public String toString() {
        return "스튜디오 ID: " + studioId + ", 예약 수: " + totalReservations;
    }
}
