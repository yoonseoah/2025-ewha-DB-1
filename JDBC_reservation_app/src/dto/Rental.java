package dto;

public class Rental {
    private int itemId;
    private String itemName;
    private String itemType;

    public Rental(int itemId, String itemName, String itemType) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemType = itemType;
    }

    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getItemType() { return itemType; }
    public void setItemType(String itemType) { this.itemType = itemType; }

}
