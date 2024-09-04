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
@Table(
		name = "airtable_field_info",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = {"base_id", "table_id", "column_name"})
		}
)
@IdClass(AirtableFieldInfo.class)
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동 증가 설정
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "base_id", nullable = false)
	private String baseId;

	@Column(name = "table_id", nullable = false)
	private String tableId;

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
