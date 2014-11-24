package datacenter.mep.gov.cn.air.dairy.aqi;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class MEPPageProcess implements PageProcessor{

	private Site site = Site.me().setRetryTimes(10000).setSleepTime(100).setTimeOut(30000);
	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {
		MEPDataModel MEPItem;
		Integer itemsNumber = page.getHtml().xpath("//*[@id=\"report1\"]/tbody/tr[@height=\"30\"]").all().size()-2;
		System.out.println(itemsNumber);
		for(int i=3;i<=itemsNumber+2;i++){
			List<String> item=page.getHtml().xpath("//*[@id=\"report1\"]/tbody/tr["+i+"]/td/text()").all();
			MEPItem = new MEPDataModel();
			MEPItem.setNumber(item.get(0));
			MEPItem.setCity(item.get(1));
			MEPItem.setDate(item.get(2));
			MEPItem.setPI(item.get(3));
			MEPItem.setPollution(item.get(4));
			MEPItem.setAIRQL(item.get(5));
			MEPItem.setAIRQS(item.get(6));
			page.putField(MEPItem.getNumber(), MEPItem);
		}
		String pageNow = page.getHtml().xpath("//*[@id=\"report1\"]/tbody/tr/td/input[@name=\"pageNum\"]/@value").toString().trim();
		String pageAll = page.getHtml().xpath("//*[@id=\"report1\"]/tbody/tr[@height=\"25\"]/td[1]/b[2]/font/text()").toString().trim();
		System.out.println(pageNow+"-"+pageAll);
		Integer pageNext=Integer.parseInt(pageNow)+1;
		if(!pageNow.equals(pageAll)){
			page.addTargetRequest(page.getRequest().getUrl().substring(0, page.getRequest().getUrl().length()-pageNow.length())+pageNext);
		}
	}
	
}
