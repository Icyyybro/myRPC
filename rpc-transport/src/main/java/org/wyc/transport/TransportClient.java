package org.wyc.transport;

import org.wyc.Peer;

import java.io.InputStream;

/**
 * 创建连接
 * 发送数据并等待响应
 * 关闭连接
 */
public interface TransportClient {
    void connect(Peer peer);

    InputStream write(InputStream data);

    void close();
}
