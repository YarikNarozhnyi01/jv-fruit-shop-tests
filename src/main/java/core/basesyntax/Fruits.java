package core.basesyntax;

import java.util.HashMap;
import java.util.Map;

public class Fruits {
    private Map<String, Integer> fruitQuantities;

    public Fruits() {
        fruitQuantities = new HashMap<>();
    }

    public int balance(String fruitType,int init) {
        fruitQuantities.put(fruitType,init);
        return init;
    }

    public int supply(String fruitType,int quantityOfFruits) {
        int supplyAmount = fruitQuantities.get(fruitType) + quantityOfFruits;
        fruitQuantities.put(fruitType,supplyAmount);
        return supplyAmount;
    }

    public int purchase(String fruitType,int amount) {
        int purchaseAmount = fruitQuantities.get(fruitType) - amount;
        fruitQuantities.put(fruitType,purchaseAmount);
        return purchaseAmount;
    }

    public int returnFruit(String fruitType,int amount) {
        int returnedAmount = fruitQuantities.get(fruitType) + amount;
        fruitQuantities.put(fruitType,returnedAmount);
        return returnedAmount;
    }
}
