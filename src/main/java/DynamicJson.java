import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Scanner;

public class DynamicJson {
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 通过命令行输入 动态加载 fastjson版本
        System.out.println("请输入你想使用的 fastjson版本");
        Scanner scanner = new Scanner(System.in);
        String version = scanner.next();
        System.out.println(version);
        File jar = new File("fastjson-" + version + ".jar");
        if(jar.isFile()){
            ClassLoader cl = new URLClassLoader(new URL[]{jar.toURI().toURL()});
            // com.alibaba.fastjson.JSON.toJSONString(Object)
            Class jsonClass = cl.loadClass("com.alibaba.fastjson.JSON");
            System.out.println( jsonClass.getMethod("toJSONString",Object.class).invoke(null,new ArrayList<>()));
        }else{
            System.out.println("jar not found!");
        }
    }
}
