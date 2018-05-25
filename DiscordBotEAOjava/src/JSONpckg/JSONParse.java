package JSONpckg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.org.apache.xerces.internal.util.URI.MalformedURIException;

public class JSONParse {
	ObjectMapper om = new ObjectMapper();
	public String getLatInfo() throws MalformedURIException,IOException
 {
		JsonNode mainfile=om.readTree(new URL("https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=1&playlistId=UUn-v-03DbCpe9tM1__NX1ig&key=AIzaSyCn0xUM90g6aCoJfrR7q03abmSMJudxR1I"));
		JsonNode title=mainfile.findValue("title");
		JsonNode videoId=mainfile.findValue("resourceId").findValue("videoId");
		
		String A=title.textValue();
		String B=videoId.textValue();

		return A+"@"+B;
}
	public List<String> channelSearch(String search_term) throws MalformedURIException, IOException
	{
		JsonNode mainfile=om.readTree(new URL("https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId=UUn-v-03DbCpe9tM1__NX1ig&fields=items(snippet(resourceId%2FvideoId%2Ctitle))%2CnextPageToken&key=AIzaSyCn0xUM90g6aCoJfrR7q03abmSMJudxR1I"));
		List<JsonNode> all_titles=mainfile.findValues("title");
		JsonNode next_page_token=mainfile.findValue("nextPageToken");
		List<JsonNode> all_links=mainfile.findValues("resourceId");
		while(next_page_token != null)
		{
			mainfile=om.readTree(new URL("https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&pageToken="+next_page_token.textValue()+"&playlistId=UUn-v-03DbCpe9tM1__NX1ig&fields=items(snippet(resourceId%2FvideoId%2Ctitle))%2CnextPageToken&key=AIzaSyCn0xUM90g6aCoJfrR7q03abmSMJudxR1I"));
				List<JsonNode> next_page_title=mainfile.findValues("title");
				List<JsonNode> next_page_links=mainfile.findValues("resourceId");
				for(JsonNode n : next_page_title)
				{
					all_titles.add(n);
				}
				for(JsonNode n : next_page_links)
				{
					all_links.add(n);
				}
			next_page_token=om.readTree(new URL("https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&pageToken="+next_page_token.textValue()+"&playlistId=UUn-v-03DbCpe9tM1__NX1ig&fields=items(snippet(resourceId%2FvideoId%2Ctitle))%2CnextPageToken&key=AIzaSyCn0xUM90g6aCoJfrR7q03abmSMJudxR1I")).findValue("nextPageToken");
		}
		List<String> SearchResult=new ArrayList<String>();
		for(int i=0;i<all_titles.size();i++)
		{
			if(all_titles.get(i).textValue().contains(search_term) || all_titles.get(i).textValue().toLowerCase().contains(search_term.toLowerCase()))
			{
				//System.out.println("Title = "+all_titles.get(i).textValue()+" link = "+all_links.get(i).findValue("videoId").textValue());
				SearchResult.add("~~"+all_titles.get(i).textValue()+"$$"+all_links.get(i).findValue("videoId").textValue());
			}
		}
		return SearchResult;
	}
}