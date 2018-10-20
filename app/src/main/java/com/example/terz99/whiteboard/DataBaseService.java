package com.example.terz99.whiteboard;

import android.content.Context;
import com.firebase.client.Firebase;

public class DataBaseService {

    private Context mContext;

    public DataBaseService(Context context) {
        mContext = context;
        Firebase.setAndroidContext(mContext);
    }
}
