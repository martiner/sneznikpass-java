package cz.geek.sneznikpass;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
@JsonInclude(value = NON_NULL)
public class Credentials {

	@NonNull
	@JsonProperty("Email")
	private final String email;

	@NonNull
	@JsonProperty("Password")
	private final String password;

}
