package com.dev.angazaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dev.angazaproject.ui.main.MainFragment;

/**
 * @author kogi
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }
}
