package com.thai27.trangtintuc_v4_be.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "trangtintuc_user")
@Data
public class TrangTinTucUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @ManyToMany(targetEntity = Role.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Role> roles;

}
