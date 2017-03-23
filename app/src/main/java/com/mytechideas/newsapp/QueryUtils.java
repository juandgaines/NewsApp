package com.mytechideas.newsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by JuanDavid on 21/03/2017.
 */

public class QueryUtils {

    public static ArrayList<News> extractNewsFromJSON(String jsonString) {
        ArrayList<News> news = new ArrayList<News>();


        try {
            JSONObject root = new JSONObject(jsonString);
            JSONObject response = root.getJSONObject("response");
            JSONArray resultsArray = response.getJSONArray("results");

            for (int i = 0; i < resultsArray.length(); i++) {
                JSONObject newsObject = resultsArray.getJSONObject(i);
                String title = newsObject.getString("webTitle");
                String date = newsObject.getString("webPublicationDate");
                String url = newsObject.getString("webUrl");
                String section = newsObject.getString("sectionName");
                news.add(new News(section, title, date, url));

            }

        } catch (JSONException e)

        {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }
        return news;
    }


}
