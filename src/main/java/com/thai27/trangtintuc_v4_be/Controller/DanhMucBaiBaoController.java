package com.thai27.trangtintuc_v4_be.Controller;

import com.thai27.trangtintuc_v4_be.Entity.DanhMucBaiBao;
import com.thai27.trangtintuc_v4_be.Exception.ResourceNotFoundException;
import com.thai27.trangtintuc_v4_be.ServiceImplement.DanhMucBaiBaoSrvImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/danhmuc")
public class DanhMucBaiBaoController {

    @Autowired
    DanhMucBaiBaoSrvImp danhMucBaiBaoSrvImp;

    @GetMapping("/get/getAllDanhMucBaiBao")
    public ResponseEntity<List<DanhMucBaiBao>> getAllDanhMucBaiBao(){
        return ResponseEntity.status(HttpStatus.OK).body(danhMucBaiBaoSrvImp.getAllDanhMuc());
    }

    @PostMapping("/auth/modify/addDanhMucBaiBao")
    public ResponseEntity<DanhMucBaiBao> addDanhMucBaiBao(@RequestBody DanhMucBaiBao danhMucBaiBao){
        DanhMucBaiBao addDanhMucBaiBao = danhMucBaiBaoSrvImp.addDanhMuc(danhMucBaiBao);
        return ResponseEntity.status(HttpStatus.CREATED).body(addDanhMucBaiBao);
    }

    @PutMapping("/auth/modify/editDanhMucBaiBao/{id}")
    public ResponseEntity<DanhMucBaiBao> editDanhMucBaiBao(@PathVariable Long id, @RequestBody DanhMucBaiBao danhMucBaiBao) throws ResourceNotFoundException {
        DanhMucBaiBao editDanhMucBaiBao = danhMucBaiBaoSrvImp.editDanhMuc(id, danhMucBaiBao);
        return ResponseEntity.status(HttpStatus.OK).body(editDanhMucBaiBao);
    }

    @GetMapping("/auth/getAllDanhMucBaiBaoPaging")
    public ResponseEntity<Page<DanhMucBaiBao>> getAllDanhMucBaiBaoPaging(@RequestParam int pageNum, @RequestParam int pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(danhMucBaiBaoSrvImp.getAllDanhMucPaging(pageNum,pageSize));
    }

    @GetMapping("/auth/searchAllDanhMucBaiBao")
    public ResponseEntity<Page<DanhMucBaiBao>> searchAllDanhMucBaiBao(@RequestParam String search, @RequestParam int pageNum, @RequestParam int pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(danhMucBaiBaoSrvImp.searchAllDanhMuc(search, pageNum, pageSize));
    }
}
