package me.lorenzo.nevervpn.exception;

/**
 * Specific exception for vpn checking purposes
 */
public class VPNCheckException extends RuntimeException {

    /**
     * Constructor for the {@link VPNCheckException VPNCheckException}
     *
     * @param cause What caused the exception
     */
    public VPNCheckException(String cause) {
        super(cause);
    }
}
