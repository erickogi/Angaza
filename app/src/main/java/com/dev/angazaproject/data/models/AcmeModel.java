package com.dev.angazaproject.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author kogi
 */
public class AcmeModel {

    @SerializedName("fortune")
    @Expose
    private List<String> fortune = null;

    public List<String> getFortune() {
        return fortune;
    }

    public void setFortune(List<String> fortune) {
        this.fortune = fortune;
    }
}
