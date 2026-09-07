package ru.hantu.ralp;

import net.minecraft.network.chat.Component;

public class Messages {
    public static Component get(String key) {
        String lang = ConfigManager.language;
        return Component.literal(switch (lang) {
            case "ru" -> getRu(key);
            case "de" -> getDe(key);
            case "es" -> getEs(key);
            case "fr" -> getFr(key);
            case "pt" -> getPt(key);
            case "tr" -> getTr(key);
            case "it" -> getIt(key);
            case "ko" -> getKo(key);
            case "ja" -> getJa(key);
            case "zh" -> getZh(key);
            case "pl" -> getPl(key);
            default -> getEn(key);
        });
    }

    private static String getEn(String key) {
        return switch (key) {
            case "register.usage" -> "\u00a76\u00a7l[RaLP] \u00a7r\u00a7aUsage: \u00a77/register <password> <repeat>";
            case "register.success" -> "\u00a7aSuccessfully registered and logged in!";
            case "register.already" -> "\u00a7cYou are already registered!";
            case "register.passwords-not-match" -> "\u00a7cPasswords do not match!";
            case "register.too-short" -> "\u00a7cPassword must be at least " + ConfigManager.minPasswordLength + " characters!";
            case "login.usage" -> "\u00a76\u00a7l[RaLP] \u00a7r\u00a7cUsage: \u00a77/login <password>";
            case "login.success" -> "\u00a7aSuccessfully logged in!";
            case "login.wrong" -> "\u00a7cWrong password!";
            case "login.not-registered" -> "\u00a7cYou need to register first!";
            case "login.blocked" -> "\u00a7cToo many failed attempts! You are blocked.";
            case "login.blocked-time" -> "\u00a7cYou are blocked for {time} seconds!";
            case "login.already" -> "\u00a7cYou are already logged in!";
            case "changepassword.success" -> "\u00a7aPassword changed! Please log in again.";
            case "changepassword.error" -> "\u00a7cFailed to change password!";
            case "unreg.success" -> "\u00a7aUnregistered player {player}!";
            case "unreg.not-registered" -> "\u00a7cPlayer is not registered!";
            case "errors.player-only" -> "\u00a7cThis command is for players only!";
            case "errors.not-logged-in" -> "\u00a7cYou must log in first!";
            case "errors.no-permission" -> "\u00a7cYou don't have permission!";
            case "help.message" -> "\u00a76\u00a7l[RaLP Help]\n\u00a7r\u00a77/ralp set max-attempts <N> \u00a78- Max login attempts\n\u00a77/ralp set block-time <sec> \u00a78- Block duration\n\u00a77/ralp set min-length <N> \u00a78- Min password length\n\u00a77/ralp set language <en|ru|de|es|fr|pt|tr|it|ko|ja|zh|pl> \u00a78- Language\n\u00a77/ralp reload \u00a78- Reload config from file";
            default -> "\u00a7c[Unknown message: " + key + "]";
        };
    }

