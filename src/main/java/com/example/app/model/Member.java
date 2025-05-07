package com.example.app.model;

import jakarta.persistence.*;
import java.time.Instant;

//這個類別對應到資料庫的一張表
//table對應到資料庫中的members
@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主鍵自動+1
    private int member_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    //createed_by以及create_time不可變動設updatable
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "created_time", updatable = false)
    private Instant createdTime;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modified_time")
    private Instant modifiedTime;

    //無參數建構子，給jpa使用
    public Member() {}

    public Member(String name, String email, String phone, String createdBy) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.createdBy = createdBy;
    }

    //Getter和Setter
    public int getMemberId() {
        return member_id;
    }

    public void setMemberId(int member_id) {
        this.member_id = member_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    // 不寫setter , createby不能被修改
    public Instant getCreatedTime() {
        return createdTime;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Instant getModifiedTime() {
        return modifiedTime;
    }

    // instant time
    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

    // instant time
    public void setModifiedTime(Instant modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + member_id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdTime=" + createdTime +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", modifiedTime=" + modifiedTime +
                '}';
    }
}
