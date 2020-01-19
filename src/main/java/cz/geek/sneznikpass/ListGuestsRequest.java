package cz.geek.sneznikpass;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = true)
class ListGuestsRequest extends Request {

	@JsonProperty("OrgID")
	private final String orgId;

	public ListGuestsRequest(@NonNull String authToken, @NonNull String orgId) {
		super(authToken);
		this.orgId = orgId;
	}
}
