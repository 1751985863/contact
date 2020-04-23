package cn.nanfeng.util;

public class StringUtils {
    public static boolean compare(String str1,String str2){
        if (str1==null||"".equals(str1)){
            return false;
        }
        if (str2==null||"".equals(str2)){
            return false;
        }
        if (str2.equals(str1)){
            return true;
        }
        return false;
    }
}
