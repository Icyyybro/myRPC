package org.wyc.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * 服务实例
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceInstance {
    private Object target;      //method方法所在的类的实例对象（单例）
    private Method method;      //一个方法
}
