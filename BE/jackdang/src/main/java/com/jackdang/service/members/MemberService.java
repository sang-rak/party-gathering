package com.jackdang.service.members;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jackdang.controller.members.dto.MemberDto;
import com.jackdang.domain.entity.members.Member;
import com.jackdang.domain.repository.members.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
	

	private final MemberRepository memberRepository;
	
	/*
	 *  회원 가입
	 */
	@Transactional
	public Long save(MemberDto memberDto) {
		validateDuplicateMember(memberDto); // 중복 회원 검증
		memberRepository.save(memberDto.toEntity());
		return memberDto.getId();
	}

	private void validateDuplicateMember(MemberDto memberDto) {
		List<Member> findMembers = memberRepository.findByPhone(memberDto.getPhone());
		
		if (!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}
	
	// 회원 전체 조회
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}
	
	// 회원 단건 조회
	public MemberDto findById(Long memberId) {
		Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 아이디가 없습니다"));
		return new MemberDto(member);
	}
	
	// 회원 단건 수정
	@Transactional
	public Long update(Long memberId, MemberDto memberDto) {
		Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 아이디가 없습니다"));
		member.update(memberDto.getPassword(), memberDto.getAge(), memberDto.getIntroduce(), memberDto.getAddress(), memberDto.getJob(), memberDto.getSchool(), memberDto.getMbti(), memberDto.getLove_status(), memberDto.getReligion());
		return memberId;
	};

	// 로그인
	public boolean login(String phone, String password){
		List<Member> findMembers = memberRepository.findByPhone(phone);
		if (findMembers.isEmpty()){
			return false;
		}
		if ( !password.equals(findMembers.get(0).getPassword()) ){
			return false;
		}
		return true;
	}
}