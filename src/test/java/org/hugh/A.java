package org.hugh;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author adward
 * @date 2023/11/18 16:54
 */
class A {
    enum BB {
        // add 1
        OPEN("1"),
        CLOSE("2");
        private final String code;
        BB(String code){
            this.code = code;
        }
        public String getCode(){
            return code;
        }

    }


    public void aaa(){
        System.out.println(BB.OPEN.getCode());
        System.out.println(BB.CLOSE.getCode());
    }

    /**
     * 给定url地址获取资源的大小（以字节为单位）
     * @param urlStr
     * @return
     * @throws IOException
     */
    public static long getResourceLength(String urlStr) throws IOException {
        URL url=new URL(urlStr);
        URLConnection urlConnection=url.openConnection();
        urlConnection.connect();
        //返回响应报文头字段Content-Length的值
        return urlConnection.getContentLength();
    }


    public static void main(String[] args) {
        new A().aaa();
        Integer ii = 10;
        System.out.println(ii.toString());
        System.out.println();
    }



}
