package com.thai27.trangtintuc_v4_be.ServicerInterface;

import com.thai27.trangtintuc_v4_be.Entity.DanhMucBaiBao;
import com.thai27.trangtintuc_v4_be.Exception.ResourceNotFoundException;

import java.util.List;

public interface DanhMucBaiBaoService {

    List<DanhMucBaiBao> getAllDanhMuc();

    DanhMucBaiBao addDanhMuc(DanhMucBaiBao danhMucBaiBao);

    DanhMucBaiBao editDanhMuc(Long id, DanhMucBaiBao danhMucBaiBao) throws ResourceNotFoundException;

}
