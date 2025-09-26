package com.github.axinger.oos.dto;


@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class S3ObjectSummary {

    private String bucketName;

    private String objectName;

    private long size;

}
