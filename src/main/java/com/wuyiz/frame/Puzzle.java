package com.wuyiz.frame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ClassName puzzle
 * @Description: TODO
 * @Author AllureLove
 * @Date 2019/10/16
 * @Version V1.0
 **/
public class Puzzle extends JFrame implements Runnable {
    JLabel total_time, total_count;     //total time and operation count
    long startTime, endTime;            //initial time and end time
    private MainPanel panel;            //main panel container
    private JMenuBar jMenuBar;          //mission board

    private JMenu menu, menuSelect, menuChange, menuRank, menuHelp;     //taskbar item
    private JMenuItem itemStart, itemExit, itemView;        //menu item: start game, exit game, view original photo

    private JRadioButtonMenuItem game_rank[] = new JRadioButtonMenuItem[3];     //menu item group
    private JRadioButtonMenuItem pic_change[] = new JRadioButtonMenuItem[3];

    private String path;    //Image path
    private int pattern;    //Numbers of pic

    //initial panel
    public Puzzle() {
        jMenuBar = new JMenuBar();
        menu = new JMenu("菜单");
        menuSelect = new JMenu("选择");
        menuHelp = new JMenu("帮助");
        menuChange = new JMenu("变更图片");
        menuRank = new JMenu("难度");

        itemStart = new JCheckBoxMenuItem("开始");
        itemExit = new JCheckBoxMenuItem("退出");
        itemView = new JCheckBoxMenuItem("查看原图");

        total_time = new JLabel("时间：");
        total_count = new JLabel("步数：");
        total_time.setForeground(Color.RED);
        total_count.setForeground(Color.RED);

        //create menu group
        String content;
        ButtonGroup groupChange = new ButtonGroup();
        ButtonGroup groupRank = new ButtonGroup();

        for (int i = 0; i < pic_change.length; i++) {
            //add image item to menu[pic_change]
            pic_change[i] = new JRadioButtonMenuItem("0" + (i + 1) + ".jpg");
            groupChange.add(pic_change[i]);
            menuChange.add(pic_change[i]);
        }
        pic_change[2].setSelected(true);

        for (int i = 0; i < game_rank.length; i++) {
            //add  Game difficulty to menu[game_rank]
            if (i == 0) {
                content = "easy";
            } else if (i == 1) {
                content = "normal";
            } else {
                content = "hard";
            }

            game_rank[i] = new JRadioButtonMenuItem(content);
            groupRank.add(game_rank[i]);
            menuRank.add(game_rank[i]);
        }
        game_rank[0].setSelected(true);

        //menu add
        menu.add(itemStart);
        menu.add(itemView);
        menu.add(itemExit);
        menuSelect.add(menuChange);
        menuSelect.add(menuRank);
        jMenuBar.add(menu);
        jMenuBar.add(menuSelect);
        jMenuBar.add(menuHelp);
        jMenuBar.add(new Label(""));
        jMenuBar.add(total_time);
        jMenuBar.add(new Label(" "));
        jMenuBar.add(total_count);
        jMenuBar.add(new Label(" "));

        this.setJMenuBar(jMenuBar);

        itemStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                breakState();
            }
        });

        itemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        itemView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame model = new JFrame("原图");
                model.setSize(660, 600);

                ImageIcon imageIcon = new ImageIcon(path + "\\index.jpg");
                imageIcon.setImage(imageIcon.getImage()
                        .getScaledInstance(model.getWidth(), model.getHeight()
                                , Image.SCALE_DEFAULT));
                JButton index = new JButton(imageIcon);

                model.setResizable(false);
                model.add(index);
                model.setVisible(true);
            }
        });

        this.setTitle("拼图");
        this.setSize(660, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);   //Adjust size

        this.setPath();
        this.setPattern();

        panel = new MainPanel(path, pattern);

        startTime = System.currentTimeMillis();
        this.add(panel);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle();
        Thread thread = new Thread(puzzle);
        thread.start();
    }

    public void breakState() {
        startTime = System.currentTimeMillis();     //set initial time
        setPattern();       //set the currently selected pattern
        setPath();          //set the currently selected icon path
        panel.breakRandom(path, pattern);
    }

    public void setPath() {
//        path = "img\\type1\\";
        for (int i = 0; i < pic_change.length; i++) {
            if (pic_change[i].isSelected()) {
                path = "img\\type" + (i + 1) + "\\";
            }
        }
    }

    public void setPattern() {
//        this.pattern = pattern;
        for (int i = 0; i < game_rank.length; i++) {
            if (game_rank[i].isSelected()) {
                switch (i) {
                    case 0:
                        pattern = 3;
                        break;
                    case 1:
                        pattern = 4;
                        break;
                    case 2:
                        pattern = 5;
                        break;
                }
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            endTime = System.currentTimeMillis(); //ms
            int time = (int) ((endTime - startTime) / 1000);
            total_time.setText("时间：" + time + "s");
            total_count.setText("步数：" + panel.getCount());
        }
    }
}
