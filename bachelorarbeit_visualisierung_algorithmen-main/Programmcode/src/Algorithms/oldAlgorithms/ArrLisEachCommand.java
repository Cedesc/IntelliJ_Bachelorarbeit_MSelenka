package Algorithms.oldAlgorithms;

import abstractAlgorithm.AbstractAlgorithm;
import datastructures.ArrayList;
import supportClasses.types;

public class ArrLisEachCommand extends AbstractAlgorithm {

    @Override
    public void executeAlgorithm() throws InterruptedException {

        ArrayList arrayList1 = create_ArrayList(types.NUMBER, 12);

        arrayList1.insertElementAtEnd(11);
        arrayList1.insertElementAtEnd(12);

        arrayList1.insertElementAtStart(21);
        arrayList1.insertElementAtStart(22);

        arrayList1.insertElement(1, 31);
        arrayList1.insertElement(3, 32);

        arrayList1.swapElements(0, 2);
        arrayList1.swapElements(2, 1);

    }

}
