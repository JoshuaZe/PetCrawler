package datacenter.mep.gov.cn.air.dairy.aqi;

public class MEPDataModel {
	private String Number;
	private String City;
	private String Date;
	//private String AQI;
	private String PI;
	private String Pollution;
	private String AIRQL;
	private String AIRQS;
	
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
	public String getPollution() {
		return Pollution;
	}
	public void setPollution(String pollution) {
		Pollution = pollution;
	}
	public String getPI() {
		return PI;
	}
	public void setPI(String pI) {
		PI = pI;
	}
	public String getAIRQL() {
		return AIRQL;
	}
	public void setAIRQL(String aIRQL) {
		AIRQL = aIRQL;
	}
	public String getAIRQS() {
		return AIRQS;
	}
	public void setAIRQS(String aIRQS) {
		AIRQS = aIRQS;
	}
}
