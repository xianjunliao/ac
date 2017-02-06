package com.ac.service.user.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ac.service.common.impl.CommonServiceImpl;
import com.ac.service.user.UserServise;
@Service
@Transactional
public class UserServiceImpl extends CommonServiceImpl implements UserServise{

}
