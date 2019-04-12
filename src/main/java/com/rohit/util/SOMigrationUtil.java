package com.rohit.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TreeMap;

public class SOMigrationUtil {
	public static Map<String, String> columnMap = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);

	public static java.sql.Date dateFormat(String startDate) throws ParseException {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		java.util.Date date = sdf1.parse(startDate);
		java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
		return sqlStartDate;
	}

	static {
		columnMap.put("Payment_method", "SO_TYPE");
		columnMap.put("Transaction Reference", "SO_REFERENCE");
		columnMap.put("Related Reference(MT 202 field 21)", "RELATEDREFERENCE");
		columnMap.put("Currency", "SO_CURRENCY");
		columnMap.put("Amount", "SO_AMOUNT");
		columnMap.put("Order Customer", "ORD_CUSTOMER");
		columnMap.put("Order Bank", "ORD_BANK_DTL");
		columnMap.put("Intermediary", "INTERMEDIARY");
		columnMap.put("Beneficiary Bank", "BENE_BANK");
		columnMap.put("Beneficiary", "BENE_DETAILS");
		columnMap.put("Details_of_payment", "DETAIL_OF_PYMT");
		columnMap.put("Bank to Bank info", "BANK2BANK");
		columnMap.put("Frequency Start_date", "FIRST_ACTIVATION");
		columnMap.put("Frequency End_date", "LAST_ACTIVATION");
		columnMap.put("Frequency", "SCH_INTERVAL");
		columnMap.put("Frequency Back value", "SCH_HLDY_FL");
		columnMap.put("Frequency Period", "SCH_TYPE");
		columnMap.put("Next Activation Date", "SCH_DATE");
		columnMap.put("Standing Order Type", "SO_TYPE");
	}

}
