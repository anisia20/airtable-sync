package com.gomdoc.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NewsncashBoMapper {

	@Select("select c.positivetext from newsncash_bo.content_mst c "
			+ " where c.gnbcode = '005' "
			+ " and c.positivetext is not null "
			+ " and c.positivetext not in('') "
			+ " order BY c.positivetext desc limit 10000 ")
	public List<String> getGptHashtag();
}
