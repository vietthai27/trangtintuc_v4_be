package com.thai27.trangtintuc_v4_be.Controller;

import com.thai27.trangtintuc_v4_be.DTO.BaiBaoDetail;
import com.thai27.trangtintuc_v4_be.DTO.DanhMucBaiBaoEdit;
import com.thai27.trangtintuc_v4_be.Entity.BaiBao;
import com.thai27.trangtintuc_v4_be.Exception.ResourceNotFoundException;
import com.thai27.trangtintuc_v4_be.ServiceImplement.BaiBaoSrvImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/baibao")
public class BaiBaoController {

    @Autowired
    BaiBaoSrvImp baiBaoSrvImp;

    @GetMapping("/get/getBaiBaoById/{id}")
    public Optional<BaiBao> getBaiBaoById(@PathVariable Long id) throws ResourceNotFoundException {
        return baiBaoSrvImp.getBaiBaoById(id);
    }

    @GetMapping("/get/getBaiBaoDetailById/{id}")
    public BaiBaoDetail getBaiBaoDetailById(@PathVariable Long id) throws ResourceNotFoundException {
        return baiBaoSrvImp.getBaiBaoDetailById(id);
    }

    @GetMapping("/get/getAllBaiBao")
    public Page<BaiBaoDetail> getAllBaiBao(@RequestParam Integer pageNum,@RequestParam Integer pageSize) {
        return baiBaoSrvImp.getAllBaiBao(pageNum,pageSize);
    }

    @GetMapping("/get/searchAllBaiBao")
    public Page<BaiBaoDetail> searchAllBaiBao(@RequestParam String tenBaiBao, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return baiBaoSrvImp.searchAllBaiBao(tenBaiBao,pageNum,pageSize);
    }

    @GetMapping("/get/findByOrderByNgayDangDesc")
    public List<BaiBao> findByOrderByNgayDangDesc() {
        return baiBaoSrvImp.findByOrderByNgayDangDesc();
    }


    @PostMapping("/modify/addBaiBao/{idDanhMucCon}")
    public BaiBao addBaiBao(@RequestBody BaiBao baiBao,@PathVariable Long idDanhMucCon) {
        return baiBaoSrvImp.addBaiBao(baiBao,idDanhMucCon);
    }

    @PostMapping("/modify/editBaiBao/{id}/{idCon}")
    public BaiBao editBaiBao(@PathVariable Long id,@PathVariable Long idCon, @RequestBody BaiBao baiBao) throws ResourceNotFoundException {
        return baiBaoSrvImp.editBaiBao(id, idCon, baiBao);
    }

    @DeleteMapping("/delete/deleteBaiBao/{id}")
    public void deleteBaiBao(@PathVariable Long id) {
        baiBaoSrvImp.deleteBaiBao(id);
    }
}
