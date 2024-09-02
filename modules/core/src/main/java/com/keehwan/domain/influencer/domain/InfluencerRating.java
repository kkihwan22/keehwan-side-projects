package com.keehwan.domain.influencer.domain;

public class InfluencerRating {
    private int count;
    private long total;

    public double getRating() {
        if (count < 1 || total < 1) return 0;
        return (double) total / count;
    }
}
