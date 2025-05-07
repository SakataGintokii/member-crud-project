package com.example.app.controller;

import com.example.app.model.Member;
import com.example.app.service.MemberService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    // 處理POST請求新增member
    @PostMapping("/create")
    public Member create(@RequestBody Member member, @RequestParam String user) {
        return service.addMember(member, user);
    }

    // 處理GET請求查詢全部資料
    @GetMapping("/getall")
    public List<Member> getAll() {
        return service.getAllMembers();
    }

    // 處理GET請求並查詢id
    @GetMapping("/get/{id}")
    public Member getById(@PathVariable int id) {
        return service.getMemberById(id);
    }

    // 處理PUT請求並更新id資訊
    @PutMapping("/update/{id}")
    public Member update(@PathVariable int id, @RequestBody Member member, @RequestParam String user) {
        return service.updateMember(id, member, user);
    }

    // 處理DELETE請求刪除id資訊
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        service.deleteMember(id);
    }

    // 處理GET請求進行關鍵字查詢
    @GetMapping("/search")
    public List<Member> search(@RequestParam String keyword) {
        return service.searchMembers(keyword);
    }
}
