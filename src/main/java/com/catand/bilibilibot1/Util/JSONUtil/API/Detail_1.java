package com.catand.bilibilibot1.Util.JSONUtil.API;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Detail_1 {
	String qqdocurl;

	public Detail_1() {
	}
}
