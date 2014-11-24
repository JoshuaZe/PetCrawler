package apps.webofknowledge.com;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.ResultItemsCollectorPipeline;
import util.SimpleFileWriter;

public class WoSCrawler {
	private static HashMap<String,WoSDataModel> WoSResults=new HashMap<String,WoSDataModel>();
	public static class MyAuthenticator extends Authenticator {
        private String user = "";
        private String password = "";
        public MyAuthenticator(String user, String password) {
            this.user = user;
            this.password = password;
        }
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, password.toCharArray());
        }
    }
	/**
	 * only english articles on web of science
	 * no session consideration
	 * default deep is one list only
	 * @param urloflist
	 * @param urloflist outputlocation(folder)
	 * @param urloflist outputlocation(folder) deepofcited deepofreference = 0
	 * @author Joshua
	 */
	public static void main(String[] args) {
		System.setProperty("http.proxySet", "true");
		System.setProperty("http.proxyHost", "222.29.196.232");
		System.setProperty("http.proxyPort", "808");
		System.setProperty("http.nonProxyHosts", "localhost|192.168.0.*");
		Authenticator.setDefault(new MyAuthenticator("Joshua", "90Y12M23D"));
		
		String urloflist="http://apps.webofknowledge.com/Search.do?product=UA&SID=X2H2b5tKcZesbXP1euO&search_mode=Refine&prID=dc448e15-08a3-4027-a2d1-c2c68e974b2c";
		String outputlocation="WOSPaper";
		Integer deepofcited=2;
		Integer deepofreference=0;
		//@param urloflist outputlocation deepofcited deepofreference
		if(args.length==4){
			urloflist=args[0];
			outputlocation=args[1]+"\\";
			deepofcited=Integer.parseInt(args[2]);
			//deepofreference=Integer.parseInt(args[3]);
			System.out.println("outputlocation : "+outputlocation);
		}else if(args.length==2){
			urloflist=args[0];
			outputlocation=args[1]+"\\";
			System.out.println("outputlocation : "+outputlocation);
		}else if(args.length==1){
			urloflist=args[0];
			outputlocation=outputlocation+"\\";
			System.out.println("outputlocation : "+outputlocation);
		}else{
			outputlocation=outputlocation+"\\";
			System.out.println("outputlocation : "+outputlocation);
			System.out.println("wrong numbers of args");
			System.out.println("java -jar MEPCrawler.jar \"urloflist\" outputlocation (deepofcited=2)");
		}
		
		if(urloflist==null)return;
		
		try {
			doWoSListCrawler(urloflist,deepofcited,deepofreference);
		} catch (Exception e) {
			System.out.println("Wrong With doWoSListCrawler");
		}
		
		System.out.println("doWoSListCrawler finished!" + WoSResults.size());
		SimpleFileWriter writer=null;
		Iterator iter = WoSResults.entrySet().iterator();
		int n=0;
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			WoSDataModel item=(WoSDataModel)entry.getValue();
			n++;
			writer = SimpleFileWriter.openFileForWriting(outputlocation+n+".txt");
			if (writer == null) {
			    System.out.println("Couldn't open file!");
			    continue;
			}
			
			//not null
			writer.print("WoSNo:");
			writer.println(item.getWoSNo());
			//not null
			writer.print("Title:");
			writer.println(item.getTitle());
			//not null
			writer.print("Year:");
			writer.println(item.getYear());
			//null
			if(item.getDoi()!=null){
				writer.print("DOI:");
				writer.println(item.getDoi());
			}
			//not null
			writer.print("Magazine:");
			writer.println(item.getMagazine());
			//null
			//writer.println(item.getMagazinedetail());
			//null
			//writer.println(item.getIssn());
			//not null
			//writer.println(item.getResearharea());
			//not null
			//writer.println(item.getWoscategory());
			//not null
			writer.print("Abstract:");
			writer.println(item.getAbstrac());
			
			int i;
			//not null
			if(item.getKeywords().size()!=0){
				writer.print("Keywords:");
				for(i=0;i<item.getKeywords().size();i++){
					if(i==item.getKeywords().size()-1){
						writer.println(item.getKeywords().get(i));
					}else{
						writer.print(item.getKeywords().get(i)+";");
					}
				}
			}
			//not null
			if(item.getKeywordsplus().size()!=0){
				writer.print("KeywordsPlus:");
				for(i=0;i<item.getKeywordsplus().size();i++){
					if(i==item.getKeywordsplus().size()-1){
						writer.println(item.getKeywordsplus().get(i));
					}else{
						writer.print(item.getKeywordsplus().get(i)+";");
					}
				}
			}
			//not null
			writer.print("Authors:");
			for(i=0;i<item.getAuthors().size();i++){
				if(i==item.getAuthors().size()-1){
					writer.println(item.getAuthors().get(i));
				}else{
					writer.print(item.getAuthors().get(i)+";");
				}
			}
			//null
			/*writer.print("References:");
			for(i=0;i<item.getReferences().size();i++){
				if(i==item.getReferences().size()-1){
					writer.println(item.getReferences().get(i));
				}else{
					writer.print(item.getReferences().get(i)+";");
				}
			}*/
			//null
			if((item.getCites()!=null)&&(item.getCites().size()!=0)){
				writer.print("Cites:");
				for(i=0;i<item.getCites().size();i++){
					if(i==item.getCites().size()-1){
						writer.println(item.getCites().get(i));
					}else{
						writer.print(item.getCites().get(i)+";");
					}
				}
			}
			writer.close();
			//System.out.println(item.getTitle()+" finished!");
		}
		
		System.out.println("all write finished!");
	}
	
	public static ResultItemsCollectorPipeline doWoSListCrawler(String urloflist,Integer deepofcited,Integer deepofreference){
		ResultItemsCollectorPipeline listResultCollector = new ResultItemsCollectorPipeline();
		ResultItemsCollectorPipeline tempCollector = new ResultItemsCollectorPipeline();
		String urlofpaper = Spider.create(new WoSListPageProcess())
								  .addPipeline(new ConsolePipeline())
								  .<ResultItems>get(urloflist)
								  .get("firstpaperurloflist").toString();
		Spider.create(new WoSPaperPageProcess())
		  .addUrl(urlofpaper)
		  .addPipeline(new ConsolePipeline())
		  .addPipeline(listResultCollector)
		  .run();
		
		for(int i=0;i<listResultCollector.getCollected().size();i++){
			if(listResultCollector.getCollected().get(i).isSkip())continue;
			Iterator iter = listResultCollector.getCollected().get(i).getAll().entrySet().iterator();
			while (iter.hasNext()) {//only one
				Map.Entry entry = (Map.Entry) iter.next();
				WoSDataModel item=(WoSDataModel)entry.getValue();
				if((item.getCiteStr()!=null)&&(deepofcited>0)){
					tempCollector=doWoSListCrawler(item.getCiteStr(),deepofcited-1,0);
					for(int tmpcites=0;tmpcites<tempCollector.getCollected().size();tmpcites++){
						item.addAllCites(tempCollector.getCollected().get(tmpcites).getAll().keySet());
					}
				}
				/*if((item.getRefStr()!=null)&&(deepofreference>0)){
					tempCollector=doWoSListCrawler(item.getRefStr(),0,deepofreference-1);
					for(int tmprefs=0;tmprefs<tempCollector.getCollected().size();tmprefs++){
						item.addAllReferences(tempCollector.getCollected().get(tmprefs).getAll().keySet());
					}
				}*/
				if(!((item.getCiteStr()!=null)&&(deepofcited<=0))){
					item.setCompleteflag(true);
				}
				if((!WoSResults.containsKey(item.getWoSNo()))&&(item.isCompleteflag())){
					WoSResults.put(item.getWoSNo(), item);
				}
			}
		}
		return listResultCollector;
	}
}