package org.wyc.codec;
import com.alibaba.fastjson.JSON;

/**
 * 基于json的序列化实现
 */
public class JSONEnocder implements Encoder{
    @Override
    public byte[] encode(byte[] bytes) {
        return JSON.toJSONBytes(bytes);
    }
}
