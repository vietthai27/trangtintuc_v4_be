package com.thai27.trangtintuc_v4_be.Repository;

import com.thai27.trangtintuc_v4_be.Entity.BaiBao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaiBaoRepo extends JpaRepository<BaiBao,Long> {

    List<BaiBao> findByOrderByNgayDangDesc();

    @Query(value = "SELECT * FROM bai_bao where LOWER(ten_bai_bao)  LIKE LOWER (concat('%', :tenBaiBao,'%')) ",nativeQuery = true)
    Page<BaiBao> searchAllBaiBao(@Param("tenBaiBao")String tenBaiBao, PageRequest pageRequest);
}
