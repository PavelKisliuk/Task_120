import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Task_120 {
	public static void main(String[] args) {
		final String outputPath = "OUTPUT.TXT";
		final Area test = new Area();
		try(final BufferedWriter output = Files.newBufferedWriter(Paths.get(outputPath))) {
			output.write(test.toString());
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}

//-----------------------------------------------------------------------------
/*public*/ class Area {
	//-----------------------------------------------------------------------------fields
	private int[][] area;
	private int rows;
	private int columns;
	//-----------------------------------------------------------------------------constructors
	/*public*/ private Area(final String path)
	{
		try(final Scanner input = new Scanner(Paths.get(path))) {
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
				//-----------------------------------------------------------------------------
				//function from the Internet, but I understood how it work's
				for(int row = 0; row < this.rows; row++){
					for(int column = 0; column < this.columns; column++){
						if((row > 0) && (column > 0)){
							this.area[row][column]+= Math.min(this.area[row - 1][column], this.area[row][column - 1]);
						}
						else{
							if(row > 0){
								this.area[row][column]+= this.area[row - 1][column];
							}
							else if(column > 0){
								this.area[row][column]+= this.area[row][column - 1];
							}
						}
					}
				}
				//-----------------------------------------------------------------------------
			}
			//-----------------------------------------------------------------------------
			else {
				throw new IOException("File is empty!");
			}
			//-----------------------------------------------------------------------------
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*public*/ Area()
	{
		this("INPUT.TXT");
	}
	//-----------------------------------------------------------------------------
	//-----------------------------------------------------------------------------methods
	@Override
	public String toString()
	{
		return String.valueOf(this.area[this.rows - 1][this.columns - 1]);
	}
}