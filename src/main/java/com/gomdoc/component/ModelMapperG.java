package com.gomdoc.component;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import lombok.extern.jbosslog.JBossLog;

@JBossLog
@Component
public class ModelMapperG {
	
	private static ModelMapper modelMapper;
	
	static {
	    modelMapper = new ModelMapper();
	    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//	    modelMapper.getConfiguration().setDeepCopyEnabled(true);
	}
	
	public <D> D map(Object source, Class<D> destinationType) {
		try {
			return modelMapper.map(source, destinationType);
		} catch (Exception e) {
			log.error("err="+e.getMessage());
			return null;
		}
	}
	
	public <D> D object2Map(Object source, Class<D> destinationType) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		try {
			return modelMapper.map(source, destinationType);
		} catch (Exception e) {
			log.error("err="+e.getMessage());
			return null;
		}
	}
}
