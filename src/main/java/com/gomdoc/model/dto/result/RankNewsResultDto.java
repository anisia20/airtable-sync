package com.gomdoc.model.dto.result;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RankNewsResultDto {
	private int consumer_seq;
	private int good;
	private String content;
	private String refer;
	private String title;
	private int replycount;
}
