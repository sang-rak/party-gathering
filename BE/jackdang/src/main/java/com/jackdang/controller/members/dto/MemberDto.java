package com.jackdang.controller.members.dto;

import javax.persistence.Column;

import com.jackdang.controller.members.MemberController;
import com.jackdang.domain.entity.members.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDto {
	@Column(name = "member_id")
	private Long id;
	@Column(nullable = false, unique = true)
	private String phone;
	private String password;
	// @Column(nullable = false, unique = true)
	private String nickname;
	private String username; // 로그인 아이디
	private String membership; // 회원 등급
	// @Column(nullable = false)
	private int age;
	private String gender;
	private String profile_filed;
	private String introduce;
	private String address;
	private boolean marketing_agree;
	private String job;
	private String school;
	private String mbti;
	private String love_status;
	private String religion;

	
	@Builder
	public MemberDto(
			Long id, 
			String phone, 
			String password, 
			String nickname, 
			int age, 
			String gender,
			String profile_filed,
			String introduce,
			String address,
			boolean marketing_agree
			) {
		this.id = id;
		this.phone = phone;
		this.password = password;
		this.nickname = nickname;
		this.age = age;
		this.gender = gender;
		this.profile_filed = profile_filed;
		this.introduce = introduce;
		this.address = address;
		this.marketing_agree = marketing_agree;
		
	}
	/*
	 * 회원 등록
	 */
	public Member toEntity() {
		return Member.builder()
				.id(id)
				.phone(phone)
				.password(password)
				.nickname(nickname)
				.age(age)
				.gender(gender)
				.profile_filed(profile_filed)
				.introduce(introduce)
				.address(address)
				.marketing_agree(marketing_agree)
				.build();
	}
	/*
	 * 회원 조회
	 */
	public MemberDto(Member member) {
		this.id = member.getId();
		this.phone = member.getPhone();
		this.password = member.getPassword();
		this.nickname = member.getNickname();
		this.age = member.getAge();
		this.gender = member.getGender();
		this.profile_filed = member.getProfile_filed();
		this.introduce = member.getIntroduce();
		this.address = member.getAddress();
		this.job = member.getJob();
		this.school = member.getSchool();
		this.mbti = member.getMbti();
		this.love_status = member.getLove_status();
		this.religion = member.getReligion();
	}
    @Data
    @AllArgsConstructor
    public static class Result<T> {
    	private int count;
    	private T data;
    }
    
    @Data
    @AllArgsConstructor
    public static class MemberDtojoin {
    	private String phone;
    }
    
    @Data
    public static class UpdateMemberRequest {
    	private String password;
    }
    
    @Data
    @AllArgsConstructor
    public static class UpdateMemberResponse {
    	private Long id;
    	private String password;
    }
    
    
    @Data
    public static class CreateMemberRequest {
    	private String phone;
    	private String password;
    	private String nickname;
    	private String gender;    	
    	private int age;
    	private boolean marketing_agree;
    }
    
    @Data
    public static class CreateMemberResponse{
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

	public void setPassword(String password) {
		// TODO Auto-generated method stub

	}
    
	public void setMembership(String string) {
		// TODO Auto-generated method stub

	}
}
