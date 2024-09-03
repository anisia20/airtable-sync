package com.gomdoc.mapper.repository;

import com.gomdoc.model.vo.AirtableFieldInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirtableKeyInfoRepository extends CrudRepository<AirtableFieldInfo, String> {
}
