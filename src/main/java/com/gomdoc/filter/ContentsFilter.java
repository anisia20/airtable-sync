package com.gomdoc.filter;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import lombok.extern.jbosslog.JBossLog;

@Provider
@JBossLog
public class ContentsFilter implements ContainerRequestFilter {

	@Context
	private HttpServletRequest httpServletRequest;

	public void filter(ContainerRequestContext requestContext) {
		// TODO
		//log.info("start ranknews U="+requestContext.getHeaderString+" IP="+SignalUtil.getRemoteAddr(getRemoteAddr)+" UA="+requestContext.getHeaderString("User-Agent"));
	}
}