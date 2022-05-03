package com.medicine.medicine.dto;

import com.medicine.medicine.domain.entity.FileEntity;
import lombok.*;

@Getter
@Setter
@ToString

@NoArgsConstructor
public class fileDto {
    private Long fileid;
    private String origFilename;
    private String filename;
    private String filePath;

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
    public fileDto(Long fileid, String origFilename, String filename, String filePath) {
        this.fileid = fileid;
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
    }
}
