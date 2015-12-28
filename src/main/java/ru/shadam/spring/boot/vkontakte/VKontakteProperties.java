package ru.shadam.spring.boot.vkontakte;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author sala
 */
@ConfigurationProperties(prefix = "ru.shadam.social-vkontakte")
public class VKontakteProperties  {
    private String clientId;
    private String clientSecret;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
