package com.keehwan.core.announcement.domain;

import com.keehwan.share.domain.BaseCreatedAndUpdatedDateTimeWithAudit;

import java.util.List;

public class Event extends BaseCreatedAndUpdatedDateTimeWithAudit {
    private Long id;
    private List<EventTranslation> translations;
    private CommonAnnouncementContent content;
}
