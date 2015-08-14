package com.example.aircraft.aircraft;

import java.util.Comparator;

/**
 * Created by swetharaghu on 4/21/2015.
 * Aircraft class defining the properties of an aircraft, which also maintaines unique count within the priorityqueue
 */
public class AirCraft implements Comparable{

    private AirCraftId id;
    private int type;
    private int size;
    private int count;

    public AirCraft(AirCraftId id, int type, int size){
        this.id = id;
        this.type = type;
        this.size = size;
    }

    public AirCraftId getId() {
        return id;
    }

    public void setId(AirCraftId id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public enum AirCraftId{
        LARGE_CARGO_AIRCRAFT(0),
        SMALL_CARGO_AIRCRAFT(1),
        LARGE_PASSENGER_AIRCRAFT(2),
        SMALL_PASSENGER_AIRCRAFT(3);

        private final int value;

        private AirCraftId(final int newValue){
            value = newValue;
        }
        public int getValue(){
            return value;
        }
    }

    /**
     * The main method which implements the business rule.
     * a. Passenger AC’s have removal precedence over Cargo AC’s.
     * b. Large AC’s of a given type have removal precedence over Small AC’s of the same type.
     * c. Earlier enqueued AC’s of a given type and size have precedence over later enqueued
     * AC’s of the same type and size.
     * @param anotherObj other aircraft
     * @return whether this object is greater than object passed to it.
     */
    @Override
    public int compareTo(Object anotherObj) {

        AirCraft o1 =  this;
        AirCraft o2 = (AirCraft) anotherObj;

        if(Math.abs(o1.getType() - o2.getType()) > 0){
            return o1.getType() - o2.getType();
        }else if(o1.getType() == o1.getType()){
            if(Math.abs(o1.getSize() - o2.getSize()) > 0) {
                return o2.getSize() - o2.getSize();
            }
        }
        return o1.getCount() > o2.getCount() ? 1 : -1;

    }

}
