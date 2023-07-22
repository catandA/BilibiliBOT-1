package com.catand.bilibilibot1.util.json;

import com.alibaba.fastjson.JSONException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {
	public static boolean isValidJSON(String json){
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(json);
			return true;
		} catch (JSONException | JsonProcessingException e) {
		    e.printStackTrace();
			return false;
		}
	}
}
