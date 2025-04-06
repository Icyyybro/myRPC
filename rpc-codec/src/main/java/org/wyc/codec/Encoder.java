package org.wyc.codec;

/**
 * 序列化
 */
public interface Encoder {
    byte[] encode(byte[] bytes);
}
