package Crafting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class CraftingTable {

    // public Item Craft(Item firstItem, Item secondItem) { }

    public CraftingTable() { SetupCraftingTable(); }

    public HashMap<String, Item> craftableItems = new HashMap<String, Item>();

    public Item CraftItem(Item[] inputItems) {
        String lookName = "";

        for (int i = 0; i < inputItems.length; i++) {
            lookName += inputItems[i].itemName;
        }

        if (craftableItems.containsKey(lookName)) {
            return craftableItems.get(lookName);
        }

        return null;
    }
    
    /**
     * 
     * @throws Exception
     */
    public void SetupCraftingTable() {
        try {
        File craftFile = new File(System.getProperty("user.dir") + "\\Crafting\\Recipes\\CraftableItems.txt");
        BufferedReader br = new BufferedReader(new FileReader(craftFile));
        String line = br.readLine();

        while (line != null) {
            String[] split = line.split("=");
            String[] materials = split[0].split(",");
            String outputName = split[split.length-1];

            String item = "";
            String reversedItem = "";
            Item output = new Item(outputName);
            
            for (int i = 0; i < materials.length; i++) {
                item += materials[i];
            }

            craftableItems.put(item, output);
            if (!reversedItem.equals(item)) craftableItems.put(reversedItem, output);

            line = br.readLine();
            br.close();
        }
        } catch (Exception e) {
            System.out.println("Failed To Load Crafting Table: \nReason - \n"+e);
        }

        
    }
}
