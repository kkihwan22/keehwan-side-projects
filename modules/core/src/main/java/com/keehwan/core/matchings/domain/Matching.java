package com.keehwan.core.matchings.domain;

import com.keehwan.core.influencer.domain.Influencer;
import com.keehwan.core.matchings.domain.enums.MatchingStatus;
import com.keehwan.core.user.domain.User;

import java.time.LocalDateTime;

public class Matching {
    private Long id;
    private User user;
    private Influencer influencer;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    private MatchingStatus status;
}
