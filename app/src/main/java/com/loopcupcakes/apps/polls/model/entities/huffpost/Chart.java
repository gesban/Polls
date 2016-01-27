
package com.loopcupcakes.apps.polls.model.entities.huffpost;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Chart implements Parcelable {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("topic")
    @Expose
    private String topic;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("short_title")
    @Expose
    private String shortTitle;
    @SerializedName("election_date")
    @Expose
    private String electionDate;
    @SerializedName("poll_count")
    @Expose
    private Integer pollCount;
    @SerializedName("last_updated")
    @Expose
    private String lastUpdated;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("estimates")
    @Expose
    private List<Estimate> estimates = new ArrayList<Estimate>();
    @SerializedName("estimates_by_date")
    @Expose
    private List<EstimatesByDate> estimatesByDate = new ArrayList<EstimatesByDate>();

    /**
     *
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     *     The slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     *
     * @param slug
     *     The slug
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     *
     * @return
     *     The topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     *
     * @param topic
     *     The topic
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     *
     * @return
     *     The state
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @param state
     *     The state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return
     *     The shortTitle
     */
    public String getShortTitle() {
        return shortTitle;
    }

    /**
     *
     * @param shortTitle
     *     The short_title
     */
    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    /**
     *
     * @return
     *     The electionDate
     */
    public String getElectionDate() {
        return electionDate;
    }

    /**
     *
     * @param electionDate
     *     The election_date
     */
    public void setElectionDate(String electionDate) {
        this.electionDate = electionDate;
    }

    /**
     *
     * @return
     *     The pollCount
     */
    public Integer getPollCount() {
        return pollCount;
    }

    /**
     *
     * @param pollCount
     *     The poll_count
     */
    public void setPollCount(Integer pollCount) {
        this.pollCount = pollCount;
    }

    /**
     *
     * @return
     *     The lastUpdated
     */
    public String getLastUpdated() {
        return lastUpdated;
    }

    /**
     *
     * @param lastUpdated
     *     The last_updated
     */
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     *
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     *     The estimates
     */
    public List<Estimate> getEstimates() {
        return estimates;
    }

    /**
     *
     * @param estimates
     *     The estimates
     */
    public void setEstimates(List<Estimate> estimates) {
        this.estimates = estimates;
    }

    /**
     *
     * @return
     *     The estimatesByDate
     */
    public List<EstimatesByDate> getEstimatesByDate() {
        return estimatesByDate;
    }

    /**
     *
     * @param estimatesByDate
     *     The estimates_by_date
     */
    public void setEstimatesByDate(List<EstimatesByDate> estimatesByDate) {
        this.estimatesByDate = estimatesByDate;
    }


    protected Chart(Parcel in) {
        title = in.readString();
        slug = in.readString();
        topic = in.readString();
        state = in.readString();
        shortTitle = in.readString();
        electionDate = in.readString();
        pollCount = in.readByte() == 0x00 ? null : in.readInt();
        lastUpdated = in.readString();
        url = in.readString();
        if (in.readByte() == 0x01) {
            estimates = new ArrayList<Estimate>();
            in.readList(estimates, Estimate.class.getClassLoader());
        } else {
            estimates = null;
        }
        if (in.readByte() == 0x01) {
            estimatesByDate = new ArrayList<EstimatesByDate>();
            in.readList(estimatesByDate, EstimatesByDate.class.getClassLoader());
        } else {
            estimatesByDate = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(slug);
        dest.writeString(topic);
        dest.writeString(state);
        dest.writeString(shortTitle);
        dest.writeString(electionDate);
        if (pollCount == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(pollCount);
        }
        dest.writeString(lastUpdated);
        dest.writeString(url);
        if (estimates == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(estimates);
        }
        if (estimatesByDate == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(estimatesByDate);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Chart> CREATOR = new Parcelable.Creator<Chart>() {
        @Override
        public Chart createFromParcel(Parcel in) {
            return new Chart(in);
        }

        @Override
        public Chart[] newArray(int size) {
            return new Chart[size];
        }
    };

    @Override
    public String toString() {
        return "Chart{" +
                "title='" + title + '\'' +
                ", estimates=" + estimates +
                '}';
    }
}