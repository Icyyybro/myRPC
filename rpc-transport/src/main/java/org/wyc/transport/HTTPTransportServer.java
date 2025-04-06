package org.wyc.transport;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class HTTPTransportServer implements TransportServer {
    private RequestHandler handler;
    private Server server;

    @Override
    public void init(int port, RequestHandler handler) {
        this.handler = handler;
        // 创建一个Jetty服务器实例，监听指定的端口
        this.server = new Server(port);

        // servlet 接收请求
        // 创建一个Servlet上下文处理器，用于处理HTTP请求
        ServletContextHandler ctx = new ServletContextHandler();
        server.setHandler(ctx);
        // 创建一个ServletHolder，用于处理接收的数据（调用handler实现逻辑）
        ServletHolder holder = new ServletHolder(new RequestServlet());
        // 将ServletHolder添加到Servlet上下文处理器中，并映射到根路径"/*"（即处理所有请求）
        ctx.addServlet(holder, "/*");
    }

    @Override
    public void start() {
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 因为我们要处理post请求，所以这里重写一下doPost方法
     */
    class RequestServlet extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            log.info("client connected");
            // 获取请求的输入流（读取客户端发送的数据）
            InputStream in = req.getInputStream();
            // 获取响应的输出流（向客户端返回数据）
            OutputStream out = resp.getOutputStream();
            // 如果存在外部处理器（handler），将输入流和输出流传给它处理
            if(handler != null) {
                handler.onRequest(in, out);
            }
            // 强制刷新输出流，确保数据发送到客户端
            out.flush();
        }
    }
}
