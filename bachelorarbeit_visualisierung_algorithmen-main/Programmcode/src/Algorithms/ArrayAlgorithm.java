package Algorithms;

import abstractAlgorithmus.AbstractAlgorithm;
import datastructures.*;
import supportClasses.types;

public class ArrayAlgorithm extends AbstractAlgorithm {
    @Override
    public void executeAlgorithm() throws InterruptedException {
        Array array1 = create_Array(types.NUMBER, 30);
        Array array2 = create_ArrayWithValues(types.NUMBER, 10, new Object[]{1, 2, 3, 4});
        array2.setElement(2, 4);
        array2.deleteElementByIndex(2);
        array2.deleteArray();

        array1.insertElementAtStart(4);
        array1.insertElementAtEnd(5);
        array1.insertELement(array1.getIndexByValue(5),9);
        array1.swapElements(1,2);
        array1.deleteElementByValue(array1.getValueByIndex(0));
        array1.deleteElementByIndex(array1.getSize()-1);
        array1.deleteArray();

    }
}
