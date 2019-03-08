
/*
    Djikstra Algorithm
    Tugas Besar Strategi Algoritma
    
    Miftakhul Aziz (14116078)
    Yulianto Pambudi(14116098)
    Muhammad Fahmi Akbar (14116105)

Referensi : Djikstra Algorithm by Parminder Jeet Kaur
*/

import java.util.Scanner;

public class ShortestPathMain {
                        int total=0;
                        int dest,source;
                        int jarak[];
	//number of simpul
	int simpul = 26;
	//matrix
	int[][] matrix;
	//array for storing followed path
	int[] path;
	//array for storing visited nodes
	int[] Q;
	//the nodes distance from the processing node
	int[] D;
	//for calculating edges
	int[] res;
	
	//Scanner class object
	Scanner scan = new Scanner(System.in);
	
	//for initalization
	public ShortestPathMain(){
	int i;
	
	//matrix
	matrix = new int[simpul][simpul];
                        //adjancency list for 7 nodes
	for(i=0; i<simpul; i++){
                                for(int j=0; j<simpul; j++)
                                matrix[i][j] = -1;
                         }
	myGraph();
                        displayGraph();
	
                                                jarak = new int [simpul];
		//array for storing followed path
		path = new int[simpul];
		//array for storing visited nodes
		Q = new int[simpul];
		//the nodes distance from the processing node
		D = new int[simpul];
		//for calculating edges
		res = new int[simpul];
		
		//initializing the arrays
		for(i=0; i<simpul; i++)
			path[i] = -1;
		
		for(i=0; i<simpul; i++)
			Q[i] = -1;
		
		for(i=0; i<simpul; i++)
			D[i] = -1;		
		
		for(i=0; i<simpul; i++)
			res[i] = -1;
		
		//start to find shortest path
		String c = "";
                                                            do{
                                                	shortestPath();
			System.out.println("\nMau Mencari rute terpendek lagi?: (Y/N) ");
			c = scan.next();
			reset();
                                                                }while(c.equals("y") || c.equals("Y"));	
		System.out.println("Good Bye! :)");
	}
	
	//Plotting the graph
	public void myGraph(){
		//memberi nilai pada matrix beserta jarak/bobot
		matrix[0][1] = 796;	
                                                matrix[0][3] = 666;   
                                                matrix[1][2] = 902; 
		matrix[1][0] = 796;	
                                                matrix[1][2] = 902;	
                                                matrix[1][4] = 240;
                                                matrix[2][1] = 902; 
                                                matrix[2][7] =434; 
                                                matrix[3][0] =666; 
                                                matrix[3][4] = 699; 
                                                matrix[3][5]=422; 
                                                matrix[4][1] = 240; 
                                                matrix[4][3] = 699; 
                                                matrix[4][6] = 569; 
                                                matrix[5][3] = 422; 
                                                matrix[5][6] = 658;
                                                matrix[5][8] = 347; 
                                                matrix[6][4] = 569; 
                                                matrix[6][5] = 658; 
                                                matrix[6][10] = 128; 
                                                matrix[7][2] = 434;
                                                matrix[7][6] = 390; 
                                                matrix[7][18] = 987; 
                                                matrix[8][5] =347; 
                                                matrix[8][9] =544; 
                                                matrix[8][11] = 278;
                                                matrix[9][8] = 544; 
                                                matrix[9][10] =125; 
                                                matrix[9][12] = 476; 
                                                matrix[10][6] = 128; 
                                                matrix[10][9] =125; 
                                                matrix[11][8] = 278; 
                                                matrix[11][12]=800; 
                                                matrix[11][13] = 414;
                                                matrix[12][9] =476; 
                                                matrix[12][11] = 100; 
                                                matrix[12][16] =267; 
                                                matrix[13][11] = 414; 
                                                matrix[13][14] = 597;
                                                matrix[13][19] =557; 
                                                matrix[14][13] = 597; 
                                                matrix[14][15] = 324; 
                                                matrix[14][19] = 757; 
                                                matrix[15][14]=324;
                                                matrix[15][16] = 198; 
                                                matrix[15][22] = 680; 
                                                matrix[16][12] =267; 
                                                matrix[16][15] =198; 
                                                matrix[16][17] = 477;
                                                matrix[17][16] = 477; 
                                                matrix[17][18] = 124; 
                                                matrix[17][20] = 509; 
                                                matrix[18][7] = 987; 
                                                matrix[18][17] = 124;
                                                matrix[18][25]= 1670; 
                                                matrix[19][13]=557; 
                                                matrix[19][14]=757; 
                                                matrix[19][21] =845; 
                                                matrix[20][17] =509;
                                                matrix[20][22]=321;     
                                                matrix[20][25] = 637; 
                                                matrix[21][19] = 845; 
                                                matrix[21][23]=606; 
                                                matrix[21][24] = 1320; 
                                                matrix[22][15] =680;
                                                matrix[22][20] =321; 
                                                matrix[22][23] = 640; 
                                                matrix[23][21] = 606; 
                                                matrix[23][22] = 640; 
                                                matrix[23][24] =492; 
                                                matrix[24][21] =1320;
                                                matrix[24][23] =492; 
                                                matrix[24][25] = 955; 
                                                matrix[25][18] =1670; 
                                                matrix[25][20] = 637; 
                                                matrix[25][24] = 955;   
	}

