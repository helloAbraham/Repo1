package com.websecure.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.websecure.dto.MemberInfoDTO;
import com.websecure.entity.MemberInfo;
import com.websecure.entity.MyUser;
import com.websecure.repository.MemberInfoRepository;
import com.websecure.repository.MyUserRepository;
import com.websecure.service.MemberInfoService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class MiController {
	
//	@Autowired
//	private MemberInfoRepository memberInfoRepo;
	
//	@PostMapping("/member/adding")
//	public MemberInfo addMemberInfo(@RequestBody MemberInfo memberInfo) {
//		return memberInfoRepo.save(memberInfo);
//	}
	
//	@GetMapping("/")
//	public String home() {
//		return "Welcome Home |^^| ";
//	}
//	//@CrossOrigin(origins = "http://localhost:8080") 
//	@GetMapping("/login")
//	public String handleLogin() {
//		return "custome_login";
//	}
	
	//Better way to call RestController using DTO
	@Autowired
	private MemberInfoService memberInfoService;
	
	@PostMapping("/api/members/save")
	public MemberInfoDTO saveMember(@RequestBody MemberInfoDTO memberInfoDTO) {
		return memberInfoService.createMember(memberInfoDTO);
	}
	
	/** below methods eventually will be after authenticated users use */
	
	@GetMapping("/api/members/all")
	public List<MemberInfoDTO> allMembers() {
		return memberInfoService.getAllMemberInfo();
	}
	
	@GetMapping("/api/members/{id}")
	public MemberInfoDTO getMemberById(@PathVariable Long id) {
		return memberInfoService.getMemberById(id);
	}
	
	@PutMapping("/api/members/update/{id}")
	public MemberInfoDTO updateMyMember(@PathVariable Long id, @RequestBody MemberInfoDTO memberInfoDTO) {
		return memberInfoService.updateMember(id, memberInfoDTO);
	}
	
	@DeleteMapping("/api/members/delete/{id}")
	public void deleteMember(@PathVariable Long id) {
		memberInfoService.deleteMember(id);
	}

}


