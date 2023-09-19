/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.perpustakaan.service;

import com.polstat.perpustakaan.dto.MemberDto;
import com.polstat.perpustakaan.entity.Member;
import com.polstat.perpustakaan.mapper.MemberMapper;
import com.polstat.perpustakaan.repository.MemberRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author asmua
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public MemberDto createMember(MemberDto memberDto) {
        Member member = MemberMapper.mapToMember(memberDto);
        Member savedMember = memberRepository.save(member);
        return MemberMapper.mapToMemberDto(savedMember);
    }

    @Override
    public List<MemberDto> getMembers() {
        List<Member> members = (List<Member>) memberRepository.findAll();
        List<MemberDto> memberDtos = members.stream()
                .map((product) -> (MemberMapper.mapToMemberDto(product)))
                .collect(Collectors.toList());
        return memberDtos;
    }

    @Override
    public MemberDto updateMember(MemberDto memberDto) {
        Member existingMember = memberRepository.findById(memberDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        existingMember.setName(memberDto.getName());
        existingMember.setAddress(memberDto.getAddress());
        existingMember.setPhoneNumber(memberDto.getPhoneNumber());

        Member updatedMember = memberRepository.save(existingMember);
        return MemberMapper.mapToMemberDto(updatedMember);
    }

    @Override
    public void deleteMember(MemberDto memberDto) {
        Member memberToDelete = memberRepository.findById(memberDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        memberRepository.delete(memberToDelete);
    }

    @Override
    public MemberDto getMember(Long id) {
        Member member = memberRepository.getReferenceById(id);
        return MemberMapper.mapToMemberDto(member);
    }

}
