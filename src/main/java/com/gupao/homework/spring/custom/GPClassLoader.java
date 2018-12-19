package com.gupao.homework.spring.custom;

import java.io.*;

/**
 * @author: DreamLee
 * @date: Created on 23:15 2018/11/10
 * @description: 代码生成，编译，重新动态load
 * @modified:
 */
public class GPClassLoader extends ClassLoader {

    private File baseDir;

    public GPClassLoader() {
        String basePath = GPClassLoader.class.getResource("").getPath();
        this.baseDir = new File(basePath);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String className = GPClassLoader.class.getPackage().getName() + "." + name;
        if (null != baseDir) {
            System.out.println(baseDir);
            System.out.println(name);
            File classFile = new File(baseDir, name.replaceAll("\\.", "/") + ".class");
            FileInputStream in = null;
            ByteArrayOutputStream out = null;
            if (classFile.exists()) {
                try {
                    in = new FileInputStream(classFile);
                    out = new ByteArrayOutputStream();
                    byte[] buff = new byte[1024];
                    int len;
                    while ((len = in.read(buff)) != -1) {
                        out.write(buff, 0, len);
                    }
                    return defineClass(className, out.toByteArray(), 0, out.size());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (null != in) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (null != out) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }
}
