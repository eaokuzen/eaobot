package JSONpckg;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class getJson {
	getJson()
	{
		try
		{
		URL url;
		String s="https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=1&playlistId=UUn-v-03DbCpe9tM1__NX1ig&key=AIzaSyCn0xUM90g6aCoJfrR7q03abmSMJudxR1I";
		System.out.print(s);
		url=new URL(s);
		URLConnection urlconn=url.openConnection();
		InputStreamReader instream=new InputStreamReader(urlconn.getInputStream());
		BufferedReader buff=new BufferedReader(instream);
		String line=buff.readLine();
		String JSONData=null;
		while(line != null)
		{
			JSONData=JSONData+buff.readLine();
			line=buff.readLine();
		}
		}
		
		catch(Exception e)
		{
		}
	}
}

