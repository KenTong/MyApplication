package lab.app_weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private String url = "http://api.openweathermap.org/data/2.5/forecast/city?q=taipei&APPID=cf92aa3b6c57eeb29d96a11522cf1eac";
    private TextView city_text, temp_text;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        city_text = (TextView) findViewById(R.id.city_text);
        temp_text = (TextView) findViewById(R.id.temp_text);
        imageView = (ImageView) findViewById(R.id.imageView);

        new RunWork().start();
    }


    class RunWork extends Thread {
        double temp = 0.0;
        OkHttpClient client = new OkHttpClient();

        String run(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        }

        Runnable r = new Runnable() {
            @Override
            public void run() {
                temp_text.setText(String.format("%.2f °C", temp));
            }
        };

        public void run() {
            try {
                String json = run(url);
                temp = new JSONObject(json).getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("temp");
                temp -= 273.15;
                runOnUiThread(r);
            } catch(Exception e) {

            }
        }

    }
}
