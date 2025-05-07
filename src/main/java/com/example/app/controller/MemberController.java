package com.example.app.controller;

import com.example.app.model.Member;
import com.example.app.service.MemberService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/members")  // 這是控制器的根路徑
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @PostMapping("/create") // 處理 POST 請求
    public Member create(@RequestBody Member member, @RequestParam String user) {
        return service.addMember(member, user);
    }

    @GetMapping("/getall") // 處理 GET 請求
    public List<Member> getAll() {
        return service.getAllMembers();
    }

    @GetMapping("/get/{id}") // 處理 GET 請求並使用路徑變數
    public Member getById(@PathVariable int id) {
        return service.getMemberById(id);
    }

    @PutMapping("/update/{id}") // 處理 PUT 請求
    public Member update(@PathVariable int id, @RequestBody Member member, @RequestParam String user) {
        return service.updateMember(id, member, user);
    }

    @DeleteMapping("/delete/{id}") // 處理 DELETE 請求
    public void delete(@PathVariable int id) {
        service.deleteMember(id);
    }

    @GetMapping("/search") // 處理 GET 請求
    public List<Member> search(@RequestParam String keyword) {
        return service.searchMembers(keyword);
    }
}
