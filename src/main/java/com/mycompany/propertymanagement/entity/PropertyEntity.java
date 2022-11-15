package com.mycompany.propertymanagement.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="PROPERTY_TABLE")
@Getter
@Setter
@NoArgsConstructor
public class PropertyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //@Column(name="PROPERTY_TITLE",nullable = false)
    @Column(name="PROPERTY_TITLE")
    private String title;
    //@Column(name="PROPERTY_DESCRIPTION",nullable = false)
    @Column(name="PROPERTY_DESCRIPTION")
    private String description;
    //@Column(name="PROPERTY_PRICE",nullable = false)
    @Column(name="PROPERTY_PRICE")
    private Double price;
    //@Column(name="PROPERTY_ADDRESS",nullable = false)
    @Column(name="PROPERTY_ADDRESS")
    private String address;
    @ManyToOne(fetch = FetchType.LAZY)//it will not fetch the user data while fetch property data
    //@JoinColumn(name = "USER_ID",nullable = false)
    @JoinColumn(name = "USER_ID")
    private UserEntity userEntity;

}
