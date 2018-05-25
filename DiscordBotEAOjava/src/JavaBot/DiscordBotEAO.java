package JavaBot;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.AccountManager;

public class DiscordBotEAO {
	private static String Bot_Token="NDM0OTY2NTU2NDQ3ODY2ODkw.DbSF1g.CRrpU1EBUK3HbuHKQifhNE8YzEM";
	private static String myUserId="228509981861740545";
	public static void main(String[] args) {
		JDA discord=null;
			try 
			{
				discord=new JDABuilder(AccountType.BOT).setToken(Bot_Token).buildBlocking();
				AccountManager mgr=discord.getSelfUser().getManager();
				mgr.setName("KuzBOT");
			} 
			catch (LoginException | InterruptedException e) 
			{
				e.printStackTrace();
			}
			discord.addEventListener(new RespondToMessage());		
	}

}
