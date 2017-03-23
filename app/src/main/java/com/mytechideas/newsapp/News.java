package com.mytechideas.newsapp;

/**
 * Created by JuanDavid on 20/03/2017.
 */

public class News {

    private String mPublicationDate;
    private String mUrl;
    private String mTitle;
    private String mSectionName;


    public News(String sectionName, String Title, String publicationDate, String url) {

        mTitle = Title;
        mPublicationDate = publicationDate;
        mSectionName = sectionName;
        mUrl = url;

    }

    public String getPublicationDate() {
        return mPublicationDate;
    }

    public String getUrl() {
        return mUrl;

    }

    public String getSectionName() {
        return mSectionName;
    }

    public String getTitle() {
        return mTitle;
    }
}
