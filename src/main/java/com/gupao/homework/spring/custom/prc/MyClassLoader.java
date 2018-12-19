package com.gupao.homework.spring.custom.prc;

import java.io.*;

/**
 * @author: DreamLee
 * @date: Created on 20:06 2018/11/11
 * @description:
 * @modified:
 */
public class MyClassLoader extends ClassLoader {

    private File baseDir;

    public MyClassLoader() {
        String path = MyClassLoader.class.getResource("").getPath();
        baseDir = new File(path);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String className = MyClassLoader.class.getPackage().getName() + "." + name;
        if (null != baseDir) {
            File file = new File(baseDir, name +".class");
            FileInputStream in = null;
            ByteArrayOutputStream out = null;
            if (file.exists()) {
                try {
                    in = new FileInputStream(file);
                    out = new ByteArrayOutputStream();
                    int len = 0;
                    byte[] buff = new byte[1024];
                    while ((len = in.read(buff)) != -1) {
                        out.write(buff, 0, len);
                    }
                    Class<?> clazz = defineClass(className, out.toByteArray(), 0, out.size());
                    return clazz;
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
                    file.delete();
                }
            }
        }
        return null;
    }

}
