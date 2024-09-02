package com.gomdoc.controller.apiv1;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.spi.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;

import com.gomdoc.component.RedisCmd;
import com.gomdoc.model.dto.NewsEtc;
import com.gomdoc.service.WorkService;
import com.gomdoc.utils.SignalUtil;
import lombok.extern.jbosslog.JBossLog;

@Path("/v1/work")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@JBossLog
public class WorkController {
	@Autowired
	WorkService contentsService;
	
	@Autowired
	WorkService workService;

	@Autowired
	RedisCmd redisCmd;

	@GET
    @Path("db2q/{type}/{startdate}/{enddate}")
    @PermitAll
	@Operation(
	summary = "db2q",
	description = "data를 mq로 이관한다. "
	)
    public Response db2q(@Context HttpRequest request,
    		@PathParam String type,
    		@PathParam String startdate,
    		@PathParam String enddate) {
		HttpHeaders headers = request.getHttpHeaders();
		log.info("start ranknews IP="+SignalUtil.getRemoteIpAddr(request)+", SUA="+headers.getHeaderString("sec-ch-ua")+",SUM="+headers.getHeaderString("sec-ch-ua-mobile")+", UA="+headers.getHeaderString("User-Agent")+", C="+headers.getCookies());
		if("news".equals(type))
			return contentsService.newsDb2MQ(startdate, enddate);
		if("comment".equals(type))
			return contentsService.commentDb2MQ(startdate, enddate);
		
		return Response.status(HttpStatus.SC_BAD_REQUEST).tag("타입이 정확하지 않습니다. ").build();
			
	}
}
