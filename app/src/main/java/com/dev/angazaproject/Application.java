package com.dev.angazaproject;

import android.support.multidex.MultiDexApplication;

/**
 * @author kogi
 */
public class Application extends MultiDexApplication {
    public static volatile boolean isConnected=true;

    @Override
    public void onCreate() {
        super.onCreate();

    }


    }
