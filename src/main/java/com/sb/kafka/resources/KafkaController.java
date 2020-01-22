/**
 * 
 */
package com.sb.kafka.resources;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sb.kafka.dto.Response;

/**
 * @author ankur-mahajan
 *
 */
@RestController
@RequestMapping("/api")
public class KafkaController {

	private final AtomicLong counter = new AtomicLong();

	@RequestMapping(value = "/status", method = RequestMethod.GET)
	private ResponseEntity<Response> getStatus() {
		Response response = new Response();
		response.setSuccess(true);
		response.setMessage("Service is running!!");
		response.setData(counter.incrementAndGet());
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
