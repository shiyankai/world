package com.fileDeal;

public class LineContext {
	StringBuffer sb = null;

	LineContext() {
		sb = new StringBuffer();
	}

	void appenLine(Object obj) {
		sb.append(obj + "\n");
	}

	void clear() {
		sb = new StringBuffer();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return sb.toString();
	}
}
