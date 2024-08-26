package com.gomdoc.service;

import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

import com.gomdoc.module.HotIssueNlpRedisSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gomdoc.component.SignalResourceManager;
import com.gomdoc.config.Def;
import com.gomdoc.deamon.DaemonG;
import com.gomdoc.model.redis.SignalCommonVo;
import com.gomdoc.module.RiskNlpRedisSender;
import lombok.extern.jbosslog.JBossLog;

@Service("deamongService")
@JBossLog
public class DeamongService {
	
	@Autowired
	SignalResourceManager resourceManager;

	public HashMap<String, DaemonG> daemonGList = new HashMap<>();

	/**
	 * sample map
	 * 
	 * @return
	 */
	public HashMap<String, DaemonG> getDeamonList() {
		return daemonGList;
	}

	public boolean isRunning(String nm) {

		if (daemonGList.get(nm) == null)
			return false;

		if (daemonGList.get(nm).isStop())
			return false;

		if (System.currentTimeMillis() - daemonGList.get(nm).getLastCheckTime() > 60000) {
			log.error("processs hang. NM="+nm+". status change to stop");
			daemonGList.get(nm).nextStop();
			return false;
		}

		log.debug(nm+" is only one running.");
		return true;
	}

	///////////////////////
	
	/** 
	 * make sender sendeRisk
	 */
	public void sendeRiskExec() {
		Set<String> set = daemonGList.keySet().stream().filter(s -> s.startsWith(Def.DAEMON_SENDER_RISK)).collect(Collectors.toSet());
		for(String str : set) {
			if(isRunning(str));
		}
		
		String redisstr = resourceManager.getRedisCmd().keys(Def.REDIS_Q_NLP_RISKKEYWORD_SEND);
		if(redisstr == null || redisstr.isEmpty() || redisstr.contains(Def.REDIS_Q_NLP_RISKKEYWORD_SEND) == false ) {
			return;
		}
		
		String dmNm = Def.DAEMON_SENDER_RISK + "_" + set.size();
		if(isRunning(dmNm))return;
		
		SignalCommonVo commons = resourceManager.getCommonConfig();
		if (commons == null)
			return;
		if (set.size() >= commons.getRisk_sender_max_cnt()) {
			log.debug("ds wordcloud send thread count is over. max="+commons.getRisk_sender_max_cnt()+" cnt="+ set.size());
			return;
		}

		DaemonG dm = new RiskNlpRedisSender(this, resourceManager, dmNm);
		daemonGList.put(dmNm, dm);
		new Thread(dm).start();
	}

	/**
	 * make sender DAEMON_SENDER_HOTISSUE
	 */
	public void sendeHotIssueExec() {
		Set<String> set = daemonGList.keySet().stream().filter(s -> s.startsWith(Def.DAEMON_SENDER_HOTISSUE)).collect(Collectors.toSet());
		for(String str : set) {
			if(isRunning(str));
		}

		String redisstr = resourceManager.getRedisCmd().keys(Def.REDIS_Q_NLP_HOTISSUEKEYWORD_SEND);
		if(redisstr == null || redisstr.isEmpty() || redisstr.contains(Def.REDIS_Q_NLP_HOTISSUEKEYWORD_SEND) == false ) {
			return;
		}

		String dmNm = Def.DAEMON_SENDER_HOTISSUE + "_" + set.size();
		if(isRunning(dmNm))return;

		SignalCommonVo commons = resourceManager.getCommonConfig();
		if (commons == null)
			return;
		if (set.size() >= commons.getHotissue_sender_max_cnt()) {
			log.debug("hotissue send thread count is over. max="+commons.getHotissue_sender_max_cnt()+" cnt="+ set.size());
			return;
		}

		DaemonG dm = new HotIssueNlpRedisSender(this, resourceManager, dmNm);
		daemonGList.put(dmNm, dm);
		new Thread(dm).start();
	}
}
