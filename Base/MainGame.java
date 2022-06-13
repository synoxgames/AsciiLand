package Base;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Base.Vector2.TravelDirection;
import Crafting.*;
import Map.MapManager;
import Mine.*;

public class MainGame {

    static Player player = new Player();
    static HashMap<Integer, String> mainAction = new HashMap<Integer, String>();
    static boolean inGame = false;

    public static void main(String[] args) throws Exception {
        
        StartGame();

        while (inGame) {
            GameLoop();
        }
    }

    public static void StartGame() throws Exception {
        inGame = true;
        MapManager.LoadMap();
        player = new Player(MapManager.GetPlayerPosition());
        System.out.println(player.playerLocation);
        mainAction.put(1, "View Map");
        mainAction.put(2, "Move Player");
        mainAction.put(3, "Mine Resource");
        mainAction.put(4, "View Inventory");
        mainAction.put(5, "Check Surrounding");
    }

    public static void GameLoop() {
        System.out.println("What Would You Like To Do?");
        System.out.println("===========================\n");
        PickOption(mainAction);
        System.out.println("============================\n\n\n\n\n\n");
    }

    public static void PickOption(HashMap<Integer, String> hashMap) {
        System.out.println(StringManager.HashToList(hashMap));
        int option = GetOption(">> ");

        switch (option) {
            case 1:
                MapManager.ViewMap(player.playerLocation);
                return;
            case 2:
                MoveDirection("Enter Direction | [W,A,S,D] | Max: 3\n\n>> ");
                return;
            case 3:
                MineResource();
                return;
            case 4:
                System.out.println(StringManager.InventroyToList(player.invItemCount));
                return;
            case 5:
                CheckSurroundings();
                break;
            default:
                System.out.println("Sorry! This hasn't been found!");
                return;
        }
    }

    public static int GetOption(String message) {
        try {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        int toReturn = scanner.nextInt();
        return toReturn;
        } catch (Exception e) {
            return 0;
        }
    }

    public static void MoveDirection(String message) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print(message);
            String key = scanner.nextLine();
            player.MovePlayer(key);
        } catch (Exception e) {
           return;
        }
    }

    public static Vector2 CheckDirection(String message) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print(message);
            int charInt = Character.toLowerCase(scanner.next().charAt(0));
            Vector2 moveDirection = new Vector2(player.playerLocation);

            /**
             * 119  - w
             * 97   - a
             * 115  - s
             * 100  - d
             */
            switch (charInt) {
                case 119:
                    moveDirection.AddVector(Vector2.up);
                    break;
                case 97:
                    moveDirection.AddVector(Vector2.left);
                    break;
                case 115:
                    moveDirection.AddVector(Vector2.down);
                    break;
                case 100:
                    moveDirection.AddVector(Vector2.right);
                    break;
                default:
                    System.out.println("Error Moving!");
                    break;
            }

            return moveDirection;
        } catch (Exception e) {
            return null;
        }
    }

    public static void MineResource() {
        Vector2 charPos = CheckDirection("Enter Mining Direction | [W,A,S,D] | Max: 1\n\n>> ");
        char tileGot = MapManager.GetCharFromMap(charPos);
        System.out.println("Tile Found: "+tileGot);
        
        if (MapManager.itemMap.containsKey(tileGot)) {
            MinableItem minable = MapManager.itemMap.get(tileGot);
            Item outputItem = minable.Mine();
            player.AddItem(outputItem);
            System.out.println(outputItem);
            MapManager.ReplaceChar(charPos, '.');
        } else {
            System.out.println("No Minable Item Found");
        }
    }

    public static void CheckSurroundings() {
        Vector2 charPos = CheckDirection("Enter Tile Check Direction | [W,A,S,D] | Max: 1\n\n>> ");
        char tileGot = MapManager.GetCharFromMap(charPos);
        System.out.println(MapManager.CheckTileType(tileGot));
    }
    
}
