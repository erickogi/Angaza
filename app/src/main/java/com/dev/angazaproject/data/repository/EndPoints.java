package com.dev.angazaproject.data.repository;

import com.dev.angazaproject.data.models.AcmeModel;

import retrofit2.Call;
import retrofit2.http.GET;
/**
 * @author kogi
 */
public interface EndPoints {
    @GET("fortune")
    Call<AcmeModel> getAcme();


}
