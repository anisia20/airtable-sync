package com.gomdoc.mapper;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.gomdoc.model.vo.CommentVo;
import com.gomdoc.model.vo.NewsEtcVo;
import com.gomdoc.model.vo.NewsVo;

@Mapper
public interface NewsInfoMapper {
	
	@Transactional
	@Select("SELECT " +
          "p.idsk, " +
          "t.tl, " +
          "d.ds, " +
          "DATE_FORMAT(p.pdate, '%Y-%m-%d %H:%i:%s') as pdate, " +
          "nme.nm, " +
          "DATE_FORMAT(p.edate, '%Y-%m-%d %H:%i:%s') as edate, " +
          "DATE_FORMAT(p.dateline, '%Y-%m-%d %H:%i:%s') as dateline, " +
          "p.provider_news_id, " +
          "p.provider_link_page, " +
          "p.naver_link_page, " +
          "p.publisher_code " +
          "FROM " +
          "news_pub as p " +
          "join news_tl as t on t.idsk = p.idsk " +
          "left join news_ds as d on d.idsk = p.idsk " +
          "left join news_info.news_nm nme on p.idsk = nme.idsk " +
          "where p.pdate >= STR_TO_DATE(#{startDate}, '%Y%m%d') and p.pdate < STR_TO_DATE(#{endDate}, '%Y%m%d') ORDER BY p.pdate DESC")
	public List<NewsVo> getNews(String startDate, String endDate); 
	
	@Transactional
	@Select("select c.idsk, c.cid, c.user, c.text, DATE_FORMAT(c.time, '%Y-%m-%d %H:%i:%s') as time, c.result, DATE_FORMAT(p.pdate, '%Y-%m-%d %H:%i:%s') as pdate, t.tl " +
			"from news_info.news_comment as c " +
			"join news_info.news_pub as p on c.idsk = p.idsk " +
			"join news_info.news_tl as t on c.idsk = t.idsk " +
			"where c.time >= STR_TO_DATE(#{startDate}, '%Y%m%d') and c.time < STR_TO_DATE(#{endDate}, '%Y%m%d') ORDER BY c.time desc "
			)
	public List<CommentVo> getComment(String startDate, String endDate); 
	
	@Select("select nil.*, nis.male, nis.female, nis.10age as age10, nis.20age as age20, nis.30age as age30, nis.40age as age40, nis.50age as age50, nis.60age as age60 from news_info.news_info_likeit nil, news_info.news_info_stat nis "
			+ " where nil.idsk = nis.idsk "
			+ " and nil.idsk like #{dt} "
			+ " order BY nil.idsk desc ")
	public List<NewsEtcVo> getEtcData(String dt); 
}
