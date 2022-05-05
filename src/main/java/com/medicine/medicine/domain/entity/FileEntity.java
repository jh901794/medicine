package com.medicine.medicine.domain.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "file")
public class FileEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long fileid;

    @Column(nullable = false)
    private String origFilename;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "MediEntity_id")
    private MediEntity mediEntity;
    public void MediEntity_id(MediEntity mediEntity) {
        this.mediEntity = mediEntity;
    }
    @Builder
    public FileEntity(Long fileid, String origFilename, String filename, String filePath) {
        this.fileid = fileid;
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
    }
}
