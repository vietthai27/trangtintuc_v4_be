package com.thai27.trangtintuc_v4_be.Repository;

import com.thai27.trangtintuc_v4_be.DTO.BaiBaoDetail;
import com.thai27.trangtintuc_v4_be.Entity.DanhMucBaiBao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DanhMucBaiBaoRepo extends JpaRepository<DanhMucBaiBao,Long> {

    Page<DanhMucBaiBao> findAllByTenDanhMucLikeIgnoreCase(String search, PageRequest pageRequest);
}
