package core.domain.maps;

import java.awt.Color;
import java.awt.Graphics;

public abstract class CompleteGrid implements  Grid {

	protected final Grid simpleGrid;
	
	
	//constructor's section 
	public CompleteGrid(Grid newGrid){
		simpleGrid = newGrid;
		//content = simpleGrid.content;
		
		
	}
	
	@Override
	public void draw(Graphics g) {
		simpleGrid.draw(g);
		
		for(int i = 0; i<((EmptyGrid)simpleGrid).height; i++)
			for( int j = 0; j < ((EmptyGrid)simpleGrid).width; j++){
				if(((EmptyGrid)simpleGrid).content[i][j] !=0){
				Color color = Color.gray;
				if(((EmptyGrid)simpleGrid).content[i][j] == 1) color = Color.gray;
				else if( ((EmptyGrid)simpleGrid).content[i][j] == 2) color = Color.green;
				else if( ((EmptyGrid)simpleGrid).content[i][j] == 3) color = Color.red;
				else if( ((EmptyGrid)simpleGrid).content[i][j] == 4) color = Color.blue;
				else color = Color.green;
			    g.setColor(color);
			    g.fillRect(j * ((EmptyGrid)simpleGrid).sizeOfUnit, i * ((EmptyGrid)simpleGrid).sizeOfUnit, ((EmptyGrid)simpleGrid).sizeOfUnit, ((EmptyGrid)simpleGrid).sizeOfUnit);
			
				}	
				
				
			}
		
	}
	
/*	public void setSize(int newWidth, int newheight){
		
		((EmptyGrid)simpleGrid).setSize(newWidth, newheight);
		((EmptyGrid)simpleGrid).width = newWidth;
		((EmptyGrid)simpleGrid).height = newheight;
		((EmptyGrid)simpleGrid).content = new int[newheight][newWidth];
	}; */
	
	
	
	//===============
	
	public int  getHeight(){
		
		return simpleGrid.getHeight();
	}
	
	public int getWidth(){
		
		return simpleGrid.getWidth();
	}
	
	public int getUnitSize(){
		
		return simpleGrid.getUnitSize();
	}
	
	public void setSize(int newWidth, int newheight){
		
		simpleGrid.setSize(newWidth, newheight);
	};

	
	public int getCellType(int i, int j)
	{


		return simpleGrid.getCellType(i, j);
	}
	
	public void setCell(int cordinatX, int cordinatY, int type)
	{
		simpleGrid.setCell(cordinatX, cordinatY, type);
	}
	
	public void setContent(int[][] newContent){
		
		simpleGrid.setContent(newContent);
	}
	 public int[][] getContent(){
		 return simpleGrid.getContent();
		 
	 }
	
//====	

}
