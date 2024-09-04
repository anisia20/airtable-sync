package com.gomdoc.scheduled;

import javax.enterprise.context.ApplicationScoped;

import org.springframework.beans.factory.annotation.Autowired;

import com.gomdoc.component.ResourceManager;
import com.gomdoc.service.DeamongService;
import com.gomdoc.service.WorkService;

@ApplicationScoped
public class Schedules {
	
	@Autowired
	WorkService contentsService;

	@Autowired
	DeamongService daemonService;
	
	@Autowired
    ResourceManager resourceManager;

//	@Scheduled(every="1s")
//	void startTh() {
//		daemonService.sendeRiskExec();
//	}
	
}
