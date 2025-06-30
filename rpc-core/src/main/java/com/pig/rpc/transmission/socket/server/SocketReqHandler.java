package com.pig.rpc.transmission.socket.server;

import com.pig.rpc.dto.RpcReq;
import com.pig.rpc.dto.RpcResp;
import com.pig.rpc.handler.RpcReqHandler;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @Author Mr.Pan
 * @Date 2025/2/21
 **/
@Slf4j
@AllArgsConstructor
public class SocketReqHandler implements Runnable { // 开启一个线程去socket里面读请求
    private final Socket socket;
    private final RpcReqHandler rpcReqHandler;

    @SneakyThrows
    @Override
    public void run() {
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        RpcReq rpcReq = (RpcReq) inputStream.readObject();

        Object data = rpcReqHandler.invoke(rpcReq);

        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        RpcResp<?> rpcResp = RpcResp.success(rpcReq.getReqId(), data);
        outputStream.writeObject(rpcResp);
        outputStream.flush();
    }
}
