package com.gomdoc.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsEtc {
	Integer male   ;
	Integer female ;
	Integer age10  ;
	Integer age20  ;
	Integer age30  ;
	Integer age40  ;
	Integer age50  ;
	Integer age60  ;
	Integer likes     ;
	Integer warm      ;
	Integer sad       ;
	Integer angry     ;
	Integer want      ;
	Integer fan       ;
	Integer useful    ;
	Integer wow       ;
	Integer touched   ;
	Integer analytical;
	Integer recommend ;
}
