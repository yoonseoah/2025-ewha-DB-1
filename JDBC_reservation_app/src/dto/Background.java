package dto;

public class Background {
    private int backgroundId;
    private String colorName;

    public Background(int backgroundId, String colorName) {
        this.backgroundId = backgroundId;
        this.colorName = colorName;
    }

    public int getBackgroundId() { return backgroundId; }
    public void setBackgroundId(int backgroundId) { this.backgroundId = backgroundId; }

    public String getColorName() { return colorName; }
    public void setColorName(String colorName) { this.colorName = colorName; }

}
