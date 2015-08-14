package com.example.aircraft.aircraft;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by swetharaghu on 4/22/2015.
 * Singleton Controller which holds the reference of model.
 * Whenever controller performs any action, ie enqueue, dequeue, system boot,  the same is delegated to View elements through model
 */
public class AirCraftQueueController {
    private PriorityBlockingQueue<AirCraft> aircraftQueue;
    private AtomicInteger acCount;
    private static AirCraftQueueController instance = null;
    private AirCraftModel mModel;
    private HashMap<AirCraft.AirCraftId, Integer> itemsMap = new HashMap<AirCraft.AirCraftId, Integer>();

    private AirCraftQueueController(){
        mModel = new AirCraftModel();
        initializeSystem();
    }

    public static AirCraftQueueController getInstance(){
        if(instance == null){
            instance = new AirCraftQueueController();
        }
        return instance;
    }

    public AirCraftModel getModel(){
        return mModel;
    }

    /*
    * Initialize the queue
     */
    public void initializeSystem(){
        aircraftQueue = new PriorityBlockingQueue<AirCraft>();
        acCount = new AtomicInteger();

        itemsMap.put(AirCraft.AirCraftId.LARGE_CARGO_AIRCRAFT, 0);
        itemsMap.put(AirCraft.AirCraftId.SMALL_CARGO_AIRCRAFT, 0);
        itemsMap.put(AirCraft.AirCraftId.LARGE_PASSENGER_AIRCRAFT, 0);
        itemsMap.put(AirCraft.AirCraftId.SMALL_PASSENGER_AIRCRAFT, 0);

        for(Map.Entry<AirCraft.AirCraftId, Integer> entry : itemsMap.entrySet()){
            mModel.setData(new DataType(entry.getKey(), entry.getValue()));
        }

    }

    /*
     * enqueue aircraft used to insert a new AC into the system
     */
    public void enqueue(AirCraft airCraft){
        airCraft.setCount(acCount.getAndIncrement());
        aircraftQueue.add(airCraft);
        // Update local hashmap
        itemsMap.put(airCraft.getId(), itemsMap.get(airCraft.getId()) + 1);
        //Update the model and when model is updated UI listview is updated
        mModel.setData(new DataType(airCraft.getId(), itemsMap.get(airCraft.getId())));
    }


    /*
    *  dequeue aircraft used to remove an AC from the system.
     */
    public void dequeue(){
        AirCraft airCraft = aircraftQueue.poll();
        if(airCraft != null) {
            // Update local hashmap
            itemsMap.put(airCraft.getId(), itemsMap.get(airCraft.getId())-1);
            //Update the model and when model is updated UI listview is updated
            mModel.setData(new DataType(airCraft.getId(), itemsMap.get(airCraft.getId())));
        }
    }
}
