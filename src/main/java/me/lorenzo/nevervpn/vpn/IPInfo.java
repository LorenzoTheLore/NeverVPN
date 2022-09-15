package me.lorenzo.nevervpn.vpn;

/**
 * Model used to store ip information
 */
public class IPInfo {
    /**
     * Ip address of this instance
     */
    private final String ip;
    /**
     * Represents if this object is a vpn instance
     */
    private final boolean vpn;
    /**
     * Returns nationality of this ip
     */
    private final String nationality;

    /**
     * Public multi-purpose constructor
     *
     * @param ip  Ip of this instance
     * @param vpn Is this ip a VPN?
     */
    public IPInfo(String ip, boolean vpn, String nationality) {
        this.ip = ip;
        this.vpn = vpn;
        this.nationality = nationality;
    }

    /**
     * Retrieves current instance ip
     *
     * @return Ip assigned to this instance
     */
    public String getIp() {
        return ip;
    }

    /**
     * Returns if the current instance is a VPN ip
     *
     * @return Ip is vpn
     */
    public boolean isVpn() {
        return vpn;
    }

    /**
     * Returns nationality of current instance's ip
     *
     * @return Nationality of current instance's ip
     */
    public String getNationality() {
        return nationality;
    }
}
