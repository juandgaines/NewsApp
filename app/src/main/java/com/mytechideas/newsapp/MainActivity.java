package com.mytechideas.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>>{


    public static final String LOG_TAG = MainActivity.class.getName();
    private static final String NEWS_REQUEST_URL = "https://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=c4196b37-80f3-4f1c-a7f2-48a42c587fc2";
    private static final int NEWS_LOADER_ID = 1;
    private newsAdapter mNewsAdapter;
    private ArrayList<News> mArrayNews= new ArrayList<>();

    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();


        ListView list = (ListView) findViewById(R.id.list);
        mEmptyStateTextView=(TextView) findViewById(R.id.empty_view);

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
        if (isConnected) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
            Log.v(LOG_TAG, "initLoader launched");
        }
        else{
            ProgressBar mProgressBar = (ProgressBar) findViewById(R.id.loading_spinner);
            mProgressBar.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet);
        }

    }

    @Override
    public Loader onCreateLoader(int i, Bundle bundle) {
        Log.v(LOG_TAG, "initLoader created");
        return new NewsLoader(this,NEWS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader <List<News>> loader, List<News> news) {

        mEmptyStateTextView.setVisibility(View.VISIBLE);
        mEmptyStateTextView.setText(R.string.no_news);
        ProgressBar mProgressBar = (ProgressBar) findViewById(R.id.loading_spinner);
        mProgressBar.setVisibility(View.GONE);

        mNewsAdapter.clear();

        Log.v(LOG_TAG, "onLoadFinished launched");
        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (news != null && !news.isEmpty()){
            mNewsAdapter.addAll(news);
            mEmptyStateTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {
        Log.v(LOG_TAG, "onLoaderReset launched");
        mNewsAdapter.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
}
