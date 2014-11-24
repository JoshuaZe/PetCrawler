package apps.webofknowledge.com;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WoSDataModel {
	private String wosno;
	private String title;
	private String abstrac;
	private List<String> authors=new ArrayList<String>();
	private String magazine;
	private String magazinedetail;
	private String doi;
	private String year;
	private String researharea;
	private String woscategory;
	private String issn;
	private List<String> keywords=new ArrayList<String>();
	private List<String> keywordsplus=new ArrayList<String>();
	private List<String> cites=new ArrayList<String>();
	private List<String> references=new ArrayList<String>();
	private boolean completeflag=false;
	//support re-list
	private String citeStr;
	private String refStr;
	public String getCiteStr() {
		return citeStr;
	}
	public void setCiteStr(String citeStr) {
		this.citeStr = citeStr;
	}
	public String getRefStr() {
		return refStr;
	}
	public void setRefStr(String refStr) {
		this.refStr = refStr;
	}
	public String getWoSNo() {
		return wosno;
	}
	public void setWoSNo(String woSNo) {
		wosno = woSNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getAuthors() {
		return authors;
	}
	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}
	public void addAuthor(String author) {
		this.authors.add(author);
	}
	public String getMagazine() {
		return magazine;
	}
	public void setMagazine(String magazine) {
		this.magazine = magazine;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public List<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
	public void addkeyword(String keyword) {
		this.keywords.add(keyword);
	}
	public List<String> getCites() {
		return cites;
	}
	public void setCites(List<String> cites) {
		this.cites = cites;
	}
	public void addCite(String cite) {
		this.cites.add(cite);
	}
	public void addAllCites(Collection<? extends String> cites) {
		this.cites.addAll(cites);
	}
	public List<String> getReferences() {
		return references;
	}
	public void setReferences(List<String> references) {
		this.references = references;
	}
	public void addReference(String reference) {
		this.references.add(reference);
	}
	public void addAllReferences(Collection<? extends String> references) {
		this.references.addAll(references);
	}
	public String getAbstrac() {
		return abstrac;
	}
	public void setAbstrac(String abstrac) {
		this.abstrac = abstrac;
	}
	public String getMagazinedetail() {
		return magazinedetail;
	}
	public void setMagazinedetail(String magazinedetail) {
		this.magazinedetail = magazinedetail;
	}
	public String getDoi() {
		return doi;
	}
	public void setDoi(String doi) {
		this.doi = doi;
	}
	public List<String> getKeywordsplus() {
		return keywordsplus;
	}
	public void setKeywordsplus(List<String> keywordsplus) {
		this.keywordsplus = keywordsplus;
	}
	public void addKeywordsplus(String keywordsplus) {
		this.keywordsplus.add(keywordsplus);
	}
	public String getResearharea() {
		return researharea;
	}
	public void setResearharea(String researharea) {
		this.researharea = researharea;
	}
	public String getWoscategory() {
		return woscategory;
	}
	public void setWoscategory(String woscategory) {
		this.woscategory = woscategory;
	}
	public String getIssn() {
		return issn;
	}
	public void setIssn(String issn) {
		this.issn = issn;
	}
	public boolean isCompleteflag() {
		return completeflag;
	}
	public void setCompleteflag(boolean completeflag) {
		this.completeflag = completeflag;
	}
}
