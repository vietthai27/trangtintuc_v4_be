package com.thai27.trangtintuc_v4_be.Controller;

import com.thai27.trangtintuc_v4_be.DTO.BaiBaoDetail;
import com.thai27.trangtintuc_v4_be.DTO.DanhMucBaiBaoEdit;
import com.thai27.trangtintuc_v4_be.Entity.BaiBao;
import com.thai27.trangtintuc_v4_be.Exception.ResourceNotFoundException;
import com.thai27.trangtintuc_v4_be.ServiceImplement.BaiBaoSrvImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/baibao")
public class BaiBaoController {

    @Autowired
    BaiBaoSrvImp baiBaoSrvImp;

    @GetMapping("/get/test")
    public void test() {

        ArrayList<String> listdata = new ArrayList<String>();
        ArrayList<String> listdata1 = new ArrayList<String>();
        ArrayList<String> listdata2 = new ArrayList<String>();
        ArrayList<String> listdata3 = new ArrayList<String>();
        ArrayList<String> listdata4 = new ArrayList<String>();

        for (int i = 1; i < 17; i++) {
            listdata.add("Num " + i);
        }

        Long inst1 = System.nanoTime();

        int i =0;
        while(i < listdata.size()) {
            listdata1.add(listdata.get(i));
            i++;
            listdata2.add(listdata.get(i));
            i++;
            listdata3.add(listdata.get(i));
            i++;
            listdata4.add(listdata.get(i));
            i++;
        }

//        for (int i = 0; i < 16; i++) {
//            if((i+1 +4) %4 == 1) {
//                listdata1.add(listdata.get(i));
//            }
//            else if((i+1 +4) %4 == 2) {
//                listdata2.add(listdata.get(i));
//            }
//            if((i+1 +4) %4 == 3) {
//                listdata3.add(listdata.get(i));
//            }
//            if((i+1 +4) %4 == 0) {
//                listdata4.add(listdata.get(i));
//            }
//
//        }

        Long inst2 = System.nanoTime();;




        System.out.println("_______");
        System.out.println("_______");
        System.out.println("_______" + listdata);
        System.out.println("_______");
        System.out.println("_______" + listdata1);
        System.out.println("_______" + listdata2);
        System.out.println("_______" + listdata3);
        System.out.println("_______" + listdata4);
        System.out.println("_______Elapsed Time: "+ (inst2 - inst1));
    }

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
