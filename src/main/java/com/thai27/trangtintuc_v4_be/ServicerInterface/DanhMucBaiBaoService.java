package com.thai27.trangtintuc_v4_be.ServicerInterface;

import com.thai27.trangtintuc_v4_be.Entity.DanhMucBaiBao;
import com.thai27.trangtintuc_v4_be.Exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DanhMucBaiBaoService {

    List<DanhMucBaiBao> getAllDanhMuc();

    Page<DanhMucBaiBao> searchAllDanhMuc(String search ,int pageNum, int pageSize);

    Page<DanhMucBaiBao> getAllDanhMucPaging(int pageNum, int pageSize);

    DanhMucBaiBao addDanhMuc(DanhMucBaiBao danhMucBaiBao);

    DanhMucBaiBao editDanhMuc(Long id, DanhMucBaiBao danhMucBaiBao) throws ResourceNotFoundException;

}
