package com.gomdoc.component;

import java.net.URI;
import java.net.URLDecoder;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.buffer.Buffer;
import io.vertx.mutiny.ext.web.client.HttpResponse;
import io.vertx.mutiny.ext.web.client.WebClient;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import io.vertx.core.http.HttpHeaders;

import javax.inject.Inject;

@Component
public class WebClientCmd {
	private final WebClient webClient;
	@Inject
	public WebClientCmd(Vertx vertx) {
		this.webClient = WebClient.create(vertx);
	}
	public Document getWebDocument(String url) {
		Document doc;
		Connection conn = Jsoup.connect(url);
		conn.header(HttpHeaders.USER_AGENT.toString(), "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 Edge/16.16299");
		try {
			doc = conn.get();
		} catch (Exception e) {
			return null;
		}
		
		return doc;
				
	}
	
	public String postSendBizAlimApiResponse(String url, String seckey, String jsonbody) {
		String jsonstr = null;
		Connection conn = Jsoup.connect(url).ignoreContentType(true);
		conn.header(HttpHeaders.USER_AGENT.toString(), "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 Edge/16.16299");
		conn.header(HttpHeaders.CONTENT_TYPE.toString(), "application/json;charset=UTF-8");
		conn.header("X-Secret-Key", seckey);
		conn.requestBody(jsonbody);
		try {
			jsonstr = conn.post().body().toString();
		} catch (Exception e) {
			return null;
		}
		
		return jsonstr;
	}
	
	public String postWebBody(String url, String jsonbody) {
		String jsonstr = null;
		Connection conn = Jsoup.connect(url).ignoreContentType(true);
		conn.method(Connection.Method.POST);
		conn.header(HttpHeaders.USER_AGENT.toString(), "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 Edge/16.16299");
		conn.header(HttpHeaders.CONTENT_TYPE.toString(), "application/json;charset=UTF-8");
		conn.requestBody(jsonbody);
		try {
			jsonstr = conn.execute().body();
		} catch (Exception e) {
			return null;
		}
		
		return jsonstr;
	}
	
	public Document getWebDocument(String url, String charset) {
		Document doc;
		
		Connection conn = Jsoup.connect(url);
		conn.header(HttpHeaders.USER_AGENT.toString(), "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 Edge/16.16299");
		try {
			URI u = new URI(url);
			doc = conn.url(new URI(u.getScheme(), u.getUserInfo(), u.getHost(), u.getPort(), URLDecoder.decode(u.getPath(), charset), u.getQuery(), u.getFragment()).toURL()).get();
		} catch (Exception e) {
			return null;
		}
		
		return doc;
				
	}

	public String iamsignalNewsApi(String url, String jsonbody) {
		String jsonstr = null;
		String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3NDQ2Mzc1ODAsInN1YiI6Imppbndvb3BhcmtAZHJhbWFuY29tcGFueS5jb20iLCJpc3MiOiJhcGkuc2lnbmFsc3R1ZGlvLmlvIiwiYXVkIjoidXNpbmctand0IiwiY2MiOm51bGwsImNuIjpudWxsLCJncm91cHMiOlsiVSJdfQ.f1431ZkJ5jko0eeGvJ5cxYinr9WVrtsnzzMHPlbwoLiF42oCQZvfD_IPsPDAWyn7MknO_Nr6M-Z0qXhK2a5SvV9Kta_saSg-PVoSBjS09YPeywb8c4YqZXbyzhtLEDMnaxDe26RhZaFpQC-eXAdzTdNqRt2ktB6a7MtECxaS_b9EdRQL_Dj3mfyUGpadbqhT5FsWXOr5O22a1Vvb7XgFT7X-wUGNADentWed5O4TdEJZbj6vE7O3WWpUCW1NuPfPlV1JQP00-iZz030w2w8cd0YgshbhapN30SvzHymDpy_m13maC6gfbRiLWUP7HsEERn0HlBNxqKo20f5lu7KANTI9kFzdi7V9ImWtUNgAO2ouGVW5M99dU0hwI6GSvjxFJ282S7BMwp6qCgUkrrogyaVTESgfbwBlXThuY4cGiESzqrqoyGwqMDZ1BQ4G4dnjra6HCevW5mhpc-3R_seWuWTVVN8xJgVjfqeOHn8gC8EZgo3E4FDezcbJJmlcMLsXohDrNOPJeHvpzi1TPWiWrclULkJnT_-Hqlz0le7jg1v5FEA_VsJDrIBVd4eMttb6K62uAHLvi1VsGkkOtB49EtdUKGieZMN-iJfNE61eb21-1DsvMfP3uOVW7ysM9mQ6pTCYakyYRdRQ7yfQez5rr5dFRi3i4lkwF0R8lT1Q6gw";
		Connection conn = Jsoup.connect(url).ignoreContentType(true);
		conn.header(HttpHeaders.USER_AGENT.toString(), "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 Edge/16.16299");
		conn.header(HttpHeaders.CONTENT_TYPE.toString(), "application/json;charset=UTF-8");
		conn.header("Authorization", "Bearer "+token);
		conn.requestBody(jsonbody);
		try {
			jsonstr = conn.post().body().text();
		} catch (Exception e) {
			return null;
		}

		return jsonstr;
	}
	public HttpResponse<Buffer> post(String url, Object obj) {
		HttpResponse<Buffer> response;
		try{
			JsonObject body = new JsonObject(Json.encode(obj));
			response = webClient.postAbs(url)
//					.putHeader("Content-Type", "application/json")
					.sendJsonAndAwait(body);
		} catch (Exception e) {
			return null;
		}
		if(response.statusCode() == 200){
			return response;
		}else {
			return null;
		}
	}

	public HttpResponse<Buffer> get(String url) {
		HttpResponse<Buffer> response;
		try{
			response = webClient.getAbs(url).sendAndAwait();
		} catch (Exception e) {
			return null;
		}
		if(response.statusCode() == 200){
			return response;
		}else {
			return null;
		}
	}
}
