package com.gomdoc.model.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsEtcVo implements Serializable{
	String idsk   ;
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
