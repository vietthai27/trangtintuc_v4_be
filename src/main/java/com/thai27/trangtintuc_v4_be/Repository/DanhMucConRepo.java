package com.thai27.trangtintuc_v4_be.Repository;

import com.thai27.trangtintuc_v4_be.Entity.DanhMucCon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DanhMucConRepo extends JpaRepository<DanhMucCon,Long> {

    List<DanhMucCon> findByDanhMucBaiBao_Id(Long id);
}
