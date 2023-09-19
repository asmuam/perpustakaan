/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.perpustakaan.service;

import com.polstat.perpustakaan.dto.BookDto;
import com.polstat.perpustakaan.dto.MemberDto;
import java.util.List;

/**
 *
 * @author asmua
 */
public interface MemberService {

    public MemberDto createMember(MemberDto memberDto);

    public List<MemberDto> getMembers();

    public MemberDto updateMember(MemberDto memberDto);

    public void deleteMember(MemberDto memberDto);

    public MemberDto getMember(Long id);
}
