package ru.hantu.ralp;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class CommandRalp {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
            CommandManager.literal("ralp")
                .requires(source -> source.hasPermissionLevel(4))
                .executes(ctx -> {
                    ctx.getSource().sendFeedback(() -> Messages.get("help.message"), false);
                    return 1;
                })
                .then(CommandManager.literal("help").executes(ctx -> {
                    ctx.getSource().sendFeedback(() -> Messages.get("help.message"), false);
                    return 1;
                }))
                .then(CommandManager.literal("reload").executes(ctx -> {
                    ConfigManager.init();
                    ctx.getSource().sendFeedback(() -> Text.literal("\u00a7aConfig reloaded!"), true);
                    return 1;
                }))
                .then(CommandManager.literal("set")
                    .then(CommandManager.argument("key", StringArgumentType.word())
                        .then(CommandManager.argument("value", StringArgumentType.word())
                            .executes(CommandRalp::execute))))
        );
    }

    private static int execute(CommandContext<ServerCommandSource> ctx) {
        String key = StringArgumentType.getString(ctx, "key").toLowerCase();
        String valueStr = StringArgumentType.getString(ctx, "value");
        try {
            switch (key) {
                case "max-attempts" -> ConfigManager.maxLoginAttempts = Integer.parseInt(valueStr);
                case "block-time" -> ConfigManager.blockTimeSeconds = Integer.parseInt(valueStr);
                case "min-length" -> ConfigManager.minPasswordLength = Integer.parseInt(valueStr);
                case "language" -> {
                    if (!ConfigManager.SUPPORTED_LANGUAGES.contains(valueStr)) {
                        ctx.getSource().sendError(Text.literal(
                            "\u00a7cUnsupported language! Use: " + String.join(", ", ConfigManager.SUPPORTED_LANGUAGES)));
                        return 0;
                    }
                    ConfigManager.language = valueStr;
                }
                default -> {
                    ctx.getSource().sendError(Text.literal("\u00a7cUnknown setting: " + key));
                    return 0;
                }
            }
        } catch (NumberFormatException e) {
            ctx.getSource().sendError(Text.literal("\u00a7cValue must be a number for this setting!"));
            return 0;
        }
        ConfigManager.save();
        ctx.getSource().sendFeedback(() -> Text.literal("\u00a7aSet " + key + " = " + valueStr), true);
        return 1;
    }
}