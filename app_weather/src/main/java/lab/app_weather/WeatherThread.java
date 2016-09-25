package lab.app_weather;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by student on 2016/8/29.
 */
public class WeatherThread extends Thread {
    private String url = "http://api.openweathermap.org/data/2.5/forecast/city?q=taipei&APPID=cf92aa3b6c57eeb29d96a11522cf1eac";

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
            String json = run(url);
            double temp = new JSONObject(json).getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("temp");
            String name = new JSONObject(json).getJSONObject("city").getString("name");
            String icon = new JSONObject(json).getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("icon");
            temp -= 273.15;
            Weather weather = new Weather();
            weather.setTemp(temp);
            weather.setName(name);
            weather.setIcon(String.format("http://openweathermap.org/img/w/%s.png", icon));
            System.out.println(temp);
            AppWidgetWeather.weather = weather;

        } catch(Exception e) {

        }
    }
}
