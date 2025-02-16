package jsonParser;
class JsonParser {
	

	String Json;
	int index;

	public Object parse(String Json) {

		this.Json = Json;
		index = 0;
		return parseValue();

	}

	private Object parseValue() {

		skipWhiteSpace();
		if (Json.charAt(index) == '{')
			return parseObject();
		if (Json.charAt(index) == '[')
			return parseArray();
		if (Json.charAt(index) == '"')
			return parseString();
		if (Json.charAt(index) == '-' || Character.isDigit(Json.charAt(index)))
			return parseNumber();
		if (Json.startsWith("true", index)) {
			index += 4;
			return true;
		}
		if (Json.startsWith("false", index)) {
			index += 5;
			return false;
		}
		if (Json.startsWith("null", index)) {
			index += 4;
			return null;
		}

		throw new RuntimeException("Not a valid JSON value at index :: " + index);

	}

	private Number parseNumber() {

		int start = index;

		while ((index < Json.length())
				&& (Character.isDigit(Json.charAt(index)) || Json.charAt(index) == '-' || Json.charAt(index) == '.'))
			index++;

		String number = Json.substring(start, index);
		System.out.println(number);
		return number.contains(".") ? Double.parseDouble(number) : Integer.parseInt(number);

	}

	private String parseString() {

		index++; // skip '"'
		int start = index;
		while (Json.charAt(index) != '"')
			index++;
		index++;
		System.out.println(Json.substring(start, index - 1));
		return Json.substring(start, index - 1);

	}

	private Object parseArray() {

		JsonArray Array = new JsonArray();

		index++;
		skipWhiteSpace();
		while (Json.charAt(index) != ']') {
			Array.add(parseValue());
			skipWhiteSpace();
			if (Json.charAt(index) == ',')
				index++;
			skipWhiteSpace();
		}
		return Array;

	}

	private Object parseObject() {
		index++; // skip "{"
		skipWhiteSpace();
		JsonObject jsonObject = new JsonObject();

		while (Json.charAt(index) != '}') {

			String key = parseString();
			skipWhiteSpace();
			if (Json.charAt(index) != ':')
				throw new RuntimeException("Expected ':' after key at index " + index);
			index++;
			skipWhiteSpace();
			Object object = parseValue();

			jsonObject.put(key, object);
			System.out.println(jsonObject.toString());
			skipWhiteSpace();
			if (Json.charAt(index) == ',')
				index++;
			skipWhiteSpace();
		}
		index++; // skip '}'
		skipWhiteSpace();

		return jsonObject;
	}

	private void skipWhiteSpace() {

		while (index < Json.length() && Json.charAt(index) == ' ') {
			index++;
		}

	}

}