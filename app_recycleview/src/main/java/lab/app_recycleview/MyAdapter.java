package lab.app_recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by student on 2016/9/5.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>  {

    private ItemData[] itemsData;
    private Context context;

    public MyAdapter(Context context, ItemData[] itemsData) {
        this.context = context;
        this.itemsData = itemsData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(context).inflate(R.layout.row, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtViewTitle.setText(itemsData[position].getTitle());
        Picasso.with(context).load(itemsData[position].getImageUrl()).into(holder.imgViewIcon);
    }

    @Override
    public int getItemCount() {
        return itemsData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtViewTitle;
        public ImageView imgViewIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtViewTitle = (TextView) itemView.findViewById(R.id.item_title);
            imgViewIcon = (ImageView) itemView.findViewById(R.id.item_icon);
        }

        @Override
        public void onClick(View view) {

        }
    }


}
