import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class MyCatClassLoader extends ClassLoader{
    public static void main(String [] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Object o =  new MyCatClassLoader().loadClass( Cat.class.getName()).newInstance();
        System.out.println("instanceof cat:" + ( o instanceof Cat));
        Cat cat = (Cat) new MyCatClassLoader().loadClass( Cat.class.getName()).newInstance();
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if(name.contains("Cat")){
            try {
                byte[] bytes = Files.readAllBytes(new File( "target/classes/Cat.class").toPath());
                return defineClass(name,bytes,0,bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
                throw new ClassNotFoundException("",e);
            }
        }else{
            return super.loadClass(name);
        }
    }

}
