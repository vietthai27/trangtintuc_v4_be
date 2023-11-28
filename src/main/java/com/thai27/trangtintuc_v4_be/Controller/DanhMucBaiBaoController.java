package com.thai27.trangtintuc_v4_be.Controller;

import com.thai27.trangtintuc_v4_be.Entity.DanhMucBaiBao;
import com.thai27.trangtintuc_v4_be.Exception.ResourceNotFoundException;
import com.thai27.trangtintuc_v4_be.ServiceImplement.DanhMucBaiBaoSrvImp;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/modify/addDanhMucBaiBao")
    public ResponseEntity<DanhMucBaiBao> addDanhMucBaiBao(@RequestBody DanhMucBaiBao danhMucBaiBao){
        DanhMucBaiBao addDanhMucBaiBao = danhMucBaiBaoSrvImp.addDanhMuc(danhMucBaiBao);
        return ResponseEntity.status(HttpStatus.CREATED).body(addDanhMucBaiBao);
    }

    @PutMapping("/modify/editDanhMucBaiBao/{id}")
    public ResponseEntity<DanhMucBaiBao> editDanhMucBaiBao(@PathVariable Long id, @RequestBody DanhMucBaiBao danhMucBaiBao) throws ResourceNotFoundException {
        DanhMucBaiBao editDanhMucBaiBao = danhMucBaiBaoSrvImp.editDanhMuc(id, danhMucBaiBao);
        return ResponseEntity.status(HttpStatus.OK).body(editDanhMucBaiBao);
    }
}
