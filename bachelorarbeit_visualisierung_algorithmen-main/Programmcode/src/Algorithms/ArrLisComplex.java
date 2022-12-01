package Algorithms;

import abstractAlgorithm.AbstractAlgorithm;
import datastructures.ArrayList;
import supportClasses.types;

// OWN TEST STUFF
public class ArrLisComplex extends AbstractAlgorithm {

    @Override
    public void executeAlgorithm() throws InterruptedException {

        ArrayList arrayList1 = create_ArrayList(types.NUMBER, 12);
        ArrayList arrayList2 = create_ArrayList(types.NUMBER, 5);

        arrayList1.insertElementAtEnd(11);
        arrayList1.insertElementAtEnd(12);

        arrayList2.insertElementAtEnd(21);

        ArrayList arrayList3 = create_ArrayList(types.NUMBER, 3);

        arrayList2.insertElementAtEnd(22);

        arrayList3.insertElementAtEnd(31);

        arrayList2.insertElementAtEnd(23);

        arrayList1.insertElementAtEnd(13);

        arrayList2.swapElements(1, 0);
        arrayList2.swapElements(2, 0);
        arrayList2.swapElements(1, 2);

        arrayList1.swapElements(0, 2);


    }

}
