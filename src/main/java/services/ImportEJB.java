package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;

import jpas.JPA;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.collect.Lists;

import daos.CallFailureDAO;
import daos.EventCauseDAO;
import daos.FailureClassDAO;
import daos.InvalidCallFailureDAO;
import daos.MccMncDAO;
import daos.UETypeDAO;
import entities.CallFailure;
import entities.EventCause;
import entities.EventCausePK;
import entities.FailureClass;
import entities.InvalidCallFailure;
import entities.MccMnc;
import entities.MccMncPK;
import entities.UEType;

@Stateless
@WebService(endpointInterface="services.ImportService")
@Remote(ImportService.class)
public class ImportEJB implements ImportService {
	private static DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private List<CallFailure> callFailures = new ArrayList<CallFailure>();
	private List<InvalidCallFailure> invalidCallFailures = new ArrayList<InvalidCallFailure>();
	private CallFailureDAO failureDAO;
	private InvalidCallFailureDAO invalidDAO;
	private EventCauseDAO eventCauseDAO;
	private FailureClassDAO failureClassDAO;
	private UETypeDAO ueTypeDAO;
	private MccMncDAO mccMncDAO;
	
	@Inject
	public void setDao(@JPA CallFailureDAO dao) {
		this.failureDAO = dao;
	}
	
	@Inject
	public void setInvalidDao(@JPA InvalidCallFailureDAO dao) {
		this.invalidDAO = dao;
	}
	
	@Inject
	public void setFailureClassDao(@JPA FailureClassDAO dao) {
		this.failureClassDAO = dao;
	}
	
	@Inject
	public void setUetypeDao(@JPA UETypeDAO dao) {
		this.ueTypeDAO = dao;
	}
	
	@Inject
	public void setMccMncDao(@JPA MccMncDAO dao) {
		this.mccMncDAO = dao;
	}
	
	@Inject
	public void setEventcauseDao(@JPA EventCauseDAO dao) {
		this.eventCauseDAO = dao;
	}
	
	public void addToDatabase(File uploadedFile, String fileExtension){
		generateDatabase(uploadedFile, fileExtension);
	}
	
	private void generateDatabase(File uploadedFile, String fileExtension) {
		Workbook excelData = null;
		try {
			if(fileExtension.equals("xls")){
				excelData = new HSSFWorkbook(new FileInputStream(uploadedFile));
			} else if(fileExtension.equals("xlsx")){
				excelData = new XSSFWorkbook(new FileInputStream(uploadedFile));
			}
			
			List<Row> eventCauseRows = Lists.newArrayList(excelData.getSheetAt(1).iterator());
			List<Row> failureClassRows = Lists.newArrayList(excelData.getSheetAt(2).iterator());
			List<Row> ueTypeRows = Lists.newArrayList(excelData.getSheetAt(3).iterator());
			List<Row> mccMncRows = Lists.newArrayList(excelData.getSheetAt(4).iterator());
			List<Row> callFailureRows = Lists.newArrayList(excelData.getSheetAt(0).iterator());
			
			for(Row row : eventCauseRows.subList(1, eventCauseRows.size())){
				parseEventCauseCells(row.cellIterator());
			}
			
			for(Row row : failureClassRows.subList(1, failureClassRows.size())){
				parseFailureClassCells(row.cellIterator());
			}

			for(Row row : ueTypeRows.subList(1, ueTypeRows.size())){
				parseUETypeCells(row.cellIterator());
			}
			
			for(Row row : mccMncRows.subList(1, mccMncRows.size())){
				parseMCCMNCCells(row.cellIterator());
			}
			
			for(Row row : callFailureRows.subList(1, callFailureRows.size())){
				parseCallFailureCells(row.cellIterator());
			}
			
//			int count = 0;
//			for(CallFailure callFailure : callFailures){
//				failureDAO.addCallFailure(callFailure);
//				System.out.println(count++);
//			}
			
			
			failureDAO.addManyCallFailures(callFailures);
			
			for(InvalidCallFailure invalidCallFailure : invalidCallFailures){
				invalidDAO.addInvalidCallFailure(invalidCallFailure);
			}
			
			
		} catch (IOException e) {
		}
	}
	
