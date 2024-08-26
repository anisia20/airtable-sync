package com.gomdoc.model.vo.es;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentEsVo {
	
	@JsonIgnore
	String id;
	
	String cid; 
	String comment; 
	String idsk; 
	String pdate; 
	String result_comment; 
	String time; 
	String tl; 
	String user; 
}
