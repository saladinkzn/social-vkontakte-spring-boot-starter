package ru.shadam.spring.boot.vkontakte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.social.SocialWebAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.GenericConnectionStatusView;
import org.springframework.social.vkontakte.api.VKontakte;
import org.springframework.social.vkontakte.api.impl.VKontakteTemplate;
import org.springframework.social.vkontakte.connect.VKontakteConnectionFactory;
/**
 * @author sala
 */
@Configuration
@ConditionalOnClass({SocialConfigurerAdapter.class, VKontakteConnectionFactory.class})
@ConditionalOnProperty(prefix= "ru.shadam.social-vkontakte", name = { "client-id", "client-secret"})
@AutoConfigureBefore(SocialWebAutoConfiguration.class)
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class VKontakteAutoConfiguration {

    @Configuration
    @EnableSocial
    @EnableConfigurationProperties(VKontakteProperties.class)
    @ConditionalOnWebApplication
    protected static class VKontakteConfigurationAdapter extends SocialConfigurerAdapter {

        @Autowired
        private VKontakteProperties properties;

        @Bean
        @ConditionalOnMissingBean
        @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
        public VKontakte vkontakte(ConnectionRepository repository) {
            Connection<VKontakte> connection = repository.findPrimaryConnection(VKontakte.class);
            if (connection != null) {
                return connection.getApi();
            }
            return new VKontakteTemplate(this.properties.getClientId(), this.properties.getClientSecret());
        }

        @Bean(name = { "connect/vkontakteConnect", "connect/vkontakteConnected" })
        @ConditionalOnProperty(prefix = "spring.social", name = "auto-connection-views")
        public GenericConnectionStatusView twitterConnectView() {
            return new GenericConnectionStatusView("vkontakte", "Vkontakte");
        }

        private ConnectionFactory<?> createConnectionFactory() {
            return new VKontakteConnectionFactory(this.properties.getClientId(), this.properties.getClientSecret());
        }

        @Override
        public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
            connectionFactoryConfigurer.addConnectionFactory(createConnectionFactory());
        }
    }
}
