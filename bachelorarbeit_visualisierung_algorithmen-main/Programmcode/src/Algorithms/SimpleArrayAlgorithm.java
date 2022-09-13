package Algorithms;

import abstractAlgorithmus.AbstractAlgorithm;
import datastructures.*;
import supportClasses.types;

public class SimpleArrayAlgorithm extends AbstractAlgorithm {

    @Override
    public void executeAlgorithm() throws InterruptedException {
        // funktioniert komplett, sowohl forwards als auch backwards
        Array array1 = create_Array(types.NUMBER, 15);

        array1.insertElementAtStart(1);
        array1.insertElementAtEnd(2);
        array1.insertElement(1,3);
        array1.insertElement(array1.getIndexByValue(2),4);
        array1.setElement(1, 5);
        array1.swapElements(1,2);
        array1.deleteElementByValue(1);
        array1.deleteElementByValue(array1.getValueByIndex(1));
        array1.deleteElementByIndex(array1.getSize()-1);

    }

}
