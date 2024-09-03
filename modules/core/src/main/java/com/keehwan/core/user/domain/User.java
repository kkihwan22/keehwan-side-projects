package com.keehwan.core.user.domain;

import com.keehwan.core.user.domain.enums.GenderType;
import com.keehwan.core.user.domain.enums.InterestCategoryCode;
import com.keehwan.share.domain.BaseCreatedAndUpdatedDateTime;

import java.time.LocalDateTime;
import java.util.List;

public class User extends BaseCreatedAndUpdatedDateTime {
    private Long id;
    private String phoneNumber;
    private boolean phoneNumberVerified;
    private LocalDateTime sentVerificationCode;
    private LocalDateTime phoneNumberVerifiedDateTime;

    private boolean identityVerified;
    private LocalDateTime identityVerifiedDateTime;

    // Optional
    private String name;
    private Birthday birthday;
    private GenderType gender;
    private Address address;
    private String introduction;
    private List<InterestCategoryCode> interestCategories;
    private List<SNS> userSnsList;

    /**
    private boolean blockedNotification;
    private List<String> detailNotificationSettings;
    private UserSettings settings;
    private UserPreferences preferences;
     **/
}
