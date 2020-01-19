package cz.geek.sneznikpass;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static lombok.AccessLevel.PACKAGE;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = PACKAGE)
@JsonInclude(value = NON_NULL)
abstract class Request {

	@NonNull
	@JsonProperty("AuthToken")
	private String authToken;

}
