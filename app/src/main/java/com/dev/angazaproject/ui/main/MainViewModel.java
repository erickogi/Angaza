package com.dev.angazaproject.ui.main;

import android.app.Application;

import com.dev.angazaproject.Utils.DefaultExecutorSupplier;
import com.dev.angazaproject.data.models.AcmeModel;
import com.dev.angazaproject.data.repository.RequestService;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author kogi
 */
public class MainViewModel extends AndroidViewModel {
    private MutableLiveData<AcmeModel> data;

    public MainViewModel(@NonNull Application application) {
        super(application);
    }


    LiveData<AcmeModel> getData() {

        if (data == null) {
            data = new MutableLiveData<>();
        }
        load();
        return data;
    }

    private void load() {
        Call<AcmeModel> call = RequestService.getService().getAcme();

        DefaultExecutorSupplier.getInstance().forBackgroundTasks().execute(() -> {
            try {
                Response<AcmeModel> response = call.execute();

                if (response.isSuccessful()) {

                    data.postValue(response.body());
                } else {
                    data.postValue(null);

                }
            } catch (IOException e) {
                e.printStackTrace();
                data.postValue(null);

            }
        });


    }


    void refresh() {
        load();
    }
}
