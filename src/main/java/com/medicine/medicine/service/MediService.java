package com.medicine.medicine.service;


import com.medicine.medicine.domain.entity.MediEntity;
import com.medicine.medicine.domain.repository.MediRepository;
import com.medicine.medicine.dto.MediDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class MediService {
    private final MediRepository mediRepository;


    @Transactional
    public List<MediDto> getmedilist() {
        List<MediEntity> mediEntities = mediRepository.findAll();
        List<MediDto> mediDtoList = new ArrayList<>();

        for (MediEntity mediEntity : mediEntities) {
            mediDtoList.add(this.convertEntityToDto(mediEntity));
        }

        return mediDtoList;
    }

    @Transactional
    public Long savePost(MediDto mediDto) throws Exception{
        return mediRepository.save(mediDto.toEntity()).getId();
    }

    @Transactional
    public List<MediDto> searchPosts(String keyword) {

            List<MediEntity> mediEntities = mediRepository.findByTitleContaining(keyword);
            List<MediDto> mediDtoList = new ArrayList<>();


            if (mediEntities.isEmpty()) return mediDtoList;

            for (MediEntity mediEntity : mediEntities) {
                mediDtoList.add(this.convertEntityToDto(mediEntity));
            }

            return mediDtoList;
    }

    //<!--find어쩌고-->
    @Transactional
    public List<MediDto> doingPost(String string) {
        if(string == null){
            List<MediEntity> mediEntities = mediRepository.findAll();
            List<MediDto> mediDtoList = new ArrayList<>();
            for (MediEntity mediEntity : mediEntities) {
                mediDtoList.add(this.convertEntityToDto(mediEntity));
            }

            return mediDtoList;
        }else {
            List<MediEntity> mediEntities = mediRepository.findBytransContaining(string);
            List<MediDto> mediDtoList = new ArrayList<>();

            for (MediEntity mediEntity : mediEntities) {
                mediDtoList.add(this.convertEntityToDto(mediEntity));
            }

            return mediDtoList;
        }
    }

    private MediDto convertEntityToDto(MediEntity mediEntity) {

        return MediDto.builder()
                .id(mediEntity.getId())
                .order_date(mediEntity.getOrder_date())
                .delivert(mediEntity.getDelivert())
                .producer(mediEntity.getProducer())
                .title(mediEntity.getTitle())
                .trans(mediEntity.getTrans())
                .build();
    }

    @Transactional
    public List<MediDto> Daterange(LocalDate strdate, LocalDate enddate) {
        List<MediEntity> mediEntities = mediRepository.findBydelivertBetween(strdate, enddate);
        List<MediDto> mediDtoList = new ArrayList<>();

        if (mediEntities.isEmpty()) return mediDtoList;

        for (MediEntity mediEntity : mediEntities) {
            mediDtoList.add(this.convertEntityToDto(mediEntity));
        }
        return mediDtoList;
    }

    @Transactional
    public List<MediDto> searchId(String id) {
        Long _id = Long.parseLong(id);
        List<MediEntity> mediEntities = mediRepository.findByid(_id);
        List<MediDto> mediDtoList = new ArrayList<>();

        if (mediEntities.isEmpty()) return mediDtoList;

        for (MediEntity mediEntity : mediEntities) {
            mediDtoList.add(this.convertEntityToDto(mediEntity));
        }

        return mediDtoList;
    }

}
