package com.medicine.medicine.service;


import com.medicine.medicine.domain.Role;
import com.medicine.medicine.domain.entity.MemberEntity;
import com.medicine.medicine.domain.repository.MemberRepository;
import com.medicine.medicine.dto.MediDto;
import com.medicine.medicine.dto.MemberDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MemberService{
    private MemberRepository memberRepository;

    @Transactional
    public Long joinUser(MemberDto memberDto) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

        return memberRepository.save(memberDto.toEntity()).getId();
    }

    public MemberDto findEmail(String userEmail){
        MemberEntity memberEntities = memberRepository.findByEmail(userEmail);
        MemberDto memberDto = new MemberDto();

        memberDto.setEmail(memberEntities.getEmail());
        memberDto.setPassword(memberEntities.getPassword());
        memberDto.setName(memberEntities.getName());
        memberDto.setId(memberEntities.getId());

        return memberDto;
    }

}
