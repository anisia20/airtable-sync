package com.gomdoc.model.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentVo implements Serializable{
	private String idsk;
	private String cid;
	private String user;
	private String text;
	private String time;
	private String pdate;
	private String tl;
}
