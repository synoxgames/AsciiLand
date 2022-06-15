package Crafting;

public class Item extends Craftable {

    int count = 1;

    public Item(String itemName) {
        super(itemName);
    }

    public String toString() {
        return itemName;
    }

}
