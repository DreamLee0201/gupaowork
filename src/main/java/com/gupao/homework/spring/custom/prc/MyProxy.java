package com.gupao.homework.spring.custom.prc;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author: DreamLee
 * @date: Created on 20:05 2018/11/11
 * @description:
 * @modified:
 */
public class MyProxy {

    private static String ln = "\n";
    private static String lt = "\t";

    public static Object newProxyInstance(MyClassLoader loader, Class<?>[] interfaces, MyInvocationHandler h)
            throws IllegalArgumentException{
        //1.生成代理类字符串
        String src = generateProxyStr(interfaces[0]);
        //2.将字符串输出到.java文件中
        File javaFile = generateJavaFile(src);
        //3.将文件编译成.class文件
        compileJavaToClass(javaFile);
        //4.将class文件加载到jvm，并返回代理对象
        javaFile.delete();
        Object o = generateProxyObj(loader, h);
        return o;
    }

    private static String generateProxyStr(Class<?> intf) {
        StringBuffer sb = new StringBuffer();
        Package pkg = intf.getPackage();
        String name = intf.getName();
        String simpleName = intf.getSimpleName();
        Method[] methods = intf.getMethods();
        sb.append(pkg + ";" + ln + ln);
        sb.append("import java.lang.reflect.Method;" + ln);
        sb.append("import " + name + ";" + ln + ln);
        sb.append("public class $Proxy0 implements " + simpleName + "{" + ln);
        sb.append(lt + "MyInvocationHandler h;" + ln);
        sb.append(lt + "public $Proxy0(MyInvocationHandler h) {" + ln);
        sb.append(lt + lt + "this.h = h;" + ln);
        sb.append(lt + "}" + ln);
        for (Method m : methods) {
            sb.append(lt + "public void " + m.getName() + "() {" + ln);
            sb.append(lt + lt + "try {" + ln);
            sb.append(lt + lt + lt + "Method m = " + simpleName + ".class.getMethod(\"findLove\",new Class[]{});" + ln);
            sb.append(lt + lt + lt + "this.h.invoke(this,m,null);" + ln);
            sb.append(lt + lt + "} catch (Throwable throwable) {" + ln);
            sb.append(lt + lt + lt + "throwable.printStackTrace();" + ln);
            sb.append(lt + lt + "}" + ln);
            sb.append(lt + "}" + ln);
        }
        sb.append("}");

        return sb.toString();
    }

    private static File generateJavaFile(String src) {
        File file = null;
        try {
            String path = MyProxy.class.getResource("").getPath();
            System.out.println(path);
            file = new File(path + "$Proxy0.java");
            FileWriter writer = new FileWriter(file);
            writer.write(src);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private static void compileJavaToClass(File file) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager manager = compiler.getStandardFileManager(
                null, null, null);
        Iterable<? extends JavaFileObject> iterable = manager.getJavaFileObjects(file);
        JavaCompiler.CompilationTask task = compiler.getTask(
                null, manager, null, null, null, iterable);
        task.call();
        try {
            manager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Object generateProxyObj(MyClassLoader loader, MyInvocationHandler h) {
        Object o = null;
        try {
            Class<?> clazz = loader.findClass("$Proxy0");
            Constructor<?> c = clazz.getConstructor(MyInvocationHandler.class);
            o = c.newInstance(h);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

}
