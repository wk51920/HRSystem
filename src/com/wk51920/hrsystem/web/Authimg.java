package com.wk51920.hrsystem.web;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Created by wk51920 on 16/5/30.
 */
@WebServlet(urlPatterns = {"/WEB-INF/content/authImg.jsp"})
public class Authimg extends HttpServlet {
    // 定义图形验证码绘制字符的字体
    private final Font mFont = new Font("Arial Black", Font.PLAIN, 16);
    // 定义图形验证码的大小
    private final int IMG_WIDTH = 100;
    private final int IMG_HEIGHT = 18;

    // 定义一个获取随机颜色的方法
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        // 得到随机颜色
        return new Color(r, g, b);
    }

    // 重写service方法,生成对客户端的响应

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置禁止缓存
        resp.setHeader("Pragma", "No-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        resp.setContentType("image/jpeg");
        BufferedImage image = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random = new Random();
        g.setColor(getRandColor(200, 250));
        // 填充背景色
        g.fillRect(1, 1, IMG_WIDTH - 1, IMG_HEIGHT - 1);
        // 为图形验证码绘制边框
        g.setColor(new Color(102, 102, 102));
        g.drawRect(0, 0, IMG_WIDTH - 1, IMG_HEIGHT - 1);
        g.setColor(getRandColor(160, 200));
        // 生成随机干扰线
        for (int i = 0; i < 30; i++) {
            int x = random.nextInt(IMG_WIDTH - 1);
            int y = random.nextInt(IMG_HEIGHT - 1);
            int xl = random.nextInt(6) + 1;
            int yl = random.nextInt(12) + 1;
            g.drawLine(x, y, x + xl, y + yl);
        }
        g.setColor(getRandColor(160, 200));
        // 生成随机干扰线
        for (int i = 0; i < 30; i++) {
            int x = random.nextInt(IMG_WIDTH - 1);
            int y = random.nextInt(IMG_HEIGHT - 1);
            int xl = random.nextInt(12) + 1;
            int yl = random.nextInt(6) + 1;
            g.drawLine(x, y, x - xl, y - yl);
        }
        // 设置绘制字符的字体
        g.setFont(mFont);
        // 用于保存系统生成的随机字符串
        String sRand = "";
        for (int i = 0; i < 6; i++) {
            String tmp = getRandChar();
            sRand += tmp;
            // 获取随机颜色
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110),
                    20 + random.nextInt(110)));
            // 在图片上绘制系统生成的随机字符
            g.drawString(tmp, 15 * i + 10, 15);
        }
        // 获取HttpSession对象
        HttpSession session = req.getSession(true);
        // 将随机字符串放入HttpSession对象中
        session.setAttribute("rand", sRand);
        g.dispose();
        // 向输出流中输出图片
        ImageIO.write(image, "JPEG", resp.getOutputStream());
    }

    // 定义获取随机字符串的方法
    private String getRandChar() {
        // 生成一个0\1\2的随机数字
        int rand = (int) Math.round(Math.random() * 2);
        long itemp = 0;
        char ctemp = '\u0000';
        switch (rand) {
            // 生成大写字母
            case 1:
                itemp = Math.round(Math.random() * 25 + 65);
                ctemp = (char) itemp;
                return String.valueOf(ctemp);
            // 生成小写字母
            case 2:
                itemp = Math.round(Math.random() * 25 + 97);
                ctemp = (char) itemp;
                return String.valueOf(ctemp);
            // 生成数字
            default:
                itemp = Math.round(Math.random() * 9);
                return itemp + "";
        }
    }
}
