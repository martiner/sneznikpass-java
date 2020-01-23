package cz.geek.sneznikpass;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Response {

	@JsonProperty("Time")
	private String time;

	@JsonProperty("Status")
	private String status;

}
