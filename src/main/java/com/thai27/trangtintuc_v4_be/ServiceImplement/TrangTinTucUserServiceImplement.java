package com.thai27.trangtintuc_v4_be.ServiceImplement;


import com.thai27.trangtintuc_v4_be.Entity.Role;
import com.thai27.trangtintuc_v4_be.Entity.TrangTinTucUser;
import com.thai27.trangtintuc_v4_be.Exception.ResourceNotFoundException;
import com.thai27.trangtintuc_v4_be.Repository.RoleRepo;
import com.thai27.trangtintuc_v4_be.Repository.TrangTinTucUserRepo;
import com.thai27.trangtintuc_v4_be.Security.JWTAuthenProvider;
import com.thai27.trangtintuc_v4_be.Security.JWTUltil;
import com.thai27.trangtintuc_v4_be.ServicerInterface.TrangTinTucUserServiceInterface;
import com.thai27.trangtintuc_v4_be.Util.GenerateRandomString;
import com.thai27.trangtintuc_v4_be.Util.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrangTinTucUserServiceImplement implements TrangTinTucUserServiceInterface {

    @Autowired
    JWTUltil jwtUtil;

    @Autowired
    JWTAuthenProvider jwtAuth;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    TrangTinTucUserRepo trangTinTucUserRepo;

    @Autowired
    RoleRepo roleRepo;
    
    @Autowired
    GenerateRandomString randomString;

    @Autowired
    SendEmail sendEmail;


    @Override
    public String userSignup(TrangTinTucUser userInput) {
        if (trangTinTucUserRepo.findByUsername(userInput.getUsername()).isEmpty()) {
            TrangTinTucUser userData = new TrangTinTucUser();
            userData.setUsername(userInput.getUsername());
            userData.setPassword(encoder.encode(userInput.getPassword()));
            userData.setEmail(userInput.getEmail());
            userData.setRoles(roleRepo.findByRolename("USER"));
            trangTinTucUserRepo.save(userData);
            return "Thêm người dùng " + userInput.getUsername() + " thành công !!!";
        } else return "Tên người dùng " + userInput.getUsername() + " đã tồn tại !!!";
    }

    @Override
    public String login(TrangTinTucUser userData) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userData.getUsername(), userData.getPassword());
        jwtAuth.authenticate(token);
        String jwtToken = jwtUtil.generate(userData.getUsername());
        return jwtToken;
    }

    @Override
    public String getUsernameByToken(String token) {
        return jwtUtil.getUsername(token);
    }

    @Override
    public List<String> getRoleByUsername(String username) {
        return roleRepo.findRoleByUsername(username);
    }

    @Override
    public Page<TrangTinTucUser> getAllUser(Integer pageNum, Integer pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        return trangTinTucUserRepo.findAll(pageRequest);
    }

    @Override
    public TrangTinTucUser getUserById(Long id) throws ResourceNotFoundException {
        return trangTinTucUserRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không có người dùng với id:" + id));
    }

    @Override
    public void setModerRole( Long userId) throws ResourceNotFoundException {
        if(!trangTinTucUserRepo.existsById(userId)){
            throw new ResourceNotFoundException("Không có người dùng với id:" + userId);
        }
        else roleRepo.setModerRole(userId);
    }

    @Override
    public Page<TrangTinTucUser> findAllByUsername(String username, Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        String likeUsername = "%"+username+"%";
        return trangTinTucUserRepo.findAllByUsernameLikeIgnoreCase(likeUsername, pageRequest);
    }

	@Override
	public String resetPassword(String username, String email) throws ResourceNotFoundException{
		TrangTinTucUser resetUser = trangTinTucUserRepo.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Tên người dùng không tồn tại trong hệ thống: " + username));
		if(email.equals(resetUser.getEmail())) {
            String newPassword = randomString.generateRandomCode(5);
			String newEncodedPassword =encoder.encode(newPassword);
			resetUser.setPassword(newEncodedPassword);
            trangTinTucUserRepo.save(resetUser);
            sendEmail.sendNewPassword(resetUser.getEmail(),resetUser.getUsername(),newPassword);
            return "Đổi mật khẩu thành công, vui lòng check emal để biết mật khẩu mới";
		} else throw new RuntimeException("Email người dùng nhập không khớp với email đã đăng ký");
	}

    @Override
    public String changePassword(String token, String password) throws ResourceNotFoundException {
        String username = jwtUtil.getUsername(token);
        TrangTinTucUser changePassUser = trangTinTucUserRepo.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Tên người dùng không tồn tại trong hệ thống: " + username));
        changePassUser.setPassword(encoder.encode(password));
        trangTinTucUserRepo.save(changePassUser);
        return "Đổi mật khẩu thành công vui lòng đăng nhập lại";
    }
}
