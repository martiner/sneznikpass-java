package cz.geek.sneznikpass;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Authentication extends Response {

	@NonNull
	@JsonProperty("Token")
	private String token;

	@NonNull
	@JsonProperty("OrganizationID")
	private String organizationId;

	@JsonProperty("ID")
	private String id;

	@JsonProperty("OrganizationType")
	private String organizationType;

	@JsonProperty("Name")
	private String name;

	@JsonProperty("Role")
	private String role;
}
