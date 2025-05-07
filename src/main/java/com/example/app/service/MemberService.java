package com.example.app.service;

import com.example.app.model.Member;
import com.example.app.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository repo;

    // 建構子，注入 repository
    public MemberService(MemberRepository repo) {
        this.repo = repo;
    }

    //新增成員
    public Member addMember(Member member, String user) {
        // 重新new一個member，把createdBy帶進去，這樣就不用寫setCreatedBy了
        Member newMember = new Member(member.getName(), member.getEmail(), member.getPhone(), user);

        //UTC+8
        Instant now = Instant.now();
        ZonedDateTime taiwanTime = now.atZone(ZoneOffset.ofHours(8));

        newMember.setCreatedTime(taiwanTime.toInstant());
        newMember.setModifiedTime(taiwanTime.toInstant());
        newMember.setModifiedBy(user);

        return repo.save(newMember);
    }

    //更新成員
    public Member updateMember(int id, Member newData, String user) {
        Member member = repo.findById(id).orElse(null);

        if (member == null) {
            throw new RuntimeException("Member not found");
        }

        member.setName(newData.getName());
        member.setEmail(newData.getEmail());
        member.setPhone(newData.getPhone());
        member.setModifiedBy(user);

        ZonedDateTime taiwanTime = Instant.now().atZone(ZoneOffset.ofHours(8));
        member.setModifiedTime(taiwanTime.toInstant());

        return repo.save(member);
    }

    // 刪除會員
    public void deleteMember(int id) {
        repo.deleteById(id);
    }

    // 查詢所有會員
    public List<Member> getAllMembers() {
        return repo.findAll();
    }

    // 用id查詢單一會員
    public Member getMemberById(int id) {
        return repo.findById(id).orElse(null);
    }

    // 搜尋會員
    public List<Member> searchMembers(String keyword) {
        return repo.search(keyword);
    }

}
