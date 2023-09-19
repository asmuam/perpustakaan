/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.perpustakaan.mapper;

import com.polstat.perpustakaan.dto.MemberDto;
import com.polstat.perpustakaan.entity.Member;

/**
 *
 * @author asmua
 */
public class MemberMapper {

    public static MemberDto mapToMemberDto(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .memberID(member.getMemberID())
                .name(member.getName())
                .address(member.getAddress())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }

    public static Member mapToMember(MemberDto memberDto) {
        return Member.builder()
                .id(memberDto.getId())
                .memberID(memberDto.getMemberID())
                .name(memberDto.getName())
                .address(memberDto.getAddress())
                .phoneNumber(memberDto.getPhoneNumber())
                .build();
    }
}
