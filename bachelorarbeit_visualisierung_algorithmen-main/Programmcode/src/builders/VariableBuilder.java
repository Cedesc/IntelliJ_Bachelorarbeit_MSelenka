package builders;

import abstractAlgorithm.AbstractAlgorithm;
import datastructures.InfoVariable;
import datastructures.Variable;
import supportClasses.types;

public class VariableBuilder {

    //  creates Variable
    public Variable createVariable(AbstractAlgorithm AbstractAlgorithm, InfoVariable infoVariable, types typ) {
        return new Variable(infoVariable, AbstractAlgorithm, typ);
    }

    // creates InfoVariable
    public InfoVariable createInfoVariable(AbstractAlgorithm AbstractAlgorithm, types typ){
        return new InfoVariable(AbstractAlgorithm, typ);
    }
}
