package JavaBot;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.security.auth.login.LoginException;

import JSONpckg.JSONParse;
import net.dv8tion.jda.client.events.call.CallDeleteEvent;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageHistory;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.AccountManager;
import net.dv8tion.jda.core.managers.impl.ManagerBase;
import JavaBot.Sname;



public class RespondToMessage extends ListenerAdapter
{
	private static String myUserId="228509981861740545";
	public void onMessageReceived(MessageReceivedEvent mre)
	{
		String command=mre.getMessage().getContentRaw();
		if(command.equalsIgnoreCase("Test"))
		{
			mre.getMessage().delete().queue();
			mre.getTextChannel().sendMessage("Class Called, Your command = "+mre.getMessage().getContentRaw() + " Command Typed by "+ mre.getAuthor().getAsMention()).queue(Message -> Message.delete().queueAfter(5, TimeUnit.SECONDS));;		
		}
		if(command.contains("~SYN")&&mre.getAuthor().getId().equals("228509981861740545"))
		{
			String x=command.substring(5);
			System.out.println(x);
			try
			{
			Sname.nameit(x);
			}
			catch(InterruptedException | LoginException e)
			{
			}
		}
		if(command.equalsIgnoreCase("Latest upload"))
		{
			mre.getMessage().delete().queueAfter(3, TimeUnit.SECONDS);
			try
			{
			String g = new JSONParse().getLatInfo();
			System.out.println(g);
			String title=g.substring(0, (g.length()-12));
			String VideoID=g.substring((g.length()-11),g.length());
			mre.getTextChannel().sendMessage(mre.getAuthor().getAsMention() + " The latest upload by Kuzen is "+"`"+title+"`").queue();
			mre.getTextChannel().sendMessage("It can be seen here => https://www.youtube.com/watch?v="+VideoID).queue();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		if(command.contains("~setavatar"))
		{
			String x =command;
			x=x.substring(11);
			try
			{
				Sname.setArt(new URL(x));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		if(command.contains("Delete messages") && mre.getAuthor().getId().equals(myUserId))
		{
			String u_name=command.substring(command.indexOf("<")+1, command.lastIndexOf(">"));
			int histsize=Integer.parseInt(command.substring(command.lastIndexOf("!")+1));
			List<Message> hist= mre.getTextChannel().getIterableHistory().stream().limit(histsize).filter(m -> m.getAuthor().getName().equals(u_name)).collect(Collectors.toList());
			for(int i=0;i<hist.size();i++)
			{
				String MsgIdFromHist=hist.get(i).getId();
				mre.getTextChannel().deleteMessageById(MsgIdFromHist).complete();
			}
			mre.getTextChannel().sendMessage("Messages deleted successfully").queue();
		}
		if(command.contains("search ~"))
		{
			try
			{
			String message=mre.getMessage().getContentRaw();
			message=message.substring(message.indexOf("~")+1);
			List<String> SearchResult=new JSONParse().channelSearch(message);
			mre.getTextChannel().sendMessage(mre.getAuthor().getAsMention()+" "+SearchResult.size()+" Results found").queue();
			for(int i=0;i<SearchResult.size();i++)
			{
				String title=SearchResult.get(i).substring(SearchResult.get(i).indexOf("~")+2, SearchResult.get(i).indexOf("$"));
				String link=SearchResult.get(i).substring(SearchResult.get(i).indexOf("$")+2, SearchResult.get(i).length());
				mre.getTextChannel().sendMessage("`"+title+"`"+" = "+"<https://www.youtube.com/watch?v="+link+">").queue();
			}
			}
			catch(Exception e)
			{
			}
		}
	}
}