package com.websecure.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websecure.dto.MemberInfoDTO;
import com.websecure.entity.MemberInfo;
import com.websecure.repository.MemberInfoRepository;

@Service
public class MemberInfoService {
	
	@Autowired
	private MemberInfoRepository memberInfoRepo;
	
	//Let's convert Entity to DTO
	
	private MemberInfoDTO convertToDTO(MemberInfo memberInfo) {
		return new MemberInfoDTO(memberInfo.getId(),
				memberInfo.getFirstName(),
				memberInfo.getLastName(),
				memberInfo.getAddress(),
				memberInfo.getCity(),
				memberInfo.getState(),
				memberInfo.getZipCode(),
				memberInfo.getEmail(),
				memberInfo.getPhone());
				
	}
	
	//Convert DTO to Entity
	private MemberInfo convertToEntity(MemberInfoDTO memberInfoDTO) {
		MemberInfo member = new MemberInfo();
		member.setId(memberInfoDTO.getId());
		member.setFirstName(memberInfoDTO.getFirstName());
		member.setLastName(memberInfoDTO.getLastName());
		member.setAddress(memberInfoDTO.getAddress());
		member.setCity(memberInfoDTO.getCity());
		member.setState(memberInfoDTO.getState());
		member.setZipCode(memberInfoDTO.getZipCode());
		member.setEmail(memberInfoDTO.getEmail());
		member.setPhone(memberInfoDTO.getPhone());
		
		return member;
	}
	
	//First let's create Member
	public MemberInfoDTO createMember(MemberInfoDTO memberInfoDTO) {
		MemberInfo memberinfo = convertToEntity(memberInfoDTO);
		MemberInfo savedMember = memberInfoRepo.save(memberinfo);
		return convertToDTO(savedMember);
	}
	
	//Get all Members 
	public List<MemberInfoDTO> getAllMemberInfo(){
		List<MemberInfo> members = memberInfoRepo.findAll();
		return members.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}
	
	//Get MemberInfo by id
	public MemberInfoDTO getMemberById(Long id) {
		MemberInfo mem = memberInfoRepo.findById(id).orElse(null);
		return mem != null ? convertToDTO(mem) : null;
	}
	
	//let's say member mistake their info, needs to be updated
	public MemberInfoDTO updateMember(Long id, MemberInfoDTO memberInfoDTO) {
		MemberInfo updateMem = memberInfoRepo.findById(id).orElse(null);
		if(updateMem != null) {
			updateMem.setFirstName(memberInfoDTO.getFirstName());
			updateMem.setLastName(memberInfoDTO.getLastName());
			updateMem.setAddress(memberInfoDTO.getAddress());
			updateMem.setCity(memberInfoDTO.getCity());
			updateMem.setState(memberInfoDTO.getState());
			updateMem.setZipCode(memberInfoDTO.getZipCode());
			updateMem.setEmail(memberInfoDTO.getEmail());
			updateMem.setPhone(memberInfoDTO.getPhone());
			
			MemberInfo updatedMember = memberInfoRepo.save(updateMem);
			return convertToDTO(updatedMember);
		}
		return null;
	}
	
	//If we need to delete any member
	public void deleteMember(Long id) {
		memberInfoRepo.deleteById(id);
	}

}
