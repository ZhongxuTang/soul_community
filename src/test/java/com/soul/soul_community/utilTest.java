package com.soul.soul_community;

import com.soul.soul_community.util.StringUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

/**
 * @ClassName utilTest
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/3/5 14:42
 * @Version 1.0
 **/
@SpringBootTest
public class utilTest {

    @Test
    public void stringTest(){
        /*String f = "<p>栈存取速度比堆快而且数据可以共享，但栈中数据大小与生命周期必须确定\n" +
                "\n" +
                "对象创建的过程\n" +
                "我们在需要使用List集合的时候通常会出现下面的语句\n" +
                "\n" +
                "List list = new List();\n" +
                "\n" +
                "在这条语句左边是在栈区创建一个对象的引用，就相当于告诉计算机我需要一个List类型的变量，但是我没有告诉它这个变量我要用来干什么，它的大小是多少。这时计算机只是创建了一个List集合的引用，此时没有地址只有一个名字而已。\n" +
                "\n" +
                "语句右边执行的时候会字堆内存中开辟内存空间，然后由Lsit对象的引用指向该内存空间。\n" +
                "\n" +
                "所以对于List，通常在代码中会有两种出现方式，一种就是上面那种通过new关键字开辟内存实现，还有就是只是申请引用，然后将其他引用赋给list对象。\n" +
                "\n" +
                "由此可见，当如果只是需要从其他地方去接收一个对象的值的时候完全没有必要去实例化一个对象，只需要创建一个引用即可，既节省了内存也简化了代码。当一个对象（除基本类型）需要自己进行数据的读写一系列操作的时候就必须得实例化。</p>";

        System.out.println(StringUtil.formatHTML(f));*/

    }

    @Test
    public void fileTest(){
        File f = new File(new File("/img/userArticleImg").getAbsolutePath()+ "/" + "test");
        System.out.println(f);

    }
}
