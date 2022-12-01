package commands.arrayListCommands;

import datastructures.InfoArrayList;

public class SwapElementsByIndexArrayList extends ArrayListCommand {

    // saves all relevant data of the command by initialization
    private final InfoArrayList infoArrayList;
    private final int index1;
    private final int index2;
    private final Object value1;
    private final Object value2;

    // constructor
    public SwapElementsByIndexArrayList(InfoArrayList infoArrayList, int index1, int index2, Object value1, Object value2){
        this.infoArrayList = infoArrayList;
        this.index1 = index1;
        this.index2 = index2;
        this.value1 = value1;
        this.value2 = value2;
        setCommandString("Swap ArrayList elements by index:  index1 = "+index1+"  value1 = "+value1+"  index2 = "
                +index2+"  value2 = "+value2);
    }

    // execute command during visualization
    @Override
    public void exeCommand() throws InterruptedException {
        this.infoArrayList.swapElementsByIndex(index1, index2, value1, value2);
    }

    // inverts command during visualization
    @Override
    public void backCommand() throws InterruptedException {
        this.infoArrayList.swapElementsByIndex(index2, index1, value2, value1);
    }

    @Override
    public InfoArrayList getArrayList() {
        return this.infoArrayList;
    }
}
