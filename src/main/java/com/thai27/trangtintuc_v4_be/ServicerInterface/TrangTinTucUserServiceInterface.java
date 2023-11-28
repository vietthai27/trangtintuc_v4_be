package com.thai27.trangtintuc_v4_be.ServicerInterface;


import com.thai27.trangtintuc_v4_be.Entity.TrangTinTucUser;
import com.thai27.trangtintuc_v4_be.Exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TrangTinTucUserServiceInterface {

    String userSignup(TrangTinTucUser userInput);

    String login(TrangTinTucUser userData);

    String getUsernameByToken(String token);

    List<String> getRoleByUsername(String username);

    Page<TrangTinTucUser> getAllUser(Integer pageNum, Integer pageSize);

    TrangTinTucUser getUserById(Long id) throws ResourceNotFoundException;

    void setModerRole ( Long userId) throws ResourceNotFoundException;

    Page<TrangTinTucUser> findAllByUsername(String username, Integer pageNum, Integer pageSize);
    
    String resetPassword (String username, String email) throws ResourceNotFoundException;

    String changePassword (String username, String password) throws ResourceNotFoundException;

}
