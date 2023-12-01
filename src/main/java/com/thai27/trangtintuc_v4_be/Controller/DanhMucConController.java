package com.thai27.trangtintuc_v4_be.Controller;

import com.thai27.trangtintuc_v4_be.DTO.QuanLyDanhMucCon;
import com.thai27.trangtintuc_v4_be.Entity.DanhMucCon;
import com.thai27.trangtintuc_v4_be.Exception.ResourceNotFoundException;
import com.thai27.trangtintuc_v4_be.ServiceImplement.DanhMucConSrvImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/danhmuccon")
public class DanhMucConController {

    @Autowired
    DanhMucConSrvImp danhMucConSrvImp;

    @GetMapping("/get/getAllDanhMucConByIdCha/{id}")
    public ResponseEntity<List<DanhMucCon>> getAllDanhMucConByIdCha (@PathVariable Long id) {
        List<DanhMucCon> danhMucConList = danhMucConSrvImp.getAllDanhMucConByIdCha(id);
        return ResponseEntity.status(HttpStatus.OK).body(danhMucConList);
    }

    @PostMapping("/auth/modify/addDanhMucCon/{idCha}")
    public ResponseEntity<DanhMucCon> addDanhMucCon (@PathVariable Long idCha, @RequestBody DanhMucCon danhMucCon) {
        DanhMucCon addDanhMucCon = danhMucConSrvImp.addDanhMucCon(danhMucCon,idCha);
        return ResponseEntity.status(HttpStatus.CREATED).body(addDanhMucCon);
    }

    @PutMapping("/auth/modify/edit/editDanhMucCon/{id}/{idCha}")
    public ResponseEntity<DanhMucCon> editDanhMucCon (@PathVariable Long id, @RequestBody DanhMucCon danhMucCon, @PathVariable Long idCha ) throws ResourceNotFoundException {
        DanhMucCon editDanhMucCon = danhMucConSrvImp.editDanhMucCon(id, danhMucCon, idCha);
        return ResponseEntity.status(HttpStatus.OK).body(editDanhMucCon);
    }

    @DeleteMapping("/auth/delete/deleteDanhMucCon/{id}")
    public ResponseEntity<DanhMucCon> deleteDanhMucCon (@PathVariable Long id) {
        danhMucConSrvImp.deleteDanhMucCon(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

    @GetMapping("/auth/getDanhMucConByIdCha")
    public ResponseEntity<Page<QuanLyDanhMucCon>> getDanhMucConByIdCha (@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam Long idCha) {
        Page<QuanLyDanhMucCon> danhMucConList = danhMucConSrvImp.getDanhMucConByIdCha(pageNum, pageSize,idCha);
        return ResponseEntity.status(HttpStatus.OK).body(danhMucConList);
    }
    @GetMapping("/auth/searchDanhMucConByIdCha")
    public ResponseEntity<Page<QuanLyDanhMucCon>> searchDanhMucConByIdCha (@RequestParam String search,@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam Long idCha) {
        Page<QuanLyDanhMucCon> danhMucConList = danhMucConSrvImp.searchDanhMucConByIdCha( search, pageNum, pageSize,idCha);
        return ResponseEntity.status(HttpStatus.OK).body(danhMucConList);
    }

}
