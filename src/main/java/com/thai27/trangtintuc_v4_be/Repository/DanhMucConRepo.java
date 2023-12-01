package com.thai27.trangtintuc_v4_be.Repository;

import com.thai27.trangtintuc_v4_be.DTO.QuanLyDanhMucCon;
import com.thai27.trangtintuc_v4_be.Entity.DanhMucBaiBao;
import com.thai27.trangtintuc_v4_be.Entity.DanhMucCon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DanhMucConRepo extends JpaRepository<DanhMucCon,Long> {

    List<DanhMucCon> findByDanhMucBaiBao_Id(Long id);

    @Query(value = "SELECT \n" +
            "id as id, ten_danh_muc_con as tenDanhMuc, danh_muc_bai_bao_id as idDanhMucCha\n" +
            "FROM \n" +
            "danh_muc_con \n" +
            "where \n" +
            "LOWER(ten_danh_muc_con) like LOWER (concat('%', :search,'%')) and \n" +
            "danh_muc_bai_bao_id = :idCha", nativeQuery = true)
    Page<QuanLyDanhMucCon> searchDanhMucConByIdCha(@Param("search") String search, @Param("idCha") Long idCha, PageRequest pageRequest);

    @Query(value = "SELECT \n" +
            "id as id, ten_danh_muc_con as tenDanhMuc, danh_muc_bai_bao_id as idDanhMucCha\n" +
            "FROM \n" +
            "danh_muc_con \n" +
            "where \n" +
            "danh_muc_bai_bao_id = :idCha", nativeQuery = true)
    Page<QuanLyDanhMucCon> getDanhMucConByIdCha( @Param("idCha") Long idCha, PageRequest pageRequest);
}
