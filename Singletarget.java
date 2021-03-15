
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.DataInputStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
//import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
//import java.util.Map;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;
import java.util.Collections;

//import LoopDE.Loop_PathDE.MyCallable;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.util.Set;

//import com.google.common.collect.HashMultimap;
//import com.google.common.collect.Multimap;

/*Author Semujju Stuart Dereck*/
public class Singletarget {
    
	private static  int RUN = 10; // the number of the program runs.
	//private static  double F = 0.5;
	private static final int bias = 10;
	private static final int pop_num = 50; // the number of test cases
	///private static final int fun_num = 1; // the serial number of the target benchmark function.
	private static final int R = 14;
	private static final int NODENUM = 3; // the number of the nodes/vertex in the target benchmark function.
    private static final int col = 0;
	//private static  double Pc = 0.2;
	//private static  double non_simpleid = 99.0; // prefix  given to test case not traversing a path in the group
	//private static  double non_simpleid2 = 100.0;// prefix given to test case traversing a path in the group
	private static  int PATHNUM =  2187; // In this test bench mark there are 16 groups each with 32 paths
	//private static int objective_size = 0; 
	//private static final int TOTAL_PATHNUM = 2187;
	 
	private static int tournamentSize = 10;
	static int cross_counter = 0;

	// ************************************ strings to capture simple loop paths and
	// to capture non-simple loop
	// paths**************************************************//
	static LinkedHashMap<String, Boolean> temporary_path_holder = new LinkedHashMap<String, Boolean>();
	static LinkedHashMap<String, Integer> pathcounter = new LinkedHashMap<String, Integer>();
	static LinkedHashMap<String, Boolean> evaluated_status = new LinkedHashMap<String, Boolean>();
	static //public static Map<String, int[][]> dynamic_access = new HashMap<String, int[][]>();
	int [][] solution = new  int[PATHNUM][R];
	
	
	static boolean [] statu = new boolean [PATHNUM];

	// ****************************************************************************************************************************************************************//
	public static int obj_total; 
	static int[][] dynamic16;

	static int que = 1;

	static int[] Cycle = new int[RUN];
	static float[] coverage = new float[RUN];
	static double[] runtime = new double[RUN];
	//static int[] case_num = new int[RUN];
	
	static int[] total_case_num = new int[RUN];

	static File allpaths = new File("C:/Users/scybe/eclipse-workspace/Bench/All.txt");
	static LinkedHashMap<String, Boolean> paths = new LinkedHashMap<String, Boolean>();

	@SuppressWarnings("unlikely-arg-type")
	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
		// TODO Auto-generated method stub

		// System.out.println("what");
		int k = 7;
		
		char[] set = { 'a', 'b', 'c' };
		char[] str = new char[k];

		enumeratePaths(k, set, str, 0); // call function to generate the paths
		init_Template();// function to create the groups of paths. // using the simple loop path pattern
						// information


		int[] lb = new int[R];// lower bound
		int[] ub = new int[R];// upper bound

		for (int j = 0; j < R; j++) { // the sample range of integers is between 1 and 100
			lb[j] = 1; // lower bound
			ub[j] = 1000; // upper bound

		}

