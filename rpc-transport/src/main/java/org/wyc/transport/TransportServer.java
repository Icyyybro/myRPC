package org.wyc.transport;

/**
 * 启动，监听
 * 接收请求
 * 关闭监听
 */
public interface TransportServer {
    void init(int port, RequestHandler handler);

    void start();

    void stop();
}
