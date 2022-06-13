package Base;

import java.util.ArrayList;
import java.util.HashMap;

import Crafting.*;
import Map.MapManager;

public class Player {

    public ArrayList<Item> inventory = new ArrayList<Item>();
    public HashMap<String, Integer> invItemCount = new HashMap<String, Integer>();
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
    public void AddItem(Item toAdd) {
        if (invItemCount.containsKey(toAdd.itemName)) {
            invItemCount.put(toAdd.itemName, invItemCount.get(toAdd.itemName) + 1);
            return;
        }

        inventory.add(toAdd);
        invItemCount.put(toAdd.itemName, 1);
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
            System.out.println("cock");
        }
    }

}
