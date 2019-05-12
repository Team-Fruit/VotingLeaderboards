package net.teamfruit.votingleaderboards;

import static net.teamfruit.votingleaderboards.VotingLeaderboards.*;

import java.util.Comparator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.feed_the_beast.ftbutilities.data.Leaderboard;
import com.feed_the_beast.ftbutilities.events.LeaderboardRegistryEvent;
import com.github.upcraftlp.votifier.api.reward.RewardStore;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(certificateFingerprint = FINGERPRINT_KEY, name = MODNAME, version = VERSION, acceptedMinecraftVersions = MCVERSIONS, modid = MODID, dependencies = DEPENDENCIES, updateJSON = UPDATE_JSON, serverSideOnly = true, acceptableRemoteVersions = "*")
public class VotingLeaderboards {

	//Version
	public static final String MCVERSIONS = "[1.12, 1.13)";
	public static final String VERSION = "@VERSION@";

	//Meta Information
	public static final String MODNAME = "Forge Voting Leaderboards";
	public static final String MODID = "votingleaderboards";
	public static final String DEPENDENCIES = "after:voting@[1.3.0,)";
	public static final String UPDATE_JSON = "@UPDATE_JSON@";

	public static final String FINGERPRINT_KEY = "@FINGERPRINTKEY@";
	private static final Logger log = LogManager.getLogger(MODID);

	public static Logger getLogger() {
		return log;
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(VotingLeaderboards.class);
	}

	public static boolean isDebugMode() {
		return VotingLeaderboardsConfig.debugMode;
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
	}

	@Mod.EventHandler
	public void onServerStarting(FMLServerStartingEvent event) {
	}

	@Mod.EventHandler
	public void onServerStarted(FMLServerStartedEvent event) {
	}

	@Mod.EventHandler
	public void onServerStopping(FMLServerStoppingEvent event) {
	}

	@SubscribeEvent
	public static void registerLeaderboards(LeaderboardRegistryEvent event) {
		event.register(new Leaderboard(
				new ResourceLocation(MODID, "votecount"),
				new TextComponentString(VotingLeaderboardsConfig.voteLeaderboardsName),
				player -> {
					int data = RewardStore.getStore().getVoteCount(player.getId().toString());
					return new TextComponentString(String.format(VotingLeaderboardsConfig.unitVoteCount, data));
				},
				Comparator.comparingLong(player -> {
					int data = RewardStore.getStore().getVoteCount(player.getId().toString());
					return -data;
				}),
				player -> {
					int data = RewardStore.getStore().getVoteCount(player.getId().toString());
					return data>0;
				}));

		event.register(new Leaderboard(
				new ResourceLocation(MODID, "logincount"),
				new TextComponentString(VotingLeaderboardsConfig.loginLeaderboardsName),
				player -> {
					int data = RewardStore.getStore().getLoginCount(player.getId().toString());
					return new TextComponentString(String.format(VotingLeaderboardsConfig.unitLoginCount, data));
				},
				Comparator.comparingLong(player -> {
					int data = RewardStore.getStore().getLoginCount(player.getId().toString());
					return -data;
				}),
				player -> {
					int data = RewardStore.getStore().getLoginCount(player.getId().toString());
					return data>0;
				}));
	}
}
