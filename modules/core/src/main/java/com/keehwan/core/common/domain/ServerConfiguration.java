package com.keehwan.core.common.domain;

import com.keehwan.share.domain.BaseCreatedAndUpdatedDateTimeWithAudit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "server_config")
@DynamicInsert
@DynamicUpdate
@Getter
@ToString
public class ServerConfiguration extends BaseCreatedAndUpdatedDateTimeWithAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "config_name")
    private String key;

    @Column(name = "config_value")
    private String values;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "start_at")
    private LocalDateTime startDateTime;

    @Column(name = "end_at")
    private LocalDateTime endDateTime;
}
