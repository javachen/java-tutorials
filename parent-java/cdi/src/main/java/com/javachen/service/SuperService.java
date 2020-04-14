package com.javachen.service;

import com.javachen.interceptor.Audited;

public class SuperService {
    @Audited
    public String deliverService(String uid) {
        return uid;
    }
}
