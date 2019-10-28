package com.wuyiz.frame;

import com.wuyiz.function.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Random;

/**
 * @ClassName MainPanel
 * @Description: TODO
 * @Author AllureLove
 * @Date 2019/10/19
 * @Version V1.0
 **/
public class MainPanel extends JPanel {
    int state[] = new int[25];  //Storage order
    int blankBtn;    //blankBtn btn position
    int pattern;    //Numbers of icons
    int total;  //total of icons

    int count;

    Button[] buttons = new Button[25];    //Numbers of btn
    ImageIcon[] imageIcons = new ImageIcon[25]; //Numbers of icon

    public int getCount() {
        return count;
    }

    int cutIcon_width = 220;  //cut icon size
    int cutIcon_height = 200;

    public MainPanel(String path, int pattern) {
        this.pattern = pattern;
        total = pattern * pattern;
        breakRandom(path, pattern);
    }

    public void breakRandom(String path, int pattern) {
        count = 0;
        this.pattern = pattern;
        total = pattern * pattern;
        blankBtn = total - 1;       //calculate the position of the blank btn

        ImageUtil.cutImage(new File(path + "\\index.jpg"),
                pattern, path + pattern);

        //initial the panel
        this.removeAll();
        this.updateUI();
        this.setLayout(new GridLayout(pattern, pattern));

        random(state);      //disrupt icon order

        //set the position of each btn
        for (int i = 0; i < total; i++) {
            buttons[i] = new Button();
            buttons[i].setRow(i / pattern);
            buttons[i].setCol(i % pattern);
            this.add(buttons[i]);
        }

        /**
         * add the icon to the btn and make the icon adaptive to
         * the current button size
         */
        for (int i = 0; i < total - 1; i++) {
            imageIcons[i] = new ImageIcon(path + pattern + "\\" + state[i] + ".jpg");
            imageIcons[i].setImage(imageIcons[i].getImage().getScaledInstance(cutIcon_width,
                    cutIcon_height, Image.SCALE_DEFAULT));
            buttons[i].setIcon(imageIcons[i]);
        }

        //add mouse listener event
        for (int i = 0; i < total; i++) {
            buttons[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    Button button = (Button) e.getSource();
                    remove(button);
                    count++;
                }
            });
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
    }

    //icon move function
    public void remove(Button clicked) {
        int row_black = buttons[blankBtn].getRow();     //get the position of the blank button
        int col_black = buttons[blankBtn].getCol();
        int row_click = clicked.getRow();               //get the position of the button you clicked
        int col_click = clicked.getCol();

        /**
         * if the difference between the horizontal or vertical coordinates between
         * the clicked button and the blank button is equal to 1,
         * the image position can be moved
         */
        if (Math.abs(row_black - row_click) == 1 && Math.abs(col_black - col_click) == 0
                || Math.abs(row_black - row_click) == 0 && Math.abs(col_black - col_click) == 1) {
            ImageIcon icon = (ImageIcon) clicked.getIcon();
            buttons[blankBtn].setIcon(icon);
            clicked.setIcon(null);
            int clickState = row_click * pattern + col_click;
            blankBtn = row_black * pattern + col_black;
            state[blankBtn] = state[clickState];
            state[clickState] = total - 1;
            blankBtn = clickState;
            check();
        } else {
            return;
        }
    }

    /**
     * Verify that the game is complete
     * by comparing it to the order of the originally created image
     */
    public void check() {
        for (int i = 0; i < total; i++) {
            if (state[i] != i) {
                return;
            }
            JOptionPane.showMessageDialog(this, "拼图成功");
        }
    }

}
