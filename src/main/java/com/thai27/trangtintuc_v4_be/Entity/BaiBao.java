package com.thai27.trangtintuc_v4_be.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bai_bao")
@Data
public class BaiBao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "ten_bai_bao")
    private String tenBaiBao;

    @Column(name = "tieu_de")
    private String tieuDe;

    @Column(name = "noidung",columnDefinition = "TEXT")
    private String noiDung;

    @Column(name = "ngay_dang")
    private String ngayDang;

    @Column(name = "tac_gia")
    private String tacGia;

    @Column(name = "luot_xem")
    private int luotXem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "danh_muc_con_id")
    private DanhMucCon danhMucCon;

}
