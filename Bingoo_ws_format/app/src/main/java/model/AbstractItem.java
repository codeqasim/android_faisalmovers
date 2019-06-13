package model;

public abstract class AbstractItem {

    public static final int TYPE_CENTER = 0;
    public static final int TYPE_EDGE = 1;
    public static final int TYPE_EMPTY = 2;
    public static final int TYPE_LAST = 3;
    public static final int TYPE_FOOTER = 4;


    private seatModel label;


    public AbstractItem(seatModel label) {
        this.label = label;
    }


    public seatModel getLabel() {
        return label;
    }

    abstract public int getType();

}