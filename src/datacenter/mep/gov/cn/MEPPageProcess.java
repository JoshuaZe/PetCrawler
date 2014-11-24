package datacenter.mep.gov.cn;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class MEPPageProcess implements PageProcessor{

	private Site site = Site.me();
	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {
		MEPDataModel MEPItem;
		Integer itemsNumber = page.getHtml().xpath("//*[@id=\"report1\"]/tbody/tr[@height=\"30\"]").all().size()-2;
		for(int i=3;i<=itemsNumber+2;i++){
			List<String> item=page.getHtml().xpath("//*[@id=\"report1\"]/tbody/tr["+i+"]/td/text()").all();
			MEPItem = new MEPDataModel();
			MEPItem.setNumber(item.get(0));
			MEPItem.setCity(item.get(1));
			MEPItem.setDate(item.get(2));
			MEPItem.setAQI(item.get(3));
			MEPItem.setAIRQ(item.get(4));
			MEPItem.setPollution(item.get(5));
			page.putField(MEPItem.getNumber(), MEPItem);
		}
		String pageNow = page.getHtml().xpath("//*[@id=\"report1\"]/tbody/tr/td/input[@name=\"pageNum\"]/@value").toString().trim();
		String pageAll = page.getHtml().xpath("//*[@id=\"report1\"]/tbody/tr/td[1]/b[2]/font/text()").toString().trim();
		Integer pageNext=Integer.parseInt(pageNow)+1;
		if(!pageNow.equals(pageAll)){
			//System.out.println(page.getRequest().getUrl().substring(0, page.getRequest().getUrl().length()-pageNow.length())+pageNext);
			page.addTargetRequest(page.getRequest().getUrl().substring(0, page.getRequest().getUrl().length()-pageNow.length())+pageNext);
		}
	}
	
}
