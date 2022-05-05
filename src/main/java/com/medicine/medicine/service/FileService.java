package com.medicine.medicine.service;

import com.medicine.medicine.domain.entity.FileEntity;
import com.medicine.medicine.domain.repository.FileRepository;
import com.medicine.medicine.dto.FileDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    @Transactional
    public Long saveFile(FileDto fileDto) {
        return fileRepository.save(fileDto.toEntity()).getFileid();
    }
    @Transactional
    public FileDto getFile(Long id) {
        FileEntity fileEntity = fileRepository.findById(id).get();

        FileDto fileDto = FileDto.builder()
                .fileid(fileEntity.getFileid())
                .origFilename(fileEntity.getOrigFilename())
                .filename(fileEntity.getFilename())
                .filePath(fileEntity.getFilePath())
                .build();
        return fileDto;
    }

}
