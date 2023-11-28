package com.thai27.trangtintuc_v4_be.Controller;



import com.thai27.trangtintuc_v4_be.Entity.TrangTinTucUser;
import com.thai27.trangtintuc_v4_be.Exception.ResourceNotFoundException;
import com.thai27.trangtintuc_v4_be.Repository.TrangTinTucUserRepo;
import com.thai27.trangtintuc_v4_be.Security.JWTAuthenProvider;
import com.thai27.trangtintuc_v4_be.Security.JWTUltil;
import com.thai27.trangtintuc_v4_be.ServiceImplement.TrangTinTucUserServiceImplement;
import com.thai27.trangtintuc_v4_be.ServiceImplement.UserDetailServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class TrangTinTucUserController {

    @Autowired
    JWTUltil jwtUtil;

    @Autowired
    JWTAuthenProvider jwtAuth;

    @Autowired
    UserDetailServiceImplement userSrvImp;

    @Autowired
    TrangTinTucUserServiceImplement trangTinTucUserServiceImplement;

    @Autowired
    TrangTinTucUserRepo trangTinTucUserRepo;

    @PostMapping("/permit/login")
    public String login(@RequestBody TrangTinTucUser userData) {
        return trangTinTucUserServiceImplement.login(userData);
    }

    @PostMapping("/permit/userSignup")
    public String userSignup(@RequestBody TrangTinTucUser userData) {
        return trangTinTucUserServiceImplement.userSignup(userData);
    }

    @PostMapping("/permit/getUsernameByToken")
    public String getUsernameByToken(@RequestParam String token) {
        return trangTinTucUserServiceImplement.getUsernameByToken(token);
    }

    @GetMapping("/permit/resetPassword")
    public String resetPassword (@RequestParam String email,@RequestParam String username)throws ResourceNotFoundException {
        return trangTinTucUserServiceImplement.resetPassword(username, email);
    }

    @GetMapping("/auth/changePassword")
    public String changePassword (@RequestParam String token,@RequestParam String password)throws ResourceNotFoundException {
        return trangTinTucUserServiceImplement.changePassword(token, password);
    }

    @PostMapping("/permit/getRoleByUsername")
    public List<String> getRoleByUsername(@RequestParam String username) {
        return trangTinTucUserServiceImplement.getRoleByUsername(username);
    }

    @GetMapping("/auth/getAllUser")
    public Page<TrangTinTucUser> getAllUser (@RequestParam Integer pageNum, @RequestParam Integer pageSize ) {
        return trangTinTucUserServiceImplement.getAllUser(pageNum,pageSize);
    }

    @GetMapping("/auth/searchAllUser")
    public Page<TrangTinTucUser> searchAllUser (@RequestParam String search ,@RequestParam Integer pageNum, @RequestParam Integer pageSize ) {
        return trangTinTucUserServiceImplement.findAllByUsername(search,pageNum,pageSize);
    }

    @GetMapping("/auth/getUserById/{userId}")
    public TrangTinTucUser getUserById(@PathVariable Long userId) throws ResourceNotFoundException {
        return trangTinTucUserServiceImplement.getUserById(userId);
    }

    @PutMapping("/auth/setModerRole/{userId}")
    public void setModerRole (@PathVariable Long userId) throws ResourceNotFoundException {
        trangTinTucUserServiceImplement.setModerRole(userId);
    }

    @DeleteMapping("/auth/deleteUserById/{userId}")
    public void deleteUserById (@PathVariable Long userId) {
        trangTinTucUserRepo.deleteUserRole(userId);
        trangTinTucUserRepo.deleteUser(userId);
    }

    @DeleteMapping("/auth/deleteUserModer/{userId}")
    public void deleteUserModer (@PathVariable Long userId) {
        trangTinTucUserRepo.deleteUserRoleModer(userId);
    }

}