		for (int run = 0; run < RUN; run++) {

			int[][] x = new int[pop_num][R];
			int[][] v = new int[pop_num][R];
			
		//	int[][] temporal = new int[pop_num][R];
			obj_total = 0;
			
			
			solution = new  int[PATHNUM][R];
			
			
			 statu = new boolean [PATHNUM];
			
			int crossover_count = 0;
            boolean  statusChecker =false;
			LinkedHashMap<String, List<Double>> parent_maps = new LinkedHashMap<String, List<Double>>();
			
			LinkedHashMap<String, List<Double>> offspring_maps = new LinkedHashMap<String, List<Double>>();
			
			LinkedHashMap<String, Double> combined_maps = new LinkedHashMap<String, Double>();
		//	LinkedHashMap<String, List<Double>> fitnessmaps = new LinkedHashMap<String, List<Double>>();

			LinkedHashMap<String, Integer> setrank1 = new LinkedHashMap<String, Integer>();
		
			List<String> updated_population = new ArrayList<String>();
			
			List<String> combined_population = new ArrayList<String>();
			List<String> offspring_population = new ArrayList<String>();
			//List<String> parent_population = new ArrayList<String>();
			List<String> single_population = new ArrayList<String>();

			List<String> combined_population_list = new ArrayList<String>();
			
			
			
			List<String> parents_population   = new ArrayList<String>();
			
			List<String> parents_population_copy   = new ArrayList<String>();
			
		
			int totalpathcounter = 0;

			int obj_total = 0;

			String getfinalpath = null;
			//String final_path_checker;

			if (run > 0) { // in each run reset the number counter of the paths covered in each group

				reset_All();
				
				updated_population.clear();
				combined_population.clear();
				offspring_population.clear();
				single_population.clear();
				offspring_maps.clear();
				setrank1.clear();
				//setrank2.clear();
				//offspring_maps.clear();
				parents_population_copy.clear();
				combined_maps.clear();
				parent_maps.clear();

			}

			for (int i = 0; i < pop_num; i++) // initialize the population with the dimension of R values/inputs
			{

				for (int j = 0; j < R; j++) {
				
					x[i][j]  = (int)(Math.random()*((ub[j]  - lb[j])+1))+ lb[j];

				}

			

				getfinalpath = pathnum(x[i]); // get path traversed by input x is returned
				archiving( getfinalpath , i, x);
				parents_population.add("parent" + " " + i);
				//record covered path and find group where it belong
				//save test case in corresponding path group
				
				 parents_population.add("parent" + " " + i);
			}

			parents_population_copy.addAll(parents_population);
			combined_population_list.addAll(parents_population_copy);
	
			Cycle[run] = 1;

				long start_time = System.currentTimeMillis();
				long wait_time = 1518750;
				long end_time = start_time + wait_time;
				List<Double> temp_2 = new ArrayList<Double>();
				
				while ((System.currentTimeMillis() < end_time) && obj_total < PATHNUM) // not exceeded the set

				{
				
					if (crossover_count == 0) {
						
					Random   random    = new Random();
					for (int i = 0; i < 25; i++) {
				
						//pick two parents to cross
						
						String randomKey_1 =   parents_population_copy.get( random.nextInt(parents_population_copy.size())) ;
						
						parents_population_copy.remove(randomKey_1);
						
						String randomKey_2 =   parents_population_copy.get( random.nextInt(parents_population_copy.size())) ;
						
						parents_population_copy.remove(randomKey_2);
						//access parent via parent_maps_init
						
						//split the string
						//parents_population.add("parent" + " " + i);
						String [] parent_1_string = randomKey_1.split(" ");	
						
						String par_1;

						int pos_1;
						
						
						par_1 = parent_1_string[0];
						pos_1 = Integer.parseInt(parent_1_string[1]);
						
                     //  String [] parent_2_string = randomKey_2.split(" ");	
						
						String [] parent_2_string = randomKey_1.split(" ");	
						
						String par_2;

						int pos_2;
						
						
						par_2 = parent_2_string[0];
						pos_2 = Integer.parseInt(parent_2_string[1]);
					
				    	Random rand = new Random();
					//	double rdm  = rand.nextDouble();		   
						double rdm  = Math.random();
						 if(rdm < 0.75) {
						
							//single-point crossover
						    	
						    	//randomly find a position and exchange the tails
							 
							int parent_1_random = rand.nextInt(R);
						    	
						    int new_point = parent_1_random; 
						    //exchange elements with parent_2
						    
						    //	int[][] temporal = new int[pop_num][R];
						  
						    for (int j = 0 ; j < R; j++) {
					    		
					    		
						    	v[pos_1][j] =  x[pos_1][j];
						    	v[pos_2][j] =  x[pos_2][j];
					    		
					    	}
					    	
						    int g = new_point;
						   
						  //  for (int g = new_point ; new_point  < R; new_point++) {
					    		
					    	while (g <= R){	
					    		//switch the tails of parent one and parent two
						    	v[pos_1][g] =   x[pos_2][g];
						    	v[pos_2][g] =   x[pos_1][g];

					    	 g++;
					    	}
						    
						
							double mutate  = Math.random();
						    
							 if(mutate < (1/R)) {   
								 
								
								    int child_1  = (int) (Math.random() * R);
						        	
						        	int child_2  = (int) (Math.random() * R);
						        	
						        	
						        	v[pos_1][child_1] =   (int)(Math.random()*((ub[child_1]  - lb[child_1])+1))+ lb[child_1];
						        	
						        	offspring_population.add("child" + " " + pos_1);
						        	
						        	v[pos_2][child_2] =   (int)(Math.random()*((ub[child_2]  - lb[child_2])+1))+ lb[child_2]; 
						        	
						        	offspring_population.add("child" + " " + pos_2);
						        	
						     
							 }
						 
							 else {
								 
								 
								 offspring_population.add("child" + " " + pos_1);
								 
								 offspring_population.add("child" + " " + pos_2);
								 
							 }
						 
						 }
						    
						    
						else {
						    	
					    		for (int j = 0 ; j < R; j++) {
						    		
						    		
						    		v[pos_1][j] =  x[pos_1][j];
						    		v[pos_2][j] =  x[pos_2][j];
						    		
						    	}
					    		
					    		
					    		//mutate
					    		

					    	//    Random mut = new Random();
								double mutate  = Math.random();
							    
								 if(mutate < (1/R)) {   
									 
									
									    int child_1  = (int) (Math.random() * R);
							        	
							        	int child_2  = (int) (Math.random() * R);
							        	
							        	
							        	v[pos_1][child_1] =   (int)(Math.random()*((ub[child_1]  - lb[child_1])+1))+ lb[child_1];
							        	
							        
							        	
							        	offspring_population.add("child" + " " + pos_1);
							        	
							        	v[pos_2][child_2] =   (int)(Math.random()*((ub[child_2]  - lb[child_2])+1))+ lb[child_2]; 
							        	
							        	offspring_population.add("child" + " " + pos_2);
							        	
							     
								 }
							 
								
					    		
						    }
						
						
                           //mutation  (1/size of test case input vector)
	
					}
					//get path traversed by offsprings
				   
					
					for (int i = 0; i <  offspring_population.size(); i++) {// initialize the population with the dimension of R values/inputs
				
				    String [] get_index = offspring_population.get(i).split(" ");	
						
				    String off_1;

					int index_1;
					
					
					off_1 = get_index[0];
					index_1 = Integer.parseInt(get_index[1]);	
					
					getfinalpath = pathnum(v[index_1]);
					
				
					archiving( getfinalpath , index_1, v);
				
					
					}
					
				}
					
					
					
				else {
					     Random  random    = new Random();
					    // combined_maps.put( "child" + " " + typeId  , offspring_fitness);
					   int counter  = 0;
					
						
						for (int i = 0; i < 25; i++) {
					
							//pick two parents to cross
							
							String randomKey_1 =   parents_population_copy.get( random.nextInt(parents_population_copy.size())) ;
							
							parents_population_copy.remove(randomKey_1);
							
							String randomKey_2 =   parents_population_copy.get( random.nextInt(parents_population_copy.size())) ;
							
							parents_population_copy.remove(randomKey_2);
							//access parent via parent_maps_init
							
							//split the string
							//parents_population.add("parent" + " " + i);
							String [] parent_1_string = randomKey_1.split(" ");	
							
							String par_1;

							int pos_1;
							
							
							par_1 = parent_1_string[0];
							pos_1 = Integer.parseInt(parent_1_string[1]);
							
	                     //  String [] parent_2_string = randomKey_2.split(" ");	
							
							String [] parent_2_string = randomKey_1.split(" ");	
							
							String par_2;

							int pos_2;
							
							
							par_2 = parent_2_string[0];
							pos_2 = Integer.parseInt(parent_2_string[1]);
							
						  //System.out.println("Random number 1 : "+Math.random()
							//crossover nbased on probability
					    	Random rand = new Random();
						//	double rdm  = rand.nextDouble();		   
							double rdm  = Math.random();
							 if(rdm < 0.75) {
							
								//single-point crossover
							    	
							    	//randomly find a position and exchange the tails
								 
								int parent_1_random = rand.nextInt(R);
							    	
							    int new_point = parent_1_random; 
							    //exchange elements with parent_2
							    
							    //	int[][] temporal = new int[pop_num][R];
							  
							    for (int j = 0 ; j < R; j++) {
						    		
						    		
							    	v[pos_1][j] =  x[pos_1][j];
							    	v[pos_2][j] =  x[pos_2][j];
						    		
						    	}
						    	
							    int g = new_point;
							   
							  //  for (int g = new_point ; new_point  < R; new_point++) {
						    		
						    	while (g < R){	
						    		//switch the tails of parent one and parent two
							    	v[pos_1][g] =   x[pos_2][g];
							    	v[pos_2][g] =   x[pos_1][g];

						    	 g++;
						    	}
							    
							  
								double mutate  = Math.random();
							    
								 if(mutate < (1/R)) {   
									 
									
									    int child_1  = (int) (Math.random() * R);
							        	
							        	int child_2  = (int) (Math.random() * R);
							        	
							        	
							        	v[pos_1][child_1] =   (int)(Math.random()*((ub[child_1]  - lb[child_1])+1))+ lb[child_1];
							        	
							        	offspring_population.add("child" + " " + pos_1);
							        	
							        	v[pos_2][child_2] =   (int)(Math.random()*((ub[child_2]  - lb[child_2])+1))+ lb[child_2]; 
							        	
							        	offspring_population.add("child" + " " + pos_2);
							        	
							     
								 }
							 
								 else {
									 
									 
									 offspring_population.add("child" + " " + pos_1);
									 
									 offspring_population.add("child" + " " + pos_2);
									 
								 }
							 
							 }
							    
							    
							else {
							    	
						    		for (int j = 0 ; j < R; j++) {
							    		
							    		
							    		v[pos_1][j] =  x[pos_1][j];
							    		v[pos_2][j] =  x[pos_2][j];
							    		
							    	}
						    		
						    		
						    		//mutate
						    		

						    	//    Random mut = new Random();
									double mutate  = Math.random();
								    
									 if(mutate < (1/R)) {   
										 
										
										    int child_1  = (int) (Math.random() * R);
								        	
								        	int child_2  = (int) (Math.random() * R);
								        	
								        	
								        	v[pos_1][child_1] =   (int)(Math.random()*((ub[child_1]  - lb[child_1])+1))+ lb[child_1];
								        	
								        
								        	
								        	offspring_population.add("child" + " " + pos_1);
								        	
								        	v[pos_2][child_2] =   (int)(Math.random()*((ub[child_2]  - lb[child_2])+1))+ lb[child_2]; 
								        	
								        	offspring_population.add("child" + " " + pos_2);
								        	
								     
									 }
								 
									
						    		
							    }
							
							
	                           //mutation  (1/size of test case input vector)
		
						
				    }
					   
						
						for (int i = 0; i <  offspring_population.size(); i++) {// initialize the population with the dimension of R values/inputs
					
					    String [] get_index = offspring_population.get(i).split("");	
							
					    String off_1;

						int index_1;
						
						
						off_1 = get_index[0];
						index_1 = Integer.parseInt(get_index[1]);	
							
					    
						getfinalpath = pathnum(v[index_1]);   //get path traversed by offspring test case 
						
						archiving( getfinalpath , i, v);//add test case to archive
						
						
						}
					
				
				}	
			
					crossover_count++;
					
					
					for (int i = 0; i < offspring_population.size(); i++) // add all offpsings to parent and form final combined population
					{
			
							combined_population_list.add(offspring_population.get(i));
				    }
						
					List<String> final_combined_population = new ArrayList<String>();

					// temp.clear();

					final_combined_population.addAll(combined_population_list);	
				//evaluate parent and child population
					
					double offspring_fitness ;
					
					for (int i = 0; i < final_combined_population.size(); i++) // contains both parent and offspring test cases
					{
					
                        String checkString = final_combined_population.get(i);	
                      
                        String [] getType = checkString.split(" ");
                      
						String typeName  =   getType[0];

						int typeId  =  Integer.parseInt(getType[1]);
						
						
						//	fit.add(target_path_+ " " + path_status +" " + nonsimple_unmatched2);
						
						if (typeName.equals("parent")) {

					     	offspring_fitness = benchmarkfunction(x[typeId]);
					     	//extract path and fitness
					     	combined_maps.put( "parent" + " " + typeId  , offspring_fitness);
					     	
					     	//static LinkedHashMap<String,  ArrayList<String>> check_fitness = new LinkedHashMap<String, ArrayList<String>>();
					     		
							}
					   
						else {
						
							//fit.add(target_path_+ " " + path_status +" " + nonsimple_unmatched2);
							
							offspring_fitness = benchmarkfunction(v[typeId]);
							combined_maps.put( "child" + " " + typeId  , offspring_fitness);
							//int counter  = 0;
					
						}
						
					
					}
					
					int counter = 0;
					while (counter <  50) {
						
						 List<String> keys = new ArrayList<String>(combined_maps.keySet());
						 
						 Random rand = new Random();
						 
						 String key_ss = keys.get(rand.nextInt(keys.size()));
						 String key_holder = null;
						 double min = combined_maps.get(key_ss);
						 
						 for (int i = 1 ; i < 10; i++) {
							 
						    String key = keys.get(rand.nextInt(keys.size()));
						      
						      if (combined_maps.get(key) < min) {
				                   min = combined_maps.get(key);
				                   key_holder = key;
				                
				            }
					
						}
						
						 
						 parents_population_copy.add(key_holder);
						// combined_maps.remove(key_holder);
		          
					} 
			     
				
				}
			
			System.out.println("NO. of cycles=" + (Cycle[run] - 1)); // ���Number of Cycle
			coverage[run] = totalpathcounter * 100 / PATHNUM; // percentage of paths covered per run
			System.out.println("Path coverage=" + coverage[run] + "%");
			System.out.println("The optimal solution is");
			System.out.println("template 1(bbbb): ");
			for (int a = 0; a < PATHNUM; a++) // Output the result
			{
				if (statu[a]) {
					System.out.print("path" + a + ":");
					for (int j = 0; j < R; j++)
						System.out.print(solution[a][j] + " ");
					System.out.println();
				} else
					System.out.println("path" + a + "Not covered.");
			}

			// one run ends here

		}

