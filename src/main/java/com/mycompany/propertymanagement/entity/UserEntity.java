package com.mycompany.propertymanagement.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="USER_TABLE")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="NAME")
    private String ownerName;
    @Column(name="EMAIL")
    private String ownerEmail;
    @Column(name="PHONE")
    private String phone;
    @Column(name="PASSWORD")
    private String password;
}
