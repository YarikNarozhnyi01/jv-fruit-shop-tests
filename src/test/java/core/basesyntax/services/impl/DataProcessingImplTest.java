package core.basesyntax.services.impl;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.model.Operation;
import core.basesyntax.operationhandlers.BalanceOperationHandler;
import core.basesyntax.operationhandlers.OperationHandler;
import core.basesyntax.operationhandlers.PurchaseOperationHandler;
import core.basesyntax.operationhandlers.ReturnOperationHandler;
import core.basesyntax.operationhandlers.SupplyOperationHandler;
import core.basesyntax.services.DataProcessing;
import core.basesyntax.storage.Storage;
import core.basesyntax.strategy.OperationStrategy;
import core.basesyntax.strategy.OperationStrategyImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataProcessingImplTest {
    private static final int ZERO = 0;
    private static final int ONE = 1;

    private DataProcessing dataProcessing;
    private Storage storage;
    private OperationStrategy operationStrategy;

    @BeforeEach
    void setUp() {
        storage = new Storage();
        Map<Operation, OperationHandler> operationOperationHandlerMap = new HashMap<>();
        operationOperationHandlerMap.put(Operation.BALANCE, new BalanceOperationHandler());
        operationOperationHandlerMap.put(Operation.SUPPLY, new SupplyOperationHandler());
        operationOperationHandlerMap.put(Operation.PURCHASE, new PurchaseOperationHandler());
        operationOperationHandlerMap.put(Operation.RETURN, new ReturnOperationHandler());
        operationStrategy = new OperationStrategyImpl(operationOperationHandlerMap);
        dataProcessing = new DataProcessingImpl((OperationStrategyImpl) operationStrategy, storage);
    }

    @Test
    void dataProcessing_validFruitTransactionObjects_ok() {
        List<String> strings = new ArrayList<>();
        strings.add("b,banana,152");
        strings.add("b,apple,90");

        List<FruitTransaction> fruitTransactions = new ArrayList<>();
        FruitTransaction fruitTransactionFirst = new FruitTransaction();

        fruitTransactionFirst.setQuantity(152);
        fruitTransactionFirst.setFruit("banana");
        fruitTransactions.add(fruitTransactionFirst);

        FruitTransaction fruitTransactionSecond = new FruitTransaction();

        fruitTransactionSecond.setQuantity(90);
        fruitTransactionSecond.setFruit("apple");
        fruitTransactions.add(fruitTransactionSecond);

        List<FruitTransaction> processedFruitList = dataProcessing.processData(strings);

        Assertions.assertEquals(fruitTransactions.get(ZERO).getFruit(),
                processedFruitList.get(ZERO).getFruit());
        Assertions.assertEquals(fruitTransactions.get(ONE).getQuantity(),
                processedFruitList.get(ONE).getQuantity());
    }
}
