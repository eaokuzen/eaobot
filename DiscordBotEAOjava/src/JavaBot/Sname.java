package JavaBot;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.security.auth.login.LoginException;

import com.sun.org.apache.xerces.internal.util.URI.MalformedURIException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.SelfUser;
import net.dv8tion.jda.core.managers.AccountManager;
import net.dv8tion.jda.core.managers.impl.ManagerBase;
import net.dv8tion.jda.core.requests.Route.CompiledRoute;
import net.dv8tion.jda.core.entities.Icon;

public class Sname {
	
	static JDA discord=null;
	
	public static void nameit(String x) throws LoginException,InterruptedException
	{
			discord = new JDABuilder(AccountType.BOT).setToken("NDM0OTY2NTU2NDQ3ODY2ODkw.DbSF1g.CRrpU1EBUK3HbuHKQifhNE8YzEM").buildBlocking();
			discord.getSelfUser().getManager().setName(x).queue();
			discord.shutdown();
	}
	public static void setArt(URL avatar_location) throws LoginException, InterruptedException,IOException,MalformedURIException
	{
		discord = new JDABuilder(AccountType.BOT).setToken("NDM0OTY2NTU2NDQ3ODY2ODkw.DbSF1g.CRrpU1EBUK3HbuHKQifhNE8YzEM").buildBlocking();
		InputStream inp=(avatar_location).openStream();
		
		discord.getSelfUser().getManager().setAvatar(Icon.from(inp)).queue();
	}
}
