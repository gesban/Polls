
package com.loopcupcakes.apps.polls.model.entities.huffpost;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Estimate implements Parcelable {

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


    protected Estimate(Parcel in) {
        choice = in.readString();
        value = in.readByte() == 0x00 ? null : in.readDouble();
        leadConfidence = (Object) in.readValue(Object.class.getClassLoader());
        firstName = in.readString();
        lastName = in.readString();
        party = in.readString();
        byte incumbentVal = in.readByte();
        incumbent = incumbentVal == 0x02 ? null : incumbentVal != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(choice);
        if (value == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(value);
        }
        dest.writeValue(leadConfidence);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(party);
        if (incumbent == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (incumbent ? 0x01 : 0x00));
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Estimate> CREATOR = new Parcelable.Creator<Estimate>() {
        @Override
        public Estimate createFromParcel(Parcel in) {
            return new Estimate(in);
        }

        @Override
        public Estimate[] newArray(int size) {
            return new Estimate[size];
        }
    };
}
