package com.metrodata.serverapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "tb_region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

}
