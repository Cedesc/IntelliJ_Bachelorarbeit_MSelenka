package Algorithms.oldAlgorithms;

import abstractAlgorithm.AbstractAlgorithm;
import datastructures.ArrayList;
import supportClasses.types;

// OWN TEST STUFF
public class ArrLisSimple extends AbstractAlgorithm {

    @Override
    public void executeAlgorithm() throws InterruptedException {

        ArrayList arrayList1 = create_ArrayList(types.NUMBER, 12);
        ArrayList arrayList2 = create_ArrayList(types.NUMBER, 5);

        arrayList1.insertElementAtEnd(1);
        arrayList1.insertElementAtEnd(2);
        arrayList1.insertElementAtEnd(3);

        arrayList1.swapElements(0, 2);

        arrayList1.insertElementAtEnd(4);

        arrayList1.swapElements(2, 1);

        arrayList1.insertElementAtEnd(5);
        arrayList1.insertElementAtEnd(6);


    }

}
