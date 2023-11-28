package com.thai27.trangtintuc_v4_be.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "danh_muc_con")
@Data
public class DanhMucCon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ten_danh_muc_con",nullable = false)
    private String tenDanhMucCon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "danh_muc_bai_bao_id")
    private DanhMucBaiBao danhMucBaiBao;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "danhMucCon")
    private List<BaiBao> baiBao;
}
