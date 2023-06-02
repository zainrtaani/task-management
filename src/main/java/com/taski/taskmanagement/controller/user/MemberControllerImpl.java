package com.taski.taskmanagement.controller.user;

import com.taski.taskmanagement.controller.util.UserUtil;
import com.taski.taskmanagement.entity.user.Member;
import com.taski.taskmanagement.entity.user.UserTypeE;
import com.taski.taskmanagement.exception.EntityNotFoundException;
import com.taski.taskmanagement.repo.MemberRepository;
import com.taski.taskmanagement.repo.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class MemberControllerImpl   {
    private final MemberRepository memberRepository;
    private final UserRepository userRepository;
    private final UserUtil userUtil;

    public MemberControllerImpl(MemberRepository memberRepository, UserRepository userRepository,
                                UserUtil userUtil) {
        this.memberRepository = memberRepository;
        this.userRepository = userRepository;
        this.userUtil = userUtil;
    }


    @PostMapping("/member/assign-team")
    public ResponseEntity<Member> assignMemberToTeam(@RequestBody Member memberDTO){
        Member member = memberRepository.findById(memberDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Not found"));
        member.setTeam(memberDTO.getTeam());
        member.setUserTypeE(UserTypeE.TEAM_MEMBER);
       return ResponseEntity.ok(userRepository.save(member));
    }



}