    private static String getRu(String key) {
        return switch (key) {
            case "register.usage" -> "\u00a76\u00a7l[RaLP] \u00a7r\u00a7a\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0439\u0442\u0435: \u00a77/register <\u043f\u0430\u0440\u043e\u043b\u044c> <\u043f\u043e\u0432\u0442\u043e\u0440>";
            case "register.success" -> "\u00a7a\u0412\u044b \u0443\u0441\u043f\u0435\u0448\u043d\u043e \u0437\u0430\u0440\u0435\u0433\u0438\u0441\u0442\u0440\u0438\u0440\u043e\u0432\u0430\u043d\u044b \u0438 \u0432\u043e\u0448\u043b\u0438 \u0432 \u0441\u0438\u0441\u0442\u0435\u043c\u0443!";
            case "register.already" -> "\u00a7c\u0412\u044b \u0443\u0436\u0435 \u0437\u0430\u0440\u0435\u0433\u0438\u0441\u0442\u0440\u0438\u0440\u043e\u0432\u0430\u043d\u044b!";
            case "register.passwords-not-match" -> "\u00a7c\u041f\u0430\u0440\u043e\u043b\u0438 \u043d\u0435 \u0441\u043e\u0432\u043f\u0430\u0434\u0430\u044e\u0442!";
            case "register.too-short" -> "\u00a7c\u041f\u0430\u0440\u043e\u043b\u044c \u0434\u043e\u043b\u0436\u0435\u043d \u0431\u044b\u0442\u044c \u043d\u0435 \u043a\u043e\u0440\u043e\u0447\u0435 " + ConfigManager.minPasswordLength + " \u0441\u0438\u043c\u0432\u043e\u043b\u043e\u0432!";
            case "login.usage" -> "\u00a76\u00a7l[RaLP] \u00a7r\u00a7c\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0439\u0442\u0435: \u00a77/login <\u043f\u0430\u0440\u043e\u043b\u044c>";
            case "login.success" -> "\u00a7a\u0412\u044b \u0443\u0441\u043f\u0435\u0448\u043d\u043e \u0432\u043e\u0448\u043b\u0438 \u0432 \u0441\u0438\u0441\u0442\u0435\u043c\u0443!";
            case "login.wrong" -> "\u00a7c\u041d\u0435\u0432\u0435\u0440\u043d\u044b\u0439 \u043f\u0430\u0440\u043e\u043b\u044c!";
            case "login.not-registered" -> "\u00a7c\u0421\u043d\u0430\u0447\u0430\u043b\u0430 \u0437\u0430\u0440\u0435\u0433\u0438\u0441\u0442\u0440\u0443\u0439\u0442\u0435\u0441\u044c!";
            case "login.blocked" -> "\u00a7c\u0421\u043b\u0438\u0448\u043a\u043e\u043c \u043c\u043d\u043e\u0433\u043e \u043f\u043e\u043f\u044b\u0442\u043e\u043a! \u0412\u044b \u0437\u0430\u0431\u043b\u043e\u043a\u0438\u0440\u043e\u0432\u0430\u043d\u044b.";
            case "login.blocked-time" -> "\u00a7c\u0412\u044b \u0437\u0430\u0431\u043b\u043e\u043a\u0438\u0440\u043e\u0432\u0430\u043d\u044b \u043d\u0430 {time} \u0441\u0435\u043a\u0443\u043d\u0434!";
            case "login.already" -> "\u00a7c\u0412\u044b \u0443\u0436\u0435 \u0432\u043e\u0448\u043b\u0438 \u0432 \u0441\u0438\u0441\u0442\u0435\u043c\u0443!";
            case "changepassword.success" -> "\u00a7a\u041f\u0430\u0440\u043e\u043b\u044c \u0438\u0437\u043c\u0435\u043d\u0451\u043d! \u041f\u043e\u0436\u0430\u043b\u0443\u0439\u0441\u0442\u0430, \u0432\u043e\u0439\u0434\u0438\u0442\u0435 \u0441\u043d\u043e\u0432\u0430.";
            case "changepassword.error" -> "\u00a7c\u041d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u0438\u0437\u043c\u0435\u043d\u0438\u0442\u044c \u043f\u0430\u0440\u043e\u043b\u044c!";
            case "unreg.success" -> "\u00a7a\u0420\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044f \u0438\u0433\u0440\u043e\u043a\u0430 {player} \u0443\u0434\u0430\u043b\u0435\u043d\u0430!";
            case "unreg.not-registered" -> "\u00a7c\u0418\u0433\u0440\u043e\u043a \u043d\u0435 \u0437\u0430\u0440\u0435\u0433\u0438\u0441\u0442\u0440\u0438\u0440\u043e\u0432\u0430\u043d!";
            case "errors.player-only" -> "\u00a7c\u042d\u0442\u0430 \u043a\u043e\u043c\u0430\u043d\u0434\u0430 \u0442\u043e\u043b\u044c\u043a\u043e \u0434\u043b\u044f \u0438\u0433\u0440\u043e\u043a\u043e\u0432!";
            case "errors.not-logged-in" -> "\u00a7c\u0412\u044b \u0434\u043e\u043b\u0436\u043d\u044b \u0441\u043d\u0430\u0447\u0430\u043b\u0430 \u0432\u043e\u0439\u0442\u0438 \u0432 \u0430\u043a\u043a\u0430\u0443\u043d\u0442!";
            case "errors.no-permission" -> "\u00a7c\u0423 \u0432\u0430\u0441 \u043d\u0435\u0442 \u043f\u0440\u0430\u0432!";
            case "help.message" -> "\u00a76\u00a7l[RaLP \u041f\u043e\u043c\u043e\u0449\u044c]\n\u00a7r\u00a77/ralp set max-attempts <N> \u00a78- \u041c\u0430\u043a\u0441. \u043f\u043e\u043f\u044b\u0442\u043e\u043a \u0432\u0445\u043e\u0434\u0430\n\u00a77/ralp set block-time <\u0441\u0435\u043a> \u00a78- \u0412\u0440\u0435\u043c\u044f \u0431\u043b\u043e\u043a\u0438\u0440\u043e\u0432\u043a\u0438\n\u00a77/ralp set min-length <N> \u00a78- \u041c\u0438\u043d. \u0434\u043b\u0438\u043d\u0430 \u043f\u0430\u0440\u043e\u043b\u044f\n\u00a77/ralp set language <en|ru|de|es|fr|pt|tr|it|ko|ja|zh|pl> \u00a78- \u042f\u0437\u044b\u043a\n\u00a77/ralp reload \u00a78- \u041f\u0435\u0440\u0435\u0437\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u044c \u043a\u043e\u043d\u0444\u0438\u0433";
            default -> "\u00a7c[\u041d\u0435\u0438\u0437\u0432\u0435\u0441\u0442\u043d\u043e\u0435 \u0441\u043e\u043e\u0431\u0449\u0435\u043d\u0438\u0435: " + key + "]";
        };
    }

