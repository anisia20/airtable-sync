package com.gomdoc.service;


import com.gomdoc.component.ModelMapperG;
import com.gomdoc.component.RedisCmd;
import com.gomdoc.component.WebClientCmd;
import com.gomdoc.utils.SignalUtil;
import lombok.extern.jbosslog.JBossLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.json.bind.Jsonb;

@Service
@JBossLog
public class WorkService {
	
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




}
