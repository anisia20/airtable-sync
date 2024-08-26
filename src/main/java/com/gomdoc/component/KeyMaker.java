package com.gomdoc.component;

import com.gomdoc.config.Def;
import com.gomdoc.utils.UuidMaker;
import lombok.extern.jbosslog.JBossLog;
import org.springframework.stereotype.Component;

import java.util.Hashtable;

@JBossLog
@Component
public class KeyMaker {
    public static Hashtable<String, Object> resources = new Hashtable<String, Object>();
    public static UuidMaker keyMaker;
    static {
        keyMaker = getKeyMaker();
    }
    public static UuidMaker getKeyMaker(){
        try {
            Object obj = Def.AGENT_KEY;
            UuidMaker km = new UuidMaker((int) 1);
            resources.put(Def.AGENT_KEY, km);
            return km;
        } catch (Exception e) {
            log.error("KeyMaker gathering fail. err="+e.toString(), e);
            return null;
        }
    }

    public String nextKey(String template){
        return keyMaker.nextKey(template);
    }
}