    private static String getDe(String key) {
        return switch (key) {
            case "register.usage" -> "\u00a76\u00a7l[RaLP] \u00a7r\u00a7aVerwendung: \u00a77/register <Passwort> <wiederholen>";
            case "register.success" -> "\u00a7aErfolgreich registriert und eingeloggt!";
            case "register.already" -> "\u00a7cSie sind bereits registriert!";
            case "register.passwords-not-match" -> "\u00a7cPassw\u00f6rter stimmen nicht \u00fcberein!";
            case "register.too-short" -> "\u00a7cPasswort muss mindestens " + ConfigManager.minPasswordLength + " Zeichen lang sein!";
            case "login.usage" -> "\u00a76\u00a7l[RaLP] \u00a7r\u00a7cVerwendung: \u00a77/login <Passwort>";
            case "login.success" -> "\u00a7aErfolgreich eingeloggt!";
            case "login.wrong" -> "\u00a7cFalsches Passwort!";
            case "login.not-registered" -> "\u00a7cSie m\u00fcssen sich zuerst registrieren!";
            case "login.blocked" -> "\u00a7cZu viele fehlgeschlagene Versuche! Sie sind gesperrt.";
            case "login.blocked-time" -> "\u00a7cSie sind f\u00fcr {time} Sekunden gesperrt!";
            case "login.already" -> "\u00a7cSie sind bereits eingeloggt!";
            case "changepassword.success" -> "\u00a7aPasswort ge\u00e4ndert! Bitte erneut einloggen.";
            case "changepassword.error" -> "\u00a7cPasswort\u00e4nderung fehlgeschlagen!";
            case "unreg.success" -> "\u00a7aSpieler {player} wurde entfernt!";
            case "unreg.not-registered" -> "\u00a7cSpieler ist nicht registriert!";
            case "errors.player-only" -> "\u00a7cDieser Befehl ist nur f\u00fcr Spieler!";
            case "errors.not-logged-in" -> "\u00a7cSie m\u00fcssen sich zuerst einloggen!";
            case "errors.no-permission" -> "\u00a7cSie haben keine Berechtigung!";
            case "help.message" -> "\u00a76\u00a7l[RaLP Hilfe]\n\u00a7r\u00a77/ralp set max-attempts <N> \u00a78- Max. Login-Versuche\n\u00a77/ralp set block-time <Sek> \u00a78- Sperrdauer\n\u00a77/ralp set min-length <N> \u00a78- Min. Passwortl\u00e4nge\n\u00a77/ralp set language <en|ru|de|es|fr|pt|tr|it|ko|ja|zh|pl> \u00a78- Sprache\n\u00a77/ralp reload \u00a78- Konfiguration neu laden";
            default -> "\u00a7c[Unbekannte Nachricht: " + key + "]";
        };
    }

    private static String getEs(String key) {
        return switch (key) {
            case "register.usage" -> "\u00a76\u00a7l[RaLP] \u00a7r\u00a7aUso: \u00a77/register <contrase\u00f1a> <repetir>";
            case "register.success" -> "\u00a7a\u00a1Registrado e iniciado sesi\u00f3n correctamente!";
            case "register.already" -> "\u00a7c\u00a1Ya est\u00e1s registrado!";
            case "register.passwords-not-match" -> "\u00a7c\u00a1Las contrase\u00f1as no coinciden!";
            case "register.too-short" -> "\u00a7c\u00a1La contrase\u00f1a debe tener al menos " + ConfigManager.minPasswordLength + " caracteres!";
            case "login.usage" -> "\u00a76\u00a7l[RaLP] \u00a7r\u00a7cUso: \u00a77/login <contrase\u00f1a>";
            case "login.success" -> "\u00a7a\u00a1Has iniciado sesi\u00f3n correctamente!";
            case "login.wrong" -> "\u00a7c\u00a1Contrase\u00f1a incorrecta!";
            case "login.not-registered" -> "\u00a7c\u00a1Primero debes registrarte!";
            case "login.blocked" -> "\u00a7c\u00a1Demasiados intentos fallidos! Est\u00e1s bloqueado.";
            case "login.blocked-time" -> "\u00a7c\u00a1Est\u00e1s bloqueado por {time} segundos!";
            case "login.already" -> "\u00a7c\u00a1Ya has iniciado sesi\u00f3n!";
            case "changepassword.success" -> "\u00a7a\u00a1Contrase\u00f1a cambiada! Por favor inicia sesi\u00f3n nuevamente.";
            case "changepassword.error" -> "\u00a7c\u00a1Error al cambiar la contrase\u00f1a!";
            case "unreg.success" -> "\u00a7a\u00a1Jugador {player} eliminado!";
            case "unreg.not-registered" -> "\u00a7c\u00a1El jugador no est\u00e1 registrado!";
            case "errors.player-only" -> "\u00a7c\u00a1Este comando es solo para jugadores!";
            case "errors.not-logged-in" -> "\u00a7c\u00a1Debes iniciar sesi\u00f3n primero!";
            case "errors.no-permission" -> "\u00a7c\u00a1No tienes permiso!";
            case "help.message" -> "\u00a76\u00a7l[Ayuda RaLP]\n\u00a7r\u00a77/ralp set max-attempts <N> \u00a78- Intentos m\u00e1ximos\n\u00a77/ralp set block-time <seg> \u00a78- Duraci\u00f3n del bloqueo\n\u00a77/ralp set min-length <N> \u00a78- Longitud m\u00ednima contrase\u00f1a\n\u00a77/ralp set language <en|ru|de|es|fr|pt|tr|it|ko|ja|zh|pl> \u00a78- Idioma\n\u00a77/ralp reload \u00a78- Recargar configuraci\u00f3n";
            default -> "\u00a7c[Mensaje desconocido: " + key + "]";
        };
    }

