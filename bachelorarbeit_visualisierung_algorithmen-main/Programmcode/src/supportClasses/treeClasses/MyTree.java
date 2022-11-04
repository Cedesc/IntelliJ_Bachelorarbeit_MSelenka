package supportClasses.treeClasses;

import java.util.ArrayList;

public class MyTree {

    private MyNode root;

    public MyTree(MyNode root) {
        this.root = root;
    }

    public void addLeaf(MyNode newLeaf) {
        // TODO: 04.11.2022 Implementation
    }

    public void changeParent(MyNode childNode, MyNode newParent) {
        // TODO: 04.11.2022 Implementation
    }

    public void deleteLeaf(MyNode node) {
        // TODO: 04.11.2022 Implementation
    }

    public void changeValue(MyNode node, int newValue) {
        // TODO: 04.11.2022 Implementation
    }

    public ArrayList<MyNode> getLeafs() {
        // For the method "positioning" is it mandatory to have the leafs in the right order from left to right.
        // This is possible with depth first search.
        // TODO: 04.11.2022 Implementation
        return null;
    }

    public int getNumberOfLevels() {
        // TODO: 04.11.2022 Implementation
        return 0;
    }

    public ArrayList<MyNode> getNodesOfLevel(int level) {
        // TODO: 04.11.2022 Implementation
        return null;
    }

    public void positioning(int xDistance, int yDistance) {

        ArrayList<MyNode> leafs = getLeafs();
        int numberOfLevels = getNumberOfLevels();
        int y = numberOfLevels * yDistance;
        int x = 0;

        // Set the position of all leafs like there were all on the lowest level.
        // The leafs that aren't on the lowest level will be updated later, but the x coordinate should remain the same.
        for(MyNode leaf : leafs) {
            leaf.setXAndY(x, y);
            // to the next position
            x += xDistance;
        }

        for(int level = numberOfLevels ; level > 0 ; level--) {
            y -= yDistance;

            for(MyNode node : getNodesOfLevel(level)) {
                if (node.isLeaf()) {
                    node.setY(y);
                }
                else {
                    // position the node in the middle over the children (or over the child in the middle?)
                    x = node.getSumOfChildrenXPositions();
                    node.setXAndY(x, y);
                }
            }
        }

    }

}
