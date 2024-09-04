package com.gomdoc.mapper.repository;

import com.gomdoc.model.vo.AirtableFieldInfo;
import com.gomdoc.model.vo.AirtableKeyInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirtableKeyInfoRepository extends CrudRepository<AirtableKeyInfo, String> {
}
