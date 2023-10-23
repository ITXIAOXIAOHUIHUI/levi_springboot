package com.springboot.levi.leviweb1.design.shuangqingweipai;

import java.io.IOException;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2023-08-30 18:51
 */
public class CustomClassLoaderDemo extends ClassLoader {

    public static void main(String[] args) {

        // 创建一个自定义类加载器实例
        CustomClassLoader customClassLoader = new CustomClassLoader();

        try {
            // 使用自定义类加载器加载 MyClass 类
            Class<?> myClass = customClassLoader.loadClass("MyClass");
            // 输出类信息
            System.out.println("Loaded class: " + myClass.getName());
            System.out.println("Class loader: " + myClass.getClassLoader());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
    }

}
class  CustomClassLoader extends  ClassLoader{

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        // 自定义加载 MyClass 类，其余类委派给父加载器加载
        if ("MyClass".equals(name)) {
            try {
                byte[] classData = loadClassData(name);
                return defineClass(name, classData, 0, classData.length);
            } catch (Exception e) {
                throw new ClassNotFoundException(name, e);
            }
        }
        return super.loadClass(name);
    }

    private byte[] loadClassData(String className) throws IOException {
        // 根据类名加载字节码数据
        // 这里需要根据实际情况实现加载逻辑
        // 返回的是类的字节码数据
        return null;
    }


}


