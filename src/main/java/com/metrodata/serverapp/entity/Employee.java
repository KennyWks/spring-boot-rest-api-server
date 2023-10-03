package com.metrodata.serverapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "tb_employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;

    @OneToOne(mappedBy = "employee")
    @PrimaryKeyJoinColumn
    private User user;

}
