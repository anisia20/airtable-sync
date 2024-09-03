package com.gomdoc.mapper.repository;

import com.gomdoc.model.vo.AirtableFieldInfo;
import com.gomdoc.model.vo.SendInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirtableFieldInfoRepository extends CrudRepository<AirtableFieldInfo, String> {
}
