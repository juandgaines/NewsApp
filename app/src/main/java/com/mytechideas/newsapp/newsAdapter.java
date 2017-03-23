package com.mytechideas.newsapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by JuanDavid on 21/03/2017.
 */

public class newsAdapter extends ArrayAdapter<News> {

    public newsAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<News> objects) {
        super(context, resource, objects);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        News mNews = getItem(position);
        View listViewConvert = convertView;

        if (listViewConvert == null) {
            listViewConvert = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView sectionView = (TextView) listViewConvert.findViewById(R.id.news_icon);
        TextView TitleView = (TextView) listViewConvert.findViewById(R.id.news_title);
        TextView DateView = (TextView) listViewConvert.findViewById(R.id.news_date);

        sectionView.setText(mNews.getSectionName());
        TitleView.setText(mNews.getTitle());
        DateView.setText("Published on: " + mNews.getPublicationDate());

        return listViewConvert;
    }


}
