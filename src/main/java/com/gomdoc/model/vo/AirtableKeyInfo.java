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
		name = "airtable_key_info",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = {"base_id", "table_id", "data_id"})
		}
)
@IdClass(AirtableKeyInfo.class)
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동 증가 설정
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "base_id", nullable = false)
	private String baseId;

	@Column(name = "table_id", nullable = false)
	private String tableId;

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
