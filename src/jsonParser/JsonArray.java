package jsonParser;

import java.util.ArrayList;
import java.util.List;

class JsonArray {

	List<Object> list = new ArrayList<>();

	public void add(Object value) {
		list.add(value);
	}

	public Object get(int index) {

		return list.get(index);
	}

	@Override
	public String toString() {
		return "JsonArray [list=" + list + "]";
	}

}