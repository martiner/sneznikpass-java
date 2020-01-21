package cz.geek.sneznikpass;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = true)
class ListStaysRequest extends Request {

	@JsonProperty("OrgID")
	private final String orgId;

	public ListStaysRequest(@NonNull String authToken, @NonNull String orgId) {
		super(authToken);
		this.orgId = orgId;
	}
}
