package builders;

import abstractAlgorithm.AbstractAlgorithm;
import datastructures.InfoList;
import datastructures.List;

public class ListBuilder {

    // creates List
    public List createList(AbstractAlgorithm AbstractAlgorithm, InfoList infoList){
        List list = new List(AbstractAlgorithm, infoList);
        return list;
    }

    // creates InfoList
    public InfoList createInfoList(AbstractAlgorithm abstractAlgorithm){
        return new InfoList(abstractAlgorithm);
    }

}
