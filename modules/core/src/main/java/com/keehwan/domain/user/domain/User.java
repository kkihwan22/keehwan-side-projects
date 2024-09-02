package com.keehwan.domain.user.domain;

import com.keehwan.domain.user.domain.enums.GenderType;
import com.keehwan.domain.user.domain.enums.InterestCategoryCode;
import com.keehwan.share.domain.BaseCreatedAndUpdatedDateTime;

import java.time.LocalDateTime;
import java.util.List;

public class User extends BaseCreatedAndUpdatedDateTime {

    private Long id;
    private String nickname;

    private Birthday birthday;
    private GenderType gender;
    private String phoneNumber;
    private Address address;

    private List<String> profileImages;
    private String introduction;
    private List<InterestCategoryCode> interestCategories;
    private List<SNS> userSnsList;

    private boolean phoneNumberVerified;
    private LocalDateTime sentVerificationCode;
    private LocalDateTime phoneNumberVerifiedDateTime;

    private boolean identityVerified;
    private LocalDateTime identityVerifiedDateTime;


    private boolean blockedNotification;
    private List<String> detailNotificationSettings;

    private UserSettings settings;
    private UserPreferences preferences;
}
