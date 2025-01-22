package com.danny.forlinkbackendspringboot;

import com.danny.forlinkbackendspringboot.common.util.AES256Utils;
import com.danny.forlinkbackendspringboot.member.Member;
import com.danny.forlinkbackendspringboot.member.MemberRepository;
import com.danny.forlinkbackendspringboot.nation.Nation;
import com.danny.forlinkbackendspringboot.nation.NationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ForlinkBackendSpringbootApplicationTests {

//    @Autowired
//    private NationRepository nationRepository;
//    @Autowired
//    private MemberRepository memberRepository;

//    @Test
//    void nationSaveTest() {
//        Nation nation = Nation.builder()
//                .code("kr")
//                .name("대한민국")
//                .build();
//        nationRepository.save(nation);
//    }


//    @Test
//    void memberTest(){
//        Nation nation = nationRepository.findById(1).orElse(null);
//        Member member = Member.builder()
//                .nation(nation)
//                .loginId("admin")
//                .password("1234")
//                .name("관리자")
//                .build();
//        memberRepository.save(member);
//    }

//    @Test
//    void querydslTest(){
//        Member member = memberRepository.findMemberById(1L);
//        System.out.println(member.getName());
//        System.out.println(member.getLoginId());
//    }


    @Test
    void contextLoads() {
        System.out.println(AES256Utils.decrypt("FLZis2s3cqgb2iOSkVOnnQ=="));
        System.out.println(AES256Utils.decrypt("rRtYR3S8XpfUM+HPXxtFyQ=="));
        System.out.println(AES256Utils.decrypt("ttYGeJjlwv8riV6NDzbikg=="));
        System.out.println(AES256Utils.decrypt("MbvIwRiXsZMRv5gEgJ2Nwg=="));
        System.out.println(AES256Utils.decrypt("2FtEI2CmsrDY3LidfWms9w=="));
    }
}
