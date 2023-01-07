package com.jackdang.controller.members;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.jackdang.controller.members.dto.MemberDto;
import com.jackdang.controller.members.dto.MemberDto.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.jackdang.domain.entity.members.Member;
import com.jackdang.domain.repository.members.MemberRepository;
import com.jackdang.service.members.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    /*
     * 회원가입
     * param: phone, password, nickname, gender, age, marketing_agree
     * 
     */
    @PostMapping("/api/v1/members")
    public Long saveMemberV1(@RequestBody MemberDto memberDto) {
    	//memberDto.setPassword(bCryptPasswordEncoder.encode(memberDto.getPassword()));
    	return memberService.save(memberDto);
    }
    
    /**
     * 회원 조회
     * 회원 기본정보 조회
     */
    @GetMapping("/api/v1/member/{memberid}")
    public MemberDto findById(@PathVariable Long memberid) {
    	return memberService.findById(memberid);
    }
    
    
    /**
     * 전체 회원 조회
     */
    @GetMapping("/api/v1/members")
    public Result memberV1() {
    	List<Member> findMembers = memberService.findMembers();
    	List<MemberDtojoin> collect = findMembers.stream()
    			.map(m -> new MemberDtojoin(m.getPhone()))
    			.collect(Collectors.toList());
    	
    	return new Result(collect.size(), collect);
    }
    /**
     * 회원 삭제
     * 회원가입한 고객이 인증번호를 제시못할경우 삭제
     */
    
    /**
     * 회원 내용 수정 및 비밀번호 수정
     */
    @PutMapping("/api/v1/member/{memberid}") 
    public Long update(@PathVariable Long memberid, @RequestBody MemberDto memberDto){
    	return memberService.update(memberid, memberDto);
    }


}
