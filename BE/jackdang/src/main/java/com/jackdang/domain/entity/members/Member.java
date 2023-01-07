package com.jackdang.domain.entity.members;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jackdang.domain.entity.common.BaseEntity;
import com.jackdang.domain.entity.interests.Interest;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseEntity {
	
	@Id @GeneratedValue
	@Column(name = "member_id")
	private Long id;
	@Column(nullable = false, unique = true)
	private String phone;
	private String password;
	// @Column(nullable = false, unique = true)
	private String username; // 로그인 아이디
	private String membership; // 회원 등급
	private String nickname;
	// @Column(nullable = false)
	private int age;
	private String gender;
	private String profile_filed;
	private String introduce;
	private String address;
	private String job;
	private String school;
	private String mbti;
	private String love_status;
	private String religion;
	private boolean marketing_agree;
	
	@OneToMany(mappedBy = "member")
	private List<Interest> interests = new ArrayList<>();
	
	public List<String> getMembershipList(){
		if(this.membership.length() > 0) {
			return Arrays.asList(this.membership.split(","));
		}
		return new ArrayList<>();
	}
	
	@Builder
	public Member(
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

	public void update(String password, int age, String introduce, String address, String job, String school, String mbti, String love_status, String religion) {
		this.password = password;
		this.age = age;
		this.introduce = introduce;
		this.address = address;
		this.job = job;
		this.school = school;
		this.mbti = mbti;
		this.love_status = love_status;
		this.religion = religion;
		
	}


	
}
