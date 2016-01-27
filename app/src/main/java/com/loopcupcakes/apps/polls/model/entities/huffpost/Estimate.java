
package com.loopcupcakes.apps.polls.model.entities.huffpost;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Estimate {

    @SerializedName("choice")
    @Expose
    private String choice;
    @SerializedName("value")
    @Expose
    private Double value;
    @SerializedName("lead_confidence")
    @Expose
    private Object leadConfidence;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("party")
    @Expose
    private String party;
    @SerializedName("incumbent")
    @Expose
    private Boolean incumbent;

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

    /**
     * 
     * @return
     *     The leadConfidence
     */
    public Object getLeadConfidence() {
        return leadConfidence;
    }

    /**
     * 
     * @param leadConfidence
     *     The lead_confidence
     */
    public void setLeadConfidence(Object leadConfidence) {
        this.leadConfidence = leadConfidence;
    }

    /**
     * 
     * @return
     *     The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 
     * @param firstName
     *     The first_name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * 
     * @return
     *     The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 
     * @param lastName
     *     The last_name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * 
     * @return
     *     The party
     */
    public String getParty() {
        return party;
    }

    /**
     * 
     * @param party
     *     The party
     */
    public void setParty(String party) {
        this.party = party;
    }

    /**
     * 
     * @return
     *     The incumbent
     */
    public Boolean getIncumbent() {
        return incumbent;
    }

    /**
     * 
     * @param incumbent
     *     The incumbent
     */
    public void setIncumbent(Boolean incumbent) {
        this.incumbent = incumbent;
    }

}
