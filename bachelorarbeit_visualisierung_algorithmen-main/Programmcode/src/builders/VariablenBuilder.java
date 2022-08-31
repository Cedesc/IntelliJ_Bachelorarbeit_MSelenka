package builders;

import abstractAlgorithmus.AbstractAlgorithm;
import datastructures.InfoVariable;
import datastructures.Variable;
import supportClasses.types;

public class VariablenBuilder{

    //  creates Variable
    public Variable createVariable(AbstractAlgorithm AbstractAlgorithm, InfoVariable infoVariable, types typ) {
        Variable var =  new Variable(infoVariable, AbstractAlgorithm, typ);
        return var;
    }

    // creates InfoVariable
    public InfoVariable createInfoVariable(AbstractAlgorithm AbstractAlgorithm, types typ){
        InfoVariable var = new InfoVariable(AbstractAlgorithm, typ);
        return var;
    }
}
