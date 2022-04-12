package com.medicine.medicine.service;


import com.medicine.medicine.domain.entity.MediEntity;
import com.medicine.medicine.domain.repository.MediRepository;
import com.medicine.medicine.dto.MediDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


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
    public Long savePost(MediDto mediDto, MultipartFile trans_file, MultipartFile tax_file, MultipartFile shop_file) throws Exception{
        String trans_projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files\\trans";

        UUID trans_uuid = UUID.randomUUID();
        String trans_fileName = trans_uuid + "_" + trans_file.getOriginalFilename();
        File trans_saveFile = new File(trans_projectPath,trans_fileName);
        trans_file.transferTo(trans_saveFile);
        mediDto.setTrans_filename(trans_fileName);
        mediDto.setTrans_filepath("/files/"+trans_fileName);


        String tax_projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files\\tax";

        UUID tax_uuid= UUID.randomUUID();
        String tax_fileName = tax_uuid + "_" + trans_file.getOriginalFilename();
        File tax_saveFile = new File(tax_projectPath,tax_fileName);
        tax_file.transferTo(tax_saveFile);
        mediDto.setTax_filename(tax_fileName);
        mediDto.setTax_filepath("/files/"+tax_fileName);


        String shop_projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files\\shop";
        UUID shop_uuid = UUID.randomUUID();
        String shop_fileName = shop_uuid + "_" + trans_file.getOriginalFilename();
        File shop_saveFile = new File(shop_projectPath,shop_fileName);
        shop_file.transferTo(shop_saveFile);
        mediDto.setShop_filename(shop_fileName);
        mediDto.setShop_filepath("/files/"+shop_fileName);



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
