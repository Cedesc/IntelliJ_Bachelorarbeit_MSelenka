package supportClasses.treeClasses;

public class ValueNullObject {

    /**
     * @return Returns true, if obj is null or a ValueNullObject. Otherwise, it returns false.
     */
    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == ValueNullObject.class;
    }

    @Override
    public String toString() {
        return "NIL";
    }
}
