package com.metrodata.serverapp.entity;

import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    private long id;
    private String username;
    private String password;
    private boolean isAccountLocked;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Employee employee;

    @ManyToMany
    @JoinTable(
            name = "tb_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

}
