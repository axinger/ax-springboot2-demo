package com.axing.common.dto;

public record PageDTO<T>(int pageNo, int pageSize, T data) {
}
