package com.bravo.demo.springboot.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bobby
 * @since 2019/9/17 22:57
 */
@Configuration
//适配器
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    //favorPathExtension表示是否支持后缀匹配
//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer.favorPathExtension(false);
//    }

    /**
     * 配置FastJson为方式一
     *
     * @return
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        /*
         * 1、需要先定义一个convert转换消息的对象 2、添加fastJson的配置信息，比如：是否要格式化返回json数据 3、在convert中添加配置信息
         * 4、将convert添加到converters当中
         *
         */
        // 1、需要先定义一个·convert转换消息的对象；
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 2、添加fastjson的配置信息，比如 是否要格式化返回json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        // 3、在convert中添加配置信息.
        fastConverter.setFastJsonConfig(fastJsonConfig);
        // 4、将convert添加到converters当中.
        converters.add(fastConverter);
    }

    /**
     * 利用fastjson替换掉jackson,且解决中文乱码问题
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        //1.需要定义一个convert转换消息的对象;
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        //2:添加fastJson的配置信息;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        /*
         * 第一个SerializerFeature.PrettyFormat可以省略，毕竟这会造成额外的内存消耗和流量，
         * fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
         * 第二个是用来指定当属性值为null时是否输出：pro:null
         * SerializerFeature.SkipTransientField
         */
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue);
        //3.处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON);
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        fastMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        fastMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
        fastMediaTypes.add(MediaType.APPLICATION_STREAM_JSON);
        //4.在convert中添加配置信息.
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
//        HttpMessageConverter<?> converter = fastJsonHttpMessageConverter;
//        return new HttpMessageConverters(converter);
        return new HttpMessageConverters(fastJsonHttpMessageConverter);
    }
}