package org.wyc.server;

import lombok.Data;
import org.wyc.codec.Decoder;
import org.wyc.codec.Encoder;
import org.wyc.codec.JSONDecoder;
import org.wyc.codec.JSONEnocder;
import org.wyc.transport.HTTPTransportServer;
import org.wyc.transport.TransportServer;

/**
 * server配置
 */
@Data
public class RpcServerConfig {
    private Class<? extends TransportServer> transportServiceClass = HTTPTransportServer.class;
    private Class<? extends Encoder> encoderClass = JSONEnocder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private int port = 3000;
}
