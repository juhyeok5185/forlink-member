package com.danny.forlinkbackendspringboot.common.encrypt;

public interface Encryptor {
    String encrypt(String data);
    String decrypt(String data);
}
