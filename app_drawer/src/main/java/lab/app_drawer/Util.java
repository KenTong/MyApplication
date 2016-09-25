package lab.app_drawer;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Util {

    public static List<BMW> list;

    public static List<BMW> readRawTextFile2(Context ctx, int resId) {
        List<BMW> list = new ArrayList<>();

        InputStream inputStream = ctx.getResources().openRawResource(resId);
        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;
        try {
            while (( line = buffreader.readLine()) != null) {
                list.add(new BMW(line.split(",")[0], line.split(",")[1], line.split(",")[2]));
            }
        } catch (IOException e) {
            return null;
        }
        return list;
    }

    public static Map<String, String> readRawTextFile(Context ctx, int resId) {
        Map<String, String> map = new TreeMap<>();

        InputStream inputStream = ctx.getResources().openRawResource(resId);
        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;
        try {
            while (( line = buffreader.readLine()) != null) {
                map.put(line.split(",")[1], line.split(",")[2]);
            }
        } catch (IOException e) {
            return null;
        }
        return map;
    }
}
