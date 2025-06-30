package com.pig.rpc.dto;

import com.pig.rpc.enums.RpcRespStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Mr.Pan
 * @Date 2025/2/19
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RpcResp<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String reqId;
    private Integer code;
    private String msg;
    private T data;


    public static <T> RpcResp<T> success(String reqId, T data) {
        RpcResp<T> resp = new RpcResp<T>();
        resp.setReqId(reqId);
        resp.setCode(0);
        resp.setData(data);

        return resp;
    }

    public static <T> RpcResp<T> fail(String reqId, RpcRespStatus status) {
        RpcResp<T> resp = new RpcResp<T>();
        resp.setReqId(reqId);
        resp.setCode(status.getCode());
        resp.setMsg(status.getMsg());

        return resp;
    }

    public static <T> RpcResp<T> fail(String reqId, String msg) {
        RpcResp<T> resp = new RpcResp<T>();
        resp.setReqId(reqId);
        resp.setCode(RpcRespStatus.FAIL.getCode());
        resp.setMsg(msg);

        return resp;
    }
}
