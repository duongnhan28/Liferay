package system.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriTemplateHandler;

import java.nio.charset.Charset;

@Configuration
@RequiredArgsConstructor
@PropertySource("classpath:config.properties")
public class Config {

    private final Environment env;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean("objectMapper")
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    @Bean("nhanVienRest")
    public RestTemplate nhanVienRest() {

        String rootURL = env.getProperty("url.nhanvien");

        RestTemplate restClient = new RestTemplate();
        DefaultUriTemplateHandler uriTemplateHandler = new DefaultUriTemplateHandler();
        uriTemplateHandler.setBaseUrl(rootURL);
        restClient.setUriTemplateHandler(uriTemplateHandler);
        restClient.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));


        return restClient;
    }

    @Bean("ngayNghiRest")
    public RestTemplate ngayNghiRest() {

        String rootURL = env.getProperty("url.ngaynghi");

        RestTemplate restClient = new RestTemplate();
        DefaultUriTemplateHandler uriTemplateHandler = new DefaultUriTemplateHandler();
        uriTemplateHandler.setBaseUrl(rootURL);
        restClient.setUriTemplateHandler(uriTemplateHandler);
        restClient.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        return restClient;
    }
    @Bean("chiTietNgayNghiRest")
    public RestTemplate chiTietNgayNghiRest() {
        String rootURL = env.getProperty("url.chitietngaynghi");
        RestTemplate restClient = new RestTemplate();

        DefaultUriTemplateHandler uriTemplateHandler = new DefaultUriTemplateHandler();
        uriTemplateHandler.setBaseUrl(rootURL);
        restClient.setUriTemplateHandler(uriTemplateHandler);
        restClient.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        return restClient;
    }
    @Bean("chiPhiPhatSinhRest")
    public RestTemplate chiPhiPhatSinhRest() {

        String rootURL = env.getProperty("url.chiphiphatsinh");

        RestTemplate restClient = new RestTemplate();
        DefaultUriTemplateHandler uriTemplateHandler = new DefaultUriTemplateHandler();
        uriTemplateHandler.setBaseUrl(rootURL);
        restClient.setUriTemplateHandler(uriTemplateHandler);
        restClient.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        return restClient;
    }

    @Bean("duAnRest")
    public RestTemplate duAnRestTemplate() {

        String rootURL = env.getProperty("url.duan");
        RestTemplate restClient = new RestTemplate();

        DefaultUriTemplateHandler uriTemplateHandler = new DefaultUriTemplateHandler();
        uriTemplateHandler.setBaseUrl(rootURL);
        restClient.setUriTemplateHandler(uriTemplateHandler);
        restClient.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        return restClient;
    }

    @Bean("nhanVienDuAnRest")
    public RestTemplate nhanVienDuAnRestTemplate() {

        String rootURL = env.getProperty("url.nhanvienduan");
        RestTemplate restClient = new RestTemplate();

        DefaultUriTemplateHandler uriTemplateHandler = new DefaultUriTemplateHandler();
        uriTemplateHandler.setBaseUrl(rootURL);
        restClient.setUriTemplateHandler(uriTemplateHandler);
        restClient.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        return restClient;
    }

    @Bean("congTacRest")
    public RestTemplate congTacRestTemplate() {

        String rootURL = env.getProperty("url.congtac");
        RestTemplate restClient = new RestTemplate();

        DefaultUriTemplateHandler uriTemplateHandler = new DefaultUriTemplateHandler();
        uriTemplateHandler.setBaseUrl(rootURL);
        restClient.setUriTemplateHandler(uriTemplateHandler);
        restClient.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        return restClient;
    }

    @Bean("vaiTroRest")
    public RestTemplate vaiTroRestTemplate() {
        String rootURL = env.getProperty("url.vaitro");
        RestTemplate restClient = new RestTemplate();

        DefaultUriTemplateHandler uriTemplateHandler = new DefaultUriTemplateHandler();
        uriTemplateHandler.setBaseUrl(rootURL);
        restClient.setUriTemplateHandler(uriTemplateHandler);
        restClient.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        return restClient;
    }

    @Bean("chiTietCongTacRest")
    public RestTemplate chiTietCongTacRestTemplate() {

        String rootURL = env.getProperty("url.chitietcongtac");
        RestTemplate restClient = new RestTemplate();

        DefaultUriTemplateHandler uriTemplateHandler = new DefaultUriTemplateHandler();
        uriTemplateHandler.setBaseUrl(rootURL);
        restClient.setUriTemplateHandler(uriTemplateHandler);
        restClient.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        return restClient;
    }

    @Bean("nhanVienCongTacRest")
    public RestTemplate nhanVienCongTacRestTemplate() {

        String rootURL = env.getProperty("url.nhanviencongtac");
        RestTemplate restClient = new RestTemplate();

        DefaultUriTemplateHandler uriTemplateHandler = new DefaultUriTemplateHandler();
        uriTemplateHandler.setBaseUrl(rootURL);
        restClient.setUriTemplateHandler(uriTemplateHandler);
        restClient.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        return restClient;
    }
    @Bean("oTRest")
    public RestTemplate khoaRestTemplate() {
        String rootURL = env.getProperty("url.overtime");
        RestTemplate restClient = new RestTemplate();

        DefaultUriTemplateHandler uriTemplateHandler = new DefaultUriTemplateHandler();
        uriTemplateHandler.setBaseUrl(rootURL);
        restClient.setUriTemplateHandler(uriTemplateHandler);
        restClient.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        return restClient;
    }
    @Bean("ngayNghiNhanVienRest")
    public RestTemplate ngayNghiNhanVienRest() {
        String rootURL = env.getProperty("url.ngaynghinhanvien");
        RestTemplate restClient = new RestTemplate();

        DefaultUriTemplateHandler uriTemplateHandler = new DefaultUriTemplateHandler();
        uriTemplateHandler.setBaseUrl(rootURL);
        restClient.setUriTemplateHandler(uriTemplateHandler);
        restClient.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        return restClient;
    }
}
