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

			commonMapping("ac", acService, request);
			return "ac/audit";
		}

	}

	@RequestMapping("/changeac")
	public String changeac(HttpServletRequest request) {
		UserEntity sysUser = getSysUser(request);
		if (sysUser == null) {
			return "redirect:/";
		} else {

			commonMapping("ac", acService, request);
			List<UserAcTempEntity> userAcTemps = acService.findListByProperty(
					UserAcTempEntity.class, "userId", sysUser.getId());
			List<ACTempEntity> tempEntities = new ArrayList<>();
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
			List<AcLogEntity> acLogEntities = new ArrayList<>();

			for (int i = 0; i < json.size(); i++) {
				AcLogEntity acLogEntity = new AcLogEntity();
				Object object = json.get(i);
				JSONObject jsonObject = JSONObject.fromObject(object);
				String acCode = (String) jsonObject.get("acCode");
				String acName = (String) jsonObject.get("acName");
				String acAmount = (String) jsonObject.get("acAmount");

				acLogEntity.setAcAmount(Double.valueOf(acAmount));
				acLogEntity.setAcCode(acCode);
				acLogEntity.setAcName(acName);
				acLogEntity.setUserId(sysUser.getId());
				acLogEntity.setAcDate(new Date());
				acLogEntity.setAcTime(new Date());

				acLogEntities.add(acLogEntity);
			}
			acService.batchSave(acLogEntities);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	
	}

}
