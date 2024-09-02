package com.gomdoc.model.vo;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "airtable_field_info")
public class AirtableFieldInfo implements Serializable{
	/**
	 * - 베이스 아이디
	 * - 테이블 아이디
	 * - 컬럼명
	 * - 키 여부(Y/N)
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
	@Column(name = "column_name", nullable = false)
	private String columnName;

	@Column(name = "key_yn")
	private String keyYn;

	@Column(name = "use_yn")
	private String useYn;

	@Column(name = "create_dt")
	private String createDt;

	@Column(name = "update_dt")
	private String updateDt;
}
