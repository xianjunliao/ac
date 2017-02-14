package com.ac.service.ac;

import java.util.List;

import com.ac.entity.AcLogEntity;
import com.ac.service.common.CommonService;

public interface AcService extends CommonService {
	
      void afreshAcLog(Integer userId);
      void addAcLog(List<AcLogEntity> list,Integer[] ids,Integer userId);
}
