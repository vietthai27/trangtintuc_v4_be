package com.thai27.trangtintuc_v4_be.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "role")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role_name")
    private String rolename;

    @ManyToMany(targetEntity = TrangTinTucUser.class, mappedBy = "roles", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TrangTinTucUser> trangTinTucUsers;
}