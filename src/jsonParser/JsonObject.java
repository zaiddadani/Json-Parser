package jsonParser;

import java.util.HashMap;
import java.util.Map;

class JsonObject {

	Map<String, Object> json = new HashMap<>();

	public Object get(String key) {

		return json.get(key);

	}

	public void put(String key, Object Value) {

		json.put(key, Value);
	}

	@Override
	public String toString() {
		return "" + json + "";
	}

}