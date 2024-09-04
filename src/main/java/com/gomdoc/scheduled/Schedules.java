package com.gomdoc.scheduled;

import javax.enterprise.context.ApplicationScoped;

import org.springframework.beans.factory.annotation.Autowired;

import com.gomdoc.component.SignalResourceManager;
import com.gomdoc.service.DeamongService;
import com.gomdoc.service.WorkService;

@ApplicationScoped
public class Schedules {
	
	@Autowired
	WorkService contentsService;

	@Autowired
	DeamongService daemonService;
	
	@Autowired
	SignalResourceManager resourceManager;

//	@Scheduled(every="1s")
//	void startTh() {
//		daemonService.sendeRiskExec();
//	}
	
}