    private static String getFr(String key) {
        return switch (key) {
            case "register.usage" -> "\u00a76\u00a7l[RaLP] \u00a7r\u00a7aUtilisation : \u00a77/register <motdepasse> <confirmer>";
            case "register.success" -> "\u00a7aInscription et connexion r\u00e9ussies !";
            case "register.already" -> "\u00a7cVous \u00eates d\u00e9j\u00e0 inscrit !";
            case "register.passwords-not-match" -> "\u00a7cLes mots de passe ne correspondent pas !";
            case "register.too-short" -> "\u00a7cLe mot de passe doit contenir au moins " + ConfigManager.minPasswordLength + " caract\u00e8res !";
            case "login.usage" -> "\u00a76\u00a7l[RaLP] \u00a7r\u00a7cUtilisation : \u00a77/login <motdepasse>";
            case "login.success" -> "\u00a7aConnexion r\u00e9ussie !";
            case "login.wrong" -> "\u00a7cMot de passe incorrect !";
            case "login.not-registered" -> "\u00a7cVous devez d'abord vous inscrire !";
            case "login.blocked" -> "\u00a7cTrop de tentatives \u00e9chou\u00e9es ! Vous \u00eates bloqu\u00e9.";
            case "login.blocked-time" -> "\u00a7cVous \u00eates bloqu\u00e9 pendant {time} secondes !";
            case "login.already" -> "\u00a7cVous \u00eates d\u00e9j\u00e0 connect\u00e9 !";
            case "changepassword.success" -> "\u00a7aMot de passe modifi\u00e9 ! Veuillez vous reconnecter.";
            case "changepassword.error" -> "\u00a7c\u00c9chec de la modification du mot de passe !";
            case "unreg.success" -> "\u00a7aJoueur {player} supprim\u00e9 !";
            case "unreg.not-registered" -> "\u00a7cLe joueur n'est pas inscrit !";
            case "errors.player-only" -> "\u00a7cCette commande est r\u00e9serv\u00e9e aux joueurs !";
            case "errors.not-logged-in" -> "\u00a7cVous devez d'abord vous connecter !";
            case "errors.no-permission" -> "\u00a7cVous n'avez pas la permission !";
            case "help.message" -> "\u00a76\u00a7l[Aide RaLP]\n\u00a7r\u00a77/ralp set max-attempts <N> \u00a78- Tentatives max\n\u00a77/ralp set block-time <sec> \u00a78- Dur\u00e9e du blocage\n\u00a77/ralp set min-length <N> \u00a78- Longueur min mot de passe\n\u00a77/ralp set language <en|ru|de|es|fr|pt|tr|it|ko|ja|zh|pl> \u00a78- Langue\n\u00a77/ralp reload \u00a78- Recharger la config";
            default -> "\u00a7c[Message inconnu : " + key + "]";
        };
    }

    private static String getPt(String key) {
        return switch (key) {
            case "register.usage" -> "\u00a76\u00a7l[RaLP] \u00a7r\u00a7aUtiliza\u00e7\u00e3o: \u00a77/register <palavra-passe> <confirmar>";
            case "register.success" -> "\u00a7aRegisto e login efetuados com sucesso!";
            case "register.already" -> "\u00a7cJ\u00e1 est\u00e1s registado!";
            case "register.passwords-not-match" -> "\u00a7cAs palavras-passe n\u00e3o coincidem!";
            case "register.too-short" -> "\u00a7cA palavra-passe deve ter pelo menos " + ConfigManager.minPasswordLength + " caracteres!";
            case "login.usage" -> "\u00a76\u00a7l[RaLP] \u00a7r\u00a7cUtiliza\u00e7\u00e3o: \u00a77/login <palavra-passe>";
            case "login.success" -> "\u00a7aLogin efetuado com sucesso!";
            case "login.wrong" -> "\u00a7cPalavra-passe incorreta!";
            case "login.not-registered" -> "\u00a7cPrimeiro tens de te registar!";
            case "login.blocked" -> "\u00a7cDemasiadas tentativas falhadas! Est\u00e1s bloqueado.";
            case "login.blocked-time" -> "\u00a7cEst\u00e1s bloqueado por {time} segundos!";
            case "login.already" -> "\u00a7cJ\u00e1 fizeste login!";
            case "changepassword.success" -> "\u00a7aPalavra-passe alterada! Por favor faz login novamente.";
            case "changepassword.error" -> "\u00a7cFalha ao alterar a palavra-passe!";
            case "unreg.success" -> "\u00a7aJogador {player} removido!";
            case "unreg.not-registered" -> "\u00a7cO jogador n\u00e3o est\u00e1 registado!";
            case "errors.player-only" -> "\u00a7cEste comando s\u00f3 pode ser usado por jogadores!";
            case "errors.not-logged-in" -> "\u00a7cPrimeiro tens de fazer login!";
            case "errors.no-permission" -> "\u00a7cN\u00e3o tens permiss\u00e3o!";
            case "help.message" -> "\u00a76\u00a7l[Ajuda RaLP]\n\u00a7r\u00a77/ralp set max-attempts <N> \u00a78- Tentativas m\u00e1ximas\n\u00a77/ralp set block-time <seg> \u00a78- Dura\u00e7\u00e3o do bloqueio\n\u00a77/ralp set min-length <N> \u00a78- Tamanho m\u00ednimo da palavra-passe\n\u00a77/ralp set language <en|ru|de|es|fr|pt|tr|it|ko|ja|zh|pl> \u00a78- Idioma\n\u00a77/ralp reload \u00a78- Recarregar configura\u00e7\u00e3o";
            default -> "\u00a7c[Mensagem desconhecida: " + key + "]";
        };
    }

