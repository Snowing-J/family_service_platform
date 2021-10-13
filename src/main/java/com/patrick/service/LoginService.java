package com.patrick.service;

import com.patrick.bean.TblUserRecord;
import com.patrick.mapper.TblUserRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private TblUserRecordMapper tblUserRecordMapper;

    public TblUserRecord login(String username, String password){
        return tblUserRecordMapper.login(username, password);
    }
}
