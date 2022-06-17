package Base;
import java.util.ArrayList;
import java.util.HashMap;

import Crafting.*;
import Map.MapManager;

public class Player {

    public HashMap<String, Item> inventory = new HashMap<String, Item>();
    public Vector2 playerLocation;

    public Player() {
    }

    public Player(Vector2 loc) {
        playerLocation = loc;
    }

    /**
     * Adds an item to the inventory count hashmap if there is already one, if not
     * creates a new one
     * 
     * @param toAdd
     */
    public void AddItem(String toAdd) {
        if (inventory.containsKey(toAdd)) {
            Item item = inventory.get(toAdd);
            item.count++;
            inventory.put(toAdd, item);
            return;
        }

        inventory.put(toAdd, new Item(toAdd));
    }

    public void MovePlayer(String movements) {

        for (int i = 0; i < movements.length(); i++) {

            Character gottenChar = Character.toLowerCase(movements.charAt(i));
            int charInt = gottenChar;

            /**
             * 119  - w
             * 97   - a
             * 115  - s
             * 100  - d
             */
            switch (charInt) {
                case 119:
                    MovePlayer(Vector2.up);
                    break;
                case 97:
                    MovePlayer(Vector2.left);
                    break;
                case 115:
                    MovePlayer(Vector2.down);
                    break;
                case 100:
                    MovePlayer(Vector2.right);
                    break;
                default:
                    System.out.println("Error Moving!");
                    break;
            }
        }
    }

    public void MovePlayer(Vector2 moveDirection) {
        try {
            Vector2 tempPos = new Vector2(playerLocation).AddVector(moveDirection);
            if (MapManager.CheckAccessibility(tempPos)) {
                playerLocation = tempPos;
                MapManager.UpdatePlayerPosition(tempPos);
            } else {
                System.out.println("Something Blocked Your Path");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Item GetItem(String toGet) {
        return inventory.get(toGet);
    }

    public void CraftItem() {
        boolean inCrafting = true;
        CraftingTable ct = new CraftingTable();
        ArrayList<Item> craftingItems = new ArrayList<Item>();

        while (inCrafting) {
            if (inventory.size() <= 0) {
                System.out.println("Inventory Empty");
                inCrafting = false;
                return;
            }

            System.out.println(StringManager.InventroyToList(inventory));
            int getItem = StringManager.GetInt("\nSelect Item >> ");

            if (getItem > inventory.size() || getItem <= 0) {
                System.out.println("You Cannot Pick Items That Don't Exist");
            }
            
            Item gottenItem = (Item)inventory.keySet().toArray()[getItem-1];
            craftingItems.add(gottenItem);
        }
    }

}
