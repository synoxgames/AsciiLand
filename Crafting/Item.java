package Crafting;

public class Item extends Craftable {

    public int count = 1;

    public Item(String itemName) {
        super(itemName);
    }

    public String toString() {
        return itemName;
    }

}
