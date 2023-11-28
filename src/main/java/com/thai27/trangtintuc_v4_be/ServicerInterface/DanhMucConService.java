package com.thai27.trangtintuc_v4_be.ServicerInterface;

import com.thai27.trangtintuc_v4_be.Entity.DanhMucCon;
import com.thai27.trangtintuc_v4_be.Exception.ResourceNotFoundException;

import java.util.List;

public interface DanhMucConService {
    List<DanhMucCon> getAllDanhMucConByIdCha(Long id);

    DanhMucCon addDanhMucCon(DanhMucCon danhMucCon, Long idCha);

    DanhMucCon editDanhMucCon(Long id, DanhMucCon danhMucCon, Long newId) throws ResourceNotFoundException;

    void deleteDanhMucCon(Long id);
}
