package com.gomdoc.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldInfoDto {
	public Integer id;
	public String baseId;
	public String tableId;
	public String columnName;
	public String keyYn;
	public String useYn;
}
