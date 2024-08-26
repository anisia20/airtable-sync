package com.gomdoc.component;

import java.util.Hashtable;

import javax.inject.Inject;
import javax.json.bind.Jsonb;

import com.gomdoc.mapper.es.NewsEsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.gomdoc.config.Def;
import com.gomdoc.mapper.es.WordCloudEsMapper;
import com.gomdoc.model.redis.SignalCommonVo;
import com.gomdoc.service.WorkService;
import com.gomdoc.utils.UuidMaker;
import lombok.Data;
import lombok.extern.jbosslog.JBossLog;

@Data
@Component
@JBossLog
public class SignalResourceManager {
	
	protected static Hashtable<String, Object> resources = new Hashtable<String, Object>();
	static long ServNum;

	@Autowired
	RedisCmd redisCmd;

	@Inject
	Jsonb jsonb;
	
	@Autowired
	WorkService workService;
	
	@Autowired
	LearnService learnService;
	
	@Inject
    WordCloudEsMapper wordCloudEsMapper;

	@Inject
	NewsEsMapper newsEsMapper;

	/**
	 * key 생성
	 * @return
	 */
	@Bean
	public UuidMaker getKeyMaker() {
		try {
			Object obj = Def.AGENT_KEY;
			if (obj == null) {
				ServNum++;
				if (ServNum < 0 || ServNum > 99) {
					ServNum = 0;
				}

				UuidMaker km = new UuidMaker((int) ServNum);
				resources.put(Def.AGENT_KEY, km);
				return km;
			} else {
				UuidMaker km = (UuidMaker) obj;
				return km;
			}
		} catch (Exception e) {
			log.error("KeyMaker gathering fail. err="+e.toString(), e);
			return null;
		}
	}
	public SignalCommonVo getCommonConfig() {
		String jsonstr = redisCmd.get(Def.REDIS_SIGNAL_S_CONFIG);
		if(jsonstr == null) {
			log.error("do not read redisdata REDIS_SIGNAL_S_CONFIG!!");
			return null;
		}
		SignalCommonVo result =  jsonb.fromJson(jsonstr, SignalCommonVo.class);
		return result;
	}
}
