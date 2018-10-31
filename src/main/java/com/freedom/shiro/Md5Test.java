package com.freedom.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

/**
 * @Auther: freedom
 * @Date: 2018/10/30
 * @Description:
 */
@Component
public class Md5Test {

    /**
     * 加盐参数
     */
    public final static String hashAlgorithmName = "MD5";

    /**
     * 循环次数
     */
    public final static int hashIterations = 1024;

    /**
     * shiro密码加密工具类
     *
     * @param credentials 密码
     * @param salt 密码盐
     * @return
     */
    public static String md5(char[] credentials, ByteSource salt) {

        return new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations).toString();
    }


    public static void main(String[] args) {
        String algorithmName = "MD5";     //加密方式
        String credentials ="123";        //原始密码
        int hashIterations = 1024;        //加密次数
        ByteSource salt = ByteSource.Util.bytes("ccc");    //盐值
        System.out.println("salt:" + salt);
        Object result = new SimpleHash(algorithmName, credentials, salt, hashIterations);
        System.out.println("result:" + result);
    }
}
