package com.keehwan.domain.announcement.domain;

import com.keehwan.share.domain.BaseCreatedAndUpdatedDateTimeWithAudit;

import java.util.List;

public class Notice extends BaseCreatedAndUpdatedDateTimeWithAudit {
    private Long id;
    private List<NoticeTranslation> translations;
    private CommonAnnouncementContent content;
}
