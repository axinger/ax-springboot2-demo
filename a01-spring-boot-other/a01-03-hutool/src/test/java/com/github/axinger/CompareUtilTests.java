package com.github.axinger;

import cn.hutool.core.comparator.CompareUtil;
import cn.hutool.core.comparator.VersionComparator;
import org.junit.jupiter.api.Test;

public class CompareUtilTests {

    @Test
    void test1() {
        // 当isNullGreater为true时，null始终最大，此处返回的compare > 0
        int compare = CompareUtil.compare(null, "a", true);
        System.out.println("compare = " + compare);

        int compare1 = CompareUtil.compare(1, 2);
        System.out.println("compare1 = " + compare1);
    }

    @Test
    void test2() {
        // 当isNullGreater为false时，null始终最小，此处返回的compare < 0
        int compare = CompareUtil.compare(null, "a", false);
        System.out.println("compare = " + compare);
    }

    @Test
    void test3() {
        int compare2 = VersionComparator.INSTANCE.compare("1", "2");
        System.out.println("compare2 = " + compare2);

        // -1
        int compare = VersionComparator.INSTANCE.compare("1.12.1", "1.12.1c");

        System.out.println("compare = " + compare);

        int compare1 = VersionComparator.INSTANCE.compare("1.0.2", "1.0.10");
        System.out.println("compare1 = " + compare1);
    }
}
