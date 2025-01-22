package com.danny.forlinkbackendspringboot.common.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)  // 필드 이름 매칭 활성화
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE); // private 필드 접근 활성화
        return modelMapper;
    }
}
