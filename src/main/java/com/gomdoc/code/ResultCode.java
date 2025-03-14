package com.gomdoc.code;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum ResultCode {

    /** 내부 코드 체계 */
    R_000("000", "COMMON", "성공", "성공"),
    R_100("100", "AUTH", "인증에러", "인증에러"),
    
    R_301("301", "WORK", "SendInfo DB 등록 실패", "SendInfo DB 등록 실패"),
    R_302("302", "WORK", "FieldInfo DB 등록 실패", "FieldInfo DB 등록 실패"),
    R_303("303", "WORK", "KeyInfo DB 등록 실패", "KeyInfo DB 등록 실패"),
    R_304("304", "WORK", "SendInfo vo parse 실패", "SendInfo vo parse 실패"),
    R_305("305", "WORK", "FieldInfo vo parse 실패", "FieldInfo vo parse 실패"),
    R_306("306", "WORK", "KeyInfo vo parse 실패", "KeyInfo vo parse 실패"),


    R_290("290", "ETC", "기타에러", "기타에러"),
    R_ETC("999", "ETC", "기타", "기타오류"),
    ;

    public String r;
    public String tp;
    public String rd;
    public String cd;

    public static HashMap<String, ResultCode> getResultCode() {
        HashMap<String, ResultCode> codeMap = new HashMap<>();
        for (ResultCode c : values()) {
            codeMap.put(c.r, c);
        }
        return codeMap;
    }

    public static ResultCode getResultCd(String result) {
        return getResultCd(result, ResultCode.R_ETC);
    }

    public static ResultCode getResultCd(String result, ResultCode rcsresultCd) {
        ResultCode resultCd = null;
        try {
            resultCd = ResultCode.valueOf(result);
        } catch (Exception e) {
            resultCd = (rcsresultCd == null) ? ResultCode.R_ETC : rcsresultCd;
        }
        return resultCd;
    }

    @Getter
    @AllArgsConstructor
    @ToString
    public enum Prefix {
        PREFIX_R("R_"),
        ;
        public String key;
    }
}
