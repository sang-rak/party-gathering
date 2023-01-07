package com.jackdang.domain.repository.interests;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jackdang.domain.entity.interests.Interest;


public interface InterestRepository extends JpaRepository<Interest, Long> {

	// 멤버가 등록한 관심사 리스트
	List<Interest> findByMember_id(Long member_id);
	
	// 멤버가 등록한 관심사 단건
	Optional<Interest> findByMember_idAndInterestNm(Long memberId, String interest_nm);
}
