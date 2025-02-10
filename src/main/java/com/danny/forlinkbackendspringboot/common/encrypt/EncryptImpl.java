package com.danny.forlinkbackendspringboot.common.encrypt;

import com.danny.forlinkbackendspringboot.common.util.AES256Utils;
import org.springframework.stereotype.Component;

@Component
public class EncryptImpl implements Encryptor{
    @Override
    public String encrypt(String data) {
        return AES256Utils.encrypt(data);
    }

    @Override
    public String decrypt(String data) {
        return AES256Utils.decrypt(data);
    }
}
