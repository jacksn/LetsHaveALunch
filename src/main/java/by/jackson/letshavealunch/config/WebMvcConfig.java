package by.jackson.letshavealunch.config;

import by.jackson.letshavealunch.util.DateTimeUtil;
import by.jackson.letshavealunch.web.json.JacksonObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter(getJacksonObjectMapper()));
    }

    @Override
    public void addFormatters(FormatterRegistry formatterRegistry) {
        formatterRegistry.addFormatter(getLocalDateFormatter());
    }

    @Bean
    public DateTimeUtil.LocalDateFormatter getLocalDateFormatter() {
        return new DateTimeUtil.LocalDateFormatter();
    }

    @Bean
    public ObjectMapper getJacksonObjectMapper() {
        return JacksonObjectMapper.getMapper();
    }
}
