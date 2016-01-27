
package com.loopcupcakes.apps.polls.model.entities.huffpost;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Estimate_ implements Parcelable {

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


    protected Estimate_(Parcel in) {
        choice = in.readString();
        value = in.readByte() == 0x00 ? null : in.readDouble();
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
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Estimate_> CREATOR = new Parcelable.Creator<Estimate_>() {
        @Override
        public Estimate_ createFromParcel(Parcel in) {
            return new Estimate_(in);
        }

        @Override
        public Estimate_[] newArray(int size) {
            return new Estimate_[size];
        }
    };
}