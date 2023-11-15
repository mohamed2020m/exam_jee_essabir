//package com.essabir.exam.entities;
//
//import jakarta.persistence.*;
//
//import java.util.Date;
//
//@Entity
//public class Test {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    private String name;
//    @Temporal(TemporalType.DATE)
//    private Date dateNaissance;
//
//    @Lob
//    @Column(length = 1000000)
//    private byte[] image;
//
//    public Test() {
//    }
//
//    public Test(String name, Date dateNaissance) {
//        this.name = name;
//        this.dateNaissance = dateNaissance;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Date getDateNaissance() {
//        return dateNaissance;
//    }
//
//    public void setDateNaissance(Date dateNaissance) {
//        this.dateNaissance = dateNaissance;
//    }
//
//    public byte[] getImage() {
//        return image;
//    }
//
//    public void setImage(byte[] image) {
//        this.image = image;
//    }
//}
