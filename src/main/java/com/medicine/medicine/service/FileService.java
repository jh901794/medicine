package com.medicine.medicine.service;


import com.medicine.medicine.domain.entity.File;
import com.medicine.medicine.domain.entity.MediEntity;
import com.medicine.medicine.domain.repository.FileRepository;
import com.medicine.medicine.dto.FileDto;
import com.medicine.medicine.dto.MediDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {
    private FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Transactional
    public Long saveFile1(FileDto fileDto) {
        return fileRepository.save(fileDto.toEntity()).getId();
    }
    @Transactional
    public Long saveFile2(FileDto fileDto) {
        return fileRepository.save(fileDto.toEntity()).getId();
    }

    @Transactional
    public Long saveFile3(FileDto fileDto) {
        return fileRepository.save(fileDto.toEntity()).getId();
    }

    @Transactional
    public FileDto getFile(Long id) {
        File file = fileRepository.findById(id).get();

        FileDto fileDto = FileDto.builder()
                .id(id)
                .origFilename(file.getOrigFilename())
                .filename(file.getFilename())
                .filePath(file.getFilePath())
                .category(file.getCategory())
                .mediid(file.getMediid())
                .build();
        return fileDto;
    }

    @Transactional
    public List<FileDto> searchmediId(String id,String category) {
        Long _id = Long.parseLong(id);
        List<File> files = fileRepository.findByMediidAndCategory(_id, category);
        List<FileDto> fileDtoList = new ArrayList<>();

        if (files.isEmpty()) return fileDtoList;

        for (File file : files) {
            fileDtoList.add(this.convertEntityToDto(file));
        }
        return fileDtoList;
    }

    private FileDto convertEntityToDto(File file) {
        return FileDto.builder()
                .id(file.getId())
                .origFilename(file.getOrigFilename())
                .filename(file.getFilename())
                .filePath(file.getFilePath())
                .category(file.getCategory())
                .mediid(file.getMediid())
                .build();
    }
}