package me.lorenzo.nevervpn.api;

import io.ipinfo.api.IPinfo;
import io.ipinfo.api.errors.RateLimitedException;
import io.ipinfo.api.model.IPResponse;
import me.lorenzo.nevervpn.vpn.IPInfo;

/**
 * API class to handle IpInfo related requests
 */
public class IpInfoAPI {
    /**
     * Token used to use the ipinfo.io's API
     */
    private final String API_TOKEN = "ea3b668f655d8a";

    /**
     * IPInfo api instance
     */
    private final IPinfo ipInfo;

    /**
     * Public constructor for classes that needs access to IPInfo
     */
    public IpInfoAPI() {
        this.ipInfo = new IPinfo.Builder()
                .setToken(API_TOKEN)
                .build();
    }

    /**
     * Returns details about an ip
     *
     * @param ip Ip to fetch details about
     * @return Details linked to ip such as nationality, ISP ecc...
     * @throws RateLimitedException thrown if maximum requests has reached for the IPInfo api
     */
    public IPInfo getDetails(String ip) throws RateLimitedException {
        IPResponse ipResponse = ipInfo.lookupIP(ip);

        boolean isVPN = ipResponse.getPrivacy().getVpn() || ipResponse.getPrivacy().getProxy() || ipResponse.getPrivacy().getHosting();
        String nationality = ipResponse.getCountryCode();

        return new IPInfo(ip, isVPN, nationality);
    }
}
