package com.keehwan.domain.matchings.domain;

import com.keehwan.domain.influencer.domain.Influencer;
import com.keehwan.domain.matchings.domain.enums.MatchingStatus;
import com.keehwan.domain.user.domain.User;

import java.time.LocalDateTime;

public class Matching {
    private Long id;
    private User user;
    private Influencer influencer;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    private MatchingStatus status;
}
