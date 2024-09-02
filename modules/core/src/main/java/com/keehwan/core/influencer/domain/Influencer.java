package com.keehwan.core.influencer.domain;

import com.keehwan.core.influencer.domain.enums.InfluencerStatus;
import com.keehwan.core.user.domain.User;
import com.keehwan.share.domain.BaseCreatedAndUpdatedDateTimeWithAudit;

import java.util.List;

public class Influencer extends BaseCreatedAndUpdatedDateTimeWithAudit {
    private Long id;
    private User user;
    private String greetingsFilePath;
    private InfluencerStatus status;
    private boolean certified;
    private List<String> tags;
    private int likedCount;
    private int bookmarkedCount;
    private InfluencerRating rating; // TODO: vo update 잘 되는지 확인이 필요.....
}
