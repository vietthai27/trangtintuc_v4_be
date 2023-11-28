package com.thai27.trangtintuc_v4_be.ServicerInterface;

import com.thai27.trangtintuc_v4_be.Entity.BaiBao;
import com.thai27.trangtintuc_v4_be.Exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BaiBaoService {

    Optional<BaiBao> getBaiBaoById (Long id) throws ResourceNotFoundException;

    BaiBao addBaiBao (BaiBao baiBao, Long idDanhMucCon);

    BaiBao editBaiBao (Long id, BaiBao baiBao) throws ResourceNotFoundException;

    void deleteBaiBao (Long id);

    List<BaiBao> findByOrderByNgayDangDesc ();

    Page<BaiBao> getAllBaiBao(Integer pageNum, Integer pageSize);

    Page<BaiBao> searchAllBaiBao(String tenBaiBao, Integer pageNum, Integer pageSize);
}
