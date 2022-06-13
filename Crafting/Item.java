package Crafting;

public class Item extends Craftable {

    public Item(String itemName) {
        super(itemName);
    }

    public String toString() {
        return itemName;
    }

}
