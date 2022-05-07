package com.medicine.medicine.domain.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "photo")
public class PhotoEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long fileid;

    @Column(nullable = false)
    private String origFilename;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String filePath;

    @Autowired
    @ManyToOne
    @JoinColumn(name = "MediEntity_id")
    private MediEntity mediEntity;

    @Builder

    public PhotoEntity(Long fileid, String origFilename, String filename, String filePath, MediEntity mediEntity) {
        this.fileid = fileid;
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
        this.mediEntity = mediEntity;
    }
}
