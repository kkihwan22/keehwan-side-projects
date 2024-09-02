package com.keehwan.core.matchings.domain;

import com.keehwan.share.domain.BaseCreatedAndUpdatedDateTimeWithAudit;

public class MatchingReview extends BaseCreatedAndUpdatedDateTimeWithAudit {
    private Long id;

    private Matching matching;

    private int rating;
    private String content;

    private boolean blocked;
    private String blockingReason;

    private MatchingReviewReply reply;
}

