package com.example.actmobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.actmobile.model.Result;

import java.util.List;

public class countries_adapter extends RecyclerView.Adapter<countries_adapter.ViewHolder> {
    private final List<Result> results;
    private Context context;
    Integer mSelectedItem = -1;
    public static com.example.actmobile.prefConfig prefConfig;

    public countries_adapter(List<Result> results, Context context) {
        this.results = results;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_countries,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result res = results.get(position);

        holder.itemView.setSelected(mSelectedItem == position);

        Glide.with(context).load("https://www.countryflags.io/"+res.getCode().toLowerCase()+"/flat/64.png").into(holder.rc_flag);
        holder.rc_country_name.setText(res.getName());
        holder.radioButton.setChecked(position == mSelectedItem);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView rc_flag;
        TextView rc_country_name;
        RadioButton radioButton;

        public ViewHolder(View itemView) {
            super(itemView);
            rc_flag = itemView.findViewById(R.id.rc_flag);
            rc_country_name = itemView.findViewById(R.id.rc_country_name);
            radioButton= itemView.findViewById(R.id.radioButton);

            View.OnClickListener l = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedItem = getAdapterPosition();
                    String data = results.get(mSelectedItem).getName();
                    String image = results.get(mSelectedItem).getCode().toLowerCase();

                    MainActivity.country_name.setText(data);
                    Glide.with(context).load("https://www.countryflags.io/"+image+"/flat/64.png").into(MainActivity.flag_image);
                    MainActivity.dialog.dismiss();

                    prefConfig = new prefConfig(context);
                    prefConfig.writeName(data);
                    prefConfig.writeCode(image);

                    notifyItemRangeChanged(0, results.size());
                }
            };
            itemView.setOnClickListener(l);
            radioButton.setOnClickListener(l);
        }
    }
}
