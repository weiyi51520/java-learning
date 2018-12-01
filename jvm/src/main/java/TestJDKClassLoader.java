/**
 * @author Yale.Wei
 * @date 2018/11/29 18:06
 */
public class TestJDKClassLoader {

    public static void main(String[] args) {
        //启动类加载器 Bootstrap Classloader 负责加载JRE目录lib下的核心类库，如JRE目标下的rt.jar,charsets.jar...
        System.out.println(String.class.getClassLoader());
        //拓展类加载器 Extension ClassLoader 负责加载JRE扩展目录lib下ext中的jar类包
        System.out.println(com.sun.crypto.provider.DESKeyFactory.class.getClassLoader().getClass().getName());
        //系统类加载器 Application ClassLoader 负责加载ClassPath路径下的类 即我们平时写的工程目录
        System.out.println(TestJDKClassLoader.class.getClassLoader().getClass().getName());
        System.out.println(ClassLoader.getSystemClassLoader().getClass().getName());
    }

}
