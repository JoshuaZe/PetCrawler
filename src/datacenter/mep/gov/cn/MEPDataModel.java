package datacenter.mep.gov.cn;

public class MEPDataModel {
	private String Number;
	private String City;
	private String Date;
	private String AQI;
	private String AIRQ;
	private String Pollution;
	
	public String getNumber() {
		return Number;
	}
	public void setNumber(String number) {
		Number = number;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getAQI() {
		return AQI;
	}
	public void setAQI(String aQI) {
		AQI = aQI;
	}
	public String getAIRQ() {
		return AIRQ;
	}
	public void setAIRQ(String aIRQ) {
		AIRQ = aIRQ;
	}
	public String getPollution() {
		return Pollution;
	}
	public void setPollution(String pollution) {
		Pollution = pollution;
	}
}
