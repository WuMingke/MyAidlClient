package com.example.myaidlserver; // 必须

import com.example.myaidlserver.User; // 必须

interface IMyAidlInterface{
    String getName();
    User getUserName(String data);
}