package Base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Crafting.Item;

public class StringManager {
    
    public static String InventroyToList(HashMap<String, Integer> invHM) {
        if (invHM.isEmpty()) return "Invetory Empty";

        String toReturn = "";

        for (int i = 0; i < invHM.size(); i++) {
            String key = invHM.keySet().toArray()[i].toString();
            toReturn += ((i+1) + ". "+key + " - x"+invHM.get(key));
        }

        return toReturn;
    }
 
    public static String HashToList(HashMap<Integer, String> invHM) {
        if (invHM.size() <= 0) return "Invetory Empty";

        String toReturn = "";

        for (int i = 0; i < invHM.size(); i++) {
            String key = invHM.get(i+1);
            toReturn += (invHM.keySet().toArray()[i].toString() + ". "+key+"\n");
        }

        return toReturn;
    }

    public static String CraftToString(ArrayList<Item> allItems) {
        if (allItems.size() <= 0) return "Add Item";

        String toReturn = "";

        for (int i = 0; i < allItems.size(); i++) {
            toReturn += allItems.get(i).itemName+ (i == allItems.size() ? " = " : "+");
        }

        return toReturn;
    }

    public static int GetInt(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        int intGotten = scanner.nextInt();
        return intGotten;
    }
}
