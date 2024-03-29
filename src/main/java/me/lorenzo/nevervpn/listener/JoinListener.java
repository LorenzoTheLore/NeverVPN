package me.lorenzo.nevervpn.listener;

import com.destroystokyo.paper.event.player.PlayerHandshakeEvent;
import me.lorenzo.nevervpn.NeverVPN;
import me.lorenzo.nevervpn.vpn.VPNChecker;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Listen for join of players, in particular the {@link PlayerJoinEvent PlayerJoinEvent}
 */
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
    public void onJoin(PlayerHandshakeEvent event) {
        String ip = event.getSocketAddressHostname();

        vpnChecker.fetchInfo(ip).whenCompleteAsync((ipInfo, throwable) -> {
            if (ipInfo.isVpn()) {
                Bukkit.getScheduler().runTask(NeverVPN.getInstance(), () -> {
                    event.setFailed(true);
                    event.setCancelled(true);
                });
            }
        });
    }
}
