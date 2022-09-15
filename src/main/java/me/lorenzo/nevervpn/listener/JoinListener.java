package me.lorenzo.nevervpn.listener;

import me.lorenzo.nevervpn.NeverVPN;
import me.lorenzo.nevervpn.vpn.VPNChecker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    /**
     * VPNChecker instance
     */
    private final VPNChecker vpnChecker;

    /**
     * Initialize VPNChecker instance
     */
    public JoinListener() {
        this.vpnChecker = NeverVPN.getInstance().getVpnChecker();
    }

    /**
     * Handle join of each player
     *
     * @param event {@link PlayerJoinEvent PlayerJoinEvent} instance
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        String ip = event.getPlayer().getAddress().getAddress().getHostAddress();
        Player player = event.getPlayer();

        vpnChecker.fetchInfo(ip).whenCompleteAsync((ipInfo, throwable) -> {
            if (ipInfo.isVpn()) {
                Bukkit.getScheduler().runTask(NeverVPN.getInstance(), () -> {
                    player.kickPlayer("Per favore, smetti di usare la vpn!");
                });
            }
        });
    }
}
