
package com.loopcupcakes.apps.polls.model.entities.huffpost;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Estimate_ {

    @SerializedName("choice")
    @Expose
    private String choice;
    @SerializedName("value")
    @Expose
    private Double value;

    /**
     * 
     * @return
     *     The choice
     */
    public String getChoice() {
        return choice;
    }

    /**
     * 
     * @param choice
     *     The choice
     */
    public void setChoice(String choice) {
        this.choice = choice;
    }

    /**
     * 
     * @return
     *     The value
     */
    public Double getValue() {
        return value;
    }

    /**
     * 
     * @param value
     *     The value
     */
    public void setValue(Double value) {
        this.value = value;
    }

}
