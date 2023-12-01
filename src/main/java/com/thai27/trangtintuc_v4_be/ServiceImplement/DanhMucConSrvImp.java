package com.thai27.trangtintuc_v4_be.ServiceImplement;

import com.thai27.trangtintuc_v4_be.DTO.QuanLyDanhMucCon;
import com.thai27.trangtintuc_v4_be.Entity.DanhMucCon;
import com.thai27.trangtintuc_v4_be.Exception.ResourceNotFoundException;
import com.thai27.trangtintuc_v4_be.Repository.DanhMucBaiBaoRepo;
import com.thai27.trangtintuc_v4_be.Repository.DanhMucConRepo;
import com.thai27.trangtintuc_v4_be.ServicerInterface.DanhMucConService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanhMucConSrvImp implements DanhMucConService {

    @Autowired
    DanhMucConRepo danhMucConRepo;

    @Autowired
    DanhMucBaiBaoRepo danhMucBaiBaoRepo;

    @Override
    public List<DanhMucCon> getAllDanhMucConByIdCha(Long id) {
        return danhMucConRepo.findByDanhMucBaiBao_Id(id);
    }

    @Override
    public DanhMucCon addDanhMucCon(DanhMucCon danhMucCon, Long idCha) {
        DanhMucCon addDanhMucCon = new DanhMucCon();
        addDanhMucCon.setTenDanhMucCon(danhMucCon.getTenDanhMucCon());
        addDanhMucCon.setDanhMucBaiBao(danhMucBaiBaoRepo.getReferenceById(idCha));
        return danhMucConRepo.save(addDanhMucCon);
    }

    @Override
    public DanhMucCon editDanhMucCon(Long id, DanhMucCon danhMucCon, Long idCha) throws ResourceNotFoundException {
        DanhMucCon editDanhMucCon = danhMucConRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy danh mục con với id: "+ id));
        editDanhMucCon.setTenDanhMucCon(danhMucCon.getTenDanhMucCon());
        editDanhMucCon.setDanhMucBaiBao(danhMucBaiBaoRepo.getReferenceById(idCha));
        return danhMucConRepo.save(editDanhMucCon);
    }

    @Override
    public void deleteDanhMucCon(Long id) {
        danhMucConRepo.deleteById(id);
    }

    @Override
    public Page<QuanLyDanhMucCon> getDanhMucConByIdCha(int pageNum, int pageSize, Long idCha) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        return danhMucConRepo.getDanhMucConByIdCha(idCha,pageRequest);
    }

    @Override
    public Page<QuanLyDanhMucCon> searchDanhMucConByIdCha(String search, int pageNum, int pageSize,Long idCha) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        return danhMucConRepo.searchDanhMucConByIdCha(search,idCha, pageRequest);
    }
}
