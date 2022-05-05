package com.medicine.medicine.dto;

import com.medicine.medicine.domain.entity.FileEntity;
import lombok.*;

@Getter
@Setter
@ToString

@NoArgsConstructor
public class FileDto {
    private Long fileid;

    private String origFilename;
    private String filename;
    private String filePath;

    private Long fileSize;

    public FileEntity toEntity() {
        FileEntity fileEntity = FileEntity.builder()
                .fileid(fileid)
                .origFilename(origFilename)
                .filename(filename)
                .filePath(filePath)
                .build();
        return fileEntity;
    }

    @Builder
    public FileDto(Long fileid, String origFilename, String filename, String filePath, Long fileSize) {
        this.fileid = fileid;
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
}
