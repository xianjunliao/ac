package com.ac.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ac.service.CommonServiceImpl;
import com.ac.service.LoginService;
@Service
@Transactional
public class LoginServiceImpl extends CommonServiceImpl implements LoginService {

}
