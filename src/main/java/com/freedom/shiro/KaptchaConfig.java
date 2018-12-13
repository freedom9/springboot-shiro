package com.freedom.shiro;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @Auther: freedom
 * @Date: 2018/12/12
 * @Description: 图片验证码配置
 */
@Component
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha getDefaultKaptcha(){
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();

        properties.setProperty("kaptcha.border", "yes");           //是否有边框, 默认有
        properties.setProperty("kaptcha.border.color", "105,179,90");
        properties.setProperty("kaptcha.textproducer.font.color", "blue");    //字体颜色, 默认黑色
        properties.setProperty("kaptcha.image.width", "110");
        properties.setProperty("kaptcha.image.height", "40");
        properties.setProperty("kaptcha.textproducer.font.size", "40");    //字体大小, 默认40px
        properties.setProperty("kaptcha.session.key", "code");            //保存SESSION时的KEY, 默认KAPTCHA_SESSION_KEY
        properties.setProperty("kaptcha.textproducer.char.length", "4");   //验证码字符数量, 默认5
        properties.setProperty("kaptcha.textproducer.font.names","宋体，楷体，微软雅黑");   //字体名称, 默认Arial/Courier

        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);

        return defaultKaptcha;
    }
}
