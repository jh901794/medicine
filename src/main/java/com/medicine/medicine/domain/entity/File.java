package com.medicine.medicine.domain.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String origFilename;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Long mediid;

    @Builder
    public File(Long id, String origFilename, String filename, String filePath, String category,Long mediid) {
        this.id = id;
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
        this.category = category;
        this.mediid = mediid;
    }
}
