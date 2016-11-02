package me.ranol.commandsapi.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.ranol.commandsapi.api.annotations.Aliases;
import me.ranol.commandsapi.api.annotations.Arguments;
import me.ranol.commandsapi.api.annotations.CommandLabel;
import me.ranol.commandsapi.api.annotations.Description;
import me.ranol.commandsapi.api.annotations.Permission;
import me.ranol.commandsapi.api.annotations.Usage;
import me.ranol.commandsapi.api.completables.CompPlayer;
import me.ranol.commandsapi.api.completables.Completable;
import me.ranol.commandsapi.reflection.AnnotationObject;
import me.ranol.commandsapi.reflection.MethodObject;
import me.ranol.commandsapi.reflection.Reflector;
import me.ranol.commandsapi.reflection.Reflector.ClassMap;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;

public abstract class AbstractCommand extends CompPlayer implements TabExecutor {
	private static CommandMap map;
	private List<AbstractArgument> args = new ArrayList<AbstractArgument>();

	private static void loadMap() {
		map = (CommandMap) Reflector.getField(SimplePluginManager.class,
				"commandMap").get(Bukkit.getPluginManager());
	}

	public final void construct(Plugin plugin) {
		PluginCommand cmd = Reflector.newInstance(PluginCommand.class,
				new ClassMap(String.class, getLabel()), new ClassMap(
						Plugin.class, plugin));
		cmd.setAliases(Arrays.asList(getAliases()));
		cmd.setDescription(getDescription());
		cmd.setLabel(getLabel());
		cmd.setPermission(getPermissions()[0]);
		cmd.setPermissionMessage(ChatColor.translateAlternateColorCodes('&',
				Arrays.toString(getPermissions()) + "의 권한을 가져야 사용이 가능합니다!"));
		cmd.setUsage(replaceVars(getUsage()));
		cmd.setExecutor(this);
		cmd.setTabCompleter(this);
		loadMap();
		map.register(plugin.getName().toLowerCase(), cmd);
	}

	protected String replaceVars(String s) {
		return s.replace("{label}", getLabel());
	}

	public abstract boolean exec(CommandSender s, CommandArguments args);

	public String getLabel() {
		return Reflector.getAnnotation(this, CommandLabel.class).value(
				String.class);
	}

	public void addArgument(AbstractArgument args) {
		this.args.add(args);
	}

	public boolean executeArgs(CommandSender s, CommandArguments args) {
		boolean b = false;
		for (AbstractArgument arg : this.args) {
			MethodObject m = Reflector.getMethod(arg.getClass(), "exec",
					CommandSender.class, CommandArguments.class);
			AnnotationObject anno = Reflector.getAnnotation(m, Arguments.class);
			if (anno == null)
				continue;

			Class<?>[] classes = anno.value(Class[].class);
			b = b || arg.exec(s, new CommandArguments(classes, args.toArray()));
		}
		return b;
	}

	public final String[] getPermissions() {
		return Reflector.getAnnotation(this, Permission.class).value(
				String[].class);
	}

	public final String[] getAliases() {
		return Reflector.getAnnotation(this, Aliases.class).value(
				String[].class);
	}

	public final String getDescription() {
		return Reflector.getAnnotation(this, Description.class).value(
				String.class);
	}

	public final String getUsage() {
		return Reflector.getAnnotation(this, Usage.class).value(String.class);
	}

	@Override
	public List<String> onTabComplete(CommandSender s, Command c, String l,
			String[] a) {
		List<String> result = new ArrayList<>();
		for (AbstractArgument arg : this.args) {
			if (arg instanceof Completable) {
				MethodObject m = Reflector.getMethod(arg.getClass(), "exec",
						CommandSender.class, CommandArguments.class);
				AnnotationObject anno = Reflector.getAnnotation(m,
						Arguments.class);
				if (anno == null)
					continue;

				Class<?>[] classes = anno.value(Class[].class);
				result.addAll(((Completable) arg).complete(s,
						new CommandArguments(classes, (Object[]) a)));
			}
		}
		result.addAll(complete(s, new CommandArguments(a)));
		return result;
	}

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
		return exec(s, new CommandArguments((Object[]) a));
	}
}
