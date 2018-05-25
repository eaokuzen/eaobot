package JavaBot;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Tests {

	public static void main(String[] args) {
		ObjectMapper om=new ObjectMapper();
		try
		{
		JsonNode x =om.readTree(new URL("https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&pageToken=CGQQAA&playlistId=UUn-v-03DbCpe9tM1__NX1ig&fields=items(snippet(resourceId%2FvideoId%2Ctitle))%2CnextPageToken&key=AIzaSyCn0xUM90g6aCoJfrR7q03abmSMJudxR1I")).findValue("nextPageToken");
		if(x == null)
		{
			System.out.println("Hello");
		}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
