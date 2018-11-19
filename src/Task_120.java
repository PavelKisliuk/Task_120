import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Task_120 {
	public static void main(String[] args) {
		Area test = new Area();
		try(final Formatter output = new Formatter("OUTPUT.TXT")) {
			output.format(test.toString());
		}catch (FileNotFoundException | FormatterClosedException e) {
			e.printStackTrace();
		}
	}
}

class Area {
	private int[][] area;
	private int rows;
	private int columns;

	//-----------------------------------------------------------------------------
	public Area(String path)
	{
		try(final Scanner input = new Scanner(Paths.get(path))) {
			//-----------------------------------------------------------------------------
			if(input.hasNext()) {
				//-----------------------------------------------------------------------------
				this.rows = input.nextInt(); //read data from file
				this.columns = input.nextInt(); //read data from file
				this.area = new int[this.rows][this.columns];
				for(int row = 0; row < this.rows; row++) {
					for(int column = 0; column < this.columns; column++) {
						this.area[row][column] = input.nextInt(); //read data from file
					}
				}


				for(int row = 0; row < this.rows; row++){
					for(int column = 0; column < this.columns; column++){
						if(row>0 && column>0){
							this.area[row][column]+=Math.min(this.area[row-1][column], this.area[row][column-1]);
						}
						else{
							if(row>0){
								this.area[row][column]+=this.area[row-1][column];
							}else if(column>0){
								this.area[row][column]+=this.area[row][column-1];
							}
						}
					}
				}
			}
			//-----------------------------------------------------------------------------
			else {
				throw new IOException("File is empty!");
			}
			//-----------------------------------------------------------------------------
		}catch (IOException | NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public Area()
	{
		this("INPUT.TXT");
	}

	@Override
	public String toString()
	{
		return String.valueOf(this.area[this.rows - 1][this.columns - 1]);
	}
}
