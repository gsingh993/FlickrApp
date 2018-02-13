package com.example.gurjitsingh3499.flickrapp;

import android.net.Uri;
import android.util.Log;

import java.util.List;

/**
 * Created by gurjitsingh on 2/13/18.
 */

class GetFlickrJsonData implements GetRawData.OnDownloadComplete {
    private static final String TAG = "GetFlickrJsonData";

    private List<Photo> mPhotoList = null;
    private String mBaseURL;
    private String mLanguage;
    private boolean mMatchAll;

    private final OnDataAvailable mCallBack;

    interface OnDataAvailable {
        void onDataAvailable(List<Photo> data, DownloadStatus status);
    }

    public GetFlickrJsonData(OnDataAvailable CallBack, String BaseURL, String Language,  boolean MatchAll) {
        Log.d(TAG, "GetFlickrJsonData: called");
        this.mBaseURL = BaseURL;
        this.mLanguage = Language;
        this.mMatchAll = MatchAll;
        this.mCallBack = CallBack;
    }
    
    void executeOnSameThread(String searchCriteria){
        Log.d(TAG, "executeOnSameThread: ");
        String destinationUri = createUri(searchCriteria, mLanguage, mMatchAll);

        GetRawData getRawData = new GetRawData(this);
        getRawData.execute(destinationUri);
        Log.d(TAG, "executeOnSameThread: ends");
    }

    private String createUri(String searchCriteria, String lang, boolean matchAll){
        Log.d(TAG, "createUri: starts");
        return Uri.parse(mBaseURL).buildUpon()
                .appendQueryParameter("tags", searchCriteria)
                .appendQueryParameter("tagmode", matchAll ? "ALL": "ANY")
                .appendQueryParameter("lang", lang)
                .appendQueryParameter("format", "json")
                .appendQueryParameter("nojsoncallback", "1")
                .build().toString();
    }
    @Override
    public void onDownloadComplete(String data, DownloadStatus status) {

    }
}
