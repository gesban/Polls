
package com.loopcupcakes.apps.polls.model.entities.huffpost;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class EstimatesByDate implements Parcelable {

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

    @Override
    public String toString() {
        return "EstimatesByDate{" +
                "date='" + date + '\'' +
                ", estimates=" + estimates +
                '}';
    }

    protected EstimatesByDate(Parcel in) {
        date = in.readString();
        if (in.readByte() == 0x01) {
            estimates = new ArrayList<Estimate_>();
            in.readList(estimates, Estimate_.class.getClassLoader());
        } else {
            estimates = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        if (estimates == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(estimates);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<EstimatesByDate> CREATOR = new Parcelable.Creator<EstimatesByDate>() {
        @Override
        public EstimatesByDate createFromParcel(Parcel in) {
            return new EstimatesByDate(in);
        }

        @Override
        public EstimatesByDate[] newArray(int size) {
            return new EstimatesByDate[size];
        }
    };
}