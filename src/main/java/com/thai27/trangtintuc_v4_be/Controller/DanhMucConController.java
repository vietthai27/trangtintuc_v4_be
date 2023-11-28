package com.thai27.trangtintuc_v4_be.Controller;

import com.thai27.trangtintuc_v4_be.Entity.DanhMucCon;
import com.thai27.trangtintuc_v4_be.Exception.ResourceNotFoundException;
import com.thai27.trangtintuc_v4_be.ServiceImplement.DanhMucConSrvImp;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/modify/addDanhMucCon/{idCha}")
    public ResponseEntity<DanhMucCon> addDanhMucCon (@PathVariable Long idCha, @RequestBody DanhMucCon danhMucCon) {
        DanhMucCon addDanhMucCon = danhMucConSrvImp.addDanhMucCon(danhMucCon,idCha);
        return ResponseEntity.status(HttpStatus.CREATED).body(addDanhMucCon);
    }

    @PutMapping("/modify/edit/editDanhMucCon/{id}")
    public ResponseEntity<DanhMucCon> editDanhMucCon (@PathVariable Long id, @RequestBody DanhMucCon danhMucCon, @RequestParam Long idCha ) throws ResourceNotFoundException {
        DanhMucCon editDanhMucCon = danhMucConSrvImp.editDanhMucCon(id, danhMucCon, idCha);
        return ResponseEntity.status(HttpStatus.OK).body(editDanhMucCon);
    }

    @DeleteMapping("/delete/deleteDanhMucCon/{id}")
    public ResponseEntity<DanhMucCon> deleteDanhMucCon (@PathVariable Long id) {
        danhMucConSrvImp.deleteDanhMucCon(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
}
