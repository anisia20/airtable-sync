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
public class NewsVo implements Serializable{
	private String idsk;
	private String tl;
	private String ds;
	private String pdate;
	private String nm;
	private String edate;
	private String dateline;
	private String provider_news_id;
	private String provider_link_page;
	private String naver_link_page;
	private String publisher_code;
}
