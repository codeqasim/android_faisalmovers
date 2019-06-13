package com.faisalmovers.travels.bus;

import model.AbstractItem;
import model.seatModel;

public class EdgeItem extends AbstractItem {

    public EdgeItem(seatModel label) {
        super(label);
    }



    @Override
    public int getType() {
        return TYPE_EDGE;
    }

}
