package com.gomdoc.scheduled;

import static io.quarkus.scheduler.Scheduled.ConcurrentExecution.SKIP;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.springframework.beans.factory.annotation.Autowired;

import io.quarkus.scheduler.Scheduled;
import com.gomdoc.component.SignalResourceManager;
import com.gomdoc.service.DeamongService;
import com.gomdoc.service.WorkService;
import com.gomdoc.utils.SignalUtil;

@ApplicationScoped
public class Schedules {
	
	@Autowired
	WorkService contentsService;

	@Autowired
	DeamongService daemonService;
	
	@Autowired
	SignalResourceManager resourceManager;

	@Scheduled(every="1s")
	void startWordCloudTh() {
		daemonService.sendeHotIssueExec();
		daemonService.sendeRiskExec();
	}
	
}
