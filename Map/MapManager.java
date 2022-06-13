package Map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

import javax.swing.plaf.basic.BasicComboBoxUI.ItemHandler;

import Base.Vector2;
import Crafting.Item;
import Mine.MinableItem;
import Mine.MinableItem.StrengthRequired;

public class MapManager {

    public static int viewSize = 15;
    public static Character[][] mainMap = new Character[124][124];
    public static final HashMap<Character, MinableItem> itemMap = new HashMap<Character, MinableItem>() {
         { put('t', new MinableItem(new Item("Wood"), 5f, StrengthRequired.None)); }
    };
    public static final HashMap<Character, String> mapItemType = new HashMap<Character, String>() {
        { put('t', "Tree"); }
        { put('.', "Grass"); }
        { put('~', "Water"); }
        { put('#', "Concrete"); }
    };

    public static void LoadMap() throws Exception {
        File mapFile = new File(System.getProperty("user.dir") + "\\Map\\Map.txt");
        BufferedReader br = new BufferedReader(new FileReader(mapFile));
        String line = br.readLine();

        int index = 0;

        while (line != null) {
            mainMap[index] = new Character[mainMap.length];
            
            for (int i = 0; i < mainMap[index].length; i++) {
                mainMap[index][i] = line.charAt(i);
            }

            index++;
            line = br.readLine();
        }
        
    }

    public static Vector2 GetPlayerPosition() {
        Vector2 toReturn = new Vector2();

        for (int i = 0; i < mainMap.length; i++) {
            for (int y = 0; y < mainMap[i].length; y++) {

              if (mainMap[i][y] == '^') {
                return new Vector2(i,y);
              }
            }
        }

        System.out.println(toReturn);
        return toReturn;
    }

    public static void ViewMap(Vector2 pos) {
        for (int i = pos.x - viewSize; i < pos.x + viewSize; i++) {
            for (int y = pos.y - viewSize; y < pos.y + viewSize; y++) {
                if (i >= 0 && i < mainMap.length && y >= 0 && y < mainMap.length) {
                    System.out.print(mainMap[i][y]);
                } else {
                    System.out.print(" ");
                }
            }

            System.out.println();
        }
    }

    public static void UpdatePlayerPosition(Vector2 newPos) {
        char nextTile = mainMap[newPos.x][newPos.y];
        Vector2 previousTile = GetPlayerPosition();
        mainMap[newPos.x][newPos.y] = '^';
        mainMap[previousTile.x][previousTile.y] = nextTile;
    }

  //  public static void 

    public static boolean CheckAccessibility(Vector2 checkPosition) {
        boolean xCheck = checkPosition.x >= 0 && checkPosition.x < mainMap.length-1;
        boolean yCheck = checkPosition.y >= 0 && checkPosition.y < mainMap.length-1;
        boolean typeCheck = mainMap[checkPosition.x][checkPosition.y] == 46;

        return xCheck && yCheck && typeCheck;
    }

    public static char GetCharFromMap(Vector2 checkPos) {
        return mainMap[checkPos.x][checkPos.y];
    }

    public static void ReplaceChar(Vector2 position, Character tile) {
        mainMap[position.x][position.y] = tile;
    }

    public static void FindClosestTree() {
        System.out.println("nah");
    }

    public static String CheckTileType(char toCheck) {
        if (!mapItemType.containsKey(toCheck)) return "Item Not Found! Please Add "+toCheck;

        String tileName = mapItemType.get(toCheck);

        if (itemMap.containsKey(toCheck)) {
            tileName += "\n====================\nMinable\nResource: \t"+itemMap.get(toCheck).outputItem.itemName+"\nDurability: \t"+itemMap.get(toCheck).durability.toString();
        }

        return tileName;
    }

}
