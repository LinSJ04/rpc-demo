package com.pig.rpc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @Author Mr.Pan
 * @Date 2025/2/19
 **/
@Getter
@ToString
@AllArgsConstructor
public enum RpcRespStatus {
    SUCCESS(0, "success"),
    FAIL(9999, "fail"),
    ;

    private final int code;
    private final String msg;

    public static boolean isSuccessful(Integer code) {
        return SUCCESS.getCode() == code;
    }


    public static boolean isFailed(Integer code) {
        return !isSuccessful(code);
    }
}
