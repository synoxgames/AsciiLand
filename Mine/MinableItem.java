package Mine;

import Base.Timer.Timer;
import Crafting.*;

public class MinableItem {

    public Item outputItem;
    public float mineTime;

    public MinableItem() { }

    public MinableItem(Item op, float mt) {
        outputItem = op;
        mineTime = mt;
    }

    public Item FinishMinedResource() {
        return outputItem;
    }

    public Item Mine() {
       System.out.println("You Got "+outputItem);
        return FinishMinedResource();
    }
    
}
