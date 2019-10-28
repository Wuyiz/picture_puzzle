package com.wuyiz.frame;

import javax.swing.*;

/**
 * @ClassName Button
 * @Description: TODO
 * @Author AllureLove
 * @Date 2019/10/20
 * @Version V1.0
 **/
public class Button extends JButton {
    int row;    //X-axis of the btn
    int col;    //Y-axis of the btn

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
