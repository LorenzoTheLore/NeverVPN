package me.lorenzo.nevervpn;

import me.lorenzo.nevervpn.listener.JoinListener;
import me.lorenzo.nevervpn.vpn.VPNChecker;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class of the NeverVPN plugin
 */
public final class NeverVPN extends JavaPlugin implements Listener {
    /**
     * Plugin instance
     */
    private static NeverVPN instance;

    /**
     * Class used to retrieve ip informations from ipinfo.io or cache if present
     */
    private VPNChecker vpnChecker;

    /**
     * Retrieve {@link NeverVPN NeverVPN} instance
     *
     * @return Instance of this plugin
     */
    public static NeverVPN getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        this.vpnChecker = new VPNChecker();
        this.vpnChecker.loadCache(); //Load cached ips

        registerListeners();
    }

    @Override
    public void onDisable() {
        this.vpnChecker.saveCache(); //Save cached ips
    }

    /**
     * Retrieve {@link VPNChecker VPNChecker} instance
     *
     * @return {@link VPNChecker VPNChecker} instance
     */
    public VPNChecker getVpnChecker() {
        return vpnChecker;
    }

    /**
     * Register all bukkit's {@link Listener EventListeners}
     */
    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
    }
}
