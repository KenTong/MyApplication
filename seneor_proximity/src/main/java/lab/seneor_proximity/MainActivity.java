package lab.seneor_proximity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private SensorManager manager;
    private Sensor sensor;
    private Context context;
    private TextView left, right;
    private int answer = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        left = (TextView) findViewById(R.id.left);
        right = (TextView) findViewById(R.id.right);

        left.setOnLongClickListener(onLongClickListener);
        right.setOnLongClickListener(onLongClickListener);
        left.setOnTouchListener(onTouchListener);
        right.setOnTouchListener(onTouchListener);
        left.setText(" ");
        right.setText(" ");

        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

    }

    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                switch (view.getId()) {
                    case R.id.left:
                        answer = 1;
                        break;
                    case R.id.right:
                        answer = 2;
                        break;
                }
            }
            return false;
        }
    };

    View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            left.setText(" ");
            right.setText(" ");
            answer = 0;
            return true;
        }
    };

    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float value = sensorEvent.values[0];
            setTitle(value + " " + answer);
            System.out.println(value + "");
            if(value == 0) {

                switch (answer) {
                    case 1:
                        left.setText("左");
                        right.setText (" ");
                        break;
                    case 2:
                        left.setText(" ");
                        right.setText("右");
                        break;

                }
            }
            //new RunWork(value).start();

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };


    @Override
    protected void onStart() {
        super.onStart();
        manager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        super.onStop();
        manager.unregisterListener(listener);
    }

    class RunWork extends Thread {
        float value;
        RunWork(float value) {
            this.value = value;
        }
        OkHttpClient client = new OkHttpClient();

        String run(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        }

        public void run() {
            try {
                if(value == 0) {
                    run("http://192.168.43.200/LED=ON");
                } else {
                    run("http://192.168.43.200/LED=OFF");
                }
            } catch(Exception e) {

            }

        }
    }

}
