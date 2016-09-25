package lab.app_weather;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.widget.RemoteViews;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Implementation of App Widget functionality.
 */
public class AppWidgetWeather extends AppWidgetProvider {

    private Handler handler = new Handler();
    private Runnable r, r2;
    public static Weather weather;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        CharSequence widgetTemp = "waiting...";
        CharSequence widgetName = "waiting...";
        String widgetIconURL = "";
        final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget_weather);

        if(weather != null) {
            widgetTemp = String.format("%.2f °C", weather.getTemp());
            widgetName = weather.getName();
            widgetIconURL = weather.getIcon();
            // Construct the RemoteViews object
            views.setTextViewText(R.id.appwidget_name, widgetName);
            views.setTextViewText(R.id.appwidget_temp, widgetTemp);

            Target target = new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    views.setImageViewBitmap(R.id.appwidget_icon, getResizedBitmap(bitmap, 250, 250));
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            };
            Picasso.with(context).load(widgetIconURL).into(target);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 101, new Intent(context, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.appwidget_icon, pendingIntent);

        }
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }


    @Override
    public void onUpdate(final Context context, final AppWidgetManager appWidgetManager, final int[] appWidgetIds) {

        r = new Runnable() {
            @Override
            public void run() {
                for (int appWidgetId : appWidgetIds) {
                    updateAppWidget(context, appWidgetManager, appWidgetId);
                }
                handler.postDelayed(r, 1000);
            }
        };

        r2 = new Runnable() {
            @Override
            public void run() {
                new WeatherThread().start();
                handler.postDelayed(r2, 1000);
            }
        };

        handler.post(r);
        handler.post(r2);
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        handler.removeCallbacks(r);
        handler.removeCallbacks(r2);
    }

    public static Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        try {
            Bitmap resizedBitmap = Bitmap.createBitmap(
                    bm, 0, 0, width, height, matrix, false);
            bm.recycle();
            return resizedBitmap;
        } catch(Exception e) {
            return bm;
        }
    }
}

