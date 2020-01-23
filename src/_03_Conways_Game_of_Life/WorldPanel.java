package _03_Conways_Game_of_Life;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class WorldPanel extends JPanel implements MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private int cellsPerRow;
	private int cellSize;
	int row;
	int colum;
	int livingNeighbors = 0;
	Random r = new Random();
int ra;
	
	private Timer timer;
	
	//1. Create a 2D array of Cells. Do not initialize it.
Cell[][] cells;
	
	
	public WorldPanel(int w, int h, int cpr) {
		setPreferredSize(new Dimension(w, h));
		addMouseListener(this);
		timer = new Timer(500, this);
		this.cellsPerRow = cpr;
	
		//2. Calculate the cell size.
		cellSize = w/cpr;
		//3. Initialize the cell array to the appropriate size.
		cells = new Cell[w][h];
		//3. Iterate through the array and initialize each cell.
		//   Don't forget to consider the cell's dimensions when 
		//   passing in the location.
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				cells[i][j] = new Cell(i / w , i / h, cellSize);
			}
		}
	}
	
	public void randomizeCells() {
		//4. Iterate through each cell and randomly set each
		//   cell's isAlive member to true of false
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				ra = r.nextInt(2);
				if(ra == 0) {
					cells[i][j].isAlive = false;
				}
				else if(ra == 1) {
					cells[i][j].isAlive = true;
				}
			}
		}
		repaint();
	}
	
	public void clearCells() {
		//5. Iterate through the cells and set them all to dead.
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				cells[i][j].isAlive = false;
			}
		}
		repaint();
	}
	
	public void startAnimation() {
		timer.start();
	}
	
	public void stopAnimation() {
		timer.stop();
	}
	
	public void setAnimationDelay(int sp) {
		timer.setDelay(sp);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//6. Iterate through the cells and draw them all
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				cells[i][j].draw(g);
			}
		}
		
		
		// draws grid
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}
	
	//advances world one step
	public void step() {
		//7. iterate through cells and fill in the livingNeighbors array
		// . using the getLivingNeighbors method.
		int[][] livingNeighbors = new int[cellsPerRow][cellsPerRow];
		
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				livingNeighbors[i][j] = getLivingNeighbors(i,j); 
				

			}
		}
		//8. check if each cell should live or die
	
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				cells[i][j].liveOrDie(getLivingNeighbors(i, j));
				

			}
		}
		
		
		repaint();
	}
	
	//9. Complete the method.
	//   It returns an int of 8 or less based on how many
	//   living neighbors there are of the 
	//   cell identified by x and y
	public int getLivingNeighbors(int rows, int colums){

//Top Left *
		if(rows == 0 && colums == 0) {
	//check
			if(cells[rows + 1][colums].isAlive) {
				livingNeighbors++;
			}
			if(cells[rows][colums + 1].isAlive) {
				livingNeighbors++;
			}
			if(cells[rows + 1][colums + 1].isAlive) {
				livingNeighbors++;
			}
			
		}
//Top Right *
			if(rows == 0 && colums == cells.length - 1) {
	//check
				if(cells[rows + 1][colums].isAlive) {
					livingNeighbors++;
				}
				if(cells[rows][colums -1].isAlive) {
					livingNeighbors++;
				}
				if(cells[rows + 1][colums - 1].isAlive) {
					livingNeighbors++;
				}
		}
//Top Middle *
		if(rows == 0 && (colums != 0 && colums != cells.length - 1)) {
	//check
			if(cells[rows + 1][colums].isAlive) {
				livingNeighbors++;
			}
			if(cells[rows][colums + 1].isAlive) {
				livingNeighbors++;
			}
			if(cells[rows][colums - 1].isAlive) {
				livingNeighbors++;
			}
			if(cells[rows + 1][colums - 1].isAlive) {
				livingNeighbors++;
			}
			if(cells[rows + 1][colums + 1].isAlive) {
				livingNeighbors++;
			}
			
		}
//Bottom Left *
		if(rows == cells.length - 1 && colums == 0) {
	//check
			if(cells[rows - 1][colums + 1].isAlive) {
				livingNeighbors++;
			}
			if(cells[rows][colums + 1].isAlive) {
				livingNeighbors++;
			}
			if(cells[rows - 1][colums].isAlive) {
				livingNeighbors++;
			}
			
		}
//Bottom Right *
		if(rows == cells.length - 1 && colums == cells.length - 1) {
	//check
			if(cells[rows][colums - 1].isAlive) {
				livingNeighbors++;
			}
			if(cells[rows - 1][colums].isAlive) {
				livingNeighbors++;
			}
			if(cells[rows - 1][colums - 1].isAlive) {
				livingNeighbors++;
			}
			
		}
//Bottom Middle *
		if(rows == cells.length - 1 && (colums != 0 && colums != cells.length - 1)) {
	//check
			if(cells[rows][colums - 1].isAlive) {
				livingNeighbors++;
			}
			if(cells[rows][colums + 1].isAlive) {
				livingNeighbors++;
			}
			if(cells[rows - 1][colums].isAlive) {
				livingNeighbors++;
			}
			if(cells[rows - 1][colums - 1].isAlive) {
				livingNeighbors++;
			}
			if(cells[rows - 1][colums + 1].isAlive) {
				livingNeighbors++;
			}
			
		}
//Left Middle *
		if(colums == 0 && (rows != 0 && rows != cells.length - 1)) {
	//check
			if(cells[rows - 1][colums].isAlive) {
				livingNeighbors++;
			}
			if(cells[rows + 1][colums].isAlive) {
				livingNeighbors++;
			}
			if(cells[rows][colums + 1].isAlive) {
				livingNeighbors++;
			}
			if(cells[rows - 1][colums + 1].isAlive) {
				livingNeighbors++;
			}
			if(cells[rows + 1][colums + 1].isAlive) {
				livingNeighbors++;
			}
			
		}
//Right Middle *
		if(colums == cells.length - 1 && (rows != 0 && rows != cells.length - 1)) {
	//check
			if(cells[rows][colums - 1].isAlive) {
				livingNeighbors++;
			}
			if(cells[rows + 1][colums].isAlive) {
				livingNeighbors++;
			}
			if(cells[rows - 1][colums].isAlive) {
				livingNeighbors++;
			}
			if(cells[rows - 1][colums - 1].isAlive) {
				livingNeighbors++;
			}
			if(cells[rows + 1][colums - 1].isAlive) {
				livingNeighbors++;
			}
		}
//Middle
		if(colums != 0 && colums != cells.length - 1 && rows != 0 && rows != cells.length - 1) {
	//check
			if(cells[rows + 1][colums].isAlive) {
				livingNeighbors++;
			}
			if(cells[rows - 1][colums].isAlive) {
				livingNeighbors++;
			}
			if(cells[rows][colums + 1].isAlive) {
				livingNeighbors++;
			}
			if(cells[rows][colums - 1].isAlive) {
				livingNeighbors++;
			}
			if(cells[rows + 1][colums + 1].isAlive) {
				livingNeighbors++;
			}
			if(cells[rows + 1][colums - 1].isAlive) {
				livingNeighbors++;
			}
			if(cells[rows - 1][colums + 1].isAlive) {
				livingNeighbors++;
			}
			if(cells[rows - 1][colums - 1].isAlive) {
				livingNeighbors++;
			}
		}
		
		
		
		
		return livingNeighbors;
		
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//10. Use e.getX() and e.getY() to determine
		//    which cell is clicked. Then toggle
		//    the isAlive variable for that cell.
		
int row = e.getX() / cellSize;
int colum = e.getY() / cellSize;

	cells[row][colum].isAlive = true;

				
			
		
		
		
		
		
		
		
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		step();		
	}
}
