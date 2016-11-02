package me.ranol.commandsapi;

import java.util.ArrayList;
import java.util.List;

import me.ranol.commandsapi.api.AbstractArgument;
import me.ranol.commandsapi.api.AbstractCommand;
import me.ranol.commandsapi.api.CommandArguments;
import me.ranol.commandsapi.api.CompletionUtils;
import me.ranol.commandsapi.api.RestoreInstances;
import me.ranol.commandsapi.api.annotations.Aliases;
import me.ranol.commandsapi.api.annotations.Arguments;
import me.ranol.commandsapi.api.annotations.CommandLabel;
import me.ranol.commandsapi.api.annotations.Description;
import me.ranol.commandsapi.api.annotations.Permission;
import me.ranol.commandsapi.api.annotations.Usage;
import me.ranol.commandsapi.api.completables.CompPlayer;
import me.ranol.commandsapi.api.completables.Completable;
import me.ranol.commandsapi.api.restore.ResPlayer;

import org.bukkit.command.CommandSender;

@Aliases({ "exam", "ex" })
@Description("The Test Command!")
@Usage("/{label} {arg 1} {arg 2}")
@Permission({ "example.test", "example.test2" })
@CommandLabel("example")
public class ExampleCommand extends AbstractCommand {
	{
		addArgument(new Argument1());
	}

	@Override
	public boolean exec(CommandSender s, CommandArguments args) {
		return super.executeArgs(s, args);
	}

	public static class Argument1 implements AbstractArgument, Completable {

		@Override
		@Arguments({ String.class, String.class, String.class })
		public boolean exec(CommandSender s, CommandArguments args) {
			s.sendMessage(args.getString(1)
					+ ", "
					+ args.getString(2)
					+ ", "
					+ args.getWithString(3,
							RestoreInstances.getInstance(ResPlayer.class)));
			return true;
		}

		@Override
		public List<String> complete(CommandSender s, CommandArguments args) {
			System.out.println(args.size());
			if (args.size() == 3) {
				return CompletionUtils.getInstance(CompPlayer.class, s, args);
			}
			return new ArrayList<>();
		}
	}
}
