package lab.app_drawer;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by student on 2016/8/22.
 */
public class BMWFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String data = getTag();
        View v = inflater.inflate(R.layout.fragment_main, null);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.layout);

        for(BMW bmw : Util.list) {
            if(bmw.getType().equals(data)) {
                TextView tv = new TextView(getActivity());
                tv.setText(bmw.getName());
                ImageView iv = new ImageView(getActivity());
                Picasso.with(getActivity()).load(bmw.getImageUrl()).into(iv);

                layout.addView(tv);
                layout.addView(iv);
            }
        }
        return v;
    }
}
