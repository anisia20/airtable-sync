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
public class NewsEsVo {
	
	@JsonIgnore
	String id;
	
	String angry; 
	String category; 
	String dateline; 
	String ds;
	String edate; 
	String fan; 
	String female; 
	String idsk; 
	String likes; 
	String male; 
	String naver_link_page; 
	String news_info_no_comments; 
	String news_info_no_del_comments; 
	String news_info_no_total_comments; 
	String nm; 
	String pdate; 
	String provider_link_page; 
	String provider_news_id; 
	String publisher_code; 
	String result; 
	String sad; 
	String tl; 
	String want; 
	String warm;
	
}
