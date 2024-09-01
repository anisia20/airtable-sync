package com.gomdoc.service;


import com.gomdoc.component.ModelMapperG;
import com.gomdoc.component.RedisCmd;
import com.gomdoc.component.WebClientCmd;
import com.gomdoc.mapper.NewsInfoMapper;
import com.gomdoc.utils.SignalUtil;
import lombok.extern.jbosslog.JBossLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.ws.rs.core.Response;

@Service
@JBossLog
public class WorkService {
	
	@Autowired
	NewsInfoMapper newsMapper;
	
	@Autowired
	SignalUtil util;

	@Autowired
	RedisCmd redisCmd;
	
	@Autowired
	ModelMapperG modelMapperG;
	
    @Inject
	Jsonb jsonb;
    
    @Inject
	WebClientCmd webClientCmd;

	public Response newsDb2MQ(String startdate, String enddate) {
		String nowdate = startdate;
		while(true) {
			nowdate = util.getYmd(nowdate, 1);
			newsMapper.getNews(util.getYmd(nowdate, -1), nowdate).stream().forEach(data -> {
				String ds =	data.getDs().replace("&amp;", "").replace("\\n", "");
				String tl = data.getTl().replace("&amp;", "").replace("\\n", "");
				
				data.setDs(ds);
				data.setTl(tl);
//				activeMQCmd.newsSend(jsonb.toJson(data));
			});
			
			log.info("complate db2q news date="+nowdate);
			if(enddate.equals(nowdate)) break;
		}
		
		return Response.ok().build();
	}
	
	public Response commentDb2MQ(String startdate, String enddate) {
		String nowdate = startdate;
		while(true) {
			nowdate = util.getYmd(nowdate, 1);
			newsMapper.getComment(util.getYmd(nowdate, -1), nowdate).stream().forEach(data -> {
				String text =	data.getText().replace("&amp;", "").replace("\\n", "");
				String tl = data.getTl().replace("&amp;", "").replace("\\n", "");
				
				data.setText(text);
				data.setTl(tl);
//				activeMQCmd.commentSend(jsonb.toJson(data));
			});
			
			log.info("complate db2q comment date="+nowdate);
			if(enddate.equals(nowdate)) break;
		}
		
		return Response.ok().build();
	}
	


}
