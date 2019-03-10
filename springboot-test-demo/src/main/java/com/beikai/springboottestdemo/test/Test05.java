package com.beikai.springboottestdemo.test;

/**
 * @ClassName Test05
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/10 23:12
 * @Version 1.0
 *
 *   写一个函数，2 个参数，1 个字符串，1 个字节数，返回截取的字符串，要求字符串中的中文不能出现乱码：
 *    如（“我ABC”，4）应该截为“我AB”，输入（“我ABC 汉DEF”，6）应该输出为“我ABC”而不是“我ABC+汉的半个”
 **/
public class Test05 {

    public static void main(String[] args) {

        sub("2346韩ee",6);
    }

    public static void sub(String str,int index){

        // 遍历字符串,找到添加的位置
        char[] chars = str.toCharArray();
        // 创建 stringbuffer 存储数据
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < index; i++) {
            // 判断是否是 在 0 ~ 255 之间,如果在,说明是一个字符,如果不在,说明是一个汉字
            if (chars[i] >= 0 && chars[i] <= 255){
                stringBuffer.append(chars[i]);
            }else {
                // 判断当前索引是否是倒数第二个,如果是,并且最后一个字符是汉字的话没有影响,如果不是倒数第二个,则需要对原来的输入时选择的下标减1
                if (i != index -1){
                    stringBuffer.append(chars[i]);
                }
                index--;
            }
            
        }
        System.out.println(stringBuffer.toString());
    }
}
