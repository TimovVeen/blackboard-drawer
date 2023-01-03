package com.example;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GridPixel extends JPanel implements MouseListener, MouseMotionListener{
    static final int SIZE = 50;

    int x;
    int y;

    int xSel = -1;
    int ySel = -1;

    int brushColor = 0;

    public int[] grid;
    boolean mouseDown = false;

    public GridPixel(int x, int y) {
        
        addMouseListener(this);
        addMouseMotionListener(this);

        this.x = x;
        this.y = y;
        

        grid = new int[256];
        for (int i=0; i < grid.length; i++) {
            grid[i] = 1;
        }

        setBounds(x, y, SIZE * 16, SIZE * 16);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i=0; i < 16; i++) {
            for (int j=0; j < 16; j++) {
                g.setColor(MainFrame.colors[grid[j + i * 16]]);
                g.fillRect(i * SIZE + x, j * SIZE + y, SIZE, SIZE);
            }
            if (inGrid(xSel, ySel)) {
                g.setColor(Color.gray);
                g.drawRect(xSel * SIZE + x, ySel * SIZE + y, SIZE, SIZE);
            }
        }
    }

    volatile private boolean isRunning = false;
    private synchronized boolean checkAndMark() {
        if (isRunning) return false;
        isRunning = true;
        return true;
    }
    private void initThread() {
        if (checkAndMark()) {
            new Thread() {
                public void run() {
                    do {
                        Point sel = getSelected();
                        if (inGrid(sel.x, sel.y)) {
                            grid[sel.y + sel.x * 16] = brushColor;
                        }
                    } while (mouseDown);
                    isRunning = false;
                }
            }.start();
        }
    }

    public void setBrush(int c) {
        brushColor = c;
    }

    public void clearGrid() {
        for (int i=0; i < grid.length; i++) {
            grid[i] = 1;
        }
    }

    boolean inGrid(int x, int y) {
        return (x >= 0 && x <= 15 && y >= 0 && y <= 15);
    }

    Point getSelected() {
        Point screen = MouseInfo.getPointerInfo().getLocation();
        screen.x -= 8;
        //screen.y -= 33;
        screen.y -= 55;

        return new Point((screen.x - x) / SIZE, (screen.y - y) / SIZE);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseDown = true;
            initThread();
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseDown = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point pt = getSelected();
        
        xSel = pt.x;
        ySel = pt.y;
        repaint();
    }
}
