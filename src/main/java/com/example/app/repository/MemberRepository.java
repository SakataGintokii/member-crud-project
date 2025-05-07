package com.example.app.repository;

import com.example.app.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//繼承 JpaRepository 後，可以自動擁有很多基本的 CRUD 方法（不用自己寫） findById findAll save deleteById
// 指定這個介面是 Repository，讓 Spring 容器可以自動掃描並管理
@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    // 關鍵字查詢
    @Query("SELECT m FROM Member m WHERE m.name LIKE %:keyword% OR m.email LIKE %:keyword% OR m.phone LIKE %:keyword%")
    //param 把 Java 的變數安全地帶進查詢裡，避免 SQL Injection
    List<Member> search(@Param("keyword") String keyword);
}
