package com.example.cooking.adapter;

import android.content.Context;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cooking.R;
import com.example.cooking.model.Dish;

import java.util.List;

public class DishAdapter extends BaseAdapter {

    private Context mContext;
    private int mLayout;
    private List<Dish> dishList;

    public DishAdapter(Context mContext, int mLayout, List<Dish> dishList) {
        this.mContext = mContext;
        this.mLayout = mLayout;
        this.dishList = dishList;
    }

    @Override
    public int getCount() {
        return dishList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(mLayout, null);
            holder.img = view.findViewById(R.id.dishItem_imageView);
            holder.name = view.findViewById(R.id.dishName_textView);
            holder.description = view.findViewById(R.id.dishDescription_textView);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Dish dish = dishList.get(i);
        holder.img.setImageResource(dish.getImageID());
        holder.name.setText(dish.getName());
        holder.description.setText(dish.getDescription());
        return view;
    }

    private class ViewHolder {
        ImageView img;
        TextView name, description;
    }
}