	private void parseEventCauseCells(Iterator<Cell> cellIterator) {
		int causeCode;
		String desc;
		int eventId;

		causeCode = (int) cellIterator.next().getNumericCellValue();
    	eventId = (int) cellIterator.next().getNumericCellValue();
    	desc = cellIterator.next().getStringCellValue();
    	
    	EventCause ec = new EventCause();
    	EventCausePK ecPk = new EventCausePK();
    	ecPk.setA_Event_ID(eventId);
    	ecPk.setB_Cause_Code(causeCode);
    	ec.setId(ecPk);
    	ec.setDescription(desc);
    
    	eventCauseDAO.addEventCause(ec);
	}
	
	private void parseFailureClassCells(Iterator<Cell> cellIterator){
		int failureClass;
		String desc;
    	
		failureClass = (int) cellIterator.next().getNumericCellValue();
    	desc = cellIterator.next().getStringCellValue();
    	
    	FailureClass fc = new FailureClass();
    	fc.setFailureClass(failureClass);
    	fc.setDescription(desc);
    	failureClassDAO.addFailureClass(fc);
	}
	
	private void parseUETypeCells(Iterator<Cell> cellIterator) {
		int tac;
		String marketingName = "";
		String manu;
		String access;
		String model = "";
		String vName;
		String ueType;
		String os;
		String inputMode;

		Cell next;
		int nextType;

		tac = (int) cellIterator.next().getNumericCellValue();

		next = cellIterator.next();
		nextType = next.getCellType();

		if(nextType == Cell.CELL_TYPE_NUMERIC)
			marketingName = String.valueOf(next.getNumericCellValue());
		else if(nextType == Cell.CELL_TYPE_STRING)
			marketingName = next.getStringCellValue();

		manu = cellIterator.next().getStringCellValue();
		access = cellIterator.next().getStringCellValue();

		next = cellIterator.next();
		nextType = next.getCellType();

		if(nextType == Cell.CELL_TYPE_NUMERIC)
			model = String.valueOf(next.getNumericCellValue());
		else if(nextType == Cell.CELL_TYPE_STRING)
			model = next.getStringCellValue();

		vName = cellIterator.next().getStringCellValue();
		ueType = cellIterator.next().getStringCellValue();
		os = cellIterator.next().getStringCellValue();
		inputMode = cellIterator.next().getStringCellValue();


		UEType uet = new UEType();
		uet.setTac(tac);
		uet.setMarketingName(marketingName);
		uet.setManufacturer(manu);
		uet.setAccessCapability(access);
		uet.setModel(model);
		uet.setVendorName(vName);
		uet.setUEType(ueType);
		uet.setOs(os);
		uet.setInputMode(inputMode);
		ueTypeDAO.addUEType(uet);
	}
	
	private void parseMCCMNCCells(Iterator<Cell> cellIterator) {
		int mcc;
		int mnc;
		String country;
		String operator;

		mcc = (int) cellIterator.next().getNumericCellValue();
		mnc = (int) cellIterator.next().getNumericCellValue();
		country = cellIterator.next().getStringCellValue();
		operator = cellIterator.next().getStringCellValue();

		MccMnc mccmnc = new MccMnc();
		MccMncPK mpk = new MccMncPK();
		mpk.setMcc(mcc);
		mpk.setMnc(mnc);

		mccmnc.setId(mpk);
		mccmnc.setCountry(country);
		mccmnc.setOperator(operator);

    	mccMncDAO.addMccMnc(mccmnc);
	}
	
