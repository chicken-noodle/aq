package com.example.aq;

import com.example.aq.Domain.UserInfo;

public class t {
    public static void main(String args[]) {
        UserInfo userInfo = new UserInfo();
        userInfo.setScore(2);
        userInfo.setAccount(2);
        System.out.println(userInfo.toString());
    }
}
