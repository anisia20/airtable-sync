package com.gomdoc.model.vo.es;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RiskVo {
	@JsonIgnore
	String id;
	@JsonIgnore
	String text;
	
	
	String channel; 
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	String cid; 
	
	String date;
	String idsk; 
	String keyword_group_id; 
	int length; 
	String morph; 
	String sentiment; 
}
