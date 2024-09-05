package com.gomdoc.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class KeyInfoDto {
	public Integer id;
	public String baseId;
	public String tableId;
	public String dataId;
	public String keyInfo;
	public String useYn;
}
