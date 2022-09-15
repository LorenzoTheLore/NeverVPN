package me.lorenzo.nevervpn.vpn;

import io.ipinfo.api.errors.RateLimitedException;
import me.lorenzo.nevervpn.api.IpInfoAPI;
import me.lorenzo.nevervpn.exception.VPNCheckException;
import me.lorenzo.nevervpn.persistence.ipinfo.IPInfoPersistenceHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Utility class used to check ip-related things
 */
public class VPNChecker {
    /**
     * List of cached ips already vpn-checked
     */
    private final List<IPInfo> cachedIpList;
    /**
     * API used to make requests to ipinfo.io
     */
    private final IpInfoAPI ipInfoAPI;
    /**
     * Handler for persistence logic
     */
    private final IPInfoPersistenceHandler ipInfoPersistenceHandler;

    /**
     * Initialize cache and API
     */
    public VPNChecker() {
        this.cachedIpList = new ArrayList<>();
        this.ipInfoAPI = new IpInfoAPI();
        this.ipInfoPersistenceHandler = new IPInfoPersistenceHandler();
    }

    /**
     * Fetch info about specified ip, retrieving from cache if present
     *
     * @param ip Ip to check
     * @return {@link IPInfo IPInfo} instance
     */
    public CompletableFuture<IPInfo> fetchInfo(String ip) {
        return CompletableFuture.supplyAsync(() -> getCached(ip).orElseGet(() -> {
            try {
                IPInfo ipInfo = ipInfoAPI.getDetails(ip);
                cachedIpList.add(ipInfo);

                return ipInfo;
            } catch (RateLimitedException e) {
                throw new VPNCheckException("Failed to check ip " + ip);
            }
        }));
    }

    /**
     * Returns cached {@link IPInfo IPInfo} associated with specified ip if present
     *
     * @param ipAddress Ip address to retrieve informations about
     * @return Ip details
     */
    private Optional<IPInfo> getCached(String ipAddress) {
        return cachedIpList.stream()
                .filter(ipInfo -> ipInfo.getIp().equalsIgnoreCase(ipAddress))
                .findFirst();
    }

    /**
     * Write {@link #cachedIpList} to JSON storage for caching purposes
     */
    public void saveCache() {
        this.cachedIpList.forEach(ipInfoPersistenceHandler::serialize);
    }

    /**
     * Write {@link #cachedIpList} ips
     */
    public void loadCache() {
        this.cachedIpList.addAll(ipInfoPersistenceHandler.fetchIPInfos());
    }
}
