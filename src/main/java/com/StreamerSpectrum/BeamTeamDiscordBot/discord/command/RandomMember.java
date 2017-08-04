package com.StreamerSpectrum.BeamTeamDiscordBot.discord.command;

import java.util.List;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;

import com.StreamerSpectrum.BeamTeamDiscordBot.beam.resource.BeamTeam;
import com.StreamerSpectrum.BeamTeamDiscordBot.beam.resource.BeamTeamUser;
import com.StreamerSpectrum.BeamTeamDiscordBot.singletons.MixerManager;
import com.StreamerSpectrum.BeamTeamDiscordBot.singletons.JDAManager;

import com.jagrosh.jdautilities.commandclient.Command;
import com.jagrosh.jdautilities.commandclient.CommandEvent;

public class RandomMember extends Command {

	public RandomMember() {
		this.name = "randommember";
		this.help = "Takes in a team and displays info about a random member from it.";
		this.arguments = "teamNameOrID";
	}

	@Override
	protected void execute(CommandEvent event) {
		if (!StringUtils.isBlank(event.getArgs())) {
			BeamTeam team = MixerManager.getTeam(event.getArgs());

			if (null != team) {
				List<BeamTeamUser> teamMembers = MixerManager.getTeamMembers(team);
				BeamTeamUser member = teamMembers.get(new Random().nextInt(teamMembers.size()));

				CommandHelper.sendTeamUserEmbed(event, member);
			}
		} else {
			JDAManager.sendMessage(event, "Missing arguments from command!");
		}
	}

}
