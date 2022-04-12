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
    private String trans_filename;
    private String trans_filepath;
    private String tax_filename;
    private String tax_filepath;
    private String shop_filename;
    private String shop_filepath;

    public MediEntity toEntity(){
        MediEntity mediEntity = MediEntity.builder()
                .id(id)
                .order_date(order_date)
                .delivert(delivert)
                .producer(producer)
                .title(title)
                .trans(trans)
                .trans_filename(trans_filename)
                .trans_filepath(trans_filepath)
                .tax_filename(tax_filename)
                .tax_filepath(tax_filepath)
                .shop_filename(shop_filename)
                .shop_filepath(shop_filepath)
                .build();
        return mediEntity;
    }
    @Builder
    public MediDto(Long id, LocalDate order_date, LocalDate delivert, String producer, String title, String trans, String trans_filename, String trans_filepath, String tax_filename, String tax_filepath, String shop_filename, String shop_filepath) {
        this.id = id;
        this.order_date = order_date;
        this.delivert = delivert;
        this.producer = producer;
        this.title = title;
        this.trans = trans;
        this.trans_filename = trans_filename;
        this.trans_filepath = trans_filepath;
        this.tax_filename = tax_filename;
        this.tax_filepath = tax_filepath;
        this.shop_filename = shop_filename;
        this.shop_filepath = shop_filepath;
    }
}