    private static String getTr(String key) {
        return switch (key) {
            case "register.usage" -> "\u00a76\u00a7l[RaLP] \u00a7r\u00a7aKullan\u0131m: \u00a77/register <\u015fifre> <tekrar>";
            case "register.success" -> "\u00a7aBa\u015far\u0131yla kaydoldunuz ve giri\u015f yapt\u0131n\u0131z!";
            case "register.already" -> "\u00a7cZaten kay\u0131tl\u0131s\u0131n\u0131z!";
            case "register.passwords-not-match" -> "\u00a7c\u015eifreler uyu\u015fmuyor!";
            case "register.too-short" -> "\u00a7c\u015eifre en az " + ConfigManager.minPasswordLength + " karakter olmal\u0131d\u0131r!";
            case "login.usage" -> "\u00a76\u00a7l[RaLP] \u00a7r\u00a7cKullan\u0131m: \u00a77/login <\u015fifre>";
            case "login.success" -> "\u00a7aBa\u015far\u0131yla giri\u015f yapt\u0131n\u0131z!";
            case "login.wrong" -> "\u00a7cYanl\u0131\u015f \u015fifre!";
            case "login.not-registered" -> "\u00a7c\u00d6nce kay\u0131t olmal\u0131s\u0131n\u0131z!";
            case "login.blocked" -> "\u00a7c\u00c7ok fazla ba\u015far\u0131s\u0131z deneme! Engellendiniz.";
            case "login.blocked-time" -> "\u00a7c{time} saniye boyunca engellendiniz!";
            case "login.already" -> "\u00a7cZaten giri\u015f yapt\u0131n\u0131z!";
            case "changepassword.success" -> "\u00a7a\u015eifre de\u011fi\u015ftirildi! L\u00fctfen tekrar giri\u015f yap\u0131n.";
            case "changepassword.error" -> "\u00a7c\u015eifre de\u011fi\u015ftirilemedi!";
            case "unreg.success" -> "\u00a7aOyuncu {player} kald\u0131r\u0131ld\u0131!";
            case "unreg.not-registered" -> "\u00a7cOyuncu kay\u0131tl\u0131 de\u011fil!";
            case "errors.player-only" -> "\u00a7cBu komut sadece oyuncular i\u00e7indir!";
            case "errors.not-logged-in" -> "\u00a7c\u00d6nce giri\u015f yapmal\u0131s\u0131n\u0131z!";
            case "errors.no-permission" -> "\u00a7c\u0130zniniz yok!";
            case "help.message" -> "\u00a76\u00a7l[RaLP Yard\u0131m]\n\u00a7r\u00a77/ralp set max-attempts <N> \u00a78- Maks. giri\u015f denemesi\n\u00a77/ralp set block-time <sn> \u00a78- Engelleme s\u00fcresi\n\u00a77/ralp set min-length <N> \u00a78- Min. \u015fifre uzunlu\u011fu\n\u00a77/ralp set language <en|ru|de|es|fr|pt|tr|it|ko|ja|zh|pl> \u00a78- Dil\n\u00a77/ralp reload \u00a78- Yap\u0131land\u0131rmay\u0131 yeniden y\u00fckle";
            default -> "\u00a7c[Bilinmeyen mesaj: " + key + "]";
        };
    }

    private static String getIt(String key) {
        return switch (key) {
            case "register.usage" -> "\u00a76\u00a7l[RaLP] \u00a7r\u00a7aUso: \u00a77/register <password> <ripeti>";
            case "register.success" -> "\u00a7aRegistrazione e login effettuati con successo!";
            case "register.already" -> "\u00a7cSei gi\u00e0 registrato!";
            case "register.passwords-not-match" -> "\u00a7cLe password non corrispondono!";
            case "register.too-short" -> "\u00a7cLa password deve essere di almeno " + ConfigManager.minPasswordLength + " caratteri!";
            case "login.usage" -> "\u00a76\u00a7l[RaLP] \u00a7r\u00a7cUso: \u00a77/login <password>";
            case "login.success" -> "\u00a7aLogin effettuato con successo!";
            case "login.wrong" -> "\u00a7cPassword errata!";
            case "login.not-registered" -> "\u00a7cDevi prima registrarti!";
            case "login.blocked" -> "\u00a7cTroppi tentativi falliti! Sei bloccato.";
            case "login.blocked-time" -> "\u00a7cSei bloccato per {time} secondi!";
            case "login.already" -> "\u00a7cHai gi\u00e0 effettuato il login!";
            case "changepassword.success" -> "\u00a7aPassword cambiata! Per favore effettua di nuovo il login.";
            case "changepassword.error" -> "\u00a7cErrore nel cambio password!";
            case "unreg.success" -> "\u00a7aGiocatore {player} rimosso!";
            case "unreg.not-registered" -> "\u00a7cIl giocatore non \u00e8 registrato!";
            case "errors.player-only" -> "\u00a7cQuesto comando \u00e8 solo per i giocatori!";
            case "errors.not-logged-in" -> "\u00a7cDevi prima effettuare il login!";
            case "errors.no-permission" -> "\u00a7cNon hai il permesso!";
            case "help.message" -> "\u00a76\u00a7l[Aiuto RaLP]\n\u00a7r\u00a77/ralp set max-attempts <N> \u00a78- Tentativi massimi\n\u00a77/ralp set block-time <sec> \u00a78- Durata blocco\n\u00a77/ralp set min-length <N> \u00a78- Lunghezza min password\n\u00a77/ralp set language <en|ru|de|es|fr|pt|tr|it|ko|ja|zh|pl> \u00a78- Lingua\n\u00a77/ralp reload \u00a78- Ricarica configurazione";
            default -> "\u00a7c[Messaggio sconosciuto: " + key + "]";
        };
    }

