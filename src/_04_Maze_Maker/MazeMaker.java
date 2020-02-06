package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		int randomW = randGen.nextInt(w);
		int randomH = randGen.nextInt(h);
		
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(maze.cells[randomW][randomH]);
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		
		//A. mark cell as visited
currentCell.setBeenVisited(true);
		//B. Get an ArrayList of unvisited neighbors using the current cell and the method below
		ArrayList<Cell> unviseted = getUnvisitedNeighbors(currentCell);
		//C. if has unvisited neighbors,
		if(unviseted.size() > 0) {
			//C1. select one at random.
			Random random = new Random();
			int rand = random.nextInt(unviseted.size() - 1);
			//C2. push it to the stack
		uncheckedCells.push(unviseted.get(rand));
			//C3. remove the wall between the two cells
removeWalls(currentCell,unviseted.get(rand));
			//C4. make the new cell the current cell and mark it as visited
		currentCell = unviseted.get(rand);
		currentCell.setBeenVisited(true);
			//C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}
			
		//D. if all neighbors are visited
			if(unviseted.size() == 0) {
			//D1. if the stack is not empty
			if(uncheckedCells.size() != 0) {
				// D1a. pop a cell from the stack
		uncheckedCells.pop();
				// D1b. make that the current cell
		currentCell = uncheckedCells.pop();
				// D1c. call the selectNextPath method with the current cell
		selectNextPath(currentCell);
			}
				}
			}
		
		
	

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		//TEST TEST TEST TEST TEST
		System.out.println("c1 x =" + c1.getX());
		System.out.println("c1 y =" + c1.getY());
		System.out.println("c2 x =" + c2.getX());
		System.out.println("c2 y =" + c2.getY());
		System.out.println("test c1 + 1" + c1.getX() + 1);
		//left wall
		if(c1.getX() == 0) {
			if(c1.getX() == c2.getX() - 1) {
				c1.setEastWall(false);
				c2.setWestWall(false);
			}
		}
		else if(c2.getX() == 0) {
			if(c2.getX() == c1.getX() - 1) {
				c2.setEastWall(false);
				c1.setWestWall(false);
			}
		}
		//right wall
		if(c1.getX() == 10) {
			if(c1.getX() == c2.getX() + 1) {
				c2.setEastWall(false);
				c1.setWestWall(false);
			}
		}
		else if(c2.getX() == 10) {
			if(c2.getX() == c1.getX() + 1) {
				c1.setEastWall(false);
				c2.setWestWall(false);
			}
		}
		//top wall
		if(c1.getY() == 0) {
			if(c1.getY() == c2.getY() - 1) {
				c2.setNorthWall(false);
				c1.setSouthWall(false);
			}
		}
		else if(c2.getY() == 0) {
			if(c2.getY() == c1.getY() - 1) {
				c1.setNorthWall(false);
				c2.setSouthWall(false);
			}
		}
		//bottom wall
		 if(c1.getY() == 10) {
			if(c1.getY() == c2.getY() + 1) {
				c1.setNorthWall(false);
				c2.setSouthWall(false);
			}
		}
		else if(c2.getY() == 10) {
			if(c2.getY() == c1.getY() + 1) {
				c2.setNorthWall(false);
				c1.setSouthWall(false);
			}
		}
		
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> unvisited = new ArrayList<Cell>();
if(!c.hasBeenVisited()) {
unvisited.add(c);
}
		return unvisited;
	}
}
