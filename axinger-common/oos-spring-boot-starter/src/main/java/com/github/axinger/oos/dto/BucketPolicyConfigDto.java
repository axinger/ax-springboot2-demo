package com.github.axinger.oos.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Bucket访问策略配置
 */
@Data
@EqualsAndHashCode
@Builder
public class BucketPolicyConfigDto {
    private String ID;
    private String Version;
    private List<Statement> Statement;

    @Data
    @EqualsAndHashCode
    @Builder
    public static class Statement {
        private String Effect;
        private Principal Principal;
        private String[] Action;
        private String[] Resource;

    }

    @Data
    @EqualsAndHashCode
    @Builder
    public static class Principal{
        private String[] AWS;
    }
}
