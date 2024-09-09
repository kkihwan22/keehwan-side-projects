package com.keehwan.core.matchings.domain;

import com.keehwan.core.counselor.domain.Counselor;
import com.keehwan.core.matchings.domain.enums.MatchingStatus;
import com.keehwan.core.user.domain.User;

import java.time.LocalDateTime;

public class Matching {
    private Long id;
    private User user;
    private Counselor counselor;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    private MatchingStatus status;
}
