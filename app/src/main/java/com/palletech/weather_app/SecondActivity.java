package com.palletech.weather_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView sunrise,sunset,temp,humidity,pressure,speed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        sunrise = (TextView) findViewById(R.id.tv_sunrise);
        sunset = (TextView) findViewById(R.id.tv_sunset);

        temp = (TextView) findViewById(R.id.tv_temp);
        humidity = (TextView) findViewById(R.id.tv_humidity);
        pressure = (TextView) findViewById(R.id.tv_pressure);

        speed = (TextView) findViewById(R.id.tv_speed);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        Double sun = b.getDouble("sunrise");
        sunrise.setText("sunrise :"+sun);

        Double sunset1 = b.getDouble("sunset");
        sunset.setText("sunset"+sunset1);

        Double temp1 = b.getDouble("temp");
        temp.setText("temp"+temp1);

        Double humidity1 = b.getDouble("humidity");
        humidity.setText("humidity"+humidity1);

        Double pressure1 = b.getDouble("pressure");
        pressure.setText("pressure"+pressure1);


        Double speed1 = b.getDouble("speed");
        speed.setText("speed"+speed1);
    }
}
