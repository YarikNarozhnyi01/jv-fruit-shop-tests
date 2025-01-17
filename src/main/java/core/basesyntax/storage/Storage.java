package core.basesyntax.storage;

import java.util.HashMap;
import java.util.Map;

public class Storage {
    private static final Map<String, Integer> storage = new HashMap<>();

    public int getCurrentAmount(String fruitType) {
        return storage.getOrDefault(fruitType, 0);
    }

    public int getOrDefault(String fruitType, int defaultValue) {
        return storage.getOrDefault(fruitType, defaultValue);
    }

    public Map<String, Integer> getStorage() {
        return storage;
    }

    public static void clearAll() {
        storage.clear();
    }

    public Integer get(String fruit) {
        if (!storage.containsKey(fruit)) {
            throw new IllegalArgumentException("Fruit not found: " + fruit);
        }
        return storage.get(fruit);
    }
}
