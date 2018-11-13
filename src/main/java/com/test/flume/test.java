package com.test.flume;

import java.util.regex.Pattern;

public class test {
    public static void main(String[] args) {

        String data = "videoexpose&2018-11-07 00:33:53.081&6&&20607222223c70840aca4268a93b&10004401&27.56.65.197&6883295&4308459&&0&vivashow_b7b9d727f658450c86091199a0f7d66f&HOME_HOT&VIDEO_CARD";
        String[] aa = data.split("&");
        String uuid = aa[4];
        System.out.println(uuid);
        if (Pattern.compile("(?i)[a-z]").matcher(uuid).find()) {
            uuid = "12345667777777777777";
        }
        System.out.println(uuid);


    }
}