	private void parseCallFailureCells(Iterator<Cell> cellIterator) {
		Date date;
		int eventId;
		int ueType;
		int market;
		int operator;
		int cellId;
		int duration;
		int failureClass = -1;
		int causeCode = -1;
		String invalidFailureClass = "";
		String invalidCauseCode = "";
		String neVersion;
		long imsi;
		long hier3_id;
		long hier32_id;
		long hier321_id;
		Cell next;
		int nextType;
		
		boolean valid = true;
		
		date = cellIterator.next().getDateCellValue();
		
		if(!isDateValid(date)){
			valid = false;
		}
		
		eventId = (int) cellIterator.next().getNumericCellValue();
		
		next = cellIterator.next();
		nextType = next.getCellType();
		
		if(nextType == Cell.CELL_TYPE_STRING){
			invalidFailureClass = next.getStringCellValue();
			valid = false;
		}
		else{
			failureClass = (int) next.getNumericCellValue();
			if(!valid || !failureClassDAO.doesFailureClassExist(failureClass)){
				valid = false;
				invalidFailureClass = String.valueOf(failureClass);
			}
		}
		
		ueType = (int) cellIterator.next().getNumericCellValue();
		
		if(!valid || !ueTypeDAO.doesUETypeExist(ueType)){
			valid = false;
		}
		
		market = (int) cellIterator.next().getNumericCellValue();
		operator = (int) cellIterator.next().getNumericCellValue();
		
		MccMncPK mpk = new MccMncPK();
		mpk.setMcc(market);
		mpk.setMnc(operator);
		
		if(!valid || !mccMncDAO.doesMccMncExist(mpk)){
			valid = false;	
		}
		
		cellId = (int) cellIterator.next().getNumericCellValue();
		duration = (int) cellIterator.next().getNumericCellValue();
		
		next = cellIterator.next();
		nextType = next.getCellType();
		
		if(nextType == Cell.CELL_TYPE_STRING){
			invalidCauseCode = next.getStringCellValue();
			valid = false;
		}
		else{
			causeCode = (int) next.getNumericCellValue();
			
			EventCausePK epk = new EventCausePK();
			epk.setA_Event_ID(eventId);
			epk.setB_Cause_Code(causeCode);
			
			if(!valid || !eventCauseDAO.doesEventCauseExist(epk)){
				valid = false;
				if(invalidFailureClass.equals("")){
					invalidFailureClass = String.valueOf(failureClass);
				}
				invalidCauseCode = String.valueOf(causeCode);
			}
		}
		
		neVersion = cellIterator.next().getStringCellValue();
		imsi = (long) cellIterator.next().getNumericCellValue();
		hier3_id = (long) cellIterator.next().getNumericCellValue();
		hier32_id = (long) cellIterator.next().getNumericCellValue();
		hier321_id = (long) cellIterator.next().getNumericCellValue();
		
		if(valid){
			EventCausePK epk = new EventCausePK();
			epk.setA_Event_ID(eventId);
			epk.setB_Cause_Code(causeCode);
			
			CallFailure cf = new CallFailure();
			cf.setDate(date);
			cf.setEventCause(eventCauseDAO.getEventCause(epk));
			cf.setFailureClass(failureClassDAO.getFailureClass(failureClass));
			cf.setUEType(ueTypeDAO.getUEType(ueType));
			cf.setMccMnc(mccMncDAO.getMCC_MNC(mpk));
			cf.setCellId(cellId);
			cf.setDuration(duration);
			cf.setNEVersion(neVersion);
			cf.setImsi(BigInteger.valueOf(imsi));
			cf.setHier3Id(hier3_id);
			cf.setHier32Id(hier32_id);
			cf.setHier321Id(hier321_id);
//			failureDAO.addCallFailure(cf);
			callFailures.add(cf);
		} else{
			InvalidCallFailure icf = new InvalidCallFailure();
			icf.setDate(date);
			icf.setEventId(eventId);
			icf.setFailureClass(invalidFailureClass);
			icf.setUEType(ueType);
			icf.setMarket(market);
			icf.setOperator(operator);
			icf.setCellId(cellId);
			icf.setDuration(duration);
			icf.setCauseCode(invalidCauseCode);
			icf.setImsi(BigInteger.valueOf(imsi));
			icf.setHier3Id(hier3_id);
			icf.setHier32Id(hier32_id);
			icf.setHier321Id(hier321_id);
//			invalidDAO.addInvalidCallFailure(icf);
			invalidCallFailures.add(icf);
		}
	}
	
	private static boolean isDateValid(Date date){
		String dateString = dateFormatter.format(date);
		
		int day = Integer.parseInt(dateString.substring(0, 2));
		int month = Integer.parseInt(dateString.substring(3, 5));
		int year = Integer.parseInt(dateString.substring(6, 10));
		int hour = Integer.parseInt(dateString.substring(11, 13));
		int min = Integer.parseInt(dateString.substring(14, 16));
		
		if(day < 1 || day > 31){
			return false;
		}
		if(month < 1 || month > 12){
			return false;
		}
		if(year < 2000 || year > Calendar.getInstance().get(Calendar.YEAR)){
			return false;
		}
		if(hour < 0 || hour > 23){
			return false;
		}
		if(min < 0 || min > 59){
			return false;
		}
		
		//April, June, September, November
		if((month == 4 || month == 6 || month == 9 || month == 9) && (day > 30)){
			return false;
		}
		
		//February
		if(month == 2){
			if(isLeapYear(year) && day > 29){
				return false;
			}
			if(day > 28){
				return false;
			}
		}
		return true;
	}
	
	private static boolean isLeapYear(int year){
		return ((year%4 == 0) && (year%100 != 0) || (year%400 == 0));
	}
}
