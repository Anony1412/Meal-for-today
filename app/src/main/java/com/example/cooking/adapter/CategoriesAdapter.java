package com.example.cooking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cooking.R;
import com.example.cooking.model.Categories;

import java.util.List;

public class CategoriesAdapter extends BaseAdapter {

    private Context mContext;
    private int mLayout;
    private List<Categories> categoriesList;

    public CategoriesAdapter(Context mContext, int mLayout, List<Categories> categoriesList) {
        this.mContext = mContext;
        this.mLayout = mLayout;
        this.categoriesList = categoriesList;
    }

    @Override
    public int getCount() {
        return categoriesList.size();
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
            holder.imgAvatar = view.findViewById(R.id.categories_background_imageView);
            holder.txtName = view.findViewById(R.id.categories_name_textView);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Categories categories = categoriesList.get(i);
        holder.imgAvatar.setImageResource(categories.getIdPicture());
        holder.txtName.setText(categories.getName());
        return view;
    }

    private class ViewHolder {
        ImageView imgAvatar;
        TextView txtName;
    }
}
