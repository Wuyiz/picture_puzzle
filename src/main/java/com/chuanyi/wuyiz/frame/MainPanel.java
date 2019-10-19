package com.chuanyi.wuyiz.frame;

import com.chuanyi.wuyiz.method.ImageUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * @ClassName MainPanel
 * @Description: TODO
 * @Author AllureLove
 * @Date 2019/10/19
 * @Version V1.0
 **/
public class MainPanel extends JPanel {
    JButton[] buttons = new JButton[25];    //Numbers of btn
    ImageIcon[] imageIcons = new ImageIcon[25]; //Numbers of icon
    int state[] = new int[25];  //Storage order
    int nullBtn;    //Blank btn position
    int pattern;    //Numbers of icons
    int total;  //total of icons

    int cutIcon_width = 220;  //cut icon size
    int cutIcon_height = 200;

    public MainPanel(String path, int pattern) {
        this.pattern = pattern;
        total = pattern * pattern;
        breakRandom(path, pattern);
    }

    public void breakRandom(String path, int pattern) {
        this.pattern = pattern;
        total = pattern * pattern;
        nullBtn = total - 1;

        ImageUtil.cutImage(new File(path + "\\index.jpg"),
                pattern, path + pattern);

        this.removeAll();
        this.updateUI();
        this.setLayout(new GridLayout(pattern, pattern));

        random(state);

        for (int i = 0; i < total; i++) {
            buttons[i] = new JButton();
            this.add(buttons[i]);
        }

        for (int i = 0; i < total - 1; i++) {
            imageIcons[i] = new ImageIcon(path + pattern + "\\" + state[i] + ".jpg");
            imageIcons[i].setImage(imageIcons[i].getImage().getScaledInstance(cutIcon_width,
                    cutIcon_height,Image.SCALE_DEFAULT));
            buttons[i].setIcon(imageIcons[i]);
        }
    }

    public void random(int a[]) {
        int i = 0;
        Random cd = new Random();
        a[0] = cd.nextInt(total - 1);

        for (; i < total - 1; i++) {
            int temp = cd.nextInt(total - 1);
            for (int j = 0; j < i; j++) {
                if (a[j] != temp) {
                    a[i] = temp;
                } else {
                    i--;
                    break;
                }
            }
        }
        a[i] = total - 1;
        System.out.printf("图片的初始顺序为：");
        for (i = 0; i < total; i++) {
            System.out.printf(a[i] + " ");
        }
    }
}
