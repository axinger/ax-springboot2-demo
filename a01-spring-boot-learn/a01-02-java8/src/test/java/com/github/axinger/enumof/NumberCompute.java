package com.github.axinger.enumof;

interface Compute {
    int compute(int a, int b);
}
/// 可以用作策略模式
public enum NumberCompute implements Compute {

    ADD {
        @Override
        public int compute(int a, int b) {
            return a + b;
        }
    },

    MULTIPLY {
        @Override
        public int compute(int a, int b) {
            return a * b;
        }
    }

}

