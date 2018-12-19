package com.gupao.homework.spring.custom;

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
 * @date: Created on 23:14 2018/11/10
 * @description: 生成代理对象的代码
 * @modified:
 */
public class GPProxy {

    private static String ln = "\r\n";
    private static String lt = "\t";

    public static Object newProxyInstance(GPClassLoader loader, Class<?>[] interfaces, GPInvocationHandler handler) {
        //1.生成源代码
        String proxySrc = generateSrc(interfaces[0]);

        //2.将生成的源代码输出到磁盘，保存为.java文件
        String filePath = GPProxy.class.getResource("").getPath();
        System.out.println(filePath);
//        File file = new File("F:\\project\\gupaowork\\src\\main\\java\\com\\gupao\\homework\\spring\\custom\\" + "$Proxy0.java");
        File file = new File(filePath + "$Proxy0.java");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(proxySrc);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //3.编译源代码，并且生成.class文件
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> iterable = manager.getJavaFileObjects(file);
        JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, iterable);
        task.call();
        try {
            manager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //4.将class文件中的内容，动态加载到jvm中来
        //5.返回被代理后的代理对象
        try {
            Class<?> proxyClass = loader.findClass("$Proxy0");
            Constructor<?> c = proxyClass.getConstructor(GPInvocationHandler.class);
            Object o = c.newInstance(handler);
            return o;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String generateSrc(Class<?> interfaces) {
        StringBuffer src = new StringBuffer();
        src.append("package com.gupao.homework.spring.custom;" + ln);
        src.append("import java.lang.reflect.Method;" + ln);
        src.append("import " + interfaces.getName() + ";" + ln);
        src.append("public class $Proxy0 implements " + interfaces.getSimpleName() + "{" + ln);

        src.append(lt + "GPInvocationHandler h;" + ln);

        src.append(lt + "public $Proxy0(GPInvocationHandler h) {" + ln);
        src.append(lt + "this.h = h;" + ln);
        src.append(lt + "}" + ln);

        for (Method method : interfaces.getMethods()) {
            src.append(lt + "public " + method.getReturnType().getName() + " " + method.getName() + "() {" + ln);
            src.append(lt + lt + "try {" + ln);
            src.append(lt + lt + lt + "Method m = " + interfaces.getSimpleName() + ".class.getMethod(\""
                    + method.getName() + "\",new Class[]{});" + ln);
            src.append(lt + lt + lt + "this.h.invoke(this,m,null);" + ln);
            src.append(lt + lt +"} catch (Throwable throwable) {" + ln);
            src.append(lt + lt + lt + "throwable.printStackTrace();" + ln);
            src.append(lt + lt + "}" + ln);

            src.append(lt + "}" + ln);
        }
        src.append("}");
        return src.toString();
    }

}
