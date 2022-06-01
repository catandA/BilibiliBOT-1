package com.catand.bilibilibot1.Util.JSONUtil.API;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Meta {
	Detail_1 detail_1;

	public Meta() {
	}
}
