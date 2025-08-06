package com.github.axinger;

import cn.hutool.core.comparator.VersionComparator;
import org.junit.jupiter.api.Test;

public class VersionTests {

    /**
     * 返回值 < 0：表示调用对象小于参数对象
     * 返回值 = 0：表示调用对象等于参数对象
     * 返回值 > 0：表示调用对象大于参数对象
     */
    @Test
    void test1() {

        // -1
        int compare = VersionComparator.INSTANCE.compare("1.12.1", "1.12.1c");
        System.out.println("compare = " + compare);

        int compare1 = VersionComparator.INSTANCE.compare("1.0.2", "1.0.10");
        System.out.println("compare1 = " + compare1);

        // 1<2
        int compare2 = VersionComparator.INSTANCE.compare("1", "2");
        System.out.println("compare2 = " + compare2);


        int compare3 = VersionComparator.INSTANCE.compare("1.1.0", "1.11.0");
        System.out.println("compare3 = " + compare3);

        // 1=1 => 0
        int compare4 = VersionComparator.INSTANCE.compare("1.0", "1");
        System.out.println("compare4 = " + compare4);

        // 2>1  => 1
        int compare5 = VersionComparator.INSTANCE.compare("2", "1");
        System.out.println("compare5 = " + compare5);
    }
}
