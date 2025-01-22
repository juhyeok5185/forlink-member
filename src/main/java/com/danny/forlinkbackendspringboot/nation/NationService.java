package com.danny.forlinkbackendspringboot.nation;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NationService {
    private final NationReader nationReader;
    private final NationStore nationStore;
    private final ModelMapper modelMapper;

//    @Transactional
    public NationResponse save(NationRequest request) {
        Nation nation = nationStore.save(modelMapper.map(request, Nation.class));
        return modelMapper.map(nation, NationResponse.class);
    }

//    @Transactional
    public List<NationResponse> findAll() {
        return nationReader.findAll().stream()
                .map(item -> modelMapper.map(item, NationResponse.class))
                .toList();
    }

//    @Transactional
    public NationResponse findById(Integer nationId) {
        return modelMapper.map(nationReader.findById(nationId), NationResponse.class);
    }

//    @Transactional
    public NationResponse update(Integer nationId, NationRequest request) {
        Nation nation = nationStore.update(nationReader.findById(nationId), request);
        return modelMapper.map(nation, NationResponse.class);
    }

    public Integer delete(Integer nationId) {
        nationStore.delete(nationReader.findById(nationId));
        return nationId;
    }
}
