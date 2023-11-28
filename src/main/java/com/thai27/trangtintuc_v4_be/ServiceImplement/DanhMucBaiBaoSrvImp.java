package com.thai27.trangtintuc_v4_be.ServiceImplement;

import com.thai27.trangtintuc_v4_be.Entity.DanhMucBaiBao;
import com.thai27.trangtintuc_v4_be.Exception.ResourceNotFoundException;
import com.thai27.trangtintuc_v4_be.Repository.DanhMucBaiBaoRepo;
import com.thai27.trangtintuc_v4_be.ServicerInterface.DanhMucBaiBaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DanhMucBaiBaoSrvImp implements DanhMucBaiBaoService {

    @Autowired
    DanhMucBaiBaoRepo danhMucBaiBaoRepo;
    @Override
    public List<DanhMucBaiBao> getAllDanhMuc() {
        return danhMucBaiBaoRepo.findAll();
    }

    @Override
    public DanhMucBaiBao addDanhMuc(DanhMucBaiBao danhMucBaiBao) {
        DanhMucBaiBao addDanhMucBaiBao = new DanhMucBaiBao();
        addDanhMucBaiBao.setTenDanhMuc(danhMucBaiBao.getTenDanhMuc());
        return danhMucBaiBaoRepo.save(addDanhMucBaiBao);
    }

    @Override
    public DanhMucBaiBao editDanhMuc(Long id, DanhMucBaiBao danhMucBaiBao) throws ResourceNotFoundException {
        DanhMucBaiBao editDanhMuc = danhMucBaiBaoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy danh mục với id:" + id));
        editDanhMuc.setTenDanhMuc(danhMucBaiBao.getTenDanhMuc());
        return danhMucBaiBaoRepo.save(editDanhMuc);
    }

}
