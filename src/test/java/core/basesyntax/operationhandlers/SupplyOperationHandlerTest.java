package core.basesyntax.operationhandlers;

import core.basesyntax.storage.Storage;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SupplyOperationHandlerTest {
    private static final int BANANA_QUANTITY = 80;
    private static final int APPLE_QUANTITY = 10;

    private Storage storage;
    private OperationHandler operationHandler;
    private Map<String, Integer> storageNew;

    @BeforeEach
    void setUp() {
        storage = new Storage();
        storageNew = storage.getStorage();
        storageNew.put("banana", 100);
        storageNew.put("apple", 100);
        operationHandler = new PurchaseOperationHandler();
    }

    @AfterEach
    void tearDown() {
        storage.clearAll();
    }

    @Test
    void check_supplyOperationHandlerIsValid_ok() {
        operationHandler.apply("banana", 20);
        operationHandler.apply("apple", 90);

        Assertions.assertEquals(BANANA_QUANTITY, storage.getStorage().get("banana"));
        Assertions.assertEquals(APPLE_QUANTITY, storage.getStorage().get("apple"));
    }
}
