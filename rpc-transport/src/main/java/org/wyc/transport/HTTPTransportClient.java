package org.wyc.transport;

import org.apache.commons.io.IOUtils;
import org.wyc.Peer;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPTransportClient implements TransportClient {
    private String url;
    @Override
    public void connect(Peer peer) {
        this.url = "httP://" + peer.getHost() + ":" + peer.getPort();
    }

    @Override
    public InputStream write(InputStream data) {
        try {
            // 创建一个HTTP连接对象
            HttpURLConnection httpConn = (HttpURLConnection)new URL(url).openConnection();

            // 设置允许向服务器输出数据（POST请求需要）
            httpConn.setDoOutput(true);
            // 设置允许从服务器读取数据
            httpConn.setDoInput(true);
            // 设置请求方法为POST
            httpConn.setRequestMethod("POST");

            // 建立与服务器的实际连接
            httpConn.connect();

            // 将数据写入到连接的输出流中（发送请求体数据）
            // IOUtils.copy()是一个工具方法，用于将输入流复制到输出流
            IOUtils.copy(data, httpConn.getOutputStream());

            // 获取服务器的响应状态码
            int resultCode = httpConn.getResponseCode();

            // 判断响应状态码是否为200（HTTP_OK表示请求成功）
            if(resultCode == HttpURLConnection.HTTP_OK) {
                // 返回正常的输入流（用于读取服务器返回的成功数据）
                return httpConn.getInputStream();
            }
            else {
                // 返回错误输入流（用于读取服务器返回的错误信息）
                return httpConn.getErrorStream();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {

    }
}
