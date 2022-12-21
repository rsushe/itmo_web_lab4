package com.rsushe.weblab4.audited;

import com.rsushe.weblab4.entity.AuditedCallback;
import com.rsushe.weblab4.repository.AuditedRepository;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class AuditedBeanPostProcessor implements BeanPostProcessor {

    private final Map<Class<?>, Set<Method>> classesWithAuditedMethods;

    private final AuditedRepository auditedRepository;

    @Autowired
    public AuditedBeanPostProcessor(AuditedRepository auditedRepository) {
        this.auditedRepository = auditedRepository;
        classesWithAuditedMethods = new HashMap<>();
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        Method[] methods = beanClass.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Audited.class)) {
                classesWithAuditedMethods.putIfAbsent(beanClass, new HashSet<>());
                classesWithAuditedMethods.get(beanClass).add(method);
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();

        if (classesWithAuditedMethods.containsKey(beanClass)) {
            Set<Method> methodsWithAuditedAnnotation = classesWithAuditedMethods.get(beanClass);

            System.out.println("Going to create proxy on bean " + beanName);

            Enhancer enhancer = new Enhancer();

            enhancer.setSuperclass(beanClass);
            enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
                if (methodsWithAuditedAnnotation.contains(method)) {
                    System.out.println("Proxy on method " + method.getName());
                    AuditedCallback auditedCallback = AuditedCallback
                            .builder()
                            .methodName(method.getName())
                            .callbackTime(LocalDateTime.now())
                            .build();

                    auditedRepository.save(auditedCallback);
                }
                return proxy.invokeSuper(obj, args);
            });

            Field[] fields = beanClass.getDeclaredFields();

            Class<?>[] argumentClasses = new Class[fields.length];
            Object[] arguments = new Object[fields.length];

            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);

                try {
                    Object argument = field.get(bean);

                    arguments[i] = argument;
                    argumentClasses[i] = field.getType();
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

            }

            System.out.println(beanName);
            System.out.println(Arrays.toString(argumentClasses));
            System.out.println(Arrays.toString(arguments));

            bean = enhancer.create(argumentClasses, arguments);
        }

        return bean;
    }
}
