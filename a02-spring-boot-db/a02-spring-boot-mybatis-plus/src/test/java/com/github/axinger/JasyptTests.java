package com.github.axinger;

import com.github.axinger.util.EncrypDecryptUtil;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ObjectUtils;


public class JasyptTests {



    @Test
    public void tes1() {
        //加密
        System.out.println("username="+EncrypDecryptUtil.encypt("123", "root"));
        System.out.println("password="+EncrypDecryptUtil.encypt("123", "123456"));
    }

    @Test
    public void tes2() {
        //加密
        System.out.println("username="+EncrypDecryptUtil.decypt("123", "RWzRR1pJfaSLFjgjg2xqig=="));
        System.out.println("password="+EncrypDecryptUtil.decypt("123", "lcvkuMl1WkiShRZJ+Fzlhw=="));
    }
}
