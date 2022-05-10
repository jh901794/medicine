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
    private Long Mediid;

    public File toEntity() {
        File build = File.builder()
                .id(id)
                .origFilename(origFilename)
                .filename(filename)
                .filePath(filePath)
                .mediid(Mediid)
                .build();
        return build;
    }

    @Builder

    public FileDto(Long id, String origFilename, String filename, String filePath, Long mediid) {
        this.id = id;
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
        this.Mediid = mediid;
    }
}