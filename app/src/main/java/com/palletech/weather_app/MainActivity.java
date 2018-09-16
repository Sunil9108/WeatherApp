package com.palletech.weather_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText et1,et2,et3;
    TextView tv1;
    Button b1,b2;
    Weather w;
    MyTask myTask;

    public class MyTask extends AsyncTask<String, Void, String>{
        URL url;
        HttpURLConnection connection;
        InputStream inputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        String line;
        StringBuilder stringBuilder;



        @Override
        protected String doInBackground(String... strings) {
            try {
                url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                //connection.setRequestProperty("Accept", "application/json");
                inputStream = connection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);
                line = bufferedReader.readLine();
                stringBuilder = new StringBuilder();
                while (line !=null){
                    stringBuilder.append(line);
                    line = bufferedReader.readLine();
                }
                return stringBuilder.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("json data","displayed"+s);
            try {
                JSONObject j = new JSONObject(s);
                JSONObject coord = j.getJSONObject("coord");
                Double lon = coord.getDouble("lon");
                Double lat = coord.getDouble("lat");

                JSONObject main = j.getJSONObject("main");
                Double temp = main.getDouble("temp");
                Double pressure = main.getDouble("pressure");
                Double humidity = main.getDouble("humidity");

                JSONObject wind = j.getJSONObject("wind");
                Double speed = wind.getDouble("speed");

                JSONObject sys = j.getJSONObject("sys");
                Double sunrise = sys.getDouble("sunrise");
                Double sunset = sys.getDouble("sunset");

              w = new Weather(temp,pressure,humidity,speed,sunrise,sunset);

                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("sunrise",w.getSunRise());
                intent.putExtra("sunset",w.getSunSet());
                intent.putExtra("temp",w.getTemp());
                intent.putExtra("pressure",w.getPressure());
                intent.putExtra("humidity",w.getHumidity());
                intent.putExtra("speed",w.getSpeed());

                startActivity(intent);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(s);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.et_city);
        et2 = (EditText) findViewById(R.id.et_country);
        et3 = (EditText) findViewById(R.id.et_zip_code);

        tv1 = (TextView) findViewById(R.id.tv_or);

        b1 = (Button) findViewById(R.id.weather_details);
        b2 = (Button) findViewById(R.id.weather_current_location);

        myTask = new MyTask();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et1.getText().toString()!=null) {

                    myTask.execute("http://samples.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b1b15e88fa797225412429c1c50c122a1" + et1.getText().toString() +
                            ",&appid=74957d6d4a541cb9bea6dc85cd91fdc2");
                }
                else
                {
                    myTask.execute("http://samples.openweathermap.org/data/2.5/weather?zip=94040,us&appid=b1b15e88fa797225412429c1c50c122a1" + et3.getText().toString() +
                            "&appid=74957d6d4a541cb9bea6dc85cd91fdc2");
                }

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTask.execute("http://samples.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b1b15e88fa797225412429c1c50c122a1");
            }
        });
    }
}
