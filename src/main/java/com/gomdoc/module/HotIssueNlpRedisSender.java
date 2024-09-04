package com.gomdoc.module;

import com.gomdoc.component.SignalResourceManager;
import com.gomdoc.config.Def;
import com.gomdoc.deamon.DaemonG;
import com.gomdoc.service.DeamongService;
import lombok.extern.jbosslog.JBossLog;

@JBossLog
public class HotIssueNlpRedisSender extends DaemonG {

	DeamongService parent;
	SignalResourceManager resourceManager;

	public HotIssueNlpRedisSender(DeamongService parent, SignalResourceManager resourceManager, String name) {
		setName(name);
		this.parent = parent;
		this.resourceManager = resourceManager;
		nextInit();
		log.info(getName()+" start.");
	}
	
	@Override
	public void init() {
//		if (resourceManager.getWordCloudEsMapper().check() < 0) {
//			log.warn(getName()+" init, do not connect elasticsearch!!");
//			nextSleep();
//			return;
//		}
//		if (resourceManager.getRedisCmd().get(Def.REDIS_SIGNAL_S_CONFIG) == null) {
//			log.warn(getName()+" init, do not connect redis!!");
//			nextSleep();
//			return;
//		}
		nextExecute();
	}

	@Override
	public void execute() {
//		log.debug(getName()+" excute job!!");
//		String redisstr = resourceManager.getRedisCmd().pop(Def.REDIS_Q_NLP_HOTISSUEKEYWORD_SEND);
//		if(redisstr == null || redisstr.isEmpty()) {
//			nextStop();
//			return;
//		}
//		if (resourceManager.getWordCloudEsMapper().check() < 0) {
//			nextInit();
//			return;
//		}
//		RiskVo vo = resourceManager.getJsonb().fromJson(redisstr, RiskVo.class);
	}
	
	@Override
	public void fail() {
		nextStop();
	}
	
	@Override
	public void stop() {
		log.info(getName()+" stop.");
		parent.getDeamonList().remove(getName());
	}
}
	
