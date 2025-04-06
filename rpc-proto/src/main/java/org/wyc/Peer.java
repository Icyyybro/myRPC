package org.wyc;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 表示网络传输的一个端点
 */
@Data                   //生成方法toString
@AllArgsConstructor
public class Peer {
    private String host;
    private int port;
}
