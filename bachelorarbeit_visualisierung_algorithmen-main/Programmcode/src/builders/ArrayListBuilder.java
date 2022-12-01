package builders;

import abstractAlgorithm.AbstractAlgorithm;
import datastructures.ArrayList;
import datastructures.InfoArrayList;
import supportClasses.types;

public class ArrayListBuilder {

    public ArrayList createArrayList(AbstractAlgorithm AbstractAlgorithm, InfoArrayList infoArrayList, int length){
        return new ArrayList(AbstractAlgorithm, infoArrayList, length);
    }

    public InfoArrayList createInfoArrayList(AbstractAlgorithm abstractAlgorithm, types type, int length){
        return new InfoArrayList(abstractAlgorithm, type, length);
    }

}