	//display  graph
	public void displayGraph(){
		int letter = 65;
		System.out.print("\t");
		for(int i=0; i<simpul; i++)
		System.out.print("   "+(char)(65+i)+"  ");
		System.out.println();
		//System.out.println("\t _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");
		for(int i=0; i<simpul; i++){
			System.out.print((char)letter+"\t");
			System.out.print("| ");
			for(int j=0; j<simpul; j++){
				if(String.valueOf(matrix[i][j]).length() == 2)
					System.out.print(matrix[i][j]+"  |");
				else
					System.out.print(matrix[i][j]+"  |");
			}
			System.out.println(" ");
			letter++;
		}
	}
	
	//to find shortest path
	public void shortestPath(){
		System.out.print("Masukkan simpul asal dan tujuan dalam format A-B atau a-b: ");
		 source = -1;
                                                dest = -1;
		//CONVERTING string input to integer
		String input = scan.next();
		String[] inp = input.split("-");
		byte[] s = inp[0].getBytes();
		byte[] d = inp[1].getBytes();
		
		//mapping input to matrix index
		if(s[0] >= 65 && s[0] <= (65+simpul))
			source = s[0] - 65;
                                                                       
		else if(s[0] >= 97 && s[0] <= (97+simpul))
			source = s[0] - 97;
                                                                    
		else{
			System.out.println("Masukkan salah,coba lagi");
			shortestPath();
		}
                
		if(d[0] >= 65 && d[0] <= (65+simpul))
			dest = d[0] - 65;
		else if(d[0] >= 97 && d[0] <= (97+simpul))
			dest = d[0] - 97;
		else{
			System.out.println("Masukkan salah,coba lagi");
			shortestPath();
		}
		         
                                                
		//Dijkstra call
		Dijkstra(source);
		//RETRACING the path followed
		res[0] = dest;
		int j = 1, i = dest;
		while(i>=0){
		//	System.out.println("i="+i);
			res[j] = path[i];
		//	System.out.println("res"+j+"="+res[j]);
			i = res[j];
                       
                        
		//	System.out.println("i="+i);
			if((path[i]==-1) || (res[i] == source) || (res[i] == dest))
				break;
			j++;
		}
		//display the path followed by edges
                                                displayPath();
	}
	
	//Implementing Dijkstra Algorithm
	public void Dijkstra(int source){
		int min_Node = source, min_Wt = 0;
		while(true){
			setDistance(min_Node, min_Wt, source);
			min_Node = find_Min(source);
			if(min_Node == -1)
				break;
                                                    	Q[min_Node] = min_Node;
			min_Wt = D[min_Node] ;
                                                                    }
	}
	
	//set distance of particular processing node to adjacent nodes
	public void setDistance(int min_Node, int min_Wt, int source){
		int i;
		for(i=0; i<simpul; i++){
			if(i == source)
				continue;
			else if((matrix[min_Node][i] != -1) || (min_Node == source)){
				if(D[i] == -1)
					Replace(i, min_Wt, min_Node);
				else if(D[i] > (matrix[min_Node][i]+min_Wt))
					Replace(i, min_Wt, min_Node);
			}
		}
	}
	
	//replace the value in 
	public void Replace(int i, int min_Wt, int min_Node){
                        //System.out.println(path[i]);
		path[i] = min_Node;
		D[i] = matrix[min_Node][i]+min_Wt;
	}
	
	//find the node with minimum distance from the source
	public int find_Min(int source){
		int v = 99999, node = -1;
		int i;
		for(i=0; i<simpul; i++){
			if(i == source)
				continue;
			else if((D[i] < v) && (Q[i] == -1) && (D[i] != -1)){
				v = D[i];
				node = i;      
                                                                                                 jarak[i] = v;
			} 
		}
		return node;
	}
	
	//display the path by edges
	public void displayPath(){
            
		int i=simpul-1;
		System.out.println("Jalur terpendek : ");
		while(i>=0){
			if(res[i] == -1){
				i--;
				continue;
			}
			char val= (char)(res[i]+65);
			System.out.print(val);
			if(i != 0)
				System.out.print(" -> ");
			i--;
		}
 
                        System.out.println("\nJarak terpendek : "+jarak[dest]+" meter");
	}
 	//reset array
	public void reset(){
		int i;
		for(i=0; i<simpul; i++)
			path[i] = -1;
		
		for(i=0; i<simpul; i++)
			Q[i] = -1;
		
		for(i=0; i<simpul; i++)
			D[i] = -1;		
		
		for(i=0; i<simpul; i++)
			res[i] = -1;
		
	}
        
}
