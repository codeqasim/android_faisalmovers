package com.faisalmovers.travels.bus;

import model.AbstractItem;
import model.seatModel;

public class CenterItem extends AbstractItem {

    public CenterItem(seatModel label) {
        super(label);
    }


    @Override
    public int getType() {
        return TYPE_CENTER;
    }

}
