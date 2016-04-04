package com.twitter.sarcasm.base;

import com.twitter.sarcasm.enums.AttitudeType;

/**
 * Created by Dmitry on 2016-04-03.
 */
public class TwitterData {
    private String tweet;
    private AttitudeType primaryAttitudeType;
    private AttitudeType secondaryAttitudeType;

    public TwitterData(final String tweet, final AttitudeType primaryAttitudeType, final AttitudeType secondaryAttitudeType) {
        this.tweet = tweet;
        this.primaryAttitudeType = primaryAttitudeType;
        this.secondaryAttitudeType = secondaryAttitudeType;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(final String tweet) {
        this.tweet = tweet;
    }

    public AttitudeType getPrimaryAttitudeType() {
        return primaryAttitudeType;
    }

    public void setPrimaryAttitudeType(final AttitudeType primaryAttitudeType) {
        this.primaryAttitudeType = primaryAttitudeType;
    }

    public AttitudeType getSecondaryAttitudeType() {
        return secondaryAttitudeType;
    }

    public void setSecondaryAttitudeType(final AttitudeType secondaryAttitudeType) {
        this.secondaryAttitudeType = secondaryAttitudeType;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final TwitterData that = (TwitterData) o;

        if (getTweet() != null ? !getTweet().equals(that.getTweet()) : that.getTweet() != null) return false;
        if (getPrimaryAttitudeType() != that.getPrimaryAttitudeType()) return false;
        return getSecondaryAttitudeType() == that.getSecondaryAttitudeType();

    }

    @Override
    public int hashCode() {
        int result = getTweet() != null ? getTweet().hashCode() : 0;
        result = 31 * result + (getPrimaryAttitudeType() != null ? getPrimaryAttitudeType().hashCode() : 0);
        result = 31 * result + (getSecondaryAttitudeType() != null ? getSecondaryAttitudeType().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TwitterData{" +
                "tweet='" + tweet + '\'' +
                ", primaryAttitudeType=" + primaryAttitudeType +
                ", secondaryAttitudeType=" + secondaryAttitudeType +
                '}';
    }
}
