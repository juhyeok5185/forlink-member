package com.danny.forlinkbackendspringboot.nation;

import com.danny.forlinkbackendspringboot.common.config.MyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NationReader {

    private final NationRepository nationRepository;


    public List<Nation> findAll() {
        return nationRepository.findAll();
    }

    public Nation findById(Integer nationId) {
        return nationRepository.findById(nationId)
                .orElseThrow(
                        () -> new MyException("국가를 찾을 수 없습니다.")
                );
    }
}
