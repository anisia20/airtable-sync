package com.gomdoc.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendInfoDto {
	public String userId;
	public String baseId;
	public String sendCnt;
	public String sendLimit;
}
