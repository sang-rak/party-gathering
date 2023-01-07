package com.jackdang.controller.interests;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jackdang.controller.interests.dto.InterestDto;
import com.jackdang.controller.interests.dto.InterestDto.InterestDtojoin;
import com.jackdang.controller.members.dto.MemberDto;
import com.jackdang.controller.members.dto.MemberDto.MemberDtojoin;
import com.jackdang.controller.members.dto.MemberDto.Result;
import com.jackdang.domain.entity.interests.Interest;
import com.jackdang.domain.entity.members.Member;
import com.jackdang.service.interests.InterestService;
import com.jackdang.service.members.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InterestController {
	private final InterestService interestService;
	
    /**
     * 관심사등록
     * param: interest_nm 관심사 이름, member_id 회원ID
     * 
     */
    @PostMapping("/api/v1/interest")
    public Long saveInterestV1(@RequestBody InterestDto interestDto) {
    	return interestService.save(interestDto);
    }
    
    /**
     * 관심사삭제
     * param: interest_nm 관심사 이름, member_id 회원ID
     */
    @DeleteMapping("/api/v1/interest/{memberid}/{interestNm}")
    public String deleteInterestV1(@PathVariable Long memberid, @PathVariable String interestNm) {
    	interestService.delete(memberid, interestNm);
    	return "redirect:/";
    }

    /**
     * 회원 관심사 이름 전체 조회
     * param: interest_nm 관심사 이름
     */
    @GetMapping("/api/v1/interest/{memberid}")
    public Result findById(@PathVariable Long memberid) {
    	List<Interest> findMemberInterest = interestService.findByMember_id(memberid);
    	List<InterestDtojoin> collect = findMemberInterest.stream()
    			.map(m -> new InterestDtojoin(m.getInterestNm()))
				.collect(Collectors.toList());
    	return new Result(collect.size(), collect);
    }

    /**
     * 전체 관심사 조회
     */
    
//    @GetMapping("/api/v1/interests")
//    public Result interestV1() {
//    	List<Interest> findMembers = interestService.findMembers();
//    	List<InterestDtojoin> collect = findMembers.stream()
//    			.map(m -> new MemberDtojoin(m.getPhone()))
//    			.collect(Collectors.toList());
//    	
//    	return new Result(collect.size(), collect);
//    }
}
