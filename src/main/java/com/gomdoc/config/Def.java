package com.gomdoc.config;

public class Def {
	
	public static final String AGENT_KEY = "signal123!@#";
	//Daemon type
	public static final String DAEMON_SENDER_WORDCLOUD_COMMENT = "DAEMON_SENDER_WORDCLOUD_COMMENT";
	public static final String DAEMON_SENDER_WORDCLOUD_DS = "DAEMON_SENDER_WORDCLOUD_DS";
	public static final String DAEMON_SENDER_RISK = "DAEMON_SENDER_RISK";
	public static final String DAEMON_SENDER_HOTISSUE = "DAEMON_SENDER_HOTISSUE";
	public static final String DAEMON_SAVER_NEWS_TITLE = "DAEMON_SAVER_NEWS_TITLE";

	//Redis Keys
	public static final String REDIS_SIGNAL_S_CONFIG = "REDIS_SIGNAL_S_CONFIG";
	public static final String REDIS_Q_WORDCLOUD_IDSK = "REDIS_Q_WORDCLOUD_IDSK";
	public static final String REDIS_Q_RISK_IDSK = "REDIS_Q_RISK_IDSK";
	public static final String REDIS_Q_HOTISSUE_IDSK = "REDIS_Q_HOTISSUE_IDSK";

	public static final String REDIS_SIGNAL_H_WORDCLOUD = "REDIS_SIGNAL_H_WORDCLOUD";
	public static final String REDIS_H_EXCLUDE_HOTISSUEWORD = "REDIS_H_EXCLUDE_HOTISSUEWORD";
	public static final String REDIS_Q_NLP_WORDCLOUD_SEND = "REDIS_Q_NLP_WORDCLOUD_SEND";
	
	//risk
	public static final String REDIS_Z_DS_RISKKEYWORD = "keyword:REDIS_Z_DS_RISKKEYWORD";
	public static final String REDIS_Z_COMMENT_RISKKEYWORD = "keyword:REDIS_Z_COMMENT_RISKKEYWORD";
	public static final String REDIS_H_DS_RISKKEYWORD = "keyword:REDIS_H_DS_RISKKEYWORD";
	public static final String REDIS_H_COMMENT_RISKKEYWORD = "keyword:REDIS_H_COMMENT_RISKKEYWORD";
	public static final String REDIS_Q_NLP_RISKKEYWORD_SEND = "REDIS_Q_NLP_RISKKEYWORD_SEND";
	public static final String REDIS_Q_NLP_HOTISSUEKEYWORD_SEND = "REDIS_Q_NLP_HOTISSUEKEYWORD_SEND";
	public static final String REDIS_S_RISKKEYWORD = "REDIS_S_RISKKEYWORD";
	public static final String REDIS_H_RISKALERT = "alert:REDIS_H_RISKALERT";
	public static final String REDIS_H_SIGNALDIC = "REDIS_H_SIGNALDIC";

	//naver cafe
	public static final String REDIS_H_NCAFEDATA = "ncafe:REDIS_H_NCAFEDATA";
	public static final String REDIS_Z_NCAFE_RISKKEYWORD = "ncafe:REDIS_Z_NCAFE_RISKKEYWORD";

	//iamnews
	public static final String REDIS_Q_NEWS_TITLE = "ml:REDIS_Q_NEWS_TITLE";
	public static final String REDIS_Z_NEWS_TITLE = "biz:REDIS_Z_NEWS_TITLE";
	public static final String REDIS_Z_NEWS_CATEGORY = "biz:REDIS_Z_NEWS_CATEGORY";

	
	/** 일반명사 **/
	public static final String MECAB_CODE_NNG = "NNG";
	/** 고유명사 **/
	public static final String MECAB_CODE_NNP = "NNP";
	/** 의존명사 **/
	public static final String MECAB_CODE_NNB = "NNB";
	/** 대명사 **/
	public static final String MECAB_CODE_NP = "NP";
	/** 형용사 **/
	public static final String MECAB_CODE_VA = "VA";
	/** 동사 **/
	public static final String MECAB_CODE_VV = "VV";
	/** 줄임표 ... **/
	public static final String MECAB_CODE_SE = "SE";
	/** 여는 괄호 **/
	public static final String MECAB_CODE_SSO = "SSO";
	/** 닫는 괄호 **/
	public static final String MECAB_CODE_SSC = "SSC";
	/** 구분자 **/
	public static final String MECAB_CODE_SC = "SC";
	/** 붙임표, 기타기호 **/
	public static final String MECAB_CODE_SY = "SY";

	
}
