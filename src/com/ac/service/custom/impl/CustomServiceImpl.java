package com.ac.service.custom.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ac.service.common.impl.CommonServiceImpl;
import com.ac.service.custom.CustomService;

@Service
@Transactional
public class CustomServiceImpl extends CommonServiceImpl implements CustomService{

}
