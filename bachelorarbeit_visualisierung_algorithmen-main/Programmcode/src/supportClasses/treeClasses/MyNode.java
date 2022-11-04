package supportClasses.treeClasses;

public class MyNode {

    public int index;
    public int value;
    public MyNode parent;
    public MyNode leftChild;
    public MyNode rightBrother;
    public int xCoordinate;
    public int yCoordinate;

    public MyNode() {
        // TODO: 04.11.2022 Implementation
    }

    public void changeValue(int newValue) {
        value = newValue;
    }

    public Boolean hasParent() {
        return parent != null;
    }

    public Boolean hasBrother() {
        return rightBrother != null;
    }

    public Boolean isLeaf() {
        return leftChild == null;
    }

    public int getLevel() {
        // TODO: 04.11.2022 Implementation
        return 0;
    }

    public void setX(int x) {
        xCoordinate = x;
    }

    public void setY(int y) {
        yCoordinate = y;
    }

    public void setXAndY(int x, int y) {
        setX(x);
        setY(y);
    }

    public int getSumOfChildrenXPositions() {
        // TODO: 04.11.2022 Implementation
        return 0;
    }

    public int getNumberOfChildren() {
        // TODO: 04.11.2022 Implementation
        return 0;
    }

}
