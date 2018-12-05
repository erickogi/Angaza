package com.dev.angazaproject.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.angazaproject.Application;
import com.dev.angazaproject.R;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


import static java.util.concurrent.TimeUnit.SECONDS;
/**
 * @author kogi
 */
public class MainFragment extends Fragment {

    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);
    Runnable watcher;
    ScheduledFuture<?> watcherHandle;
    private MainViewModel mViewModel;
    private TextView textMessage;
    private TextView textNetwork;
    private RadioGroup radioGroup;
    private ImageButton refreshButton;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textMessage = view.findViewById(R.id.txt_message);
        textNetwork = view.findViewById(R.id.txt_network);
        refreshButton = view.findViewById(R.id.btn_refresh);
        radioGroup=view.findViewById(R.id.radio_group);
        radioGroup.check(R.id.radio_btn_connected);
        refreshButton.setOnClickListener(v -> {

            textMessage.setText(R.string.loading);
            startTimer();
            refresh();
        });

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        initConnectivityListener();

        startTimer();
        init();

    }

    private boolean canConnect() {
        if (Application.isConnected) {
            return true;
        } else {
            Toast.makeText(getContext(), R.string.notconnected, Toast.LENGTH_SHORT).show();
            return radioGroup.getCheckedRadioButtonId()==R.id.radio_btn_always;
        }
    }

    private void refresh() {
        if (canConnect()) {
            mViewModel.refresh();
        }
    }

    private void init() {
        if (canConnect()) {
            mViewModel.getData().observe(getActivity(), acmeModel -> {

                if (acmeModel != null) {
                    String message = "";
                    for (String s : acmeModel.getFortune()) {
                        message = message.concat("\n" + s);
                    }
                    textMessage.setText(message);
                    endTimer();


                } else {
                    refresh();
                }
            });
        }
    }

    private void startTimer() {
        if(watcherHandle==null||watcherHandle.isCancelled()) {
            watchForTenSeconds();
        }
    }

    private void endTimer() {

        if(watcherHandle!=null&&!watcherHandle.isCancelled()) {
            watcherHandle.cancel(true);
        }
    }


    private void watchForTenSeconds() {
        watcher = () -> Objects.requireNonNull(getActivity()).runOnUiThread(() -> textMessage.setText(R.string.holer_msg));
        watcherHandle = scheduler.scheduleAtFixedRate(watcher, 10, 10, SECONDS);



    }

    private void initConnectivityListener() {
        ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isConnectedToInternet -> {
                    Application.isConnected = isConnectedToInternet;

                    if (isConnectedToInternet) {
                        this.textNetwork.setText(R.string.connected);
                    } else {
                        this.textNetwork.setText(R.string.notconnected);

                    }

                });
    }

}
