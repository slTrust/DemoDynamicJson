import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class MyBadClassLoader extends ClassLoader{
    public static void main(String [] args) throws ClassNotFoundException {
        // 尝试强制加载本地文件 java.lang.Object.class 来搞破坏
        new MyBadClassLoader().loadClass("java.lang.Object");
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        try {
            byte[] bytes = Files.readAllBytes(new File(name + ".class").toPath());
            return defineClass(name,bytes,0,bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ClassNotFoundException("",e);
        }
    }
}
