package cn.springmvc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * Created by Vincent on 2017/2/27.
 * Version 1.0.0
 * Description 资源文件读取工具
 */
public class SpringContextUtil implements ApplicationContextAware {

//    public static SpringContextUtil springContextUtil = new SpringContextUtil();

    private static final Logger log = LoggerFactory.getLogger(SpringContextUtil.class);
    private static ApplicationContext context = null;

//    public final staticc synchronized SpringContextUtil getInstance() {
//        return springContextUtil;
//    }

    public static SpringContextUtil getInstance() {
        log.info("initialized SpringContextUtil success. It will appear only once.");
        return Nested.springContextUtil;
    }
    // cause inner class will be loaded only once.so this is thread-safe.
    protected static class Nested {
        private static SpringContextUtil springContextUtil = new SpringContextUtil();
    }

    private SpringContextUtil() {
        super();
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    /**
     * 根据名称获取bean
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    /**
     * 根据bean名称获取指定类型bean
     * @param beanName bean名称
     * @param clazz 返回的bean类型,若类型不匹配,将抛出异常
     */
    public static <T> T getBean(String beanName, Class<T> clazz) {
        return context.getBean(beanName, clazz);
    }
    /**
     * 根据类型获取bean
     * @param clazz
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        T t = null;
        Map<String, T> map = context.getBeansOfType(clazz);
        for (Map.Entry<String, T> entry : map.entrySet()) {
            t = entry.getValue();
        }
        return t;
    }

    /**
     * 是否包含bean
     * @param beanName
     * @return
     */
    public static boolean containsBean(String beanName) {
        return context.containsBean(beanName);
    }

    /**
     * 是否是单例
     * @param beanName
     * @return
     */
    public static boolean isSingleton(String beanName) {
        return context.isSingleton(beanName);
    }

    /**
     * bean的类型
     * @param beanName
     * @return
     */
    public static Class getType(String beanName) {
        return context.getType(beanName);
    }

}

