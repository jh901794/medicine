package com.medicine.medicine.dto;
import com.medicine.medicine.domain.entity.File;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FileDto {

    private Long id;
    private String origFilename;
    private String filename;
    private String filePath;
    private String category;
    private Long mediid;

    public File toEntity() {
        File fileEntity = File.builder()
                .id(id)
                .origFilename(origFilename)
                .filename(filename)
                .filePath(filePath)
                .category(category)
                .mediid(mediid)
                .build();
        return fileEntity;
    }

    @Builder

    public FileDto(Long id, String origFilename, String filename, String filePath, String category, Long mediid) {
        this.id = id;
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
        this.category = category;
        this.mediid = mediid;
    }

}