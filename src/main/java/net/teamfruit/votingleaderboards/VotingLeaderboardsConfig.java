package net.teamfruit.votingleaderboards;

import static net.teamfruit.votingleaderboards.VotingLeaderboards.*;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author Kamesuta
 */
@Config(modid = MODID, name = "votifier/VotingLeaderboards") //--> /config/votifier/ForgeVotifier.cfg
public class VotingLeaderboardsConfig {

	@Config.RequiresMcRestart
	@Config.Name("Unit Vote Count")
	@Config.Comment({ "vote count: ... times" })
	public static String unitVoteCount = "%d times.";

	@Config.RequiresMcRestart
	@Config.Name("Vote Leaderboards Name")
	@Config.Comment({ "Leaderboards Name" })
	public static String voteLeaderboardsName = "Vote Count";

	@Config.RequiresMcRestart
	@Config.Name("Unit Login Count")
	@Config.Comment({ "login count: ... times" })
	public static String unitLoginCount = "%d times.";

	@Config.RequiresMcRestart
	@Config.Name("Login Leaderboards Name")
	@Config.Comment({ "Leaderboards Name" })
	public static String loginLeaderboardsName = "Login Count";

	@Config.Name("debug mode")
	@Config.Comment("enable more verbose output of what's going on")
	public static boolean debugMode = false;

	@Mod.EventBusSubscriber(modid = MODID)
	public static class Handler {

		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(MODID))
				ConfigManager.load(MODID, Config.Type.INSTANCE);
		}
	}
}
