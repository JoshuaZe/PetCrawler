package apps.webofknowledge.com;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class WoSPaperPageProcess implements PageProcessor {

	private Site site = Site.me().setRetryTimes(5000).setSleepTime(100).setTimeOut(30000);
	
	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {
		WoSDataModel WoSPaper= new WoSDataModel();
		if(!page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div/p[3]/value/text()").toString().trim().contains("WOS:"))page.setSkip(true);
		int divsizes=page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div").all().size();
		if((page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div").all().size()==10||
				page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div").all().size()==11)){
			//output
			System.out.println(page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div[1]/value/text()").toString().trim());
			System.out.println(page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div[3]/p/value/text()").all()
					  .get(page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div[3]/p/span/text()").all().indexOf("Published:")+1));
			if(page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div[3]/p/span/text()").all().contains("DOI:")){
				System.out.println(page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div[3]/p/value/text()").all()
						  .get(page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div[3]/p/span/text()").all().indexOf("DOI:")+1));
			}
			if(page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div[3]/p[2]/value/text()").toString().trim().isEmpty()){
				System.out.println(page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div[3]/p[2]/value/span/text()").toString().trim());
			}else{
				System.out.println(page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div[3]/p[2]/value/text()").toString().trim());
			}
			System.out.println(page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div[4]/p/text()").toString().trim());
			System.out.println(page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div[2]/p/text()").toString().trim());
			if(!page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div["+divsizes+"]/p[3]/a/@href").all().isEmpty()){
				System.out.println(page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div["+divsizes+"]/p[3]/a/@href").toString().trim());
			}
			
			//Model
			WoSPaper.setWoSNo(page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div/p[3]/value/text()").toString().trim());
			
			WoSPaper.setTitle(page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div[1]/value/text()").toString().trim());
	
			WoSPaper.setYear(page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div[3]/p/value/text()").all()
				  .get(page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div[3]/p/span/text()").all().indexOf("Published:")+1));
			//null
			if(page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div[3]/p/span/text()").all().contains("DOI:")){
				WoSPaper.setDoi(page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div[3]/p/value/text()").all()
				  .get(page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div[3]/p/span/text()").all().indexOf("DOI:")+1));
			}
			
			if(page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div[3]/p[2]/value/text()").toString().trim().isEmpty()){
				WoSPaper.setMagazine(page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div[3]/p[2]/value/span/text()").toString().trim());
			}else{
				WoSPaper.setMagazine(page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div[3]/p[2]/value/text()").toString().trim());
			}
			
			//null
			//WoSPaper.setMagazinedetail(page.getHtml().xpath("").toString().trim());
			//null
			//WoSPaper.setIssn(page.getHtml().xpath("").toString().trim());
			//null
			//WoSPaper.setResearharea(page.getHtml().xpath("").toString().trim());
			//null
			//WoSPaper.setWoscategory(page.getHtml().xpath("").toString().trim());
			WoSPaper.setAbstrac(page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div[4]/p/text()").toString().trim());
			
			//list
			if(!page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div[5]/p[1]/a/text()").all().isEmpty()){
				WoSPaper.setKeywords(page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div[5]/p[1]/a/text()").all());
			}
			if(!page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div[5]/p[2]/a/text()").all().isEmpty()){
				WoSPaper.setKeywordsplus(page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div[5]/p[2]/a/text()").all());
			}
			
			String[] tempAU=page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div[2]/p/text()").toString().split(";");
			for(int iAU=0;iAU<tempAU.length;iAU++){
				WoSPaper.addAuthor(tempAU[iAU].trim().substring(1, tempAU[iAU].trim().length()-1));
			}
			
			//WoSPaper.setRefStr(page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div/p[2]/a/@href").toString().trim());
			//null
			if(!page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div["+divsizes+"]/p[3]/a/@href").all().isEmpty()){
				WoSPaper.setCiteStr(page.getHtml().xpath("//*[@id=\"records_form\"]/div/div/div/div[1]/div/div["+divsizes+"]/p[3]/a/@href").toString().trim());
			}
			
			page.putField(WoSPaper.getWoSNo(), WoSPaper);
		}else{
			page.setSkip(true);
		}
		//next paper page
		page.addTargetRequest(page.getHtml().xpath("//*[@id=\"paginationForm\"]/span[2]/a[2]/@href").toString().trim());
		System.out.println("----------------------------------------------------");
	}

}
