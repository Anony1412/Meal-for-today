package com.example.cooking.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cooking.R;
import com.example.cooking.model.Dish;
import com.example.cooking.model.Utils;

import static com.example.cooking.view.MainActivity.QUANGBAO;

public class DishDescriptionActivity extends AppCompatActivity {

    private TextView txtIngredient, txtRecipe;
    private ImageView imgDemonstrate;

    private int dishPosition = -1;
    private int categoriesPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_description);

        Bundle bundle = this.getIntent().getBundleExtra("dishKey");
        this.categoriesPosition = bundle.getInt("categoriesPosition", -1);
        this.dishPosition = bundle.getInt("dishPosition", -1);
        init();
    }

    private void init() {
        maps();
        setItemContent();
    }

    private void setItemContent() {
        if (categoriesPosition >= 0 && dishPosition >= 0) {
            Dish dish = null;
            switch (categoriesPosition) {
                case 0: { // mon nhau
                    dish = Utils.monNhauArrayList.get(dishPosition);
                    txtIngredient.setText(dish.getName());
                    txtRecipe.setText(dish.getDescription());
                    break;
                }
                case 1: { // mon kho
                    dish = Utils.monKhoArrayList.get(dishPosition);
                    txtIngredient.setText(dish.getName());
                    txtRecipe.setText(dish.getDescription());
                    break;
                }
                case 2: { // mon chay
                    dish = Utils.monChayArrayList.get(dishPosition);
                    txtIngredient.setText(dish.getName());
                    txtRecipe.setText(dish.getDescription());
                    break;
                }
                case 3: { // mon trang mieng
                    dish = Utils.monTrangMiengArrayList.get(dishPosition);
                    txtIngredient.setText(dish.getName());
                    txtRecipe.setText(dish.getDescription());
                    break;
                }
            }
        } else Log.e(QUANGBAO, "setItemContent: " + "categoriesPosition = " + categoriesPosition + "dishPosition = " + dishPosition);

    }

    private void maps() {
        txtIngredient = findViewById(R.id.dishIngredient_textView);
        txtRecipe = findViewById(R.id.cachLamDes_textView);
        imgDemonstrate = findViewById(R.id.dishDemonstrate_imageView);
    }
}
