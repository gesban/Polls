
package com.loopcupcakes.apps.polls.model.entities.huffpost;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class EstimatesByDate {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("estimates")
    @Expose
    private List<Estimate_> estimates = new ArrayList<Estimate_>();

    /**
     * 
     * @return
     *     The date
     */
    public String getDate() {
        return date;
    }

    /**
     * 
     * @param date
     *     The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 
     * @return
     *     The estimates
     */
    public List<Estimate_> getEstimates() {
        return estimates;
    }

    /**
     * 
     * @param estimates
     *     The estimates
     */
    public void setEstimates(List<Estimate_> estimates) {
        this.estimates = estimates;
    }

}
