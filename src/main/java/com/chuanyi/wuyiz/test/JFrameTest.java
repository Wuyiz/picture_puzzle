package com.chuanyi.wuyiz.test;

import javax.swing.*;
import java.awt.*;

/**
 * @ClassName JFrameTest
 * @Description: TODO
 * @Author AllureLove
 * @Date 2019/10/12
 * @Version V1.0
 **/
public class JFrameTest extends JFrame {
    public JFrameTest(){
        this.setTitle("拼图");
        this.setSize(400,400);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel jp = new JPanel();
        jp.setBackground(Color.blue);

        JButton btn1 = new JButton("按钮1");
        btn1.setPreferredSize(new Dimension(100,300));

        JButton btn2 = new JButton("按钮2");
        btn1.setPreferredSize(new Dimension(100,300));

        JButton btn3 = new JButton("按钮3");
        btn1.setPreferredSize(new Dimension(100,300));

        jp.add(btn1);
        jp.add(btn2);
        jp.add(btn3);
    }

    public static void main(String[] args) {
        JFrameTest jft = new JFrameTest();
        jft.setVisible(true);
    }
}
