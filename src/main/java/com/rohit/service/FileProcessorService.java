package com.rohit.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLConnection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rohit.constant.Constants;
import com.rohit.dao.SchedulerRepo;
import com.rohit.dao.StandingOrderRepo;
import com.rohit.exception.SORuntimeException;
import com.rohit.model.Scheduler;
import com.rohit.model.StandingOrder;
import com.rohit.util.SOMigrationUtil;

@Service
public class FileProcessorService {

	@Autowired
	StandingOrderRepo fileUploadingStandingOrderRepo;

	@Autowired
	SchedulerRepo fileUploadingSchedulerRepo;

	/**
	 * This method is used to read CSV file and map its content to Scheduler and
	 * StandingOrder objects.
	 * 
	 */
	public void process(String filePath, MultipartFile file) throws SQLException, IOException, ParseException {

		String fileName = filePath + file.getOriginalFilename();
		FileProcessorService service = new FileProcessorService();
		BufferedReader br = null;
		String line = "";
		String[] headerValue;
		int headerCount = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> list = new ArrayList<>();
		StandingOrder order = null;
		Scheduler scheduler = null;
		List<String> outputList = new ArrayList<>();
		try {

			br = new BufferedReader(new FileReader(fileName));

			while ((line = br.readLine()) != null) {

				if (headerCount == 0) {
					outputList.add(line);
					headerValue = line.split(",");
					for (String str : headerValue)
						list.add(str);
					headerCount++;
				} else {

					List<String> tempList = new ArrayList<>();
					map = new HashMap<String, Object>();
					int counter = 0;
					long countComma = line.chars().filter(ch -> ch == ',').count();
					if (countComma == list.size() - 1) {
						headerValue = line.split(",");
						for (String str : headerValue) {
							map.put((String) list.get(counter), str);
							tempList.add(str);
							counter++;
						}
						scheduler = service.mapScheduler(map);
						order = service.mapStandingOrder(map);
						try {
							scheduler = fileUploadingSchedulerRepo.save(scheduler);
							order = fileUploadingStandingOrderRepo.save(order);
						} catch (Exception ex) {
							outputList.add(line);

						}
					}

				}

			}
			if (outputList.size() > 1) {
				downloadCSVFile(outputList, filePath);
				throw new SORuntimeException(
						Constants.FILE_ERROR_MESSAGE + filePath + "output.csv");
			}

		} catch (SORuntimeException ex) {
			throw new SORuntimeException(ex.getMessage());
		} catch (Exception ex) {
			throw new SORuntimeException(ex.getMessage());
		} finally {
			if (null != br)
				br.close();
		}

	}

	/**
	 * This method maps CSV file column to respective Scheduler object
	 */
	private Scheduler mapScheduler(Map<String, Object> map) {
		Scheduler scheduler = new Scheduler();
		try {
			for (Entry<String, Object> entry : map.entrySet()) {
				if (SOMigrationUtil.columnMap.containsKey(entry.getKey())) {
					String value = SOMigrationUtil.columnMap.get(entry.getKey());
					if (value.equalsIgnoreCase(Constants.SCH_TYPE)) {
						scheduler.setSchedulerType((String) entry.getValue());
					} else if (value.equalsIgnoreCase(Constants.SCH_DATE)) {
						scheduler.setSchedulerDate(SOMigrationUtil.dateFormat((String) entry.getValue()));
					} else if (value.equalsIgnoreCase(Constants.SCH_INTERVAL)) {
						scheduler.setSchedulerInterval(Integer.parseInt((String) entry.getValue()));
					} else if (value.equalsIgnoreCase(Constants.FIRST_ACTIVATION)) {
						// scheduler.setFirstActivation(firstActivation);
					} else if (value.equalsIgnoreCase(Constants.LAST_ACTIVATION)) {
						// scheduler.setLastActivation(lastActivation);
					} else if (value.equalsIgnoreCase(Constants.SCH_HLDY_FL)) {
						scheduler.setSchedulerHldyFl(((String) entry.getValue()).charAt(0));
					}
				}

			}
		} catch (Exception e) {
			throw new SORuntimeException(e.getMessage());

		}
		return scheduler;

	}

	/**
	 * This method maps CSV file column to respective StandingOrder object
	 */
	private StandingOrder mapStandingOrder(Map<String, Object> map) {
		StandingOrder order = new StandingOrder();
		try {
			for (Entry<String, Object> entry : map.entrySet()) {
				if (SOMigrationUtil.columnMap.containsKey(entry.getKey())) {
					String value = SOMigrationUtil.columnMap.get(entry.getKey());
					if (value.equalsIgnoreCase(Constants.SO_TYPE)) {
						order.setStandingOrderType((String) entry.getValue());
					} else if (value.equalsIgnoreCase(Constants.SO_REFERENCE)) {
						order.setReference((String) entry.getValue());
					} else if (value.equalsIgnoreCase(Constants.SO_CURRENCY)) {
						order.setCurrency((String) entry.getValue());
					} else if (value.equalsIgnoreCase(Constants.SO_AMOUNT)) {
						order.setAmount(new BigDecimal((String) entry.getValue()));
					} else if (value.equalsIgnoreCase(Constants.BENE_DETAILS)) {
						order.setBeneficiaryDETAILS((String) entry.getValue());
					} else if (value.equalsIgnoreCase(Constants.RELATEDREFERENCE)) {
						order.setRelatedReference((String) entry.getValue());
					} else if (value.equalsIgnoreCase(Constants.INTERMED_DTL)) {
						order.setIntermediary((String) entry.getValue());
					} else if (value.equalsIgnoreCase(Constants.ORD_CUSTOMER)) {
						order.setOrderCustomer((String) entry.getValue());
					} else if (value.equalsIgnoreCase(Constants.DETAIL_OF_PYMT)) {
						order.setDetailsOfPayment((String) entry.getValue());
					} else if (value.equalsIgnoreCase(Constants.BENE_BANK)) {
						order.setBeneficiaryBank((String) entry.getValue());
					} else if (value.equalsIgnoreCase(Constants.BANK2BANK)) {
						order.setBank2Bank((String) entry.getValue());
					} else if (value.equalsIgnoreCase(Constants.ORD_BANK_DTL)) {
						order.setOrderingBankDetails((String) entry.getValue());
					}
				}

			}
		} catch (Exception e) {
			throw new SORuntimeException(e.getMessage());

		}
		return order;

	}

	public void downloadCSVFile(List<String> list, String filePath) throws IOException {

		File file = new File(filePath + "//output.csv");
		file.createNewFile();
		if (file.exists()) {
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				mimeType = "application/CSV";
			}
			FileWriter fileWriter = null;

			try {
				fileWriter = new FileWriter(file.toString());

				for (String str : list) {
					fileWriter.append(str);
					fileWriter.append("\n");
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				try {
					fileWriter.flush();
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

	}

}
