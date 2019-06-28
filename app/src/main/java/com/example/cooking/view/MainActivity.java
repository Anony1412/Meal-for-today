package com.example.cooking.view;

import android.content.Intent;
import android.os.Bundle;

import com.example.cooking.R;
import com.example.cooking.adapter.CategoriesAdapter;
import com.example.cooking.adapter.SpecialDishAdapter;
import com.example.cooking.model.Categories;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.util.Log;
import android.view.MenuItem;

import com.example.cooking.model.Dish;
import com.example.cooking.model.Utils;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static String QUANGBAO = "QUANGBAO";

    private int categoriesPosition = -1;

    private GridView gvCategories;
    private ArrayList<Categories> categoriesArrayList;
    private CategoriesAdapter categoriesAdapter;

    // for special dish list
    private ArrayList<Dish> specialDishList;
    private SpecialDishAdapter specialDishAdapter;
    private ListView lvSpecialDish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        initContent();
    }

    private void initContent() {
        maps();

        // categories
        createCategories();
        setCategoriesAdapter();
        setOnItemClickedGridView();
    }

    private void setSpecialDish() {
        specialDishAdapter = new SpecialDishAdapter(MainActivity.this, R.layout.special_item, specialDishList);
        for (int i = 0; i < specialDishList.size(); i++) {
            Log.d(QUANGBAO, specialDishList.get(i).getName() );
        }
        lvSpecialDish.setAdapter(specialDishAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        // special dish
        createSpecialDishList();
        setSpecialDish();
    }

    private void createSpecialDishList() {
        // call method create from Utils class
        Utils.createMonNhauArrayList();
        Utils.createMonKhoArrayList();
        Utils.createMonChayArrayList();
        Utils.createMonTrangMiengArrayList();

        // create random dish for each day
        int nhauPosition = (int) (Math.random() * Utils.monNhauArrayList.size());
        int khoPosition = (int) (Math.random() * Utils.monKhoArrayList.size());
        int chayPosition = (int) (Math.random() * Utils.monChayArrayList.size());
        int trangMiengPosition = (int) (Math.random() * Utils.monTrangMiengArrayList.size());

        // add into array special dish list
        specialDishList = new ArrayList<>();
        specialDishList.add(Utils.monNhauArrayList.get(nhauPosition));
        specialDishList.add(Utils.monKhoArrayList.get(khoPosition));
        specialDishList.add(Utils.monChayArrayList.get(chayPosition));
        specialDishList.add(Utils.monTrangMiengArrayList.get(trangMiengPosition));
        Log.d(QUANGBAO, "nhauPosition: " + nhauPosition);
    }

    private void setOnItemClickedGridView() {
        gvCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DishActivity.class);
                intent.putExtra("categoriesPosition", i);
                startActivity(intent);
            }
        });
    }


    private void setCategoriesAdapter() {
        categoriesAdapter = new CategoriesAdapter(MainActivity.this, R.layout.categories_item, categoriesArrayList);
        gvCategories.setAdapter(categoriesAdapter);
    }

    private void createCategories() {
        categoriesArrayList = new ArrayList<>();
        categoriesArrayList.add(new Categories("Món nhậu", R.mipmap.ic_launcher));
        categoriesArrayList.add(new Categories("Món kho", R.mipmap.ic_launcher));
        categoriesArrayList.add(new Categories("Món chay", R.mipmap.ic_launcher));
        categoriesArrayList.add(new Categories("Món tráng miệng", R.mipmap.ic_launcher));
    }

    private void maps() {
        gvCategories = findViewById(R.id.categories_gridView);
        lvSpecialDish = findViewById(R.id.specialDish_listView);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_monNhau) {
            // Handle the 'mon nhau' action
            Intent intent = new Intent(MainActivity.this, DishActivity.class);
            intent.putExtra("categoriesPosition", 0);
            startActivity(intent);
        } else if (id == R.id.nav_monKho) {
            Intent intent = new Intent(MainActivity.this, DishActivity.class);
            intent.putExtra("categoriesPosition", 1);
            startActivity(intent);
        } else if (id == R.id.nav_monChay) {
            Intent intent = new Intent(MainActivity.this, DishActivity.class);
            intent.putExtra("categoriesPosition", 2);
            startActivity(intent);
        } else if (id == R.id.nav_monTrangMieng) {
            Intent intent = new Intent(MainActivity.this, DishActivity.class);
            intent.putExtra("categoriesPosition", 3);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            // nothing
        } else if (id == R.id.nav_send) {
            // nothing
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
