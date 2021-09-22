package com.example.actmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.actmobile.apicalls.ApiClient;
import com.example.actmobile.apicalls.ApiInterface;
import com.example.actmobile.model.Root;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout spinner_countries;
    static ImageView flag_image;
    static TextView country_name;
    static BottomSheetDialog dialog;
    public static prefConfig prefConfig;
    public static ApiInterface apiInterface;
    private com.example.actmobile.countries_adapter countries_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefConfig = new prefConfig(MainActivity.this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        spinner_countries = findViewById(R.id.spinner_countries);
        flag_image = findViewById(R.id.flag_image);
        country_name = findViewById(R.id.country_name);

        country_name.setText(prefConfig.readName());
        Glide.with(MainActivity.this).load("https://www.countryflags.io/"+ prefConfig.readCode() +"/flat/64.png").into(flag_image);

        spinner_countries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater li = (LayoutInflater) MainActivity.this.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
                View view1 = li.inflate(R.layout.bottom_sheet_spinner, null);

                dialog = new BottomSheetDialog(MainActivity.this);

                RecyclerView rc_countries = view1.findViewById(R.id.rc_countries);

                Call<Root> call = apiInterface.getResults();
                call.enqueue(new Callback<Root>() {

                    @Override
                    public void onResponse(Call<Root> call, Response<Root> response) {

                        rc_countries.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        Root root= response.body();
                        countries_adapter = new countries_adapter(root.result, MainActivity.this);
                        rc_countries.setAdapter(countries_adapter);

                    }

                    @Override
                    public void onFailure(Call<Root> call, Throwable t) {
                        Log.d("retrofitError",t.getMessage());
                    }
                });

                dialog.setContentView(view1);
                dialog.show();
            }
        });
    }
}