package com.gomdoc.service;


import com.gomdoc.code.ResultCode;
import com.gomdoc.component.ModelMapperG;
import com.gomdoc.component.RedisCmd;
import com.gomdoc.component.WebClientCmd;
import com.gomdoc.mapper.repository.AirtableFieldInfoRepository;
import com.gomdoc.mapper.repository.AirtableKeyInfoRepository;
import com.gomdoc.mapper.repository.SendInfoRepository;
import com.gomdoc.mapper.repository.dao.WorkDao;
import com.gomdoc.model.Result;
import com.gomdoc.model.dto.SendInfoDto;
import com.gomdoc.model.vo.SendInfo;
import com.gomdoc.utils.GUtil;
import lombok.extern.jbosslog.JBossLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.resource.spi.work.Work;
import javax.ws.rs.core.Response;

@Service
@JBossLog
public class WorkService {
	
	@Autowired
	GUtil util;

	@Autowired
	RedisCmd redisCmd;
	
	@Autowired
	ModelMapperG modelMapperG;
	
    @Inject
	Jsonb jsonb;
    
    @Inject
	WebClientCmd webClientCmd;

    @Inject
	WorkDao workDao;

	public Response setSendInfo(SendInfoDto dto) {
		Result result = new Result();

		SendInfo vo = modelMapperG.map(dto, SendInfo.class);
		int n_res = workDao.setSendInfo(vo);
		if(n_res < 0) {
			result.setResultFail(ResultCode.R_301);
			return Response.ok(result).build();
		}
		result.setSuccess(true);
		return Response.ok(result).build();
	}
}
