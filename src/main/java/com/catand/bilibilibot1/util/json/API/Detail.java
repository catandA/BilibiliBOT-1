package com.catand.bilibilibot1.util.json.API;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Detail {
	String qqdocurl;

	public Detail() {
	}
}