    private static String getKo(String key) {
        return switch (key) {
            case "register.usage" -> "\u00a76\u00a7l[RaLP] \u00a7r\u00a7a\uc0ac\uc6a9\ubc95: \u00a77/register <\ube44\ubc00\ubc88\ud638> <\ud655\uc778>";
            case "register.success" -> "\u00a7a\uc131\uacf5\uc801\uc73c\ub85c \ub4f1\ub85d\ud558\uace0 \ub85c\uadf8\uc778\ud588\uc2b5\ub2c8\ub2e4!";
            case "register.already" -> "\u00a7c\uc774\ubbf8 \ub4f1\ub85d\ub418\uc5b4 \uc788\uc2b5\ub2c8\ub2e4!";
            case "register.passwords-not-match" -> "\u00a7c\ube44\ubc00\ubc88\ud638\uac00 \uc77c\uce58\ud558\uc9c0 \uc54a\uc2b5\ub2c8\ub2e4!";
            case "register.too-short" -> "\u00a7c\ube44\ubc00\ubc88\ud638\ub294 \ucd5c\uc18c " + ConfigManager.minPasswordLength + "\uc790 \uc774\uc0c1\uc774\uc5b4\uc57c \ud569\ub2c8\ub2e4!";
            case "login.usage" -> "\u00a76\u00a7l[RaLP] \u00a7r\u00a7c\uc0ac\uc6a9\ubc95: \u00a77/login <\ube44\ubc00\ubc88\ud638>";
            case "login.success" -> "\u00a7a\uc131\uacf5\uc801\uc73c\ub85c \ub85c\uadf8\uc778\ud588\uc2b5\ub2c8\ub2e4!";
            case "login.wrong" -> "\u00a7c\uc798\ubabb\ub41c \ube44\ubc00\ubc88\ud638\uc785\ub2c8\ub2e4!";
            case "login.not-registered" -> "\u00a7c\uba3c\uc800 \ub4f1\ub85d\ud574 \uc8fc\uc138\uc694!";
            case "login.blocked" -> "\u00a7c\uc2e4\ud328\ud55c \uc2dc\ub3c4\uac00 \ub108\ubb34 \ub9ce\uc2b5\ub2c8\ub2e4! \ucc28\ub2e8\ub418\uc5c8\uc2b5\ub2c8\ub2e4.";
            case "login.blocked-time" -> "\u00a7c{time}\ucd08 \ub3d9\uc548 \ucc28\ub2e8\ub418\uc5c8\uc2b5\ub2c8\ub2e4!";
            case "login.already" -> "\u00a7c\uc774\ubbf8 \ub85c\uadf8\uc778\ud588\uc2b5\ub2c8\ub2e4!";
            case "changepassword.success" -> "\u00a7a\ube44\ubc00\ubc88\ud638\ub97c \ubcc0\uacbd\ud588\uc2b5\ub2c8\ub2e4! \ub2e4\uc2dc \ub85c\uadf8\uc778\ud574 \uc8fc\uc138\uc694.";
            case "changepassword.error" -> "\u00a7c\ube44\ubc00\ubc88\ud638 \ubcc0\uacbd\uc5d0 \uc2e4\ud328\ud588\uc2b5\ub2c8\ub2e4!";
            case "unreg.success" -> "\u00a7a\ud50c\ub808\uc774\uc5b4 {player} \ub2d8\uc758 \uacc4\uc815\uc774 \uc0ad\uc81c\ub418\uc5c8\uc2b5\ub2c8\ub2e4!";
            case "unreg.not-registered" -> "\u00a7c\ud50c\ub808\uc774\uc5b4\uac00 \ub4f1\ub85d\ub418\uc9c0 \uc54a\uc558\uc2b5\ub2c8\ub2e4!";
            case "errors.player-only" -> "\u00a7c\uc774 \uba85\ub839\uc5b4\ub294 \ud50c\ub808\uc774\uc5b4\ub9cc \uc0ac\uc6a9\ud560 \uc218 \uc788\uc2b5\ub2c8\ub2e4!";
            case "errors.not-logged-in" -> "\u00a7c\uba3c\uc800 \ub85c\uadf8\uc778\ud574 \uc8fc\uc138\uc694!";
            case "errors.no-permission" -> "\u00a7c\uad8c\ud55c\uc774 \uc5c6\uc2b5\ub2c8\ub2e4!";
            case "help.message" -> "\u00a76\u00a7l[RaLP \ub3c4\uc6c0\ub9d0]\n\u00a7r\u00a77/ralp set max-attempts <N> \u00a78- \ucd5c\ub300 \ub85c\uadf8\uc778 \uc2dc\ub3c4\n\u00a77/ralp set block-time <\ucd08> \u00a78- \ucc28\ub2e8 \uc9c0\uc18d \uc2dc\uac04\n\u00a77/ralp set min-length <N> \u00a78- \ucd5c\uc18c \ube44\ubc00\ubc88\ud638 \uae38\uc774\n\u00a77/ralp set language <en|ru|de|es|fr|pt|tr|it|ko|ja|zh|pl> \u00a78- \uc5b8\uc5b4\n\u00a77/ralp reload \u00a78- \uc124\uc815 \ub2e4\uc2dc \ubd88\ub7ec\uc624\uae30";
            default -> "\u00a7c[\uc54c \uc218 \uc5c6\ub294 \uba54\uc2dc\uc9c0: " + key + "]";
        };
    }

