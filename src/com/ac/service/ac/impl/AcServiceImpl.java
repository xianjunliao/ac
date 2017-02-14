package com.ac.service.ac.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ac.entity.AcLogEntity;
import com.ac.service.ac.AcService;
import com.ac.service.common.impl.CommonServiceImpl;
@Service
@Transactional
public class AcServiceImpl extends CommonServiceImpl implements AcService {

	@Override
	public void afreshAcLog(Integer userId) {
		deleteBySql("delete from ac_log where user_id=? and ac_date=str_to_date(?,'%Y-%m-%d')", userId,new Date());
		updateBySql("update USER_AC_TEMP set ac_status=0 where  user_id=?",userId);
	}

	@Override
	public void addAcLog(List<AcLogEntity> list, Integer[] ids, Integer userId) {
		batchSave(list);
		updateBySqlInIds("update USER_AC_TEMP set ac_status=1 where temp_id in(:ids) and user_id="+userId, ids);;
	}

}
