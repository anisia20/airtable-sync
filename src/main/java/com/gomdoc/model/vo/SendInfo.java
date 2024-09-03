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
@Table(name = "send_info")
public class SendInfo implements Serializable{
	/**
	 * - 유저아이디
	 * - 베이스 아이디
	 * - 발송 건수
	 * - 제한 건수
	 * - 등록 일시
	 * - 갱신 일시
	 */
	@Id
	@Column(name = "user_id", nullable = false)
	private String userId;

	@Id
	@Column(name = "base_id", nullable = false)
	private String baseId;

	@Id
	@Column(name = "send_cnt", nullable = false)
	private String sendCnt;

	@Column(name = "send_limit")
	private String sendLimit;

	@Column(name = "create_dt")
	private String createDt;

	@Column(name = "update_dt")
	private String updateDt;
}
