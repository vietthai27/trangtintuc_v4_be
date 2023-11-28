package com.thai27.trangtintuc_v4_be.Repository;

import com.thai27.trangtintuc_v4_be.Entity.DanhMucBaiBao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DanhMucBaiBaoRepo extends JpaRepository<DanhMucBaiBao,Long> {
}
