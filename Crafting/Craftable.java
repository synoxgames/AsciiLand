package Crafting;

public class Craftable {

    public String itemName;
    public CraftType itemType;

    public Craftable(String itemName, CraftType itemType) {
        this.itemName = itemName;
        this.itemType = itemType;
    }

    public Craftable(String itemName) {
        this.itemName = itemName;
    }

    public enum CraftType {
        Wooden,
        Iron,
        Steel
    }
}