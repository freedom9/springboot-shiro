package com.freedom.shiro;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * @Auther: freedom
 * @Date: 2018/12/12
 * @Description:
 */
@Controller
public class ValidateCodeController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @RequestMapping("/defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生成验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            request.getSession().setAttribute("KAPTCHA_SESSION_KEY", createText);

            //使用生成的验证码字符串返回一个BufferedImage对象并转为为byte写入byte数组中
            BufferedImage bufferedImage = defaultKaptcha.createImage(createText);
            ImageIO.write(bufferedImage, "jpg", jpegOutputStream);
        } catch(IllegalArgumentException e) {
            response.sendError(response.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型,使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        ServletOutputStream servletOutputStream = response.getOutputStream();
        servletOutputStream.write(captchaChallengeAsJpeg);
        servletOutputStream.flush();
        servletOutputStream.close();
    }
}
