package com.zxwl.web;

import com.zxwl.web.bean.po.GenericPo;
import com.zxwl.web.core.utils.RandomUtil;
import org.codehaus.groovy.runtime.powerassert.SourceText;

import java.util.Random;

/**
 * Project: zxwl-framework
 * Author: Sendya <18x@loacg.com>
 * Date: 2017/9/1 14:03
 */
public class Test {

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            System.out.println(RandomUtil.randomChar(7));
            System.out.println(GenericPo.createUID());
        }


/*        for (int i = 0; i < 9; i++) {//9代表循环九次，产生九个随机号码
            String number = "(0592)";//定义电话号码以 0592 开头
            Random random = new Random();//定义random，产生随机数
            for (int j = 0; j < 7; j++) {
                //生成0~9 随机数
                number += random.nextInt(9);
            }
            System.out.println(number);//输出一个电话号码
        }*/

    }
}
