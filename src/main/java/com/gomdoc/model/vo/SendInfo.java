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
        name = "send_info",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "base_id"})
        }
)
public class SendInfo implements Serializable {
    /**
     * - 유저아이디
     * - 베이스 아이디
     * - 발송 건수
     * - 제한 건수
     * - 등록 일시
     * - 갱신 일시
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동 증가 설정
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "base_id", nullable = false)
    private String baseId;

    @Column(name = "send_cnt", nullable = false)
    private String sendCnt;

    @Column(name = "send_limit")
    private String sendLimit;

    @Column(name = "create_dt")
    private String createDt;

    @Column(name = "update_dt")
    private String updateDt;
}
