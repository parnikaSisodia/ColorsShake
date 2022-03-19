package com.example.colourdivider;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;



//import androidx.layout.widget.ConstraintLayout;

public class MainActivity extends Activity implements SensorEventListener{
    private SensorManager sensorManager;
    private View view1;
    private View view2;
    private View view3;
    private View view4;
    private View view5;

    private long lastUpdate;
    int counterColorChanged =0;
    private TextView t1;
//    private TextView t2;
//    private TextView t3;
//    private TextView t4;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view1 = findViewById(R.id.upper_background);
       // view1.setBackgroundColor(Color.BLACK);

        view2=findViewById(R.id.bottom_background);
        //view2.setBackgroundColor(Color.WHITE);

        view3=findViewById(R.id.b1);
       // view3.setBackgroundColor(Color.CYAN);

        view4=findViewById(R.id.b2);
       // view4.setBackgroundColor(Color.MAGENTA);

        view5=findViewById(R.id.b3);
        //view5.setBackgroundColor(Color.BLUE);



        this.t1=findViewById(R.id.t1);
//        this.t2=findViewById(R.id.t2);
//        this.t3=findViewById(R.id.t3);
//        this.t4=findViewById(R.id.t4);



        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();

    }
    //overriding two methods of SensorEventListener
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }

    }

    private void getAccelerometer(SensorEvent event) {
        float[] values = event.values;
        // Movement
        float x = values[0];
        float y = values[1];
        float z = values[2];

        float accelerationSquareRoot = (x * x + y * y + z * z)
                / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);

        long actualTime = System.currentTimeMillis();

        if (accelerationSquareRoot >= 2) //it will be executed if you shuffle
        {

            if (actualTime - lastUpdate < 200) {
                return;
            }
            lastUpdate = actualTime;//updating lastUpdate for next shuffle

            counterColorChanged++;
            colorChangedMethod1(counterColorChanged);

        }
    }

    public void colorChangedMethod1(int c){
        if (c==1){
            view1.setBackgroundColor(getResources().getColor(R.color.purple_700));
            view2.setBackgroundColor(getResources().getColor(R.color.black));
            view3.setBackgroundColor(getResources().getColor(R.color.white));
            view4.setBackgroundColor(getResources().getColor(R.color.teal_200));
            view5.setBackgroundColor(getResources().getColor(R.color.purple_200));


            t1.setTextColor(getResources().getColor(R.color.white));
            //t2.setTextColor(getResources().getColor(R.color.white));

        }
        else if(c==2) {
            view2.setBackgroundColor(getResources().getColor(R.color.purple_700));
            view3.setBackgroundColor(getResources().getColor(R.color.black));
            view4.setBackgroundColor(getResources().getColor(R.color.white));
            view5.setBackgroundColor(getResources().getColor(R.color.teal_200));
            view1.setBackgroundColor(getResources().getColor(R.color.purple_200));
            t1.setTextColor(getResources().getColor(R.color.white));
        }
        else if(c==3){
            view3.setBackgroundColor(getResources().getColor(R.color.purple_700));
            view4.setBackgroundColor(getResources().getColor(R.color.black));
            view5.setBackgroundColor(getResources().getColor(R.color.white));
            view1.setBackgroundColor(getResources().getColor(R.color.teal_200));
            view2.setBackgroundColor(getResources().getColor(R.color.purple_200));
            t1.setTextColor(getResources().getColor(R.color.white));
        }
        else if(c==4){
            view4.setBackgroundColor(getResources().getColor(R.color.purple_700));
            view5.setBackgroundColor(getResources().getColor(R.color.black));
            view1.setBackgroundColor(getResources().getColor(R.color.white));
            view2.setBackgroundColor(getResources().getColor(R.color.teal_200));
            view3.setBackgroundColor(getResources().getColor(R.color.purple_200));
            t1.setTextColor(getResources().getColor(R.color.black));
        }
        else{
            view5.setBackgroundColor(getResources().getColor(R.color.purple_700));
            view1.setBackgroundColor(getResources().getColor(R.color.black));
            view2.setBackgroundColor(getResources().getColor(R.color.white));
            view3.setBackgroundColor(getResources().getColor(R.color.teal_200));
            view4.setBackgroundColor(getResources().getColor(R.color.purple_200));
            t1.setTextColor(getResources().getColor(R.color.white));
            counterColorChanged=0;
        }


    }



    @Override
    protected void onResume() {
        super.onResume();
        // register this class as a listener for the orientation and
        // accelerometer sensors
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}