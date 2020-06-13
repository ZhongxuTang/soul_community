package com.soul.soul_community;

import com.soul.soul_community.util.StringUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName StringUtilTest
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/4/4 22:04
 * @Version 1.0
 **/
@SpringBootTest
public class StringUtilTest {

    @Test
    public void stringTest(){
        int[] ss = StringUtil.getNumByString("[\"1\",\"2\"]");
        for (int s:ss
             ) {
            System.out.println(s);
        }

    }
}
