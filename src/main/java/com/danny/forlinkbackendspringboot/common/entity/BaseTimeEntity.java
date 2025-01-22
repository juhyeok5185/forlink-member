package com.danny.forlinkbackendspringboot.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseTimeEntity {

    @Column(name = "save_dtm")
    private LocalDateTime saveDtm; // 등록일시

    @Column(name = "mod_dtm")
    private LocalDateTime modDtm; // 최종 수정일시

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.saveDtm = now;
        this.modDtm = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.modDtm = LocalDateTime.now();
    }

}