		double time_sum = 0, time_average;
		float coverage_sum = 0, coverage_average, cycle_sum = 0, cycle_average, case_average;
		int case_sum = 0;
		for (int run = 0; run < RUN; run++) {
			time_sum = time_sum + runtime[run];
			cycle_sum = cycle_sum + (Cycle[run] - 1);
			case_sum = case_sum + total_case_num[run];
			coverage_sum = coverage_sum + coverage[run];
		  }
		
		time_average = time_sum / RUN;
		cycle_average = cycle_sum / RUN;
		case_average = case_sum / RUN;
		coverage_average = coverage_sum / RUN;

		System.out.println("time_sum = " + time_sum + "ms");
		System.out.println("time_average = " + time_average + "ms");
		System.out.println("cycle_sum = " + cycle_sum);
		System.out.println("cycle_average = " + cycle_average);
		System.out.println("case_sum = " + case_sum);
		System.out.println("case_average = " + case_average);
		System.out.println("coverage_sum = " + coverage_sum + "%");
		System.out.println("coverage_average = " + coverage_average + "%");

	
		
		//test case number statistics
		try 
		{ 
//			WritableWorkbook wbook= 
//			Workbook.createWorkbook(new File("E:/result0106.xls")); 
//			//生成名为“Ramdom”的工作表，参数0表示这是第一页 
//			WritableSheet sheet=wbook.createSheet("DE1",0); 
			
			File file = new java.io.File("C:/Users/scybe/eclipse-workspace/TestData/Test_whole.xls"); 
			Workbook book = Workbook.getWorkbook(file);   
			WritableWorkbook wbook = Workbook.createWorkbook(file, book); 
     	    WritableSheet sheet = wbook.getSheet(0);  // 写入数据 sheet    
			
			for(int run=0 ; run<RUN; run++)
			{
				int q = run;
				jxl.write.Number number = new jxl.write.Number(col, q,total_case_num[run]); 
				
				
				sheet.addCell(number); 
				System.out.println(total_case_num[run]);	
			}
			
			//total_case_num[run]
			
		 	double case_ave = getAverage(total_case_num , RUN);
			jxl.write.Number number1 = new jxl.write.Number(col, 25 ,case_ave); 
			sheet.addCell(number1);
			
			 		
			wbook.write(); 	
			wbook.close();
			 
		}catch(Exception e) 
		{ 
		System.out.println(e); 
		} 
						
		
		//coverage statistics 
		
