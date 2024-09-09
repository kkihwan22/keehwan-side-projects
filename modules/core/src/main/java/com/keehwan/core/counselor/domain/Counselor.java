package com.keehwan.core.counselor.domain;

import com.keehwan.core.account.domain.UserAccount;
import com.keehwan.core.counselor.domain.enums.InfluencerStatus;
import com.keehwan.core.user.domain.User;
import com.keehwan.share.domain.BaseCreatedAndUpdatedDateTimeWithAudit;

import java.util.List;

public class Counselor extends BaseCreatedAndUpdatedDateTimeWithAudit {
    private Long id;
    private UserAccount account;
    private User user;
    private String greetingsFilePath;
    private InfluencerStatus status;
    private boolean certified;
    private List<String> tags;
    private int likedCount;
    private int bookmarkedCount;
    private CounselorRating rating; // TODO: vo update 잘 되는지 확인이 필요.....
}
