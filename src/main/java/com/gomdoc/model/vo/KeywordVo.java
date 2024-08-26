package com.gomdoc.model.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KeywordVo implements Serializable{
	private String keyword;
	private int exclusion;
	private int id;
	private String keyword_group_name;
}
