package hw4;
//cannot close on exit
//need to expand the window to see the play buttons

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.JPanel;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUICF extends CFGame{

	private boolean onefirst = true;
	private CFPlayer one;
	private CFPlayer two;
	private boolean onewin = false;
	private boolean twowin = false;
	private boolean draw = false;
	
	private JButton b1 = new JButton("\u2193");
	private JButton b2 = new JButton("\u2193");
	private JButton b3 = new JButton("\u2193");
	private JButton b4 = new JButton("\u2193");
	private JButton b5 = new JButton("\u2193");
	private JButton b6 = new JButton("\u2193");
	private JButton b7 = new JButton("\u2193");
	
	private JFrame frame = new JFrame("Connect Four");
	private JPanel panel1 = new JPanel();
	private JLabel[][] cell = new JLabel[7][6];
	
	private int played;
	private volatile boolean click = false;
	private volatile boolean playclick = false;
	
	
	public GUICF(CFPlayer ai){
		gb = new GameBoard();
		one = new HumanPlayer();
		two = ai;
		Random rand = new Random();
		playclick = true;
		if(rand.nextInt(2) == 1){
			onefirst = true;
		}
	
		playOut();
		if(this.isGameOver()){
			if(this.winner()==1){
				if(onefirst==true){
					System.out.println(one.getName()+ " win!");
				}else{
					System.out.println( two.getName()+ " win!");
				}
			}else if(this.winner()==-1){
				if(onefirst==true){
					System.out.println(two.getName()+ " win!");
				}else{
					System.out.println( one.getName()+ " win!");
				}
			}else{
				System.out.println("game over, draw");
			}
		}
	}
	
	public GUICF(CFPlayer ai1, CFPlayer ai2){
		gb = new GameBoard();
		Container pane = frame.getContentPane();		
		JButton play = new JButton("play");
		play.addActionListener(new B1());
		pane.add(play, BorderLayout.NORTH);
		
		one = ai1;
		two = ai2;
		Random r = new Random();
		int R = r.nextInt(2);
		if(R == 1){
			onefirst = true;
		}
	
	}
	
	public void playOut(){
	
		onewin = false;	
		twowin = false;
		draw = false;
		
		while(!click&&!playclick){
		}
	
		if(onefirst){
			
			while(!this.isGameOver()){
				this.playGUI(one.nextMove(this));
				if(this.isGameOver()){
					onewin = true;
					break;
				}
				this.playGUI(two.nextMove(this));
				if(this.isGameOver()){
					twowin = true;
					break;
				}
			}
	
	
		}else{
			while(!this.isGameOver()){
				this.playGUI(two.nextMove(this));
				if(this.isGameOver()){
					twowin = true;
					break;
				}
				this.playGUI(one.nextMove(this));
				if(this.isGameOver()){
					onewin = true;
					break;
				}
			}

		}
	}
	
	private class B1 implements ActionListener{
		public void actionPerformed (ActionEvent e){
			if(!(onewin||twowin||draw)){
				if(e.getSource()==b1){
					played = 1;
				}else if(e.getSource()==b2){
					played = 2;

				}else if(e.getSource()==b3){
					played = 3;

				}else if(e.getSource()==b4){
					played = 4;

				}else if(e.getSource()==b5){
					played = 5;

				}else if(e.getSource()==b6){
					played = 6;

				}else if(e.getSource()==b7){
					played = 7;

				}
				click = true;

			}
		}
	}
	
	private class GameBoard extends JPanel{
		private GameBoard(){
		frame.setSize(280, 260);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container pane = frame.getContentPane();
		panel1.setLayout(new GridLayout(6,7));
	
		for(int i = 0; i<7; i++){
			for(int j = 0 ; j < 6; j++){
				cell[i][j] =new JLabel("");
				cell[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
			}
		}
	
		for(int j = 5; j>-1; j--){
			for(int i = 0; i<7; i++){
				panel1.add(cell[i][j]);
			}
		}
		pane.add(panel1, BorderLayout.CENTER);
		
		JPanel buttonpanel = new JPanel();
		buttonpanel.setLayout(new GridLayout(1,7));
		b1.addActionListener(new B1());
		b2.addActionListener(new B1());
		b3.addActionListener(new B1());
		b4.addActionListener(new B1());
		b5.addActionListener(new B1());
		b6.addActionListener(new B1());
		b7.addActionListener(new B1());
		//frame.getContentPane().add(b1,BorderLayout.NORTH);
		buttonpanel.add(b1);
		buttonpanel.add(b2);
		buttonpanel.add(b3);
		buttonpanel.add(b4);
		buttonpanel.add(b5);
		buttonpanel.add(b6);
		buttonpanel.add(b7);
		pane.add(buttonpanel, BorderLayout.NORTH);
		
		frame.setVisible(true);

	}
	
		private void paint (int x , int y , int color ) {
			if(color == 1){
				cell[x][y].setBackground(Color.red);
				cell[x][y].setOpaque(true);
			}else if(color == -1){
				cell[x][y].setBackground(Color.black);
				cell[x][y].setOpaque(true);
			}
	
		}
	
	}
	
	private GameBoard gb;	
	private boolean playGUI (int c ){
	
		if(this.play(c)){
			int[][] GUIcopy = this.getState();
	
			for(int j = 5; j>-1;j--){
				if(GUIcopy[c-1][j]!=0){	
					if(GUIcopy[c-1][j] == 1){
						gb.paint(c-1, j, 1);
					}else{
						gb.paint(c-1, j, -1);
					}
				}
			}
			return true;
		}else{
			return false;
		}
	}
	
	private class HumanPlayer implements CFPlayer{
	
		public int nextMove(CFGame g){
			int[][] checkcopy = g.getState();
			boolean a = true;
			while(a){
				while(!click){
				}
					
				for (int j=0; j<6; j++){
					if(checkcopy[played-1][j] == 0){
						a = false;
					}
				}
	
				if(a){
					System.out.println("Invalid entry, please enter another one");
					click = false;
				}
			}
	
			int temp = played;
			played = 0;
			click = false;
			return(temp);
		}
	
		public String getName(){
			return ("Human Player");
		}
	}
	
	
}

