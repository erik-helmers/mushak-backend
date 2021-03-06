package core.config;

import core.Settings;
import core.security.AccessDenied;
import logger.Log;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import core.security.NoRedirectStrategy;
import core.security.TokenAuthenticationFilter;
import core.security.TokenAuthenticationProvider;
import org.springframework.web.bind.annotation.RequestBody;

import static java.util.Objects.requireNonNull;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@FieldDefaults(level = PRIVATE, makeFinal = true)
class SecurityConfig extends WebSecurityConfigurerAdapter {

    TokenAuthenticationProvider provider;
    Settings settings;

    @Autowired
    SecurityConfig(final TokenAuthenticationProvider provider, Settings settings) {

        super();
        this.provider = requireNonNull(provider);
        this.settings = settings;


        boolean enabled = settings.getBoolean(Settings.Key.ENABLE_SECURITY);
        PUBLIC_URLS = getPublicUrls(enabled);
        PROTECTED_URLS = getProtectedUrls();
        Log.debug_line("SECURITY " + (enabled ? "ENABLED" : "DISABLED"));

    }


    private RequestMatcher getPublicUrls(boolean security_enabled){
        if (security_enabled) {
            return new OrRequestMatcher(
                    new AntPathRequestMatcher("/public/**"),
                    new AntPathRequestMatcher("/error")
            );
        }
        else {
            return new OrRequestMatcher(
                    new AntPathRequestMatcher( "**")
            );
        }

    }
    private RequestMatcher getProtectedUrls(){
        return new NegatedRequestMatcher(PUBLIC_URLS);
    }

    private final RequestMatcher PUBLIC_URLS ;
    private final RequestMatcher PROTECTED_URLS ;



    @Override
    protected void configure(final AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(provider);
    }

    @Override
    public void configure(final WebSecurity web) {
        web.ignoring().requestMatchers(PUBLIC_URLS);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(STATELESS)
                .and()
                .exceptionHandling()
                // this entry point handles when you request a protected page and you are not yet
                // authenticated
                .defaultAuthenticationEntryPointFor(forbiddenEntryPoint(), PROTECTED_URLS)
                .and()
                .authenticationProvider(provider)
                .addFilterBefore(restAuthenticationFilter(), AnonymousAuthenticationFilter.class)
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable();
    }

    @Bean
    TokenAuthenticationFilter restAuthenticationFilter() throws Exception {
        final TokenAuthenticationFilter filter = new TokenAuthenticationFilter(PROTECTED_URLS);
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(successHandler());
        filter.setAuthenticationFailureHandler(new AccessDenied());
        return filter;
    }

    @Bean
    SimpleUrlAuthenticationSuccessHandler successHandler() {
        final SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
        successHandler.setRedirectStrategy(new NoRedirectStrategy());
        return successHandler;
    }

    /**
     * Disable Spring boot automatic filter registration.
     */
    @Bean
    FilterRegistrationBean disableAutoRegistration(final TokenAuthenticationFilter filter) {
        final FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }

    @Bean
    AuthenticationEntryPoint forbiddenEntryPoint() {
        return new HttpStatusEntryPoint(HttpStatus.I_AM_A_TEAPOT);
    }
}