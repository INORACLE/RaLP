package ru.hantu.ralp;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class CommandRalp {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
            Commands.literal("ralp")
                .requires(source -> source.hasPermission(4))
                .executes(ctx -> {
                    ctx.getSource().sendSuccess(() -> Messages.get("help.message"), false);
                    return 1;
                })
                .then(Commands.literal("help").executes(ctx -> {
                    ctx.getSource().sendSuccess(() -> Messages.get("help.message"), false);
                    return 1;
                }))
                .then(Commands.literal("reload").executes(ctx -> {
                    ConfigManager.init();
                    ctx.getSource().sendSuccess(() -> Component.literal("\u00a7aConfig reloaded!"), true);
                    return 1;
                }))
                .then(Commands.literal("set")
                    .then(Commands.argument("key", StringArgumentType.word())
                        .then(Commands.argument("value", StringArgumentType.word())
                            .executes(CommandRalp::execute))))
        );
    }

    private static int execute(CommandContext<CommandSourceStack> ctx) {
        String key = StringArgumentType.getString(ctx, "key").toLowerCase();
        String valueStr = StringArgumentType.getString(ctx, "value");
        try {
            switch (key) {
                case "max-attempts" -> ConfigManager.maxLoginAttempts = Integer.parseInt(valueStr);
                case "block-time" -> ConfigManager.blockTimeSeconds = Integer.parseInt(valueStr);
                case "min-length" -> ConfigManager.minPasswordLength = Integer.parseInt(valueStr);
                case "language" -> {
                    if (!ConfigManager.SUPPORTED_LANGUAGES.contains(valueStr)) {
                        ctx.getSource().sendFailure(Component.literal(
                            "\u00a7cUnsupported language! Use: " + String.join(", ", ConfigManager.SUPPORTED_LANGUAGES)));
                        return 0;
                    }
                    ConfigManager.language = valueStr;
                }
                default -> {
                    ctx.getSource().sendFailure(Component.literal("\u00a7cUnknown setting: " + key));
                    return 0;
                }
            }
        } catch (NumberFormatException e) {
            ctx.getSource().sendFailure(Component.literal("\u00a7cValue must be a number for this setting!"));
            return 0;
        }
        ConfigManager.save();
        ctx.getSource().sendSuccess(() -> Component.literal("\u00a7aSet " + key + " = " + valueStr), true);
        return 1;
    }
}