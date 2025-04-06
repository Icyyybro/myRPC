package org.wyc.server;

import lombok.extern.slf4j.Slf4j;
import org.wyc.Request;
import org.wyc.ServiceDescriptor;
import org.wyc.Utils.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理RPC暴露的服务
 */
@Slf4j
public class ServiceManager {
    private Map<ServiceDescriptor, ServiceInstance> service;

    public ServiceManager() {
        this.service = new ConcurrentHashMap<>();
    }

    /**
     * 服务的注册
     * @param interfaceClass 接口的Class
     * @param bean 实现interface接口的类的对象（单例）
     * @param <T>
     */
    public <T> void register(Class<T> interfaceClass, T bean) {
        // 将接口中的所有方法扫描出来，和bean绑定起来，然后放到service这个哈希表中
        Method[] methods = ReflectionUtils.getPublicMenthods(interfaceClass);
        for (Method method : methods) {
            ServiceInstance sis = new ServiceInstance(bean, method);
            ServiceDescriptor sdp = ServiceDescriptor.from(interfaceClass, method);
            service.put(sdp, sis);
            log.info("register service: {} {}" + sdp.getClazz(), sdp.getMethod());
        }
    }

    public ServiceInstance lookup(Request request) {
        ServiceDescriptor sdp = request.getService();
        return service.get(sdp);
    }
}
