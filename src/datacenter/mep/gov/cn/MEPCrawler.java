package datacenter.mep.gov.cn;

import java.util.Iterator;
import java.util.Map;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.ResultItemsCollectorPipeline;
import util.SimpleFileWriter;
public class MEPCrawler {
	/**
	 * two kind of params input types
	 * example 1: 北京市  2014-03-23 2014-03-23
	 * example 2: 2014-03-23 2014-03-23
	 * @param city startdate enddate outputlocation
	 * @param startdate enddate outputlocation
	 * @author Joshua
	 */
	public static void main(String[] args) {
		String urlstr=null;
		String outputlocation="output.txt";
		//@param city startdate enddate
		if(args.length==4){
			urlstr="http://datacenter.mep.gov.cn/report/air_daily/air_dairy.jsp?city="+args[0]+"&startdate="+args[1]+"&enddate="+args[2]+"&page=1";
			System.out.println("city : "+args[0]);
			System.out.println("startdate : "+args[1]);
			System.out.println("enddate : "+args[2]);
			outputlocation=args[3];
			System.out.println("outputlocation : "+outputlocation);
		}else if(args.length==3){
			urlstr="http://datacenter.mep.gov.cn/report/air_daily/air_dairy.jsp?city=&startdate="+args[0]+"&enddate="+args[1]+"&page=1";
			System.out.println("city : all city");
			System.out.println("startdate : "+args[0]);
			System.out.println("enddate : "+args[1]);
			outputlocation=args[2];
			System.out.println("outputlocation : "+outputlocation);
		}else{
			System.out.println("wrong numbers of args");
			System.out.println("example 1 : 北京市  2014-02-23 2014-03-23 output.txt");
			System.out.println("example 2 : 2014-02-23 2014-03-23 output.txt");
			System.out.println("java -jar MEPCrawler.jar 2014-02-23 2014-03-23 output.txt");
		}
		
		
		if(urlstr==null)return;
		ResultItemsCollectorPipeline pageResultCollector = new ResultItemsCollectorPipeline();
		Spider.create(new MEPPageProcess())
			  .addUrl(urlstr)
			  .addPipeline(new ConsolePipeline())
			  .addPipeline(pageResultCollector)
			  .run();
		
		
		SimpleFileWriter writer = SimpleFileWriter.openFileForWriting(outputlocation);
		if (writer == null) {
		     System.out.println("Couldn't open file!");
		    return;
		}
		for(int i=0;i<pageResultCollector.getCollected().size();i++){
			Iterator iter = pageResultCollector.getCollected().get(i).getAll().entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				MEPDataModel item=(MEPDataModel)entry.getValue();
				
				writer.print(item.getNumber()+":");
				writer.print(item.getCity()+":");
				writer.print(item.getDate()+":");
				writer.print(item.getAQI()+":");
				writer.print(item.getAIRQ()+":");
				writer.println(item.getPollution());
			}
		}
		writer.close();
		
		System.out.println("finished!");
	}
}
