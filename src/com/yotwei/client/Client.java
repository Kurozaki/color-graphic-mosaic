package com.yotwei.client;

import com.yotwei.core.CMHandler;

/**
 * Created by YotWei on 2018/4/1.
 */
public class Client {

    public static void main(String[] args) throws Exception {
        CMHandler handler = new CMHandler();
        System.out.println("初始化完毕...");
//        handler.processOriginMosaicImages("avatars/", "process/");
        handler.handle("pics/death_note_l.jpg", "pics/death_note_l_out.jpg");
    }
}
