package org.encuestame.persistence.domain.social;

import org.encuestame.persistence.domain.security.SocialAccount.TypeAuth;

/**
 * Social Provider.
 * @author Picado, Juan juanATencuestame.org
 * @since Feb 27, 2011
 */
public enum SocialProvider {
    /**
     * Twitter provider.
     */
    TWITTER,
    /**
     * Facebook provider.
     */
    FACEBOOK,
    /**
     * Identica provider.
     */
    IDENTICA,

    /**
     * Linked In provider.
     */
    LINKEDIN,
    /**
     * Google Buzz provider.
     */
    BUZZ,
    /**
     * Yahoo provider.
     */
    YAHOO,

    /**
     * Constructor.
     */
    SocialProvider(){
        //Constructor.
    };

    /**
     * To String.
     */
    public String toString() {
        String provider = "";
        if (this == TWITTER) { provider = "TWITTER"; }
        else if (this == FACEBOOK) { provider = "FACEBOOK"; }
        else if (this == IDENTICA) { provider = "IDENTICA"; }
        else if (this == LINKEDIN) { provider = "LINKEDIN"; }
        else if (this == BUZZ) { provider = "BUZZ"; }
        else if (this == YAHOO) { provider = "YAHOO"; }
        return provider;
    }

    /**
     * Get Provider by String.
     * @param socialProvider period
     * @return provider enum
     */
    public static SocialProvider getProvider(final String socialProvider) {
        if (null == socialProvider) { return null; }
        else if (socialProvider.equalsIgnoreCase("TWITTER")) { return TWITTER; }
        else if (socialProvider.equalsIgnoreCase("FACEBOOK")) { return FACEBOOK; }
        else if (socialProvider.equalsIgnoreCase("IDENTICA")) { return IDENTICA; }
        else if (socialProvider.equalsIgnoreCase("LINKEDIN")) { return LINKEDIN; }
        else if (socialProvider.equalsIgnoreCase("BUZZ")) { return BUZZ; }
        else if (socialProvider.equalsIgnoreCase("YAHOO")) { return YAHOO; }
        else return null;
    }

    /**
     * Provide OAuth protocol.
     * @param provider {@link SocialProvider}.
     * @return
     */
    public static TypeAuth getTypeAuth(final SocialProvider provider){
        if(provider.equals(TWITTER) || provider.equals(IDENTICA)
                || provider.equals(LINKEDIN) || provider.equals(YAHOO)){
            return TypeAuth.OAUTH1;
        } else if (provider.equals(BUZZ) || provider.equals(FACEBOOK)){
            return TypeAuth.OAUTH2;
        } else {
            return null;
        }
    }
}
