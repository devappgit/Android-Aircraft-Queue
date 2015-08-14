package com.example.aircraft.aircraft;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by swetharaghu on 4/22/2015.
 */
public class AirCraftMainActivity extends Activity implements View.OnClickListener, Observer,
        View.OnLongClickListener, View.OnDragListener
{
    private LinearLayout imagesLayout;
    private ImageView[] acImage = new ImageView[4];
    private ListView listView;
    private static final String IMAGEVIEW_TAG = "icon bitmap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        listView = (ListView) findViewById(R.id.listView);
        ListviewAdapter adapter = new ListviewAdapter(this);
        listView.setAdapter(adapter);

        imagesLayout = (LinearLayout) findViewById(R.id.images_layout);

        acImage[0] = (ImageView) findViewById(R.id.imageView);
        acImage[1] = (ImageView) findViewById(R.id.imageView2);
        acImage[2] = (ImageView) findViewById(R.id.imageView3);
        acImage[3] = (ImageView) findViewById(R.id.imageView4);

        setOnclickListeners();
    }

    /**
     * Method to set onclick listeners
     */
    private void setOnclickListeners(){
        findViewById(R.id.boot_btn).setOnClickListener(this);
        findViewById(R.id.dequeue_btn).setOnClickListener(this);
        findViewById(R.id.enqueue_btn).setOnClickListener(this);

        acImage[0].setOnLongClickListener(this);
        acImage[0].setOnClickListener(this);
        acImage[0].setTag(IMAGEVIEW_TAG);

        acImage[1].setOnLongClickListener(this);
        acImage[1].setOnClickListener(this);

        acImage[2].setOnLongClickListener(this);
        acImage[2].setOnClickListener(this);

        acImage[3].setOnLongClickListener(this);
        acImage[3].setOnClickListener(this);
    }

    /**
     * Remove observer
     */
    @Override
    protected void onPause() {
        super.onPause();
        AirCraftQueueController.getInstance().getModel().deleteObserver(this);
    }

    /**
     * Add observer inorder to be called when there is change in data model
     */
    @Override
    protected void onResume() {
        super.onResume();
        AirCraftQueueController.getInstance().getModel().addObserver(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.boot_btn:{
                AirCraftQueueController.getInstance().initializeSystem();

            }break;
            case R.id.dequeue_btn:{
                AirCraftQueueController.getInstance().dequeue();
            }break;
            case R.id.enqueue_btn:{
                if(imagesLayout.getVisibility() == View.VISIBLE){
                        imagesLayout.setVisibility(View.GONE);
                }
                else imagesLayout.setVisibility(View.VISIBLE);

            }break;
            case R.id.imageView:{
                AirCraftQueueController.getInstance().enqueue(new AirCraft(AirCraft.AirCraftId.LARGE_CARGO_AIRCRAFT,
                                                                    AirCraftConstants.AIRCRAFT_TYPE_CARGO,
                                                                           AirCraftConstants.AIRCRAFT_SIZE_LARGE));
            }break;
            case R.id.imageView2:{
                AirCraftQueueController.getInstance().enqueue(new AirCraft(AirCraft.AirCraftId.SMALL_CARGO_AIRCRAFT,
                        AirCraftConstants.AIRCRAFT_TYPE_CARGO,
                        AirCraftConstants.AIRCRAFT_SIZE_SMALL));
            }break;
            case R.id.imageView3:{
                AirCraftQueueController.getInstance().enqueue(new AirCraft(AirCraft.AirCraftId.LARGE_PASSENGER_AIRCRAFT,
                        AirCraftConstants.AIRCRAFT_TYPE_PASSENGER,
                        AirCraftConstants.AIRCRAFT_SIZE_LARGE));
            }break;
            case R.id.imageView4:{
                AirCraftQueueController.getInstance().enqueue(new AirCraft(AirCraft.AirCraftId.SMALL_PASSENGER_AIRCRAFT,
                        AirCraftConstants.AIRCRAFT_TYPE_PASSENGER,
                        AirCraftConstants.AIRCRAFT_SIZE_SMALL));
            }break;
        }
    }

    /**
     * Updates single item in the listview
     * @param dataType
     */
    public void updateListView(DataType dataType){
        View v = listView.getChildAt(dataType.id.getValue() - listView.getFirstVisiblePosition());
        if(v == null) return;

        TextView txtView = (TextView) v.findViewById(R.id.count_txt);
        if(txtView != null)
         txtView.setText(Integer.toString(dataType.value));
    }

    @Override
    public void update(Observable observable, Object data) {
        updateListView((DataType)data);
    }




    @Override
    public boolean onDrag(View v, DragEvent event) {
        return false;
    }

    @Override
    public boolean onLongClick(View v) {
        ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
        ClipData dragData = new ClipData(v.getTag(), MIME, item);

        return false;
    }
}
