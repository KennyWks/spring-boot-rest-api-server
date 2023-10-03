package com.metrodata.serverapp.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
