package com.medicine.medicine.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "Medicine")
public class MediEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private LocalDate order_date;

    @Column(length = 100, nullable = false)
    private LocalDate delivert;

    @Column(length = 100, nullable = false)
    private String producer;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 100, nullable = false)
    private String trans;
//온도정보 없음

    //거래 명세서
    @Column(length = 300, nullable = false)
    private String trans_filename;

    @Column(length = 300, nullable = false)
    private String trans_filepath;

    //세금계산서
    @Column(length = 300, nullable = false)
    private String tax_filename;

    @Column(length = 300, nullable = false)
    private String tax_filepath;

    //출하증명서
    @Column(length = 300, nullable = false)
    private String shop_filename;

    @Column(length = 300, nullable = false)
    private String shop_filepath;


    @Builder
    public MediEntity(Long id, LocalDate order_date, LocalDate delivert, String producer, String title, String trans, String trans_filename, String trans_filepath, String tax_filename, String tax_filepath, String shop_filename, String shop_filepath) {
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
