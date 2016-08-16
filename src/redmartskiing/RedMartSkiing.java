/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redmartskiing;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Abhijith Sreekar
 */
public class RedMartSkiing {

    static int num_rows;
	static int num_cols;
	static ArrayList<int[]> mapList;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int ctr=0;
		// Open the file (as of now hardcoding the path. We can use the current path of the file also)
		String file_path="C:\\Users\\abhij\\Downloads\\";
		FileInputStream fstream = new FileInputStream(file_path+"map.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;
		mapList=new ArrayList<>();
		//Read File Line By Line
		while ((strLine = br.readLine()) != null)   {
		  // Print the content on the console
			String[] content=strLine.split(" ");
			if(ctr ==0)
			{
				num_rows=Integer.parseInt(content[0]);
				num_cols=Integer.parseInt(content[1]);
				ctr++;
				continue;
			}
			else
			{
				int[] numList= new int[content.length];
				for(int i=0;i<content.length;i++)
				{
					numList[i]=Integer.parseInt(content[i]);
				}
				mapList.add(numList);
			}
		}
		//Close the input stream
		br.close();
		
		int size=mapList.size();
		Solution skii_sol= new Solution(mapList,size);
		for(int x=0;x<num_rows;x++)
		{
			for(int y=0;y<num_cols;y++)
			{
		        if (x+1 < size)
		            skii_sol.solveSkii(mapList.get(x)[y],1,x,y,x+1,y);
		        if (x-1 >=0)
		            skii_sol.solveSkii(mapList.get(x)[y],1,x,y,x-1,y);
		        if (y+1 < size)
		            skii_sol.solveSkii(mapList.get(x)[y],1,x,y,x,y+1);
		        if (y-1 >=0)
		            skii_sol.solveSkii(mapList.get(x)[y],1,x,y,x,y-1);				
			}
		}
		System.out.println(skii_sol.length);
		System.out.println(skii_sol.drop);
	}

}

class Solution
{
	int length;
	int drop;
	ArrayList<int[]> mapList;
	int size;
	public Solution(ArrayList<int[]> mapList, int size) {
		// TODO Auto-generated constructor stub
		length=0;
		drop=0;
		this.mapList=mapList;
		this.size=size;
	}
	
	public void solveSkii(int start,int numSteps,int src_x,int src_y,int dest_x,int dest_y)
	{
        if(mapList.get(dest_x)[dest_y] >= mapList.get(src_x)[src_y])
            return;
        else
        {
            numSteps=numSteps+1;
            if (numSteps > length || (numSteps == length && start-mapList.get(dest_x)[dest_y] > drop))
            {
                length=numSteps;
                drop=start-mapList.get(dest_x)[dest_y];
            }
            if (dest_x+1 < size)
                solveSkii(start,numSteps,dest_x,dest_y,dest_x+1,dest_y);
            if (dest_x-1 >=0)
                solveSkii(start,numSteps,dest_x,dest_y,dest_x-1,dest_y);
            if (dest_y+1 < size)
                solveSkii(start,numSteps,dest_x,dest_y,dest_x,dest_y+1);
            if (dest_y-1 >= 0)
                solveSkii(start,numSteps,dest_x,dest_y,dest_x,dest_y-1);
        }
	}
	
}
