package com.jackdang.service.interests;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jackdang.controller.interests.dto.InterestDto;
import com.jackdang.domain.entity.interests.Interest;
import com.jackdang.domain.repository.interests.InterestRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InterestService {
	
	private final InterestRepository interestRepository;
	
	/*
	 *  관심사 등록
	 */
	@Transactional
	public Long save(InterestDto interestDto) {
		Long interestId = interestRepository.save(interestDto.toEntity()).getId();
		Interest interest = interestRepository.findById(interestId).orElseThrow(() -> new IllegalArgumentException("해당 관심사가 없습니다"));
		interest.update(interestDto.getInterestNm(), interestDto.getMemberId());
		return interestDto.getId(); 
	}
	 
	/*
	 *  관심사 삭제
	 */
	@Transactional
	public void delete(Long memberId, String interestNm) {
	    interestRepository.delete(
	            interestRepository.findByMember_idAndInterestNm(memberId, interestNm).orElseThrow(() -> new IllegalArgumentException("해당 관심사가 없습니다"))
	    );
	}
	

	// 관심사 전체 조회
	public List<Interest> findInterests() {
		return interestRepository.findAll();
	}
	
	// 관심사 회원 다건 조회
	public List<Interest> findByMember_id(Long memberId) {
		return interestRepository.findByMember_id(memberId);
	}
	

	
}
