package edu.louisiana.cacs.csce450Project;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class Parser{
	/*
	* YOUR CODE GOES HERE
	* 
	* You must implement two methods
	* 1. parse
	* 2. printParseTree
     
    * Print the intermediate states of the parsing process,
    * including the intermediate states of the parse tree,make
    * as specified in the class handout.
    * If the input is legal according to the grammar,
    * print ACCEPT, else UNGRAMMATICAL.
    * If the parse is successful, print the final parse tree.

	* You can modify the input and output of these function but not the name
	*/
	public Parser(String fileName){
		System.out.println("File to parse : "+fileName);
	}
	/*
	* Dummy code
	*/
	public static void printParseTree(Stack<String> parseTreeStack){
		//System.out.println("Hello World from " + getClass().getName());
		List<String> list= new ArrayList<String>(parseTreeStack);
		int i=0,j=0,k=0,d=0;
		for(String str : list){
			i=0;
			//System.out.println(str);
			while(i<str.length()){
				if(str.charAt(i)=='['){
					i++;
					k++;
					continue;
					}
				else if(str.charAt(i)==']'){
					i++;
					k--;
					continue;
				}
				else  if(str.charAt(i)=='i'){
					i=i+2;
					d++;
					j=0;
					System.out.println("id");
					while(j<d){
					System.out.print("   ");
					j++;
					}
					continue;
				} 
				else if(str.charAt(i) == ' '){
					i++;
					continue;
				}
				 
				else { System.out.println(str.charAt(i)); }
				
				i++;
				j=0;
				/*if(str.charAt(i)== '+'||str.charAt(i) == '*')
					k--;*/
				while(j<k){
				System.out.print("   ");
				j++;
				}
				/*if(str.charAt(i)== '+'||str.charAt(i) == '*')
					k++;*/
				
				
			}
		}
	}

	/*
	* Dummy code
	*/
	
	public static String[][] actionTable(){
		
		String[][] temp = new String[12][6];
		temp[0][0]="S5";  		temp[0][3]="S4";
		temp[1][1]="S6";		temp[1][5]="accept";  
		temp[2][1]="R2";  		temp[2][2]="S7";  		temp[2][4]="R2";  		temp[2][5]="R2";  
		temp[3][1]="R4";  		temp[3][2]="R4";  		temp[3][4]="R4";  		temp[3][5]="R4";
		temp[4][0]="S5";  		temp[4][3]="S4";
		temp[5][1]="R6";  		temp[5][2]="R6";  		temp[5][4]="R6";  		temp[5][5]="R6";
		temp[6][0]="S5";  		temp[6][3]="S4";
		temp[7][0]="S5";  		temp[7][3]="S4";
		temp[8][1]="S6";  		temp[8][4]="S11";
		temp[9][1]="R1";  		temp[9][2]="S7";  		temp[9][4]="R1";  		temp[9][5]="R1";
		temp[10][1]="R3";  		temp[10][2]="R3";  		temp[10][4]="R3";  		temp[10][5]="R3";
		temp[11][1]="R5";  		temp[11][2]="R5";  		temp[11][4]="R5";  		temp[11][5]="R5";
		
		return temp;
				
	}
	
	public static int[][] gotoTable(){
		int[][] temp = new int[12][3];
		temp[0][0]=1;  		temp[0][1]=2;  		temp[0][2]=3;
		temp[4][0]=8;  		temp[4][1]=2;  		temp[4][2]=3;
		temp[6][1]=9;  		temp[6][2]=3;
		temp[7][2]=10;
		return temp;			
	}
	public static Map<String, Integer> mapping(){
		Map<String, Integer> temp = new HashMap<String, Integer>();
		 temp.put("id", 0);
		 temp.put("+", 1);
		 temp.put("*", 2);
		 temp.put("(", 3);
		 temp.put(")", 4);
		 temp.put("$", 5);
		 temp.put("E", 0);
		 temp.put("T", 1);
		 temp.put("F", 2);
		return temp;
	}
	

	/*
	* Dummy code
	*/
	public void parse(){
         String[][] actionLookup = actionTable();
           //System.out.println(actionLookup[1][5]);
           int[][] gotoLookup = gotoTable();
           //System.out.println(gotoLookup[7][2]);
           Map<String, Integer> tableMapping= mapping();
           //System.out.println(gotoLookup[0][tableMapping.get("E")]);
           String[] expressions = {"EE+T","ET","TT*F","TF","F(E)","Fi"};
          // System.out.println(expressions[0].substring(0, 1));
           String str= new String();
           Queue<String> tokenQue = new LinkedList<String>();
           try {
   		    BufferedReader in = new BufferedReader(new FileReader("data/sample.txt"));
   		    str = in.readLine();
   		    	in.close();
   			} catch (IOException e) {
   			}
          /* String str1="";
           int i=0,j=1;
         //  while(!str1.equals("$") && j<str.length()){
           while(j<=str.length()){
        	   str1= str.substring(i, j);
        	   if(str1.equals("i")){
        		   tokenQue.add("id");
        		   i+=2;
        		   j=i+1;
        	   } else { tokenQue.add(str1);
        	   			i++; j=i+1;
        	   }
        	   }
           */
            StringTokenizer tokenizer = new StringTokenizer(str);
           while(tokenizer.hasMoreTokens())
        	   tokenQue.add(tokenizer.nextToken().toString());
           //System.out.println(" \t"+ \t"+++++);
           System.out.println(String.format("%-14s %-10s %-9s %-6s %-7s %-10s %-10s %-8s %-6s %-9s %-20s ","     ","input","action","action","Value","Length","temp","goto","goto","stack","           " ));
           System.out.println(String.format("%-14s %-10s %-9s %-6s %-7s %-10s %-10s %-8s %-6s %-9s %-20s ","Stack","tokens","lookup","Value","of LHS","of RHS","stack","lookup","value","action","parse tree stack" ));
           
          // System.out.println(String.format("%-14s %-12s %-10s %-8s %-5s %-5s %-10s %-10s %-5s %-10s %-20s ","Stack","input tokens","action lookup","action Value","Value of LHS ","Length of RHS","temp stack","goto lookup","goto value","stack action","parse tree stack" ));//for(String str12 : tokenQue)
        	//   System.out.println(str12);   
           //start of process 
           Stack<String> stackState=new Stack<String>();
           Stack<String> parseTreeStack=new Stack<String>();
           stackState.push("0");
           int state=0,gotoLookup_value=0,len=0,lengthOfRHS=0,k=0;
           String stacktop="",queuetop="",action_value="",tempstackdis="",tempquedis="",valueOfLHS="",action_value_temp="",outFormat="",tempStr="",tempStr1="";
           while(true){
           
           stacktop =stackState.peek();
           len = stacktop.length();
           if(gotoLookup_value<=9)
           state= Integer.parseInt(stacktop.substring(len-1, len));
           else state= Integer.parseInt(stacktop.substring(len-2, len));
           queuetop =tokenQue.peek();
           action_value = actionLookup[state][tableMapping.get(queuetop)];
           tempstackdis="";
         for(String str12 : stackState)
        	 tempstackdis+=str12;
         System.out.print(String.format("%-14s",tempstackdis));
         tempquedis="";
         for(String str12 : tokenQue)
        	 tempquedis+=str12;
         System.out.print(String.format("%-12s",tempquedis));
           System.out.print(String.format("%-10s","["+state+","+queuetop+"]"));
           System.out.print(String.format("%-8s",action_value));
           
           
           
           //assert (action_value==null) : "Invalid grammer";
         if(action_value!=null){
           if(action_value.contains("R")){
        	   
        	   
        	 valueOfLHS =  expressions[Integer.parseInt(action_value.substring(1, 2))-1].substring(0, 1);
         	 lengthOfRHS =  (expressions[Integer.parseInt(action_value.substring(1, 2))-1].length())-1;
         	 System.out.print(String.format("%-10s",valueOfLHS));
         	 System.out.print(String.format("%-10s",lengthOfRHS));
         	
         	  k=0;
         	  while(k<lengthOfRHS){
         		 stackState.pop();
         		 k++;
         	  }
         	 tempstackdis="";
             for(String str12 : stackState)
            	 tempstackdis+=str12;
             System.out.print(String.format("%-10s",tempstackdis));
         	  stacktop =(String)stackState.peek();
               len = stacktop.length();
               state= Integer.parseInt(stacktop.substring(len-1, len)); 
         	  gotoLookup_value = gotoLookup[state][tableMapping.get(valueOfLHS)];
         	  stackState.push(valueOfLHS+gotoLookup_value);
         	 System.out.print(String.format("%-10s","["+state+","+valueOfLHS+"]"));
         	System.out.print(String.format("%-5s",gotoLookup_value));
         	 System.out.print(String.format("%-10s","Push "+valueOfLHS+gotoLookup_value)); 
         	 
         	 //parse tree
         	 if(lengthOfRHS==1){
         	 tempStr=parseTreeStack.pop();
         	 tempStr="["+valueOfLHS+" "+tempStr+" ]";
         	parseTreeStack.push(tempStr);
         	 }
         	 else if(lengthOfRHS==3){
         		tempStr=parseTreeStack.pop(); 
         		      		
         		//tempStr1="["+valueOfLHS+" "+tempStr1+" ]";
         		if(valueOfLHS.contains("E")){
         			tempStr1=parseTreeStack.pop(); 
         		tempStr="["+valueOfLHS+" "+tempStr1+"+"+tempStr+" ]";         		  
         		}
         		else if(valueOfLHS.contains("T")){
         			tempStr1=parseTreeStack.pop();
         			tempStr="["+valueOfLHS+" "+tempStr1+"*"+tempStr+" ]";         			   
         		}
         		else if(valueOfLHS.contains("F"))
         			tempStr="("+tempStr+")";
         		parseTreeStack.push(tempStr);
         	 }
         	 tempstackdis="";
            for(String str12 : parseTreeStack)
           	 tempstackdis=str12+tempstackdis;
            System.out.println(String.format("%-20s",tempstackdis)); 
        	   }
        else if(action_value.contains("S")){
        	   action_value_temp= tokenQue.poll();
        	   if(action_value_temp.contains("i")){
         		  parseTreeStack.push(action_value_temp);
         		}
        	   action_value_temp+=action_value.substring(1);
        	   System.out.print(String.format("%-45s"," "));
        	   System.out.print(String.format("%-10s","Push "+action_value_temp));
        	   stackState.push(action_value_temp);
        	 //  tempStack.push(action_value_temp);
        	   
        	   //System.out.println(stackState.peek());
        	   gotoLookup_value=Integer.parseInt(action_value.substring(1));
        	  tempstackdis="";
              for(String str12 : parseTreeStack)
             	 tempstackdis=str12+tempstackdis;
              System.out.println(String.format("%-20s",tempstackdis));     	   
           } 
           
           else if(action_value.equals("accept")) {
        	   System.out.println("\n Successfully completed parsing ");
        	   break;
           }
         }
         else {  System.out.println("Invalid grammer");
        	 break;}
           
         //outFormat = String.format("%-20s %-15s %-15s %-5s %-5s %-5d %-15s %-15s %-5s %-15s", tempstackdis+tempquedis+);
           }
           
           printParseTree(parseTreeStack);
		
	}

}