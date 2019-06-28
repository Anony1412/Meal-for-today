package com.example.cooking.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cooking.R;
import com.example.cooking.adapter.DishAdapter;
import com.example.cooking.model.Utils;

import static com.example.cooking.view.MainActivity.QUANGBAO;

public class DishActivity extends AppCompatActivity {

    private ListView lvDish;
    private DishAdapter dishAdapter;

    private int categoriesPosition = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);

        this.categoriesPosition = this.getIntent().getIntExtra("categoriesPosition", -1);

        init();
    }

    private void init() {
        maps();
        createArrayList();
        setDishAdapter();
        setItemClickedListView();
    }

    private void createArrayList() {
        if (categoriesPosition >= 0) {

            switch (categoriesPosition) {
                case 0: { // create array list of 'Mon nhau'
                    Utils.createMonNhauArrayList();
                    break;
                }
                case 1: { // create array list of 'Mon kho'
                    Utils.createMonKhoArrayList();
                    break;
                }
                case 2: { // create array list of 'Mon chay'
                    Utils.createMonChayArrayList();
                    break;
                }
                case 3: { // create array list of 'Mon trang mieng'
                    Utils.createMonTrangMiengArrayList();
                    break;
                }
            }

        } else Log.e(QUANGBAO, "categoriesPosition: " + categoriesPosition);
    }

    private void setItemClickedListView() {
        lvDish.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DishActivity.this, DishDescriptionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("categoriesPosition", categoriesPosition);
                bundle.putInt("dishPosition", i);
                intent.putExtra("dishKey", bundle);
                startActivity(intent);
            }
        });
    }

    private void setDishAdapter() {
        if (categoriesPosition >= 0) {

            switch (categoriesPosition) {
                case 0: { // create adapter of 'Mon nhau'
                    dishAdapter = new DishAdapter(DishActivity.this, R.layout.dish_item, Utils.monNhauArrayList);
                    break;
                }
                case 1: { // create adapter of 'Mon kho'
                    dishAdapter = new DishAdapter(DishActivity.this, R.layout.dish_item, Utils.monKhoArrayList);
                    break;
                }
                case 2: { // create adapter of 'Mon chay'
                    dishAdapter = new DishAdapter(DishActivity.this, R.layout.dish_item, Utils.monChayArrayList);
                    break;
                }
                case 3: { // create adapter of 'Mon trang mieng'
                    dishAdapter = new DishAdapter(DishActivity.this, R.layout.dish_item, Utils.monTrangMiengArrayList);
                    break;
                }
            }

            lvDish.setAdapter(dishAdapter);
        }

    }

    private void maps() {
        lvDish = findViewById(R.id.dishActivity_listView);
    }
}
