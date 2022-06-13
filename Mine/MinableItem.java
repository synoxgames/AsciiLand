package Mine;

import Base.Timer.Timer;
import Crafting.*;

public class MinableItem {

    public Item outputItem;
    public float mineTime;
    public StrengthRequired durability = StrengthRequired.None;

    public MinableItem() { }

    public MinableItem(Item op, float mt, StrengthRequired dur) {
        outputItem = op;
        mineTime = mt;
        durability = dur;
    }

    public Item FinishMinedResource() {
        return outputItem;
    }

    public Item Mine() {
       
        Timer time = new Timer();
        time.startTimer();
        while (time.Running()) {
            System.out.println(mineTime + "\n"+time.showTimer());

            if (time.showTimer() > mineTime) {
                time.stopTimer();
            }
        }

        return FinishMinedResource();
    }
    
    public enum StrengthRequired {
        None,
        Wood,
        Stone
    }
}
