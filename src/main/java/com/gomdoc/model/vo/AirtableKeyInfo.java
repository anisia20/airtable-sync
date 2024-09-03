package com.gomdoc.model.vo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "airtable_key_info")
public class AirtableKeyInfo implements Serializable{
	/**
	 * - 베이스 아이디
	 * - 테이블 아이디
	 * - 데이터 아이디
	 * - 키정보( A_B_C )
	 * - 사용여부(Y/N)
	 * - 등록일시
	 * - 갱신일시
	 */
	@Id
	@Column(name = "base_id", nullable = false)
	private String baseId;

	@Id
	@Column(name = "table_id", nullable = false)
	private String tableId;

	@Id
	@Column(name = "data_id", nullable = false)
	private String dataId;

	@Column(name = "key_info")
	private String keyInfo;

	@Column(name = "use_yn")
	private String useYn;

	@Column(name = "create_dt")
	private String createDt;

	@Column(name = "update_dt")
	private String updateDt;
}
