package com.gomdoc.controller.apiv1;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.gomdoc.model.dto.SendInfoDto;
import io.quarkus.vertx.web.Body;
import org.apache.http.HttpStatus;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.spi.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;

import com.gomdoc.component.RedisCmd;
import com.gomdoc.service.WorkService;
import lombok.extern.jbosslog.JBossLog;

@Path("/v1/work")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@JBossLog
public class WorkController {
	@Autowired
	WorkService workService;
	
	@Autowired
	RedisCmd redisCmd;

	@POST
    @Path("sendinfo")
    @PermitAll
	@Operation(
	summary = "work",
	description = "sendinfo를 등록한다."
	)
    public Response setSendInfo(@Context HttpRequest request,
    		@Body SendInfoDto dto) {
		HttpHeaders headers = request.getHttpHeaders();
//		log.info("start ranknews IP="+GUtil.getRemoteIpAddr(request)+", SUA="+headers.getHeaderString("sec-ch-ua")+",SUM="+headers.getHeaderString("sec-ch-ua-mobile")+", UA="+headers.getHeaderString("User-Agent")+", C="+headers.getCookies());
		return workService.setSendInfo(dto);
	}
}