    private static String getJa(String key) {
        return switch (key) {
            case "register.usage" -> "\u00a76\u00a7l[RaLP] \u00a7r\u00a7a\u4f7f\u3044\u65b9: \u00a77/register <\u30d1\u30b9\u30ef\u30fc\u30c9> <\u78ba\u8a8d>";
            case "register.success" -> "\u00a7a\u767b\u9332\u3068\u30ed\u30b0\u30a4\u30f3\u306b\u6210\u529f\u3057\u307e\u3057\u305f\uff01";
            case "register.already" -> "\u00a7c\u65e2\u306b\u767b\u9332\u3055\u308c\u3066\u3044\u307e\u3059\uff01";
            case "register.passwords-not-match" -> "\u00a7c\u30d1\u30b9\u30ef\u30fc\u30c9\u304c\u4e00\u81f4\u3057\u307e\u305b\u3093\uff01";
            case "register.too-short" -> "\u00a7c\u30d1\u30b9\u30ef\u30fc\u30c9\u306f\u5c11\u306a\u304f\u3068\u3082" + ConfigManager.minPasswordLength + "\u6587\u5b57\u4ee5\u4e0a\u3067\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\uff01";
            case "login.usage" -> "\u00a76\u00a7l[RaLP] \u00a7r\u00a7c\u4f7f\u3044\u65b9: \u00a77/login <\u30d1\u30b9\u30ef\u30fc\u30c9>";
            case "login.success" -> "\u00a7a\u30ed\u30b0\u30a4\u30f3\u306b\u6210\u529f\u3057\u307e\u3057\u305f\uff01";
            case "login.wrong" -> "\u00a7c\u30d1\u30b9\u30ef\u30fc\u30c9\u304c\u9593\u9055\u3063\u3066\u3044\u307e\u3059\uff01";
            case "login.not-registered" -> "\u00a7c\u307e\u305a\u767b\u9332\u3057\u3066\u304f\u3060\u3055\u3044\uff01";
            case "login.blocked" -> "\u00a7c\u5931\u6557\u3057\u305f\u8a66\u884c\u304c\u591a\u3059\u304e\u307e\u3059\uff01\u30d6\u30ed\u30c3\u30af\u3055\u308c\u307e\u3057\u305f\u3002";
            case "login.blocked-time" -> "\u00a7c{time}\u79d2\u9593\u30d6\u30ed\u30c3\u30af\u3055\u308c\u307e\u3057\u305f\uff01";
            case "login.already" -> "\u00a7c\u3059\u3067\u306b\u30ed\u30b0\u30a4\u30f3\u3057\u3066\u3044\u307e\u3059\uff01";
            case "changepassword.success" -> "\u00a7a\u30d1\u30b9\u30ef\u30fc\u30c9\u3092\u5909\u66f4\u3057\u307e\u3057\u305f\uff01\u518d\u5ea6\u30ed\u30b0\u30a4\u30f3\u3057\u3066\u304f\u3060\u3055\u3044\u3002";
            case "changepassword.error" -> "\u00a7c\u30d1\u30b9\u30ef\u30fc\u30c9\u306e\u5909\u66f4\u306b\u5931\u6557\u3057\u307e\u3057\u305f\uff01";
            case "unreg.success" -> "\u00a7a\u30d7\u30ec\u30a4\u30e4\u30fc {player} \u306e\u30a2\u30ab\u30a6\u30f3\u30c8\u3092\u524a\u9664\u3057\u307e\u3057\u305f\uff01";
            case "unreg.not-registered" -> "\u00a7c\u30d7\u30ec\u30a4\u30e4\u30fc\u306f\u767b\u9332\u3055\u308c\u3066\u3044\u307e\u305b\u3093\uff01";
            case "errors.player-only" -> "\u00a7c\u3053\u306e\u30b3\u30de\u30f3\u30c9\u306f\u30d7\u30ec\u30a4\u30e4\u30fc\u306e\u307f\u4f7f\u7528\u3067\u304d\u307e\u3059\uff01";
            case "errors.not-logged-in" -> "\u00a7c\u307e\u305a\u30ed\u30b0\u30a4\u30f3\u3057\u3066\u304f\u3060\u3055\u3044\uff01";
            case "errors.no-permission" -> "\u00a7c\u6a29\u9650\u304c\u3042\u308a\u307e\u305b\u3093\uff01";
            case "help.message" -> "\u00a76\u00a7l[RaLP \u30d8\u30eb\u30d7]\n\u00a7r\u00a77/ralp set max-attempts <N> \u00a78- \u6700\u5927\u30ed\u30b0\u30a4\u30f3\u8a66\u884c\u56de\u6570\n\u00a77/ralp set block-time <\u79d2> \u00a78- \u30d6\u30ed\u30c3\u30af\u671f\u9593\n\u00a77/ralp set min-length <N> \u00a78- \u6700\u5c0f\u30d1\u30b9\u30ef\u30fc\u30c9\u9577\n\u00a77/ralp set language <en|ru|de|es|fr|pt|tr|it|ko|ja|zh|pl> \u00a78- \u8a00\u8a9e\n\u00a77/ralp reload \u00a78- \u8a2d\u5b9a\u3092\u518d\u8aad\u307f\u8fbc\u307f";
            default -> "\u00a7c[\u4e0d\u660e\u306a\u30e1\u30c3\u30bb\u30fc\u30b8: " + key + "]";
        };
    }

