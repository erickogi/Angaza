package com.dev.angazaproject.Utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

/**
 * @author kogi
 */
public class MainThreadExecutor implements Executor {

    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void execute(Runnable runnable) {
        handler.post(runnable);
    }
}