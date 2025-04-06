package org.wyc;

import lombok.Data;

/**
 * 请求
 */
@Data
public class Request {
    private ServiceDescriptor service;      // 需要请求的服务（远程方法）
    private Object[] parameters;            // 参数

}
