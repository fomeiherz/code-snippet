import io.spring2go.cathelper.CatServletFilter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * spring-boot新增一个启动的ServletFilter方式
 */
@Configuration
public class CatFilterConfigure {

    @Bean
    public FilterRegistrationBean catFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        CatServletFilter filter = new CatServletFilter();
        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.setName("cat-filter");
        registration.setOrder(1);
        return registration;
    }
}
