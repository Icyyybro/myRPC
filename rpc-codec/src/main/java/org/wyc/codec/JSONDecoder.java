package org.wyc.codec;


import com.alibaba.fastjson.JSON;

/**
 * 基于JSON实现樊序列化接口
 */
public class JSONDecoder implements Decoder {
    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
