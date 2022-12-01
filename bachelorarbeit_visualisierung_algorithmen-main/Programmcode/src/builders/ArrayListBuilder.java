package builders;

import abstractAlgorithm.AbstractAlgorithm;
import datastructures.ArrayList;
import datastructures.InfoArrayList;
import supportClasses.types;

public class ArrayListBuilder {

    public ArrayList createArrayList(AbstractAlgorithm AbstractAlgorithm, InfoArrayList infoArrayList, types typ, int length){
        ArrayList arrayList = new ArrayList(AbstractAlgorithm, infoArrayList, typ, length);
        return arrayList;
    }

    public InfoArrayList createInfoArrayList(AbstractAlgorithm abstractAlgorithm, types type, int length){
        InfoArrayList infoArrayList = new InfoArrayList(abstractAlgorithm, type, length);
        return infoArrayList;
    }

}
