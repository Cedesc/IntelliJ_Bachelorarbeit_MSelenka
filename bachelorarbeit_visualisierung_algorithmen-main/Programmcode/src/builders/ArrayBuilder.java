package builders;

import abstractAlgorithm.AbstractAlgorithm;
import datastructures.Array;
import datastructures.InfoArray;
import supportClasses.types;

public class ArrayBuilder{

    // creates Array
    public Array createArray(AbstractAlgorithm AbstractAlgorithm, InfoArray infoArray, types typ, int length){
        return new Array(AbstractAlgorithm, infoArray, typ, length);
    }

    // creates InfoArray
    public InfoArray createInfoArray(AbstractAlgorithm abstractAlgorithm, types type, int length){
        return new InfoArray(abstractAlgorithm, type, length);
    }

    // creates Array with values
    public Array createArray(AbstractAlgorithm AbstractAlgorithm, InfoArray infoArray, types typ, int length, Object[] values) throws InterruptedException {
        return new Array(AbstractAlgorithm, infoArray, typ, length, values);
    }

    // creates InfoArray with values
    public InfoArray createInfoArray(AbstractAlgorithm abstractAlgorithm, types type, int length, Object[] values){
        return new InfoArray(abstractAlgorithm, type, length, values);
    }

}
