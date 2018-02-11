package com.example.gurjitsingh3499.flickrapp;

import android.os.AsyncTask;

enum DownloadStatus {IDLE, PROCESSING, NOT_INITIALIZED, FAILED_OR_EMPTY, OK }


class GetRawData extends AsyncTask<String, Void, String> {
    private static final String TAG = "GetRawData";

    private DownloadStatus mDownloadStatus;

    public GetRawData() {
        mDownloadStatus = DownloadStatus.IDLE;
    }
}
