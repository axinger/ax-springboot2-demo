package com.ax.master;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JasyptTest {

    @Autowired
    private StringEncryptor encryptor;

    @Test
    public void test1() {
        System.out.println("encryptor = " + encryptor);
    }


    // 加密
    @Test
    public void getPass() {
        // 可见对同同样的信息，每次加密的结果还不一样，更加增长了可靠性。
        String name = encryptor.encrypt("root");
        String password = encryptor.encrypt("abc123456");
        System.out.println("加密name = " + name); // SUCMHuOLUZ2wDDV1c0Q8eamPQ4VlclDF4coj3XFWUfArZr4uIrTNBxLQ2OZSDYRS==
        System.out.println(password);  // gjpoJSSVktBDrEhOpprt1QYrvm8dMjUK6hf87gdNkrY9kGzs0QUFoUY/045cjHEkJz2NhNi
    }

    // 解密
    @Test
    public void passDecrypt() {
        String username = encryptor.decrypt("hifVDwDPW5MNYa0tAabvJQ==");
        String password = encryptor.decrypt("5mTXmB5IYPxqiSuD/045cjHEkJz2NhNi");
        System.out.println(username + "--" + password);
    }

}
