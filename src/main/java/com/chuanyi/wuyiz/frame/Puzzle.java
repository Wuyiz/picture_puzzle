package com.chuanyi.wuyiz.frame;


import javax.swing.*;

/**
 * @ClassName puzzle
 * @Description: TODO
 * @Author AllureLove
 * @Date 2019/10/16
 * @Version V1.0
 **/
public class Puzzle extends JFrame {
    MainPanel panel;
    String path;    //Image's path
    int pattern;    //Numbers of pic

    public Puzzle() {
        this.setTitle("拼图");
        this.setSize(660, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);   //Adjust size

        this.setPath();
        this.setPattern(3);

        panel = new MainPanel(path,pattern);

        this.add(panel);
        this.setVisible(true);
    }

    public void setPath() {
        path = "img\\type1\\";
    }

    public void setPattern(int pattern) {
        this.pattern = pattern;
    }

    public static void main(String[] args) {
        new Puzzle();
    }
}
