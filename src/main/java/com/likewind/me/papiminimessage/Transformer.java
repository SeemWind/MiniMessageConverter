package com.likewind.me.papiminimessage;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author like_wind
 */
public class Transformer {
    String input;
    String c1, c2;
    String returnText;

    public Transformer(String string) {
        this.input = string;
        this.returnText = string;
    }

    // 转换渐变颜色
    public String getGradient() {
        // minedown 渐变匹配
        Pattern mineDown = Pattern.compile("(&#[A-Fa-f0-9]{6}(-#[A-Fa-f0-9]{6})+&)");
        Matcher mineDownMatcher = mineDown.matcher(returnText);

        // hex颜色代码匹配
        System.out.println(returnText);

        // 循环替换所有渐变标签
        while (mineDownMatcher.find()) {
            ArrayList<String> colorCode = new ArrayList<>();

            Pattern color = Pattern.compile("(#[A-Fa-f0-9]{6})");
            // 捕获 minedown 渐变文本
            Matcher colorMatcher = color.matcher(mineDownMatcher.group(1));
            System.out.println(mineDownMatcher.group(1));

            // 将 hex颜色代码 存储
            while (colorMatcher.find()) {
                System.out.println(colorMatcher.group(1));
                colorCode.add(colorMatcher.group(1));
            }


            // 构建 minimessage 字符串
            StringBuilder miniMessage = new StringBuilder("<gradient");
            // 循环添加所有渐变
            for (String code : colorCode) {
                miniMessage.append(":").append(code);
            }
            miniMessage.append(">");

            System.out.println(miniMessage);

            returnText = returnText.replaceFirst(mineDown.pattern(), String.valueOf(miniMessage));
        }
        returnText = returnText.replaceAll("(&r)", "<reset>");
        return returnText;

    }

}
