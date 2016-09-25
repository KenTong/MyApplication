package lab.app_fragment.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

import lab.app_fragment.R;
import pokemon.Pokemon;
import pokemon.Util;

/**
 * Created by student on 2016/8/19.
 */
public class PokemonFragment extends Fragment {
    private TextView pokemon_textView;
    private ImageView pokemon_imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon, null);

        pokemon_textView = (TextView) view.findViewById(R.id.pokemon_textView);
        pokemon_imageView = (ImageView) view.findViewById(R.id.pokemon_imageView);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        new RunWork().start();
    }

    class RunWork extends Thread {
        private List<Pokemon> list;

        Runnable r = new Runnable() {
            @Override
            public void run() {
                int n = new Random().nextInt(list.size());
                pokemon_textView.setText(list.get(n).get名稱());
                Picasso.with(getActivity()).load(list.get(n).get圖片()).into(pokemon_imageView);
            }
        };

        public void run() {
            try {
                list = Util.getPokemon();
                getActivity().runOnUiThread(r);
            } catch(Exception e) {
                e.printStackTrace();
            }



        }

    }


}
