package com.ac.controller.ac;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ac.base.BaseController;
import com.ac.entity.ACTempEntity;
import com.ac.entity.AcLogEntity;
import com.ac.entity.UserAcTempEntity;
import com.ac.entity.UserEntity;
import com.ac.service.ac.AcService;

@Controller
@RequestMapping()
public class AcController extends BaseController {

	@Resource
	public AcService acService;

	@RequestMapping("/accounting")
	public String accounting(HttpServletRequest request) {
		UserEntity sysUser = getSysUser(request);
		if (sysUser == null) {
			return "redirect:/";
		} else {

			commonMapping("ac", acService, request);
			return "ac/ac";
		}
	}

	@RequestMapping("/queryac")
	public String toDayAc(HttpServletRequest request) {
		UserEntity sysUser = getSysUser(request);
		if (sysUser == null) {
			return "redirect:/";
		} else {

			// commonMapping("ac", acService, request);
			return "ac/audit";
		}

	}

	@RequestMapping("/changeac")
	public String changeac(HttpServletRequest request) {
		UserEntity sysUser = getSysUser(request);
		if (sysUser == null) {
			return "redirect:/";
		} else {

			List<UserAcTempEntity> userAcTemps = acService.findListByHql("from UserAcTempEntity where userId=? and acStatus=0", sysUser.getId());
			List<ACTempEntity> tempEntities = new ArrayList<>();
			double out = acService
					.findUniqueBySql(
							"select ifnull(sum(ac_amount),0) from ac_log where user_id=? and ac_type=0 and ac_date=str_to_date(?,'%Y-%m-%d')",
							sysUser.getId(), new Date());// 今天消费
			double in = acService
					.findUniqueBySql(
							"select ifnull(sum(ac_amount),0) from ac_log where user_id=? and ac_type=1 and ac_date=str_to_date(?,'%Y-%m-%d')",
							sysUser.getId(), new Date());// 今天收入
			// select * from ac_log where
			// date_format(ac_date,'%Y-%m')=date_format(DATE_SUB(curdate(),
			// INTERVAL 1 MONTH),'%Y-%m') ;
			// select * from ac_log where
			// date_format(ac_date,'%Y-%m')=date_format(now(),'%Y-%m') ;
			// SELECT * FROM ac_log WHERE
			// YEARWEEK(date_format(ac_date,'%Y-%m-%d')) = YEARWEEK(now())-1;
			// SELECT * FROM ac_log WHERE
			// YEARWEEK(date_format(ac_date,'%Y-%m-%d')) = YEARWEEK(now());
			request.setAttribute("out", out);
			request.setAttribute("in", in);
			for (UserAcTempEntity userAcTempEntity : userAcTemps) {

				ACTempEntity acTempEntity = acService.findUniqueByProperty(
						ACTempEntity.class, "id", userAcTempEntity.getTempId());
				tempEntities.add(acTempEntity);
			}
			request.setAttribute("tempEntities", tempEntities);
			return "ac/accounting";
		}

	}

	@RequestMapping("/addAcLog")
	@ResponseBody
	public int changeAc(HttpServletRequest request) {
		try {
			UserEntity sysUser = getSysUser(request);
			
			String ds = request.getParameter("ds");
			JSONArray json = JSONArray.fromObject(ds);
			Integer[] ids=new Integer[json.size()];
			List<AcLogEntity> acLogEntities = new ArrayList<>();

			for (int i = 0; i < json.size(); i++) {
				AcLogEntity acLogEntity = new AcLogEntity();
				Object object = json.get(i);
				JSONObject jsonObject = JSONObject.fromObject(object);
				String acCode = (String) jsonObject.get("acCode");
				String acName = (String) jsonObject.get("acName");
				String acType = (String) jsonObject.get("acType");
				String tempId = (String) jsonObject.get("tempId");
				String acAmount = (String) jsonObject.get("acAmount");

				acLogEntity.setAcAmount(Double.valueOf(acAmount));
				acLogEntity.setAcCode(acCode);
				acLogEntity.setAcName(acName);
				acLogEntity.setAcType(acType.equals("0")?0:1);
				acLogEntity.setUserId(sysUser.getId());
				acLogEntity.setAcDate(new Date());
				acLogEntity.setAcTime(new Date());
				int parseInt = Integer.parseInt(tempId);
				ids[i]=parseInt;
				acLogEntity.setTempId(parseInt);
				acLogEntities.add(acLogEntity);
			}
			if(ids.length>0){
			acService.batchSave(acLogEntities);
			acService.updateBySqlInIds("update USER_AC_TEMP set ac_status=1 where temp_id in(:ids) and user_id="+sysUser.getId(), ids);;
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}

	}
	@RequestMapping("/afreshAcLog")
	@ResponseBody
	public int afreshAcLog(HttpServletRequest request) {
		try {
			UserEntity sysUser = getSysUser(request);
			
			acService.deleteBySql("delete from ac_log where user_id=? and ac_date=str_to_date(?,'%Y-%m-%d')", sysUser.getId(),new Date());
			acService.updateBySql("update USER_AC_TEMP set ac_status=0 where  user_id=?",sysUser.getId());
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}

	}
}
