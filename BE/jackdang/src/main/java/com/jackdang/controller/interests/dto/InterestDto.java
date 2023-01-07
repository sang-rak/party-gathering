package com.jackdang.controller.interests.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.jackdang.controller.members.dto.MemberDto;
import com.jackdang.domain.entity.interests.Interest;
import com.jackdang.domain.entity.members.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InterestDto {

	@Column(name = "interest_id")
	private Long id;
	
	private String interestNm;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", insertable = false, updatable = false)
	private Member member;
	
	@Column(name = "member_id")
	private Long memberId;
	
	@Builder
	public InterestDto(
			Long id,
			String interestNm,
			Long memberId
			) {
		this.id = id;
		this.interestNm = interestNm;
		this.memberId = memberId;
	};
	
//	public InterestDto(Member member) {
//		this.member = member;
//	}
	/*
	 * 관심사 등록
	 */
	public Interest toEntity() {
		return Interest.builder()
				.id(id)
				.interestNm(interestNm)
				.memberId(memberId)
				.build();
	};
	/*
	 * 관심사 조회
	 */
	public InterestDto(Interest interest) {
		this.id = interest.getId();
		this.interestNm = interest.getInterestNm();
		this.memberId = interest.getMemberId();

	};
	
    @Data
    @AllArgsConstructor
    public static class Result<T> {
    	private int count;
    	private T data;
    }
    
    @Data
    @AllArgsConstructor
    public static class InterestDtojoin {
    	private String interestNm;
    }
}
