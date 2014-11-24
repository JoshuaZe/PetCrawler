package apps.webofknowledge.com;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class WoSListPageProcess implements PageProcessor {

	private Site site = Site.me().setRetryTimes(5000).setSleepTime(100).setTimeOut(30000);
	
	@Override
	public Site getSite() {
		return site;
	}
	
	@Override
	public void process(Page page) {
		page.putField("firstpaperurloflist", page.getHtml().xpath("//*[@id=\"RECORD_1\"]/div[3]/div[1]/div/a/@href").toString());
	}

}