    private static String getZh(String key) {
        return switch (key) {
            case "register.usage" -> "\u00a76\u00a7l[RaLP] \u00a7r\u00a7a\u7528\u6cd5: \u00a77/register <\u5bc6\u7801> <\u91cd\u590d>";
            case "register.success" -> "\u00a7a\u60a8\u5df2\u6210\u529f\u6ce8\u518c\u5e76\u767b\u5f55\uff01";
            case "register.already" -> "\u00a7c\u60a8\u5df2\u7ecf\u6ce8\u518c\u8fc7\u4e86\uff01";
            case "register.passwords-not-match" -> "\u00a7c\u5bc6\u7801\u4e0d\u5339\u914d\uff01";
            case "register.too-short" -> "\u00a7c\u5bc6\u7801\u957f\u5ea6\u81f3\u5c11\u4e3a " + ConfigManager.minPasswordLength + " \u4e2a\u5b57\u7b26\uff01";
            case "login.usage" -> "\u00a76\u00a7l[RaLP] \u00a7r\u00a7c\u7528\u6cd5: \u00a77/login <\u5bc6\u7801>";
            case "login.success" -> "\u00a7a\u767b\u5f55\u6210\u529f\uff01";
            case "login.wrong" -> "\u00a7c\u5bc6\u7801\u9519\u8bef\uff01";
            case "login.not-registered" -> "\u00a7c\u8bf7\u5148\u6ce8\u518c\uff01";
            case "login.blocked" -> "\u00a7c\u5931\u8d25\u5c1d\u8bd5\u6b21\u6570\u8fc7\u591a\uff01\u60a8\u5df2\u88ab\u5c01\u7981\u3002";
            case "login.blocked-time" -> "\u00a7c\u60a8\u5df2\u88ab\u5c01\u7981 {time} \u79d2\uff01";
            case "login.already" -> "\u00a7c\u60a8\u5df2\u7ecf\u767b\u5f55\u4e86\uff01";
            case "changepassword.success" -> "\u00a7a\u5bc6\u7801\u4fee\u6539\u6210\u529f\uff01\u8bf7\u91cd\u65b0\u767b\u5f55\u3002";
            case "changepassword.error" -> "\u00a7c\u5bc6\u7801\u4fee\u6539\u5931\u8d25\uff01";
            case "unreg.success" -> "\u00a7a\u73a9\u5bb6 {player} \u7684\u8d26\u6237\u5df2\u5220\u9664\uff01";
            case "unreg.not-registered" -> "\u00a7c\u73a9\u5bb6\u672a\u6ce8\u518c\uff01";
            case "errors.player-only" -> "\u00a7c\u8be5\u547d\u4ee4\u53ea\u80fd\u7531\u73a9\u5bb6\u6267\u884c\uff01";
            case "errors.not-logged-in" -> "\u00a7c\u8bf7\u5148\u767b\u5f55\uff01";
            case "errors.no-permission" -> "\u00a7c\u60a8\u6ca1\u6709\u6743\u9650\uff01";
            case "help.message" -> "\u00a76\u00a7l[RaLP \u5e2e\u52a9]\n\u00a7r\u00a77/ralp set max-attempts <N> \u00a78- \u6700\u5927\u767b\u5f55\u5c1d\u8bd5\u6b21\u6570\n\u00a77/ralp set block-time <\u79d2> \u00a78- \u5c01\u7981\u65f6\u957f\n\u00a77/ralp set min-length <N> \u00a78- \u6700\u5c0f\u5bc6\u7801\u957f\u5ea6\n\u00a77/ralp set language <en|ru|de|es|fr|pt|tr|it|ko|ja|zh|pl> \u00a78- \u8bed\u8a00\n\u00a77/ralp reload \u00a78- \u91cd\u65b0\u52a0\u8f7d\u914d\u7f6e";
            default -> "\u00a7c[\u672a\u77e5\u6d88\u606f: " + key + "]";
        };
    }

    private static String getPl(String key) {
        return switch (key) {
            case "register.usage" -> "\u00a76\u00a7l[RaLP] \u00a7r\u00a7aU\u017cycie: \u00a77/register <has\u0142o> <powt\u00f3rz>";
            case "register.success" -> "\u00a7aPomy\u015blnie zarejestrowano i zalogowano!";
            case "register.already" -> "\u00a7cJeste\u015b ju\u017c zarejestrowany!";
            case "register.passwords-not-match" -> "\u00a7cHas\u0142a nie pasuj\u0105 do siebie!";
            case "register.too-short" -> "\u00a7cHas\u0142o musi mie\u0107 co najmniej " + ConfigManager.minPasswordLength + " znak\u00f3w!";
            case "login.usage" -> "\u00a76\u00a7l[RaLP] \u00a7r\u00a7cU\u017cycie: \u00a77/login <has\u0142o>";
            case "login.success" -> "\u00a7aPomy\u015blnie zalogowano!";
            case "login.wrong" -> "\u00a7cNieprawid\u0142owe has\u0142o!";
            case "login.not-registered" -> "\u00a7cNajpierw si\u0119 zarejestruj!";
            case "login.blocked" -> "\u00a7cZbyt wiele nieudanych pr\u00f3b! Zosta\u0142e\u015b zablokowany.";
            case "login.blocked-time" -> "\u00a7cZosta\u0142e\u015b zablokowany na {time} sekund!";
            case "login.already" -> "\u00a7cJeste\u015b ju\u017c zalogowany!";
            case "changepassword.success" -> "\u00a7aHas\u0142o zosta\u0142o zmienione! Zaloguj si\u0119 ponownie.";
            case "changepassword.error" -> "\u00a7cNie uda\u0142o si\u0119 zmieni\u0107 has\u0142a!";
            case "unreg.success" -> "\u00a7aKonto gracza {player} zosta\u0142o usuni\u0119te!";
            case "unreg.not-registered" -> "\u00a7cGracz nie jest zarejestrowany!";
            case "errors.player-only" -> "\u00a7cTa komenda jest tylko dla graczy!";
            case "errors.not-logged-in" -> "\u00a7cNajpierw si\u0119 zaloguj!";
            case "errors.no-permission" -> "\u00a7cNie masz uprawnie\u0144!";
            case "help.message" -> "\u00a76\u00a7l[Pomoc RaLP]\n\u00a7r\u00a77/ralp set max-attempts <N> \u00a78- Maks. pr\u00f3b logowania\n\u00a77/ralp set block-time <sek> \u00a78- Czas blokady\n\u00a77/ralp set min-length <N> \u00a78- Min. d\u0142ugo\u015b\u0107 has\u0142a\n\u00a77/ralp set language <en|ru|de|es|fr|pt|tr|it|ko|ja|zh|pl> \u00a78- J\u0119zyk\n\u00a77/ralp reload \u00a78- Prze\u0142aduj konfiguracj\u0119";
            default -> "\u00a7c[Nieznana wiadomo\u015b\u0107: " + key + "]";
        };
    }
}