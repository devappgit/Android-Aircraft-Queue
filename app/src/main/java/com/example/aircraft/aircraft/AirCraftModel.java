package com.example.aircraft.aircraft;

import java.util.HashMap;
import java.util.Observable;

/**
 * Created by swetharaghu on 4/22/2015.
 */
public class AirCraftModel extends Observable {


    AirCraftModel(){
    }

    /**
     * Notifying the Ui regarding the latest data
     * @param dataType
     */
    public void setData(DataType dataType){
        setChanged();
        notifyObservers(dataType);
    }


}
