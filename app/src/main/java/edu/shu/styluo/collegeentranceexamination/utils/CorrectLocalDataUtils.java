package edu.shu.styluo.collegeentranceexamination.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author: styluo
 * date: 2017/4/27 1:31
 * e-mail: shu_jiahuili@foxmail.com
 */

public class CorrectLocalDataUtils {

    /**
     * 处理学科代码数据库数据异常，部分数据缺0
     */
    public static String correctMajorId(String majorId){
        if(isContainsStr(majorId)){
            return majorId; //含有字母的majorId未出错
        }
        StringBuffer result = new StringBuffer(0);
        //如果只有5位，则说明缺了前导0
        if(majorId.length() == 5){
            return result.append(majorId).toString();
        }else{
            return majorId;
        }
    }

    /**
     * 正则判断是否存在字母
     * @param majorId
     * @return
     */
    public static boolean isContainsStr(String majorId) {
        String regex=".*[a-zA-Z]+.*";
        Matcher m= Pattern.compile(regex).matcher(majorId);
        return m.matches();
    }
}
