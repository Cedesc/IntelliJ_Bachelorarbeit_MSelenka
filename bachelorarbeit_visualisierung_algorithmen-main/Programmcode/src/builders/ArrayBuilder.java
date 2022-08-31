package builders;

import abstractAlgorithmus.AbstractAlgorithm;
import datastructures.Array;
import datastructures.InfoArray;
import supportClasses.types;

public class ArrayBuilder{

    // creates Array
    public Array createArray(AbstractAlgorithm AbstractAlgorithm, InfoArray infoArray, types typ, int length){
        Array array = new Array(AbstractAlgorithm, infoArray, typ, length);
        return array;
    }

    // creates InfoArray
    public InfoArray createInfoArray(AbstractAlgorithm abstractAlgorithm, types type, int length){
        InfoArray infoArray = new InfoArray(abstractAlgorithm, type, length);
        return infoArray;
    }

    // creates Array with values
    public Array createArray(AbstractAlgorithm AbstractAlgorithm, InfoArray infoArray, types typ, int length, Object[] values) throws InterruptedException {
        Array array = new Array(AbstractAlgorithm, infoArray, typ, length, values);
        return array;
    }

    // creates InfoArray with values
    public InfoArray createInfoArray(AbstractAlgorithm abstractAlgorithm, types type, int length, Object[] values){
        InfoArray infoArray = new InfoArray(abstractAlgorithm, type, length, values);
        return infoArray;
    }

}