		//test case number statistics
				
		
		
		try 
			{ 
//					
					File file = new java.io.File("C:/Users/scybe/eclipse-workspace/TestData/Coverage_whole.xls"); 
					Workbook book = Workbook.getWorkbook(file);   
					WritableWorkbook wbook = Workbook.createWorkbook(file, book); 
		     	    WritableSheet sheet = wbook.getSheet(0);  // 写入数据 sheet    
					
					for(int run=0 ; run<RUN; run++)
					{
						int q=run;
						jxl.write.Number number = new jxl.write.Number(col, q,coverage[run]); 
						sheet.addCell(number); 
						
 
						//写入数据并关闭文件 				
					}
					
					//total_case_num[run]
					
					double case_ave = getAverages(coverage , RUN);
					jxl.write.Number number1 = new jxl.write.Number(col,25,case_ave); 
					sheet.addCell(number1);
					
					wbook.write(); 	
					wbook.close();
					 
				}catch(Exception e) 
				{ 
				System.out.println(e); 
				} 
				
		

	}

	
	
	
	public static String pathnum(int[] x) {

		StringBuilder stringBuilder = new StringBuilder();
		// stringBuilder.append("0"); //for max array just store it. it's by default
		// assigned
		String finalpath = " ";

		boolean[][] truth_table_visit = new boolean[NODENUM][R];

	 // maximum array value

			int i1;

			int max;
			int[] a4 = new int[R]; // a4 will hold the values in the individual x

			for (i1 = 0; i1 < R; i1++) {

				a4[i1] = x[i1]; // insert x values into array a4

			}

			max = a4[0]; // set max value to the first element of the array

			for (int j = 1; j < R; j++) {

				if (a4[j] > max) {

					truth_table_visit[0][j] = true; // keep track of the truth node visit at the taken iteration---make
													// it true

					// truth_table_visit[1][j] = false;
					  max = a4[j];
				}

				else {

					// truth_table_visit[0][j] = false;
					truth_table_visit[1][j] = true;
					// max = a4[j];
					// truth_table_visit[1][j] = true;
					// false_count++;
					// false_table_visit[1][j] = true;

				}
				
			}

			for (int j = 1; j < R; j++) { // align the visits to form the correct traversed path

				if ((truth_table_visit[0][j])) {

					stringBuilder.append('a');

				} 
				
		
				if ((truth_table_visit[1][j]))
					stringBuilder.append('b');
			

			}

			finalpath = stringBuilder.toString();
			// return finalpath;

		

		return finalpath; // path traversed by input/test data(x) : capture its id
	}

	public static double benchmarkfunction(int[] trix)
			throws FileNotFoundException // evaluation_of_fitness_for

	{
		double[][] fitness_matrix = new double[NODENUM][R];
		double fit = 0;
		boolean[][] benchmark_table_visit = new boolean[NODENUM][R];
		// evaluate divergence at the edge of the vertex per iteration ...

			StringBuilder benchmarkBuilder = new StringBuilder();
			String final_vertex_path;
	
			String nonsimple_violation;

			//int pattern_counter;

			//pattern_counter = pattern_number;
			int i2;
			int i3;
			double v1 = 0;

			double v2 = 0;

			double exp = 0;
			double exp_1 = 0;
			double total_1 = 0.0;

			// double matched_percentage = 0.0;
			int max1;
			// double unwinding1 = 0.0;
			// double unwinding2 = 0.0;;
			// double unwinding1 = 0.0;
			// double unwinding2 = 0.0;
			double simple_unwinding = 0.0;

			int max2;
			int[] a5 = new int[R];
			int[] a6 = new int[R];
			fitness_matrix[0][0] = Double.MAX_VALUE;
			fitness_matrix[1][0] = Double.MAX_VALUE;
			// total_violation = 0.0;
			// Unmatched_branch_node_1 = 0;
			// total_1 = 0.0;

			for (i2 = 0; i2 < R; i2++) {

				a5[i2] = trix[i2];

			}

			max1 = a5[0];

			for (int j = 1; j < R; j++) {

				if (a5[j] > max1) {

					v1 = 0.0;
					fitness_matrix[0][j] = v1;
					max1 = a5[j];
				}

				else {
					v1 = (max1 - a5[j]) + bias;

					exp = Math.pow(1.001, -(v1));

					fitness_matrix[0][j] = (1 - exp);
					// truth_table_visit[0][j] = true;
					// truth_count++;

				}

				
			}
			// f[0] = v2 ; // fitness of not taking truth node

			// fitness of not taking else edge
			for (i3 = 0; i3 < R; i3++) {

				a6[i3] = trix[i3];

			}

			max2 = a6[0];

			for (int j = 1; j < R; j++) {

				if (a6[j] <= max2) {

					v2 = 0.0;

					fitness_matrix[1][j] = v2;

				} 
				
				else {

					v2 = (a6[j] - max2) + bias;

					exp_1 = Math.pow(1.001, -(v2)); // normalize_branch_distance

					fitness_matrix[1][j] = (1 - exp_1);
					
					max2 = a6[j];
				}

				

			}

			int i1;
			int max_;

			int[] a4_ = new int[R];

			for (i1 = 0; i1 < R; i1++) {

				a4_[i1] = trix[i1];

			}

			max_ = a4_[0];

			for (int j = 1; j < R; j++) {

				if (a4_[j] > max_) {

					benchmark_table_visit[0][j] = true;
					// truth_count++;
					max_ = a4_[j];
				}

				else {

					// max = a4[j];
					benchmark_table_visit[1][j] = true;
					// false_count++;
					// false_table_visit[1][j] = true;

				}
				
			}
			// construct traversed vertex string

			for (int j = 1; j < R; j++) {

				if ((benchmark_table_visit[0][j])) {

					benchmarkBuilder.append('a');

				}

	
				if ((benchmark_table_visit[1][j])) {
						benchmarkBuilder.append('b');
				
				}
			}

			final_vertex_path = benchmarkBuilder.toString();

			// String temp_path;

			//nonsimple_violation = final_vertex_path.substring(0, Math.min(final_vertex_path.length(), pattern_number));
			// myText.substring(0, Math.min(6, myText.length())

			//if (!nonsimple_violation.equals(template_id)) {

	

			try {
				File fstream_temporary = new File("C:/Users/scybe/eclipse-workspace/Bench/All.txt");
				FileReader fileReader_ = new FileReader(fstream_temporary);
				BufferedReader br_ = new BufferedReader(fileReader_);
				String strLine_non;
				 //int counter1 = -1;
				
				while ((strLine_non = br_.readLine()) != null) {
					
					
					if (!paths.get(strLine_non)) {

						double Unmatched_branch_node_a_ = 0.0;
						double Unmatched_branch_node_b_ = 0.0;
						// traversed_violation = strLine_non.substring(strLine_non.length() - 5);

						int index = 0;
						// double unwinding2 = 0.0;
						for (int i = 0; i < 10; i++) {
							index++;
							if (final_vertex_path.charAt(i) != strLine_non.charAt(i)) {

								if (strLine_non.charAt(i) == 'a') {
									
									
									Unmatched_branch_node_a_ += fitness_matrix[0][index];

								}
								
								
							   if (strLine_non.charAt(i) == 'b') {
									
									
									Unmatched_branch_node_b_ += fitness_matrix[1][index];

								}
								
							}
								
						}
						double nonsimple_unmatched2 = 0.0;
						nonsimple_unmatched2 = Unmatched_branch_node_a_ + Unmatched_branch_node_b_;
						fit = nonsimple_unmatched2;
						
						break;
					}
	
				}
				br_.close();
			}
	          catch(Exception e) {

	        }

		return fit;

	}

	static void enumeratePaths(int k, char[] set, char[] str, int index) {
		if (index == k) {
			// System.out.println(new String(str));
			paths.put(new String(str), false); // write all resulting paths to map..

		} else {
			for (int i = 0; i < set.length; i++) {
				str[index] = set[i];
				enumeratePaths(k, set, str, index + 1); // recursive procedure to enumerate the paths.
			}
		}
	}

	public static void init_Template() throws IOException { // read paths from map to files according to template ids..
															// assign identical paths to templates

		FileOutputStream fos_all = new FileOutputStream(allpaths);
		BufferedWriter bw_all = new BufferedWriter(new OutputStreamWriter(fos_all));

		// pathcounter

		Set<String> keyz = paths.keySet();

		for (String key : keyz) {

			bw_all.write(key); // write to file
			bw_all.newLine();

			
		}

		bw_all.close();
	

	}

	public static void reset_All() {
		// clear all the maps for next run and update them
		paths.clear();
		pathcounter.clear();
		evaluated_status.clear();
		//dynamic_access.clear();

		

		// reload the path map with all the paths
		try {
			File fstream_temporary = new File("C:/Users/scybe/eclipse-workspace/Bench/All.txt");
			FileReader fileReader_ = new FileReader(fstream_temporary);
			BufferedReader br_ = new BufferedReader(fileReader_);
			String strLine_temporary;

			while ((strLine_temporary = br_.readLine()) != null) {

				paths.put(strLine_temporary, false);

			}
			br_.close();
		} catch (Exception e) {
		}

	}

	static double getAverage(int[] array, int num) {
		int sum = 0;
		for (int i = 0; i < num; i++) {
			sum += array[i];
		}
		return (double) (sum / num);
	}

	// ��׼��
	static double getStandardDevition(int[] array, int num) {
		double sum = 0;
		for (int i = 0; i < num; i++) {
			sum += Math.sqrt(((double) array[i] - getAverage(array, num)) * (array[i] - getAverage(array, num)));
		}
		return (sum / (num - 1));
	}

	
	public static int frontdominace_Comparison(List<Double> listxobjectives, List<Double> listvobjectives) {

		int dominat_flagx = 0;
		int dominat_flagv = 0;
		int two_objectivesy_count = 0;
		int two_objectivesx_count = 0;
		int objectivexcount = 0;
		int objectivevcount = 0;
		

		for (int i = 0; i < listxobjectives.size(); i++) {

			if(two_objectivesx_count == 2) {
				
				
				break;
			}
			
			
			if (listxobjectives.get(i) == listvobjectives.get(i)) {

				objectivexcount++;
				two_objectivesx_count++;
			}

			if (listxobjectives.get(i) < listvobjectives.get(i)) {

				dominat_flagx = 1;
				objectivexcount++;
			}

		}

		for (int i = 0; i < listvobjectives.size(); i++) {

		
		   if(two_objectivesy_count == 2) {
				
				
				break;
			}
			
			if (listvobjectives.get(i) == listxobjectives.get(i)) {

				objectivevcount++;
				two_objectivesy_count++;
			}

			if (listvobjectives.get(i) < listxobjectives.get(i)) {
				
				objectivevcount++;
			}

		
		}

		if ((objectivexcount > objectivevcount ) ) {

			return -1; // Testcase1 dominates
		}

		else if ((objectivevcount > objectivexcount) ) {

			return 1; // Testcase2 dominates
		}

		else {
			return 0;
		}
		/// No one dominate the other

	}

	public static int get_count(List<Double> dominatx, List<Double> dominaty) {

		int objectivedominaty = 0;

		for (int i = 0; i < dominatx.size(); i++) {

			if (dominatx.get(i) > dominaty.get(i)) {

				objectivedominaty++;

			}

		}

		return objectivedominaty;
	}

	static float getAverages( float[] array, int num) {
		float sum = 0;
		for (int i = 0; i < num; i++) {
			sum += array[i];
		}
		return (float) (sum / num);
	}	
	

	
	
	
 static void  archiving( String captured_path , int num, int[][] offspring) {	
	
	
	try {
		File fstream_temporary = new File("C:/Users/scybe/eclipse-workspace/Bench/All.txt");
		FileReader fileReader_ = new FileReader(fstream_temporary);
		BufferedReader br_ = new BufferedReader(fileReader_);
		String strLine_temporary;
		 int counter1 = -1;
		
		while ((strLine_temporary = br_.readLine()) != null) {
			counter1++;
			
			if (strLine_temporary.equals(captured_path)) {

				if (paths.get(strLine_temporary)) {
					break;
				}
				else {
					
					paths.put(strLine_temporary, true);
					
					
					if (!statu[counter1]) {
						// array template_1path_id

						for (int s = 0; s < R; s++) {

							solution[counter1][s] = offspring[num][s];

						}
						statu[counter1] = true; // ���·��Path�Ƿ����ҵ�������������
						
						obj_total++;
						// pathcounter.put("1101", pathcounter.get("1101") + 1);
						//totalpathcounter++;
						// get other nodes(not part of the simple loop pattern)

						break;
					}
				}
					
				
			//paths.put(strLine_temporary, false);
			}
		}
		br_.close();
	} catch (Exception e) {
	}
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}