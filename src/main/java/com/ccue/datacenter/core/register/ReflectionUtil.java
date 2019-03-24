package com.ccue.datacenter.core.register;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReflectionUtil {

    private static SubTypesScanner subTypesScanner = new SubTypesScanner();

    private static MethodAnnotationsScanner methodAnnotationsScanner = new MethodAnnotationsScanner();

    private static ConfigurationBuilder builder = new ConfigurationBuilder()
            .setUrls(ClasspathHelper.forPackage(""))
            .setScanners(subTypesScanner, methodAnnotationsScanner);

    private static Reflections reflections = new Reflections(builder);

    /**
     * 获取带有注解的方法
     *
     * @param anno
     * @return
     */
    public static Set<Method> getMethodsWithAnnotation(Class<? extends Annotation> anno) {
        Set<Method> methods = reflections.getMethodsAnnotatedWith(anno);
        return methods;
    }

    /**
     * 获取集合类中带有特定注解的方法
     *
     * @param classList
     * @param anno
     * @return
     */
    public static Set<Method> getMethodsWithAnnotaion(List<Class<?>> classList, Class<? extends Annotation> anno) {
        Set<Method> methods = new HashSet<>();
        for (Class clazz : classList) {
            Set<Method> clazzMethods = getClassMethodsWithAnnotation(clazz, anno);
            methods.addAll(clazzMethods);
        }
        return methods;
    }

    /**
     * 获取一个类带有注解的所有方法
     *
     * @param clazz
     * @param anno
     * @return
     */
    public static Set<Method> getClassMethodsWithAnnotation(Class clazz, Class<? extends Annotation> anno) {
        Set<Method> methodSet = new HashSet<>();
        if (clazz != null && anno != null) {
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(anno)) {
                    methodSet.add(method);
                }
            }
        }
        return methodSet;
    }


    /**
     * 获取带有注解的类
     *
     * @param anno
     * @return
     */
    public static Set<Class<?>> getClassesWithAnnotation(Class<? extends Annotation> anno) {
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(anno);
        return classes;
    }


}
