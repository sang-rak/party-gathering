package com.jackdang.controller.members;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.jackdang.common.auth.JwtAuthProvider;
import com.jackdang.controller.members.dto.AuthDto.AuthLoginRequest;
import com.jackdang.controller.members.dto.AuthDto.AuthLoginResponse;
import com.jackdang.controller.members.dto.MemberDto;
import com.jackdang.controller.members.dto.MemberDto.*;

import org.springframework.web.bind.annotation.*;

import com.jackdang.domain.entity.members.Member;
import com.jackdang.service.members.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
	private final JwtAuthProvider jwtAuthProvider;

    
    /*
     * 회원가입
     * param: phone, password, nickname, gender, age, marketing_agree
     * 
     */
    @PostMapping("/api/v1/members")
    public Long saveMemberV1(@RequestBody MemberDto memberDto) {
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


	/**
	 * 회원 로그인
	 */
	@PostMapping("/api/excludePath/login")
    public AuthLoginResponse loginMember(@RequestBody AuthLoginRequest request, HttpServletResponse response){

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime exp = now.plusDays(1L); // 만료기간 1일

		String status = "F";
		// 계정정보 존재 및 일치 여부 확인
		if ( memberService.login(request.getPhone(), request.getPassword()) ){
			String token = jwtAuthProvider.createToken(request, now, exp); // 토큰 생성
			response.setHeader("jwt-auth-token", token);
			status = "S";
		}
		return new AuthLoginResponse(request.getPhone(), status);
	}
}