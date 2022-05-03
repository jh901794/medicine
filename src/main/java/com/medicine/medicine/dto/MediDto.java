package com.medicine.medicine.dto;

import com.medicine.medicine.domain.entity.MediEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString

@NoArgsConstructor
public class MediDto {

    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate order_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate delivert;
    private String producer;
    private String title;
    private String trans;

    public MediEntity toEntity(){
        MediEntity mediEntity = MediEntity.builder()
                .id(id)
                .order_date(order_date)
                .delivert(delivert)
                .producer(producer)
                .title(title)
                .trans(trans)
                .build();
        return mediEntity;
    }
    @Builder
    public MediDto(Long id, LocalDate order_date, LocalDate delivert, String producer, String title, String trans) {
        this.id = id;
        this.order_date = order_date;
        this.delivert = delivert;
        this.producer = producer;
        this.title = title;
        this.trans = trans;
    }
}
