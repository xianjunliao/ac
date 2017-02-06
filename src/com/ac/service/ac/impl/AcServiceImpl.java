package com.ac.service.ac.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ac.service.ac.AcService;
import com.ac.service.common.impl.CommonServiceImpl;
@Service
@Transactional
public class AcServiceImpl extends CommonServiceImpl implements AcService {

}
