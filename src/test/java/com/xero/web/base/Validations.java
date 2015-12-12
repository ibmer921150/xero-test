package com.xero.web.base;

import java.util.HashMap;

public class Validations {

	String[] emailPositive = { "email@domain.com",
			"firstname.lastname@domain.com", "email@subdomain.domain.com",
			"firstname+lastname@domain.com", "email@123.123.123.123",
			"1234567890@domain.com", "email@domain-one.com",
			"_______@domain.com", "email@domain.co.cn",
			"firstname-lastname@domain.com" };

	String[] emailNegative = { "plainaddress", "#@%^%#$@#$@#.com",
			"@domain.com", "Joe Smith <email@domain.com>", "email.domain.com",
			"email@domain@domain.com", ".email@domain.com",
			"email.@domain.com", "email..email@domain.com", "你好@domain.com",
			"email@domain.com (Joe Smith)", "email@domain",
			"email@-domain.com", 
//			"email@domain.web", 
//			"email@111.222.333.44444",
			"email@domain..com", "email@[123.123.123.123]",
			"\"email\"@domain.com" };

	String[] datePositive = { "25/03/2015", "03/25/2015", "25-Feb-2015",
			"02-03-2015", "29-Feb-12", "01-Feb-2015", "1-Feb-2015", "03/03/15",
			"28/3/2015" };

	String[] dateNegative = { "31/04/2015", "29-Feb-2015", "13-13-2015",
			"000000", "abcd", "Feb-25-2015" };

	public HashMap<String, String[]> map = new HashMap<String, String[]>();

	private static Validations inst = new Validations();

	private Validations() {
		map.put("emailpositive", emailPositive);
		map.put("emailnegative", emailNegative);
		map.put("datepositive", datePositive);
		map.put("datenegative", dateNegative);
	}

	public static Validations getInstance() {
		return inst;
	}
}
