package com.mytechideas.newsapp;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>>{


    public static final String LOG_TAG = MainActivity.class.getName();
    private static final String NEWS_REQUEST_URL = "https://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=c4196b37-80f3-4f1c-a7f2-48a42c587fc2";
    private static final int NEWS_LOADER_ID = 1;
    private newsAdapter mNewsAdapter;
    private ArrayList<News> mArrayNews= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = (ListView) findViewById(R.id.list);

        mNewsAdapter = new newsAdapter(this, R.layout.list_item, mArrayNews);

        list.setAdapter(mNewsAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                News mNewsUrl = mNewsAdapter.getItem(i);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mNewsUrl.getUrl()));

                if (intent.resolveActivity(getPackageManager()) != null) {

                    startActivity(intent);
                }
            }
        });
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        Log.v(LOG_TAG, "initLoader launched");
    }

    @Override
    public Loader onCreateLoader(int i, Bundle bundle) {
        Log.v(LOG_TAG, "initLoader created");
        return new NewsLoader(this,NEWS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader <List<News>> loader, List<News> news) {
        mNewsAdapter.clear();
        Log.v(LOG_TAG, "onLoadFinished launched");
        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (news != null && !news.isEmpty()) {
            mNewsAdapter.addAll(news);

        }
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }
}
