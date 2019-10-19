package com.chuanyi.wuyiz.test;

import javax.swing.*;
import java.awt.*;

/**
 * @ClassName LayoutTest
 * @Description: TODO
 * @Author AllureLove
 * @Date 2019/10/16
 * @Version V1.0
 **/
public class LayoutTest extends JFrame {
    public LayoutTest(){
        this.setTitle("拼图");
        this.setSize(360,400);
        this.setLocationRelativeTo(null);   //null , windows
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        GridLayout gridLayout = new GridLayout(3,3);
        panel.setLayout(gridLayout);

        for (int i = 0 ; i < 9 ; i++){
            JButton btn = new JButton(String.valueOf(i));
            panel.add(btn);
        }

        this.add(panel);
    }
    public static void main(String[] args) {
        LayoutTest layoutTest = new LayoutTest();
        layoutTest.setVisible(true);
    }
}
