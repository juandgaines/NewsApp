package com.mytechideas.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by JuanDavid on 28/03/2017.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        this.mUrl = url;
    }

    @Override
    public List<News> loadInBackground() {
        if (mUrl == null)
            return null;

        List<News> list = QueryUtils.fetchNewsData(mUrl);
        return list;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
