package com.danny.forlinkbackendspringboot.nation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NationStore {
    private final NationRepository nationRepository;


    public Nation save(Nation nation) {
        return nationRepository.save(nation);
    }

    public Nation update(Nation nation , NationRequest request) {
        nation.update(request);
        return nationRepository.save(nation);
    }

    public Integer delete(Nation nation) {
        nationRepository.delete(nation);
        return nation.getNotionId();
    }
}
