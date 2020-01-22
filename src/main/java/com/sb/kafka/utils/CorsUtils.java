/**
 * 
 */
package com.sb.kafka.utils;

import java.util.Enumeration;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Utility class to handle headers for cross origin requests.
 * 
 * @author ankur.mahajan
 * @written 03-Mar-2018
 */
public class CorsUtils {

	private static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
	private static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
	private static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
	private static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
	private static final String ACCESS_CONTROL_REQUEST_METHOD = "access-control-request-method";
	private static final String ACCESS_CONTROL_REQUEST_HEADERS = "access-control-request-headers";
	private static final String ORIGIN_HEADER = "origin";
	private static final String REFERER_HEADER = "referer";

	public static ServletResponse allowAllOrigins(HttpServletRequest request, HttpServletResponse response) {

		if (request.getHeader(ACCESS_CONTROL_REQUEST_METHOD) != null) {
			Enumeration<String> e = request.getHeaders(ACCESS_CONTROL_REQUEST_METHOD);
			while (e.hasMoreElements()) {
				String value = e.nextElement();
				response.addHeader(ACCESS_CONTROL_ALLOW_METHODS, value);
			}
		}

		if (request.getHeader(ACCESS_CONTROL_REQUEST_HEADERS) != null) {
			Enumeration<String> e = request.getHeaders(ACCESS_CONTROL_REQUEST_HEADERS);
			while (e.hasMoreElements()) {
				String value = e.nextElement();
				response.addHeader(ACCESS_CONTROL_ALLOW_HEADERS, value);
			}
		}

		boolean origin_sent = false;
		if (request.getHeader(ORIGIN_HEADER) != null) {
			Enumeration<String> e = request.getHeaders(ORIGIN_HEADER);
			while (e.hasMoreElements()) {
				String value = e.nextElement();
				if (value != null) {
					origin_sent = true;
					response.addHeader(ACCESS_CONTROL_ALLOW_ORIGIN, value);
				}
			}
		}

		if (!origin_sent) {
			String origin = getOrigin(request);
			if (origin != null) {
				response.addHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
				response.addHeader(ACCESS_CONTROL_ALLOW_ORIGIN, origin);
			} else {
				response.addHeader(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
			}
		} else {
			response.addHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
		}
		return response;
	}

	public static String getOrigin(String origin, String referer) {
		if ((origin != null) && (!"null".equalsIgnoreCase(origin))) {
			return origin;
		}
		if ((referer != null) && (referer.startsWith("http"))) {
			int i = referer.indexOf("//");
			if (i != -1) {
				i = referer.indexOf('/', i + 2);
				if (i != -1) {
					return referer.substring(0, i);
				} else {
					return referer;
				}
			}
		}
		return null;
	}

	public static String getOrigin(HttpServletRequest request) {
		String origin = request.getHeader(ORIGIN_HEADER);
		String referer = request.getHeader(REFERER_HEADER);
		return getOrigin(origin, referer);
	}

}