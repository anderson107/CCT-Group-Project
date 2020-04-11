package com.saturn;

import com.saturn.dao.DataSource;

public class NewMain {

	public static void main(String[] args) {
	DataSource data = DataSource.getInstance();
	data.getFactory();
	Main.main(args);
	}

}
