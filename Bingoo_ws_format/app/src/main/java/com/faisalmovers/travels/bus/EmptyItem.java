package com.faisalmovers.travels.bus;

import model.AbstractItem;
import model.seatModel;

public class EmptyItem extends AbstractItem {

    public EmptyItem(seatModel label) {
        super(label);
    }


    @Override
    public int getType() {
        return TYPE_EMPTY;
    }

}
