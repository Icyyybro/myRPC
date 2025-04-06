package org.wyc;

import lombok.Data;

/**
 * 响应
 */
@Data
public class Response {
    private int code = 0;
    private String message;
    private Object data;
}
