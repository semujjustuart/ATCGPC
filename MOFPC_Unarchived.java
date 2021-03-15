
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
public class MOFPC_Unarchived {
    
	private static  int RUN = 30; // the number of the program runs.
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
	private static  int PATHNUM_NUMBER =  2187; // In this test bench mark there are 16 groups each with 32 paths
	//private static int objective_size = 0; 
	//private static final int TOTAL_PATHNUM = 2187;
	//private static final int PATHNUM = 27;
	private static int tournamentSize = 10;
	
	//population size = 50
	
	//	Crossover: single-point
   // crossover probability 0.75
    //Mutation: uniform mutation
	//mutation probability  1/size of test case tuple
	// tournament selection  default size 10.
  
	
	
	//static int cross_counter = 0;

	// ************************************ strings to capture simple loop paths and
	// to capture non-simple loop
	// paths**************************************************//
	//static LinkedHashMap<String, Boolean> temporary_path_holder = new LinkedHashMap<String, Boolean>();
	//static LinkedHashMap<String, Integer> pathcounter = new LinkedHashMap<String, Integer>();
	static LinkedHashMap<String, Boolean> evaluated_status = new LinkedHashMap<String, Boolean>();
	
	//public static Map<String, int[][]> dynamic_access = new HashMap<String, int[][]>();


	// ****************************************************************************************************************************************************************//

	static int[][] dynamic16;

	//static int queue = 1;

	//static int[] Cycle = new int[RUN];
	
	
	static float[] coverage = new float[RUN];
	static double[] runtime = new double[RUN];
	//static int[] case_num = new int[RUN];
	
	static int[] total_case_num = new int[RUN];

	//static File allpaths = new File("C:/Users/Mi/eclipse-workspace/MOFPC/Benches/All.txt");
	static LinkedHashMap<String, Boolean> paths = new LinkedHashMap<String, Boolean>();
	
	static LinkedHashMap<String, Boolean> temporay_paths = new LinkedHashMap<String, Boolean>();
	
	
	static LinkedHashMap<String, Boolean> target_subset = new LinkedHashMap<String, Boolean>();
    static LinkedHashMap<String, Boolean> replaced_status = new LinkedHashMap<String, Boolean>();
   // static LinkedHashMap<String, Boolean> replaced_status_2 = new LinkedHashMap<String, Boolean>();
    static LinkedHashMap<Integer, String> path_ids = new LinkedHashMap<Integer, String>();
	//static LinkedHashMap<String, Boolean> coverage_status_1 = new LinkedHashMap<String, Boolean>();
	//static LinkedHashMap<String, Boolean> coverage_status_2 = new LinkedHashMap<String, Boolean>();
	
	static LinkedHashMap<String,  ArrayList<Double>> map_of_objectives = new LinkedHashMap<String, ArrayList<Double>>();
	
	//static LinkedHashMap<String,  String> fitness_map = new LinkedHashMap<String, String>();
	
	static LinkedHashMap<String,  Double> check_path_objectives = new LinkedHashMap<String, Double>();
	
	static LinkedHashMap<String,  ArrayList<String>> check_fitness = new LinkedHashMap<String, ArrayList<String>>();
	
	static LinkedHashMap<String,  Double> remove_objective = new LinkedHashMap<String, Double>();
	
	//static LinkedHashMap<String,  String>  = new LinkedHashMap<String, String>();
	
	
	List<String> updated_population = new ArrayList<String>();
	
	//static List<String> removed_at_previous_iteration = new ArrayList<String>();
	static List<String> still_remaining_targets = new ArrayList<String>();
	
	
	//group prefixes
	static String template_1 = "aaaa";
	static String template_2 = "aaab";
	static String template_3 = "aaac";
	static String template_4 = "aaba";
	static String template_5 = "aabb";
	static String template_6 = "aabc";
	static String template_7 = "aaca";
	static String template_8 = "aacb";
	static String template_9 = "aacc";

	static String template_10 = "abaa";
	static String template_11 = "abab";
	static String template_12 = "abac";
	static String template_13 = "abba";
	static String template_14 = "abbb";
	static String template_15 = "abbc";
	static String template_16 = "abca";
	static String template_17 = "abcb";
	static String template_18 = "abcc";

	static String template_19 = "acaa";
	static String template_20 = "acab";
	static String template_21 = "acac";
	static String template_22 = "acba";
	static String template_23 = "acbb";
	static String template_24 = "acbc";
	static String template_25 = "acca";
	static String template_26 = "accb";
	static String template_27 = "accc";

	static String template_28 = "baaa";
	static String template_29 = "baab";
	static String template_30 = "baac";
	static String template_31 = "baba";
	static String template_32 = "babb";
	static String template_33 = "babc";
	static String template_34 = "baca";
	static String template_35 = "bacb";
	static String template_36 = "bacc";

	static String template_37 = "bbaa";
	static String template_38 = "bbab";
	static String template_39 = "bbac";
	static String template_40 = "bbba";
	static String template_41 = "bbbb";
	static String template_42 = "bbbc";
	static String template_43 = "bbca";
	static String template_44 = "bbcb";
	static String template_45 = "bbcc";

	static String template_46 = "bcaa";
	static String template_47 = "bcab";
	static String template_48 = "bcac";
	static String template_49 = "bcba";
	static String template_50 = "bcbb";
	static String template_51 = "bcbc";
	static String template_52 = "bcca";
	static String template_53 = "bccb";
	static String template_54 = "bccc";

	static String template_55 = "caaa";
	static String template_56 = "caab";
	static String template_57 = "caac";
	static String template_58 = "caba";
	static String template_59 = "cabb";
	static String template_60 = "cabc";
	static String template_61 = "caca";
	static String template_62 = "cacb";
	static String template_63 = "cacc";

	static String template_64 = "cbaa";
	static String template_65 = "cbab";
	static String template_66 = "cbac";
	static String template_67 = "cbba";
	static String template_68 = "cbbb";
	static String template_69 = "cbbc";
	static String template_70 = "cbca";
	static String template_71 = "cbcb";
	static String template_72 = "cbcc";

	static String template_73 = "ccaa";
	static String template_74 = "ccab";
	static String template_75 = "ccac";
	static String template_76 = "ccba";
	static String template_77 = "ccbb";
	static String template_78 = "ccbc";
	static String template_79 = "ccca";
	static String template_80 = "cccb";
	static String template_81 = "cccc";
	
	
	//archives to store all test cases
	private static int[][] solution_1 = new int[500][R];
	private static int[][] solution_2 = new int[500][R];
	private static int[][] solution_3 = new int[500][R];
	private static int[][] solution_4 = new int[500][R];
	private static int[][] solution_5 = new int[500][R];
	private static int[][] solution_6 = new int[500][R];
	private static int[][] solution_7 = new int[500][R];
	private static int[][] solution_8 = new int[500][R];
	private static int[][] solution_9 = new int[500][R];
	private static int[][] solution_10 = new int[500][R];
	private static int[][] solution_11 = new int[500][R];
	private static int[][] solution_12 = new int[500][R];
	private static int[][] solution_13 = new int[500][R];
	private static int[][] solution_14 = new int[500][R];
	private static int[][] solution_15 = new int[500][R];
	private static int[][] solution_16 = new int[500][R];
	private static int[][] solution_17 = new int[500][R];
	private static int[][] solution_18 = new int[500][R];
	private static int[][] solution_19 = new int[500][R];
	private static int[][] solution_20 = new int[500][R];
	private static int[][] solution_21 = new int[500][R];
	private static int[][] solution_22 = new int[500][R];
	private static int[][] solution_23 = new int[500][R];
	private static int[][] solution_24 = new int[500][R];
	private static int[][] solution_25 = new int[500][R];
	private static int[][] solution_26 = new int[500][R];
	private static int[][] solution_27 = new int[500][R];
	private static int[][] solution_28 = new int[500][R];
	private static int[][] solution_29 = new int[500][R];
	private static int[][] solution_30 = new int[500][R];
	private static int[][] solution_31 = new int[500][R];
	private static int[][] solution_32 = new int[500][R];
	private static int[][] solution_33 = new int[500][R];
	private static int[][] solution_34 = new int[500][R];
	private static int[][] solution_35 = new int[500][R];
	private static int[][] solution_36 = new int[500][R];
	private static int[][] solution_37 = new int[500][R];
	private static int[][] solution_38 = new int[500][R];
	private static int[][] solution_39 = new int[500][R];
	private static int[][] solution_40 = new int[500][R];
	private static int[][] solution_41 = new int[500][R];
	private static int[][] solution_42 = new int[500][R];
	private static int[][] solution_43 = new int[500][R];
	private static int[][] solution_44 = new int[500][R];
	private static int[][] solution_45 = new int[500][R];
	private static int[][] solution_46 = new int[500][R];
	private static int[][] solution_47 = new int[500][R];
	private static int[][] solution_48 = new int[500][R];
	private static int[][] solution_49 = new int[500][R];
	private static int[][] solution_50 = new int[500][R];
	private static int[][] solution_51 = new int[500][R];
	private static int[][] solution_52 = new int[500][R];
	private static int[][] solution_53 = new int[500][R];
	private static int[][] solution_54 = new int[500][R];
	private static int[][] solution_55 = new int[500][R];
	private static int[][] solution_56 = new int[500][R];
	private static int[][] solution_57 = new int[500][R];
	private static int[][] solution_58 = new int[500][R];
	private static int[][] solution_59 = new int[500][R];
	private static int[][] solution_60 = new int[500][R];
	private static int[][] solution_61 = new int[500][R];
	private static int[][] solution_62 = new int[500][R];
	private static int[][] solution_63 = new int[500][R];
	private static int[][] solution_64 = new int[500][R];
	//static LinkedHashMap<String, Boolean> coverage_status_2 = new LinkedHashMap<String, Boolean>();

	public static Map<String, int[][]> dynamic_access = new HashMap<String, int[][]>();
	
	
	
	
	//List<String> parents_population_copy   = new ArrayList<String>();
	//groups stotre the paths covered...//maybe it is unnecessary
	
	/*static List<String> group_1 = new ArrayList<String>();
	static List<String> group_2 = new ArrayList<String>();
	static List<String> group_3 = new ArrayList<String>();
	static List<String> group_4 = new ArrayList<String>();
	static List<String> group_5 = new ArrayList<String>();
	static List<String> group_6 = new ArrayList<String>();
	static List<String> group_7 = new ArrayList<String>();
	static List<String> group_8 = new ArrayList<String>();
	static List<String> group_9 = new ArrayList<String>();
	static List<String> group_10 = new ArrayList<String>();
	static List<String> group_11 = new ArrayList<String>();
	static List<String> group_12 = new ArrayList<String>();
	static List<String> group_13 = new ArrayList<String>();
	static List<String> group_14 = new ArrayList<String>();
	static List<String> group_15 = new ArrayList<String>();
	static List<String> group_16 = new ArrayList<String>();
	static List<String> group_17 = new ArrayList<String>();
	static List<String> group_18 = new ArrayList<String>();
	static List<String> group_19 = new ArrayList<String>();
	static List<String> group_20 = new ArrayList<String>();
	static List<String> group_21 = new ArrayList<String>();
	static List<String> group_22 = new ArrayList<String>();
	static List<String> group_23 = new ArrayList<String>();
	static List<String> group_24 = new ArrayList<String>();
	static List<String> group_25 = new ArrayList<String>();
	static List<String> group_26 = new ArrayList<String>();
	static List<String> group_27 = new ArrayList<String>();
	static List<String> group_28 = new ArrayList<String>();
	static List<String> group_29 = new ArrayList<String>();
	static List<String> group_30 = new ArrayList<String>();
	static List<String> group_31 = new ArrayList<String>();
	static List<String> group_32 = new ArrayList<String>();
	static List<String> group_33 = new ArrayList<String>();
	static List<String> group_34 = new ArrayList<String>();
	static List<String> group_35 = new ArrayList<String>();
	static List<String> group_36 = new ArrayList<String>();
	static List<String> group_37 = new ArrayList<String>();
	static List<String> group_38 = new ArrayList<String>();
	static List<String> group_39 = new ArrayList<String>();
	static List<String> group_40 = new ArrayList<String>();
	static List<String> group_41 = new ArrayList<String>();
	static List<String> group_42 = new ArrayList<String>();
	static List<String> group_43 = new ArrayList<String>();
	static List<String> group_44 = new ArrayList<String>();
	static List<String> group_45 = new ArrayList<String>();
	static List<String> group_46 = new ArrayList<String>();
	static List<String> group_47 = new ArrayList<String>();
	static List<String> group_48 = new ArrayList<String>();
	static List<String> group_49 = new ArrayList<String>();
	static List<String> group_50 = new ArrayList<String>();
	static List<String> group_51 = new ArrayList<String>();
	static List<String> group_52 = new ArrayList<String>();
	static List<String> group_53 = new ArrayList<String>();
	static List<String> group_54 = new ArrayList<String>();
	static List<String> group_55 = new ArrayList<String>();
	static List<String> group_56 = new ArrayList<String>();
	static List<String> group_57 = new ArrayList<String>();
	static List<String> group_58 = new ArrayList<String>();
	static List<String> group_59 = new ArrayList<String>();
	static List<String> group_60 = new ArrayList<String>();
	static List<String> group_61 = new ArrayList<String>();
	static List<String> group_62 = new ArrayList<String>();
	static List<String> group_63 = new ArrayList<String>();
	static List<String> group_64 = new ArrayList<String>(); */
	
	
	
	public static int obj_total;  //counts  paths covered
	
	//counts paths covered per group
	
	public static int group_1_count;
	public static int group_2_count;
	public static int group_3_count;
	public static int group_4_count;
	public static int group_5_count;
	public static int group_6_count;
	public static int group_7_count;
	public static int group_8_count;
	public static int group_9_count;
	
	public static int group_10_count;
	public static int group_11_count;
	public static int group_12_count;
	public static int group_13_count;
	public static int group_14_count;
	public static int group_15_count;
	public static int group_16_count;
	public static int group_17_count;
	public static int group_18_count;
	
	public static int pick_counter_1;  
	public static int group_19_count;
	public static int group_20_count;
	public static int group_21_count;
	public static int group_22_count;
	public static int group_23_count;
	public static int group_24_count;
	public static int group_25_count;
	public static int group_26_count;
	public static int group_27_count;
	
	
	
	public static int group_28_count;
	public static int group_29_count;
	public static int group_30_count;
	public static int group_31_count;
	public static int group_32_count;
	public static int group_33_count;
	public static int group_34_count;
	public static int group_35_count;
	public static int group_36_count;
	
	public static int group_37_count;
	public static int group_38_count;
	public static int group_39_count;
	public static int group_40_count;
	public static int group_41_count;
	public static int group_42_count;
	public static int group_43_count;
	public static int group_44_count;
	public static int group_45_count;
	
	public static int group_46_count;
	public static int group_47_count;
	public static int group_48_count;
	public static int group_49_count;
	public static int group_50_count;
	public static int group_51_count;
	public static int group_52_count;
	public static int group_53_count;
	public static int group_54_count;
	
	public static int group_55_count;
	public static int group_56_count;
	public static int group_57_count;
	public static int group_58_count;
	public static int group_59_count;
	public static int group_60_count;
	public static int group_61_count;
	public static int group_62_count;
	public static int group_63_count;
	public static int group_64_count;
	
	public static int crossover_count = 0;
//	static int id = 0;

	@SuppressWarnings("unlikely-arg-type")
	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
		// TODO Auto-generated method stub

		// System.out.println("what");
		int k = 7;

		char[] set = { 'a', 'b', 'c' };
		char[] str = new char[k];
  
		enumeratePaths(k, set, str, 0); // call function to generate the paths
	    back_Up();// function to back up set of paths
						// information
		 

		int[] lb = new int[R];// lower bound
		int[] ub = new int[R];// upper bound

		for (int j = 0; j < R; j++) { // the sample range of integers is between 1 and 100
			lb[j] = -1000; // lower bound
			ub[j] = 1000; // upper bound

		}

		for (int run = 0; run < RUN; run++) {

			int[][] x = new int[pop_num][R];
			int[][] v = new int[pop_num][R];
			 //id = 0;
			
			//paths.put(new String(str) , false);
			obj_total = 0;
			 group_1_count = 0;
		     group_2_count = 0;
			 group_3_count = 0;
			 group_4_count = 0;
			 group_5_count = 0;
			 group_6_count = 0;
			 group_7_count = 0;
			 group_8_count = 0;
			 group_9_count = 0;
			
			 group_10_count = 0;
			 group_11_count = 0;
			 group_12_count = 0;
			 group_13_count = 0;
			 group_14_count = 0;
			 group_15_count = 0;
			 group_16_count = 0;
			 group_17_count = 0;
			 group_18_count = 0;
			
			
			// pick_counter_1 = 0;
			 
			 group_19_count = 0;
			 group_20_count = 0;
			 group_21_count = 0;
			 group_22_count = 0;
			 group_23_count = 0;
			 group_24_count = 0;
			 group_25_count = 0;
			 group_26_count = 0;
			 group_27_count = 0;
			
			 group_28_count = 0;
			 group_29_count = 0;
			 group_30_count = 0;
			 group_31_count = 0;
			 group_32_count = 0;
			 group_33_count = 0;
			 group_34_count = 0;
			 group_35_count = 0;
			 group_36_count = 0;
			
			 group_37_count = 0;
	         group_38_count = 0;
			 group_39_count = 0;
			 group_40_count = 0;
			 group_41_count = 0;
			 group_42_count = 0;
			 group_43_count = 0;
			 group_44_count = 0;
			 group_45_count = 0;
			
		     group_46_count = 0;
			 group_47_count = 0;
			 group_48_count = 0;
			 group_49_count = 0;
			 group_50_count = 0;
		     group_51_count = 0;
			 group_52_count = 0;
			 group_53_count = 0;
			 group_54_count = 0;
			
			 group_55_count = 0;
			 group_56_count = 0;
			 group_57_count = 0;
			 group_58_count = 0;
			 group_59_count = 0;
			 group_60_count = 0;
			 group_61_count = 0;
		     group_62_count = 0;
			 group_63_count = 0;
		     group_64_count = 0;
			
		        solution_1 = new int[500][R];
				solution_2 = new int[500][R];
				solution_3 = new int[500][R];
				solution_4 = new int[500][R];
				solution_5 = new int[500][R];
				solution_6 = new int[500][R];
				solution_7 = new int[500][R];
				solution_8 = new int[500][R];
				solution_9 = new int[500][R];
				solution_10 = new int[500][R];
				solution_11 = new int[500][R];
				solution_12 = new int[500][R];
				solution_13 = new int[500][R];
				solution_14 = new int[500][R];
				solution_15 = new int[500][R];
				solution_16 = new int[500][R];
				solution_17 = new int[500][R];
				solution_18 = new int[500][R];
				solution_19 = new int[500][R];
				solution_20 = new int[500][R];
				solution_21 = new int[500][R];
				solution_22 = new int[500][R];
				solution_23 = new int[500][R];
				solution_24 = new int[500][R];
				solution_25 = new int[500][R];
				solution_26 = new int[500][R];
				solution_27 = new int[500][R];
				solution_28 = new int[500][R];
				solution_29 = new int[500][R];
				solution_30 = new int[500][R];
				solution_31 = new int[500][R];
				solution_32 = new int[500][R];
				solution_33 = new int[500][R];

				solution_34 = new int[500][R];
				solution_35 = new int[500][R];
				solution_36 = new int[500][R];
				solution_37 = new int[500][R];
				solution_38 = new int[500][R];
				solution_39 = new int[500][R];
				solution_40 = new int[500][R];
				solution_41 = new int[500][R];
				solution_42 = new int[500][R];
				solution_43 = new int[500][R];
				solution_44 = new int[500][R];
				solution_45 = new int[500][R];
				solution_46 = new int[500][R];
				solution_47 = new int[500][R];
				solution_48 = new int[500][R];
				solution_49 = new int[500][R];
				solution_50 = new int[500][R];
				solution_51 = new int[500][R];
				solution_52 = new int[500][R];
				solution_53 = new int[500][R];
				solution_54 = new int[500][R];
				solution_55 = new int[500][R];
				solution_56 = new int[500][R];
				solution_57 = new int[500][R];
				solution_58 = new int[500][R];
				solution_59 = new int[500][R];
				solution_60 = new int[500][R];
				solution_61 = new int[500][R];
				solution_62 = new int[500][R];
				solution_63 = new int[500][R];
				solution_64 = new int[500][R];
				
			
		    crossover_count = 0;
            boolean  statusChecker =false;
			LinkedHashMap<String, List<Double>> parent_maps = new LinkedHashMap<String, List<Double>>();
			
			LinkedHashMap<String, List<Double>> offspring_maps = new LinkedHashMap<String, List<Double>>();
			
			LinkedHashMap<String, List<Double>> combined_maps = new LinkedHashMap<String, List<Double>>();
		//	LinkedHashMap<String, List<Double>> fitnessmaps = new LinkedHashMap<String, List<Double>>();

			LinkedHashMap<String, Integer> setrank1 = new LinkedHashMap<String, Integer>();
		

			List<String> updated_population = new ArrayList<String>();
			
			List<String> combined_population = new ArrayList<String>();
			List<String> offspring_population = new ArrayList<String>();
			//List<String> parent_population = new ArrayList<String>();
			List<String> single_population = new ArrayList<String>();

			List<String> combined_population_list = new ArrayList<String>();
			
			List<String> temporary_path_holder = new ArrayList<String>();
			
			List<String> parents_population   = new ArrayList<String>();
			
			List<String> parents_population_copy   = new ArrayList<String>();
			
		//	String traverse_template;

			// the 2d arrays below hold the solutions to each respective group
			int [][] solution = new  int[500][R];
				
				
		//	boolean [] statu = new boolean [PATHNUM];
				

			int totalpathcounter = 0;

			int obj_total = 0;

			String getfinalpath = null;
			//String getfinalpath;

			if (run > 0) { // in each run reset the number counter of the paths covered in each group

				reset_All();
				paths.clear();
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
				paths.putAll(temporay_paths); 
			}

			for (int i = 0; i < pop_num; i++) // initialize the population with the dimension of R values/inputs
			{

				for (int j = 0; j < R; j++) {
				
					x[i][j]  = (int)(Math.random()*((ub[j]  - lb[j])+1))+ lb[j];

				}  //initial random population

				getfinalpath = pathnum(x[i]); 
				//archiving( String check_path_group , int num, int[][] offspring)
                int pickPaths= 0;
				
				Set<String> keyz = paths.keySet();
				
				
				for (String key : keyz) {

					if (pickPaths < 50) {  //pick subset of N paths
						
						target_subset.put(key + " " + "new", false);
						
						pickPaths++;
					}
					else {
						break;
					}
					
				}

				parents_population.add("parent" + " " + i);
				//record covered path and find group where it belongs
				//save test case in corresponding path group
				archiving( getfinalpath , i, x);
				
			}

			parents_population_copy.addAll(parents_population);
			
			combined_population_list.addAll(parents_population_copy);	
			///offspring_population.addAll(parents_population_copy);
			//Cycle[run] = 1;

				long start_time = System.currentTimeMillis();
				long wait_time = 1518750;
				long end_time = start_time + wait_time;
				List<Double> temp_2 = new ArrayList<Double>();
				
			  //  int generation = 0;
				
				while ((System.currentTimeMillis() < end_time) && obj_total < PATHNUM_NUMBER) // not exceeded the set

				{
				//LinkedHashMap<String, List<Double>> parent_maps_init = new LinkedHashMap<String, List<Double>>();   
				//initial random population tournament selection
			
					//List<Double> temp = new ArrayList<Double>();
					if (crossover_count == 0) {
						
					Random       random    = new Random();
			
					
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
					     
					
					    // if ()
					
				    		Random  random    = new Random();
							
							//	LinkedHashMap<String, Boolean> changed_status = new LinkedHashMap<String, Boolean>();
							//	LinkedHashMap<String, Boolean> child_status   = new LinkedHashMap<String, Boolean>();
								
				    		parents_population_copy.clear();
				    		for (int i = 0; i < 50; i++) {
								
								
								parents_population_copy.add( "parent" + " " + i);
								
								
							}
							
				    		
				    		
								for (int i = 0; i < 25; i++) {
							
									String randomKey_1 =   parents_population_copy.get( random.nextInt(parents_population_copy.size())) ;
									
									parents_population_copy.remove(randomKey_1);
									
									String randomKey_2 =   parents_population_copy.get( random.nextInt(parents_population_copy.size())) ;
									
									parents_population_copy.remove(randomKey_2);
									//access parent via parent_maps_init
									
									//split the string
									
									String [] parent_1_string = randomKey_1.split(" ");	
									
									
									String par_1;

									int pos_1;
									
									
									par_1 = parent_1_string[0];
									pos_1 = Integer.parseInt(parent_1_string[1]);
									
			                     //  String [] parent_2_string = randomKey_2.split(" ");	
									
									
									String par_2;

									int pos_2;
									
									String [] parent_2_string = randomKey_2.split(" ");
									par_2 = parent_2_string[0];
									pos_2 = Integer.parseInt(parent_2_string[1]);
									
								
									//crossover nbased on probability
									Random rand = new Random();
									double rdm  = Math.random();		   
									    
									 if(rdm < 0.75) {
									
										//single-point crossover
									    	
									    	//randomly find a position nad exchange the tails
										 
										int parent_1_random = rand.nextInt(R);
									    	
									    int single_point = parent_1_random; 
									    //exchange elements with parent_2
									    
									    //	int[][] temporal = new int[pop_num][R];
									  
									    for (int j = 0 ; j < R; j++) {
								    		
								    		
									    	v[pos_1][j] =  x[pos_1][j];
									    	v[pos_2][j] =  x[pos_2][j];
								    		
								    	}
								    	
									    int s = single_point;
									   
									    while (s <= R) {
								    		
								    		
								    		//switch the tails of parent one and parent two
									    	v[pos_1][s] =   x[pos_2][s];
									    	v[pos_2][s] =   x[pos_1][s];

								    	   s++;
								    	}
									    
							    	    Random mut = new Random();
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
			
					   
						
						for (int i_ = 0; i_ <  offspring_population.size(); i_++) {// initialize the population with the dimension of R values/inputs
					
					    String [] get_index = offspring_population.get(i_).split("");	
							
					    String off_1;

						int index_1;
						
						
						off_1 = get_index[0];
						index_1 = Integer.parseInt(get_index[1]);	
							
					    
						getfinalpath = pathnum(v[index_1]);   //get path traversed by offspring test case 
						
						archiving( getfinalpath , i_, v);//add test case to archive
						
						
					
					    //match the paths traversed
						
						
						}
					
				}
				}	
					crossover_count++;
				
					//combined_population_list.addAll(parents_population_copy);
					
					
					for (int i = 0; i < offspring_population.size(); i++) // add all offpsings to parent and form final combined population
					{
			
							combined_population_list.add(offspring_population.get(i));
				    }
						
					List<String> final_combined_population = new ArrayList<String>();

					// temp.clear();

					final_combined_population.addAll(combined_population_list);	
					
				//fit.add(target_path_+ " " + path_status +" " + nonsimple_unmatched2);
				//evaluate parent and child population
					ArrayList <String> offspring_fitness = new ArrayList<String>();
					
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
					     	check_fitness.put( "parent" + " " + typeId  , offspring_fitness);
					     	
					     	//static LinkedHashMap<String,  ArrayList<String>> check_fitness = new LinkedHashMap<String, ArrayList<String>>();
					     		
							}
					   
						else {
//							
							//fit.add(target_path_+ " " + path_status +" " + nonsimple_unmatched2);
							
							offspring_fitness = benchmarkfunction(v[typeId]);
							check_fitness.put( "child" + " " + typeId  , offspring_fitness);
							
						
							
						}
						
					
					}
					
					int colum_sizes = 0;
					
					for ( String get_keys : check_fitness.keySet() ) {	
						
						
							colum_sizes = ( check_fitness.get(get_keys).size());
							
							
							break;
					}
					//process remove_objective_routine
					//process map first
					//static LinkedHashMap<String,  ArrayList<String>> check_fitness = new LinkedHashMap<String, ArrayList<String>>();
					List<String> average_calculator = new ArrayList<String>();
					
				//	int offspring_fitness_size = offspring_fitness.size();
				
				//	Iterator<Map.Entry<String, ArrayList<String>>> iteration_ = check_fitness.entrySet()
							//.iterator();
					String actualpath = "";
					String process_1 = "";
					String process_2 = "";
					
					//String updated_value_2 = "";
					//"child" + " " + typeId  , offspring_fitness
					//String updated_value = "";
					
//					fit.add(target_path_+ " " + path_status +" " + nonsimple_unmatched2);
					
					
					
			int pid = 0;		
					
			for (int i = 0 ; i < colum_sizes; i++ )	{
					
				double add_objective_scores = 0.0;
				double sum_at_column = 0.0;

				for ( String get_keys : check_fitness.keySet() ) {	
					
					
                       // int pid  = 0;
                        
						//Map.Entry<String, ArrayList<String>> entry = iteration_.next();

						average_calculator = check_fitness.get(get_keys);								
								//entry.getValue();
						
						String get_objective_score = average_calculator.get(i);
			                  
			                  //calculate average fitness
			                  
						//	updated_population.add(entry.getKey());
						
						//50 is the number of test cases
						//while (pid < 50) {
						
						///String checkString = final_combined_population.get(i);	
	                      
	                        String [] getDetails = get_objective_score.split(" ");
						    actualpath  =  getDetails[0];
                            double objective_scores =  Double.parseDouble(getDetails[1]);
                           
                            add_objective_scores =   add_objective_scores + objective_scores ;
                            
                            //total fitness at colum i
                            sum_at_column =  (add_objective_scores / check_fitness.size());
                            //get total fitness 
						
					}
					
					//int old_value = remove_objective[actualpath]
					//if(remove_objective.contiansKey(actualpath))
					
			
					for ( String entry_1 : target_subset.keySet() ) {	//update path status
						
					
						if (entry_1.equals(actualpath)) {
							
							
							 String [] processPath =   actualpath.split(" ");
							 process_1  =  processPath[0];
							 process_2  =  processPath[1];
							 
	                         //Double objective_scores =  Double.parseDouble(getDetails[1]);
							
							if (process_2.equals("new")) {
								
								
								target_subset.put(process_1 + " " + "old" ,  false );
								
								
									remove_objective.put( actualpath, sum_at_column);
										}
							
							
							else {
								
								//remove_objective.put( actualpath,  max_1 + " " +  add_objective_scores );
								
								double previous_value = remove_objective.get(actualpath);
								
								//double get_First =  Double.parseDouble(get_Path[0]);
								
								//double get_Second =  Double.parseDouble(get_Path[1]);
								
								
								if ( previous_value  > sum_at_column ) {
									
									remove_objective.put( actualpath, sum_at_column);
								}
								
								
								else {
									
								
									// Update status to true
									
									temporary_path_holder.add(actualpath); 
									
									paths.put(actualpath, true);
									
									target_subset.remove(actualpath);
									
									
									//
								}}
					}
					
					}
					
			}	
			
			
			
			ArrayList<String> remove_objectives_1 = new ArrayList<String>();
			
			for ( String entry_2 : check_fitness.keySet() ) {	//process test case // update their fitness vectors by deleting the removed objectives
				
			
				String [] process_to_remove = entry_2.split(" ");
				
				String processes_1  =   process_to_remove[0];
				int index_2  =   Integer.parseInt(process_to_remove[1]);
				
				remove_objectives_1 = check_fitness.get(entry_2);
				
				for (int i = 0; i < remove_objectives_1.size(); i++) // process the fitness vector and delete the worst values
				{
					//fit.add(target_path_+ " " + path_status +" " + nonsimple_unmatched2);
					
					//target_subset.remove(actualpath);
					//temporary_path_holder.add(actualpath); 
					
					String [] process_paths_to_remove = remove_objectives_1.get(i).split(" ");
					
					String process_path_1  =   process_paths_to_remove[0];
					
					if (temporary_path_holder.contains(process_path_1) ) {
						
						remove_objectives_1.remove(process_path_1);
						
					}
					
				}
				
				check_fitness.put( processes_1 + " " + index_2, remove_objectives_1);
				
				remove_objectives_1.clear( );
			}
			
			
			//target_subset.put(process_1 + " " + "old" ,  false );
			
			        Set<String> remainingPath = target_subset.keySet();
			
			
					for (String keyss : remainingPath) {
			
			
						 String [] get_Remaining = keyss.split(" ");
						
						 String remaining_1  =    get_Remaining[0];
						
						 still_remaining_targets.add(remaining_1);  //back-up set of remaining targets
						
					}
					
		       
					
				if (target_subset.size()!=0)	{
					
					try {
						if ((obj_total) == PATHNUM_NUMBER ) {  // if all paths covered break out of while loop
						
							break;
						}
						
				    if ((obj_total) < PATHNUM_NUMBER ) { // if not all paths covered perform
							
				        if ((target_subset.size() == 1)) { 
								
				        	
				        	  LinkedHashMap<String,  ArrayList<Double>> updated_maps_ = new LinkedHashMap<String, ArrayList<Double>>();
				            	
					            ArrayList<String> updated_fit_ = new ArrayList<String>();	
					            
					            ArrayList<Double> updated_1 = new ArrayList<Double>();
					            	
					        	for ( String key_10 : check_fitness.keySet() ) {
							 		
							 		
					        		updated_fit_  = check_fitness.get(key_10);
					        		
					        		
					        		for (int r= 0; r < updated_fit_.size(); r++) {
					        		
					        	
									 String [] get_list = updated_fit_.get(r).split(" ");
									
									 double objective_score  =    Double.parseDouble(get_list[2]);
									 
									 updated_1.add(objective_score);
					        		}
					        		
					        		updated_maps_.put(key_10, updated_1);
					        		
					        		updated_fit_.clear();
					        		
					        		updated_1.clear();
					        	
								}	
				        	
				        	
				        	int counter = 0;
							while (counter <  50) {
								
								 List<Double> value_holder = new ArrayList<Double>();
								 List<String> keys = new ArrayList<String>(updated_maps_.keySet());
								 
								 
								 Random rand = new Random();
								 
								 String key_ss = keys.get(rand.nextInt(keys.size()));
								 String key_holder = null;
								 value_holder.addAll(updated_maps_.get(key_ss));
								 
								 
								 
								 double min =  value_holder.get(0);
								 
								 value_holder.clear();
								 
								 for (int i = 1 ; i < 10; i++) {
									 
								    String key = keys.get(rand.nextInt(keys.size()));
								      
								    value_holder.addAll(updated_maps_.get(key));
								    
								    double checker = value_holder.get(0);
								        
								      if (checker < min) {
						                   min = checker;
						                   key_holder = key;
						                
						            }
								      value_holder.clear();
								}
								
								 
								 parents_population_copy.add(key_holder);
								
							} 
				        	
				        	
					
					     statusChecker = true;
				     }//if only one uncovered path
						
				       else {  
					            if (target_subset.size() > 1) { //if many remaining uncovered paths
						
					            LinkedHashMap<String,  ArrayList<Double>> updated_maps = new LinkedHashMap<String, ArrayList<Double>>();
					            	
					            ArrayList<String> updated_fit = new ArrayList<String>();	
					            
					            ArrayList<Double> updated_ = new ArrayList<Double>();
					            	
					        	for ( String key_10 : check_fitness.keySet() ) {
							 		
							 		
					        		updated_fit  = check_fitness.get(key_10);
					        		
					        		
					        		for (int r= 0; r < updated_fit.size(); r++) {
					        		
					        	
									 String [] get_list = updated_fit.get(r).split(" ");
									
									 double objective_score  =    Double.parseDouble(get_list[2]);
									 
									 updated_.add(objective_score);
					        		}
					        		
					        		updated_maps.put(key_10, updated_);
					        		
					        		updated_fit.clear();
					        		
					        		updated_.clear();
					        		
					        		
								}	
					            	
					            	
					        //updated_maps.put(key_10, updated_);  perform preference sorting on this map
					        	
					        	int updated_sizes = 0;
								
								for ( String get_keys : updated_maps.keySet() ) {	
									  updated_sizes  = ( updated_maps.get(get_keys).size());
										break;
								} 	
					      
								

							Double[][] offspring_fitness_full = new Double[final_combined_population
									.size()][updated_sizes];   // n x m matrix of combined parents and offspring test cases
							
							int updates = 0;

							// List<String> traverse_group = new ArrayList<String>();
							
							//List<String> best_column = new ArrayList<String>();
							int fitss = 0;
							
							while (updates < final_combined_population.size()) {

								List<Double> fitness_objectives_updated = new ArrayList<Double>();
								// fitness_objectives_updated.clear();

								fitness_objectives_updated = (updated_maps.get(final_combined_population.get(updates)));
								

								List<Double> temp_3 = new ArrayList<Double>();

								// temp.clear();

								temp_3.addAll(fitness_objectives_updated);

								
								for (int ups = 0; ups < temp_3.size(); ups++) {

									offspring_fitness_full[updates][ups] = fitness_objectives_updated.get(ups);

								}
								fitness_objectives_updated.clear();
								updates++;

							}
							
					
					    	//check if paths is covered , plus check replaced status
			             	// average objective score calculation.....
							// removing objectives
							// check current generation vs previous generation
			             	// update replaced status in main set of uncovered paths
			            
			             	
			             	// update population by replacing the other targets
			             	
			             	
							
							List<String> best_column = new ArrayList<String>();
				
							int sizes = temp_2.size();

							//double row_fit;
							int colums = 0;
							String test_case;
							double fit_rowfit;
					
							LinkedHashMap<String, Double> row_getter = new LinkedHashMap<String, Double>();
							
							while (colums < sizes) {

								for (int get_row = 0; get_row < (final_combined_population.size()); get_row++) {

									fit_rowfit = (offspring_fitness_full[get_row][colums]);

									double row_fitter = fit_rowfit;

									row_getter.put(final_combined_population.get(get_row), row_fitter);

								}

								Map<String, Double> result = row_getter.entrySet().stream()   //get test case with minimum objective score for each objective
										.sorted(Map.Entry.comparingByValue())
										.collect(toMap(Map.Entry::getKey, Map.Entry::getValue,
												(oldValue, newValue) -> oldValue, LinkedHashMap::new));
								// System.out.println("Map yino" +result );

								Iterator<Map.Entry<String, Double>> best_col = result.entrySet().iterator();

								while (best_col.hasNext()) {

									Map.Entry<String, Double> entry = best_col.next();

									test_case = entry.getKey();

									if (!best_column.contains(test_case)) {

										best_column.add(test_case);

										break;
									}

								}

								result.clear();
								row_getter.clear();
								colums++;

							}
                            
							//Go straight to computing crowding distance for Font 0
							
							int distance_zero;

							/************************************************************************************************************************************************************/

							double[][] distance_matrix_zero = new double[best_column.size()][best_column.size()];

							if ((best_column.size()) > 1) {

								for (int best = 0; best < (best_column.size()); best++) {

									List<Double> front_0_1 = new ArrayList<Double>();

									front_0_1 = updated_maps.get(best_column.get(best));

									for (int ind_d = 0; ind_d < best_column.size(); ind_d++) {

										if (!((best_column.get(best).equals(best_column.get(ind_d))))) {

											List<Double> front_0_2 = new ArrayList<Double>();

											front_0_2 = updated_maps.get(best_column.get(ind_d));

											distance_zero = get_count(front_0_1, front_0_2);

											int temp_dis = distance_zero;

											distance_matrix_zero[best][ind_d] = temp_dis;

										}

									}

								}

								LinkedHashMap<String, Double> setdistance_zero_2 = new LinkedHashMap<String, Double>();

								for (int font_ = 0; font_ < best_column.size(); font_++) {

									double zeros = 0.0;

									setdistance_zero_2.put(best_column.get(font_), zeros);

								}

								for (int font_i = 0; font_i < (best_column.size()); font_i++) {

									for (int font_j = 0; font_j < best_column.size(); font_j++) {

										if (!((best_column.get(font_i).equals(best_column.get(font_j))))) {

											if (setdistance_zero_2.get(best_column
													.get(font_i)) < distance_matrix_zero[font_i][font_j]) {

												double dis = (distance_matrix_zero[font_i][font_j]);

												setdistance_zero_2.put(best_column.get(font_i), dis);

											}
										}
									}

								}

								Map<String, Double> result = setdistance_zero_2.entrySet().stream()
										.sorted(Map.Entry.comparingByValue())
										.collect(toMap(Map.Entry::getKey, Map.Entry::getValue,
												(oldValue, newValue) -> oldValue, LinkedHashMap::new));
								
								for (Map.Entry<String, Double> entry : result.entrySet()) {

									if (updated_population.size() < pop_num) {  //updated_population  holds the selected population for next generation
										updated_population.add(entry.getKey());
									}

									else {

										break;
									}

								}

								result.clear();

							}
							
							
							//updated population has best performing test cases sorted in descending order
							
							else { // process the only item in the best set

								if (!best_column.isEmpty()) {

									String item_one = best_column.get(0);

									if (updated_population.size() < pop_num) {

										updated_population.add(item_one);
									}

								}
							}

							//Establish other fonts
							
							List<String> fitness_objectives_comparator = new ArrayList<String>();
							// List<Double> fitness_objectives_p = new ArrayList<Double>();
							// List<Double> fitness_objectives_q= new ArrayList<Double>();

							for (int next_front = 0; next_front < final_combined_population.size(); next_front++) {

								if (!best_column.contains(final_combined_population.get(next_front))) {

									fitness_objectives_comparator.add(final_combined_population.get(next_front));
								}
							}

						
						
							if ((fitness_objectives_comparator.size()) > 1) {

								int[] dominateMe = new int[fitness_objectives_comparator.size()];

								// front[i] contains the list of individuals belonging to the front i
								@SuppressWarnings("unchecked")
								List<Integer>[] front = new List[(fitness_objectives_comparator.size() + 1)];

								// iDominate[k] contains the list of solutions dominated by k

								@SuppressWarnings("unchecked")
								List<Integer>[] iDominate = new List[fitness_objectives_comparator.size()];

								int flagDominate;

								for (int i = 0; i < ((front.length)); i++) {
									front[i] = new LinkedList<Integer>();
								}

								for (int p = 0; p < fitness_objectives_comparator.size(); p++) {
									// Initialize the list of individuals that i dominate and the number
									// of individuals that dominate me
									iDominate[p] = new LinkedList<Integer>();
									dominateMe[p] = 0;
								}

								// int secondary_size =0 ;
								int N = fitness_objectives_comparator.size();
								// int[][] dominanceChecks = new int[N][N];

								for (int p = 0; p < (N - 1); p++) {

									List<Double> fitness_temporary_p = new ArrayList<Double>();

									fitness_temporary_p = updated_maps.get((fitness_objectives_comparator.get(p)));

									// secondary_size = fitness_temporary_p.size();

									for (int q = p + 1; q < N; q++) {
										// if (p != q) {
										List<Double> fitness_temporary_q = new ArrayList<Double>();

										fitness_temporary_q = updated_maps
												.get((fitness_objectives_comparator.get(q)));

										flagDominate = frontdominace_Comparison(fitness_temporary_p,
												fitness_temporary_q);

										if (flagDominate == -1) {
											// System.out.println("p---dominates>>>>>" );
											iDominate[p].add(q);
											dominateMe[q]++;

										}

										else if (flagDominate == 1) {
											// System.out.println("q---dominates>>>>>" );
											iDominate[q].add(p);
											dominateMe[p]++;
										}

										else {

											List<Double> fitness_temporary_qq = new ArrayList<Double>();
											List<Double> fitness_temporary_pp = new ArrayList<Double>();

											Collections.sort(fitness_temporary_p);

											fitness_temporary_pp.addAll(fitness_temporary_p);

											Collections.sort(fitness_temporary_q);

											fitness_temporary_qq.addAll(fitness_temporary_q);

											if (fitness_temporary_pp.get(0) < fitness_temporary_qq.get(0)) {
												// System.out.println("p---dominates>>>>>" );
												iDominate[p].add(q);
												dominateMe[q]++;

											}

											else if (fitness_temporary_qq.get(0) < fitness_temporary_pp.get(0)) {
												// System.out.println("q---dominates>>>>>" );
												iDominate[q].add(p);
												dominateMe[p]++;
											}

										}

									}
								}

								for (int p = 0; p < ((fitness_objectives_comparator.size())); p++) {

									if (dominateMe[p] == 0) {
										front[0].add(p);

										// setrank.put(Integer.parseInt(fitness_objectives_comparator.get(p)), 1);
										int val = 0;
										setrank1.put(fitness_objectives_comparator.get(p), val);

									}
								}

/*****************************************************************************************************************************************************************************************/

								int font_s = 0;
								// if((front[font_s].size() != 0)) {

								Iterator<Integer> it1, it2; // Iterators
								while (front[font_s].size() != 0) {
									/// System.out.println("we got here" + front[font_s]);
									font_s++;
									it1 = front[font_s - 1].iterator();
									while (it1.hasNext()) {
										it2 = iDominate[(it1.next())].iterator();
										while (it2.hasNext()) {
											int index = it2.next();
											dominateMe[index]--;
											if (dominateMe[index] == 0) {
												front[font_s].add(index);
												// System.out.println("Block_1: " +front[font_s]);
												setrank1.put(fitness_objectives_comparator.get(index), font_s);
											}
										}

									}

								}

								LinkedHashMap<String, Double> setdistance = new LinkedHashMap<String, Double>();

								LinkedHashMap<String, Double> setdistance_one = new LinkedHashMap<String, Double>();

								Map<Integer, ArrayList<String>> reverseMap = new HashMap<>();

								for (Map.Entry<String, Integer> entry : setrank1.entrySet()) {
									if (!reverseMap.containsKey(entry.getValue())) {
										reverseMap.put(entry.getValue(), new ArrayList<>());
									}
									ArrayList<String> keys = reverseMap.get(entry.getValue());
									keys.add(entry.getKey());
									reverseMap.put(entry.getValue(), keys);
								}

								// process fonts

								int smaller;
								int smaller_one;
							//	int remaining;
								// int smaller_two;

								int fronts = 0;
								// System.out.println("map's size" + reverseMap.size());
								while (fronts < (reverseMap.size())) {

									// if (updated_population.size()== pop_num) {
									// break;
									// }

									if (fronts != (reverseMap.size() - 1)) {

										List<String> front_list = new ArrayList<String>();

										front_list = reverseMap.get(fronts);

										if ((front_list.size()) > 1) {

											double[][] distance_matrix = new double[front_list.size()][front_list
													.size()];

											for (int ind_d = 0; ind_d < (front_list.size()); ind_d++) {

												List<Double> front_getter1 = new ArrayList<Double>();

												front_getter1 = updated_maps.get((front_list.get(ind_d)));

												for (int d = 0; d < front_list.size(); d++) {

													if (!(front_list.get(ind_d).equals(front_list.get(d)))) {

														List<Double> front_getter2 = new ArrayList<Double>();

														front_getter2 = updated_maps.get((front_list.get(d)));

														smaller = get_count(front_getter1, front_getter2);

														int sma = smaller;

														distance_matrix[ind_d][d] = sma;

													}

												}

											}

											for (int font_ = 0; font_ < front_list.size(); font_++) {

												double valz = 0.0;

												setdistance.put(front_list.get(font_), valz);

											}

											// all distances set

											for (int font_i = 0; font_i < (front_list.size()); font_i++) {

												// distance_matrix

												for (int font_j = 0; font_j < front_list.size(); font_j++) {

													if (!(front_list.get(font_i).equals(front_list.get(font_j)))) {

														if (setdistance.get(front_list
																.get(font_i)) < distance_matrix[font_i][font_j]) {

															double dist = (distance_matrix[font_i][font_j]);

															setdistance.put(front_list.get(font_i), dist);

														}
													}
												}

											}

											Map<String, Double> result = setdistance.entrySet().stream()
													.sorted(Map.Entry.comparingByValue())
													.collect(toMap(Map.Entry::getKey, Map.Entry::getValue,
															(oldValue, newValue) -> oldValue, LinkedHashMap::new));

											Iterator<Map.Entry<String, Double>> iteration = result.entrySet()
													.iterator();

											while (iteration.hasNext()) {

												Map.Entry<String, Double> entry = iteration.next();

												if (updated_population.size() < pop_num) {
													updated_population.add(entry.getKey());
												}

												else {

													break;
												}

											}

											result.clear();

										}

										else {

											String best_1;

											best_1 = front_list.get(0);

											if (updated_population.size() < (pop_num)) {

												updated_population.add(best_1);

											}

										}
									}

									if (fronts == (reverseMap.size() - 1)) {

										List<String> front_list_one = new ArrayList<String>();

										front_list_one = reverseMap.get(reverseMap.size() - 1);

										// System.out.println("hahahq" +front_list_one);

										double[][] distance_matrix_one = new double[front_list_one
												.size()][front_list_one.size()];

										for (int ind_w = 0; ind_w < (front_list_one.size()); ind_w++) {

											List<Double> front_getter_one = new ArrayList<Double>();

											front_getter_one = updated_maps.get((front_list_one.get(ind_w)));

											for (int dw = 0; dw < front_list_one.size(); dw++) {

												if (!(front_list_one.get(ind_w).equals(front_list_one.get(dw)))) {

													List<Double> front_getter_two = new ArrayList<Double>();

													front_getter_two = updated_maps.get((front_list_one.get(dw)));

													smaller_one = get_count(front_getter_one, front_getter_two);

													int sm = smaller_one;

													distance_matrix_one[ind_w][dw] = sm;

												}

											}
										}
										for (int font_ = 0; font_ < front_list_one.size(); font_++) {

											double dit = 0.0;

											setdistance_one.put(front_list_one.get(font_), dit);

										}

										// all distances set

										for (int font_i = 0; font_i < (front_list_one.size()); font_i++) {

											for (int font_j = 0; font_j < front_list_one.size(); font_j++) {

												if (!(front_list_one.get(font_i)
														.equals(front_list_one.get(font_j)))) {

													if (setdistance_one.get(front_list_one
															.get(font_i)) < distance_matrix_one[font_i][font_j]) {

														double dst = (distance_matrix_one[font_i][font_j]);

														setdistance_one.put(front_list_one.get(font_i), dst);

													}
												}
											}

										}

										int remaining_ = (pop_num - updated_population.size());

										if (setdistance_one.size() <= remaining_) {

											Map<String, Double> result = setdistance_one.entrySet().stream()
													.sorted(Map.Entry.comparingByValue())
													.collect(toMap(Map.Entry::getKey, Map.Entry::getValue,
															(oldValue, newValue) -> oldValue, LinkedHashMap::new));

											Iterator<Map.Entry<String, Double>> iteration = result.entrySet()
													.iterator();

											while (iteration.hasNext()) {

												Map.Entry<String, Double> entry = iteration.next();

												if (updated_population.size() < pop_num) {
													updated_population.add(entry.getKey());
												}

												else {
													break;
												}

											}

										}

										// System.out.println( "remaining---" +remaining);
										else {

											Map<String, Double> sortedByValueDesc = setdistance_one.entrySet()
													.stream()
													.sorted(Map.Entry.<String, Double>comparingByValue().reversed())
													.collect(toMap(Map.Entry::getKey, Map.Entry::getValue,
															(e1, e2) -> e1, LinkedHashMap::new));

											List<String> front_remove = new ArrayList<String>();

											Set<String> keytest = sortedByValueDesc.keySet();

											int final_size = sortedByValueDesc.size();

											// while(sortedByValueDesc.size() > remaining) {

											for (String keysss : keytest) {

												if (final_size > remaining_) {

													front_remove.add(keysss);
												}

												else {

													break;
												}
												final_size--;
											}

											// System.out.println( "sorted map after---" +sortedByValueDesc.size());
											Set<String> keytests = sortedByValueDesc.keySet();

											for (String key_sss : keytests) {

												if (updated_population.size() < pop_num) {

													if (!front_remove.contains(key_sss)) {

														updated_population.add(key_sss);
													}

												} else {
													break;
												}
											}

										}

									}

									setdistance.clear();
									setdistance_one.clear();

									fronts++;
								}

							} else {

								if (!fitness_objectives_comparator.isEmpty()) {

									if (updated_population.size() < pop_num) {

										String get_item = (fitness_objectives_comparator.get(0));

										updated_population.add(get_item);

									}
}
}
}}
							
						}
						
					     	
					}

						catch(IndexOutOfBoundsException e) {}

							//updated_population.clear();
							combined_population.clear();
							//combined_population2.clear();
							setrank1.clear();
							//setrank2.clear();
							//setrank3.clear();
							combined_maps.clear();
							//child_maps.clear();

						
                     Set<String> add_target = paths.keySet();
	             	
	             	//replaced_status_2.put(id + new String(str), false);
	             	int true_count = 0;
	             	// get status of removal
	            	for (String key_ss : add_target) { 
	             	
	             	//pick path whose status is false
	            		if ( paths.get(key_ss) ){
	            			
	            			 true_count++;
	            		}
	            		
	            		else {
	            			
	            			break;
	            		}
	             	    	 
	            	}  
	            	
	            	if ( true_count == paths.size() ) {
	            		
	            		//if all paths have true as replaced status
	            		
	            		//set them to false
	            		
	            		//pick subset of paths not previously removed at previous generation
	            	
	            		Set<String> add_new_entry = paths.keySet();
	 	             	
	 	             	//replaced_status_2.put(id + new String(str), false);
	 	             	int entry_count = 0;
	 	             	// get status of removal
	 	            	for (String enrty_ss : add_new_entry ) { 
	            		
	 	            		  paths.put(enrty_ss , false);
	 	            		
	 	            	}
	 	            	
	 	            	if (paths.size() > 50) {
	 	            	
	 	            	int counter_add_paths = 0;
	 	            		
                          Set<String> add_target_from_Path_1 = paths.keySet();
		             	
		             	//replaced_status_2.put(id + new String(str), false);
		             	
		             	// get status of removal
		          	      for (String from_Path_1: add_target_from_Path_1) {
	 	            	
	 	            	
	 	           	
	            		    if (still_remaining_targets.contains(from_Path_1) ) {
	          	    		
	          	    		           continue;
	          	    	    }
	            		
	            		  else {
	            			  
	            			  
	            			  if (counter_add_paths < 50) {
	            			
	            			  if ( !temporary_path_holder.contains(from_Path_1) ){
	  	          	    		
	            				  target_subset.put(from_Path_1 + " " + "new", false);
		          	    			
	            				  counter_add_paths++;
	            				  
	            				// retrieve test case from archive
		          	    			//updatePopulation(from_Path_1)..//return test case not previously added to temporary population
	            				  
		          	    		}
	            			
	            			  }
	            		
	            			
	            			
	            		  }
	            		    
		          	      
		          	      }
	 	            	}
		          	      
		          	      else {
		          	    	  
		          	    	  
		          	    	Set<String> add_target_from_Path_7 = paths.keySet();
			             	
			             	//replaced_status_2.put(id + new String(str), false);
			             	
			             	// get status of removal
			          	    for (String from_Path_7: add_target_from_Path_7) {  // contains(Object)
			             	
			             	     //get current path set..the paths already in subset should not be picked again....
			             		
			          	  	//	target_subset.put(key + " " + "new", false);	
			          	    	
			                if (still_remaining_targets.contains(from_Path_7) ) {
					          	    		
					          	    		continue;
					          	    	}
			                else {  
	          	    		if ( !temporary_path_holder.contains(from_Path_7)  ){
	          	    			
	          	    			target_subset.put(from_Path_7 + " " + "new", false);
	          	    			
	          	    			// retrieve test case from archive
	          	    		// retrieve test case from archive
	          	    			//updatePopulation(from_Path_7)..//return test case not previously added to temporary population
	          	    		}}
		          	    	  
			          	    }	  
		          	    	  
		          	    	  
		          	      }
	           }
	            		
	            	else {
	            		
	            		// pick paths with false status and add them to subset of targets
	            		
	            		int path_to_add_counter = 0;
	            		
	            	if (paths.size() > 50) {
	            		Set<String> add_target_from_Path = paths.keySet();
		             	
		             	//replaced_status_2.put(id + new String(str), false);
		             	
		             	// get status of removal
		          	    for (String from_Path: add_target_from_Path) {  // contains(Object)
		             	
		             	     //get current path set..the paths already in subset should not be picked again....
		             		
		          	  	//	target_subset.put(key + " " + "new", false);	
		          	    	
		          	    		if (still_remaining_targets.contains(from_Path) ) {
				          	    		
				          	    		continue;
				          	    	}
		          	    			else {	
		          	    		
		          	    		    if (path_to_add_counter < 50) {
		          	    		       
		          	    	         if ( !paths.get(from_Path)  && (!temporary_path_holder.contains(from_Path) ) ){
		          	    		
		          	    		    target_subset.put(from_Path + " " + "new", false);
		          	    			
		          	    			path_to_add_counter++;
		          	    			
		          	    			// retrieve test case from archive
		          	    		// retrieve test case from archive
		          	    			//updatePopulation(from_Path)..//return test case not previously added to temporary population
		          	    		     }
		          	    		     
		          	    		     
		          	    		}
		          	    			}
		          	    	}
	            	}
	            	
	            	
		          	    else {
		          	    		  
		          	    		Set<String> add_target_from_Path_4 = paths.keySet();
				             	
				             	//replaced_status_2.put(id + new String(str), false);
				             	
				             	// get status of removal
				          	    for (String from_Path_4: add_target_from_Path_4) {  // contains(Object)
				             	
				             	     //get current path set..the paths already in subset should not be picked again....
				             		
				          	  	//	target_subset.put(key + " " + "new", false);	
				          	    	
				         
				          	    			if (still_remaining_targets.contains(from_Path_4) ) {
						          	    		
						          	    		continue;
						          	    	}
		          	    		  
				          	    			else {
		          	    			
		          	    		if ( !paths.get(from_Path_4)  && (!temporary_path_holder.contains(from_Path_4) ) ){
		          	    			
		          	    			target_subset.put(from_Path_4 + " " + "new", false);
		          	    			
		          	    			// retrieve test case from archive
		          	    			//updatePopulation(from_Path_4)..//return test case not previously added to temporary population
		          	    		}}
		          	    			
		          	    			
		          	    			
		          	    		}
		          	    	}
		             	
	            	}
	            	
	             	
	            	parents_population_copy.clear();
	            	
	            	for (int i = 0; i < 50 ; i++) // updated population 
	    			{

	    				//for (int j = 0; j < R; j++) {
	    				
	    				//	x[i][j]  = (int)(Math.random()*((ub[j]  - lb[j])+1))+ lb[j];

	    				//update set x
	    				parents_population_copy.add("parent" + " " + i);
	            	
	            	
	            	
	            	
				}
	            	
				}
				
				else {  //proceed to add paths if subset of target objectives is empty
					
					
					
                     Set<String> add_target = paths.keySet();
	             	
	             	//replaced_status_2.put(id + new String(str), false);
	             	int true_count = 0;
	             	// get status of removal
	            	for (String key_ss : add_target) { 
	             	
	             	//pick path whose status is false
	            		if ( paths.get(key_ss) ){
	            			
	            			 true_count++;
	            		}
	            		
	            		else {
	            			
	            			break;
	            		}
	             	    	 
	            	}  
	            	
	            	if ( true_count == paths.size() ) {
	            		
	            		//if all paths have true as replaced status
	            		
	            		//set them to false
	            		
	            		//pick subset of paths not previously removed at previous generation
	            	
	            		Set<String> add_new_entry = paths.keySet();
	 	             	
	 	             	//replaced_status_2.put(id + new String(str), false);
	 	             	int entry_count = 0;
	 	             	// get status of removal
	 	            	for (String enrty_ss : add_new_entry ) { 
	            		
	 	            		  paths.put(enrty_ss , false);
	 	            		
	 	            	}
	 	            	
	 	            	if (paths.size() > 50) {
	 	            	
	 	            	int counter_add_paths = 0;
	 	            		
                          Set<String> add_target_from_Path_1 = paths.keySet();
		             	
		             	//replaced_status_2.put(id + new String(str), false);
		             	
		             	// get status of removal
		          	      for (String from_Path_1: add_target_from_Path_1) {
	 	            	
	 	            	
	 	           	
	            		    if (still_remaining_targets.contains(from_Path_1) ) {
	          	    		
	          	    		           continue;
	          	    	    }
	            		
	            		  else {
	            			  
	            			  
	            			  if (counter_add_paths < 50) {
	            			
	            			  if ( !temporary_path_holder.contains(from_Path_1) ){
	  	          	    		
	            				  target_subset.put(from_Path_1 + " " + "new", false);
		          	    			
	            				  counter_add_paths++;
	            				  
	            				// retrieve test case from archive
		          	    			//updatePopulation(from_Path_1)..//return test case not previously added to temporary population
	            				  
		          	    		}
	            			
	            			  }
	            		
	            			
	            			
	            		  }
	            		    
		          	      
		          	      }
	 	            	}
		          	      
		          	      else {
		          	    	  
		          	    	  
		          	    	Set<String> add_target_from_Path_7 = paths.keySet();
			             	
			             	//replaced_status_2.put(id + new String(str), false);
			             	
			             	// get status of removal
			          	    for (String from_Path_7: add_target_from_Path_7) {  // contains(Object)
			             	
			             	     //get current path set..the paths already in subset should not be picked again....
			             		
			          	  	//	target_subset.put(key + " " + "new", false);	
			          	    	
			                if (still_remaining_targets.contains(from_Path_7) ) {
					          	    		
					          	    		continue;
					          	    	}
			                else {  
	          	    		if ( !temporary_path_holder.contains(from_Path_7)  ){
	          	    			
	          	    			target_subset.put(from_Path_7 + " " + "new", false);
	          	    			
	          	    			// retrieve test case from archive
	          	    		// retrieve test case from archive
	          	    			//updatePopulation(from_Path_7)..//return test case not previously added to temporary population
	          	    		}}
		          	    	  
			          	    }	  
		          	    	  
		          	    	  
		          	      }
	           }
	            		
	            	else {
	            		
	            		// pick paths with false status and add them to subset of targets
	            		
	            		int path_to_add_counter = 0;
	            		
	            	if (paths.size() > 50) {
	            		Set<String> add_target_from_Path = paths.keySet();
		             	
		             	//replaced_status_2.put(id + new String(str), false);
		             	
		             	// get status of removal
		          	    for (String from_Path: add_target_from_Path) {  // contains(Object)
		             	
		             	     //get current path set..the paths already in subset should not be picked again....
		             		
		          	  	//	target_subset.put(key + " " + "new", false);	
		          	    	
		          	    		if (still_remaining_targets.contains(from_Path) ) {
				          	    		
				          	    		continue;
				          	    	}
		          	    			else {	
		          	    		
		          	    		    if (path_to_add_counter < 50) {
		          	    		       
		          	    	         if ( !paths.get(from_Path)  && (!temporary_path_holder.contains(from_Path) ) ){
		          	    		
		          	    		    target_subset.put(from_Path + " " + "new", false);
		          	    			
		          	    			path_to_add_counter++;
		          	    			
		          	    			// retrieve test case from archive
		          	    		// retrieve test case from archive
		          	    			//updatePopulation(from_Path)..//return test case not previously added to temporary population
		          	    		     }
		          	    		     
		          	    		     
		          	    		}
		          	    			}
		          	    	}
	            	}
	            	
	            	
		          	    else {
		          	    		  
		          	    		Set<String> add_target_from_Path_4 = paths.keySet();
				             	
				             	//replaced_status_2.put(id + new String(str), false);
				             	
				             	// get status of removal
				          	    for (String from_Path_4: add_target_from_Path_4) {  // contains(Object)
				             	
				             	     //get current path set..the paths already in subset should not be picked again....
				             		
				          	  	//	target_subset.put(key + " " + "new", false);	
				          	    	
				         
				          	    			if (still_remaining_targets.contains(from_Path_4) ) {
						          	    		
						          	    		continue;
						          	    	}
		          	    		  
				          	    			else {
		          	    			
		          	    		if ( !paths.get(from_Path_4)  && (!temporary_path_holder.contains(from_Path_4) ) ){
		          	    			
		          	    			target_subset.put(from_Path_4 + " " + "new", false);
		          	    			
		          	    			// retrieve test case from archive
		          	    			//updatePopulation(from_Path_4)..//return test case not previously added to temporary population
		          	    		}}
		          	    			
		          	    			
		          	    			
		          	    		}
		          	    	}
		             	
	            	}
					
					
				}
	            	
	            	
				String updated_id;

				int updated_val;

				// int rem;

				for (int pop = 0; pop < updated_population.size(); pop++) {

					if (pop == 50) {
						break;
					}

					String[] updated_pop = (updated_population.get(pop).split(" "));
					updated_id = updated_pop[0];
					updated_val = Integer.parseInt(updated_pop[1]);

					if (updated_id.equals("parent")) {
						for (int update_parent = 0; update_parent < R; update_parent++) {
							// x[i][j] = v[i][j] ;

							x[pop][update_parent] = x[updated_val][update_parent];
						}
					}
					if (updated_id.equals("child")) {
						for (int update_child = 0; update_child < R; update_child++) {
							// x[i][j] = v[i][j] ;

							x[pop][update_child] = v[updated_val][update_child];
						}
					}

				}
	            	
	            	
				}   	
	            // 	removed_at_previous_iteration.clear();
				//}
				
				
				
			//	for (Map.Entry<String, ArrayList<String>> entry : names.entrySet()) {
				//	String key = entry.getKey();
				//	ArrayList<String> value = entry.getValue();
				//	}

			
		//	System.out.println("NO. of cycles=" + (Cycle[run] - 1)); // ���Number of Cycle
			coverage[run] = obj_total * 100 / PATHNUM_NUMBER ; // percentage of paths covered per run
			System.out.println("Path coverage=" + coverage[run] + "%");
			System.out.println("The optimal solution is");
			System.out.println("template 1(bbbb): ");
			/*for (int a = 0; a < PATHNUM; a++) // Output the result
		  {
				if (statu[a]) {
					System.out.print("path" + a + ":");
					for (int j = 0; j < R; j++)
						System.out.print(solution[a][j] + " ");
					System.out.println();
				} else
					System.out.println("path" + a + "Not covered.");
			}*/

			 

		} 

		double time_sum = 0, time_average;
		float coverage_sum = 0, coverage_average, cycle_sum = 0, cycle_average, case_average;
		int case_sum = 0;
		for (int run = 0; run < RUN; run++) {
			time_sum = time_sum + runtime[run];
		//	cycle_sum = cycle_sum + (Cycle[run] - 1);
		//	case_sum = case_sum + total_case_num[run];
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

	
	
	




	public static String pathnum(int[] x) {  //return path traversed by test case

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

	public static ArrayList<String> benchmarkfunction(int[] trix)
			throws FileNotFoundException // evaluation_of_fitness_for

	{
		double[][] fitness_matrix = new double[NODENUM][R];
		ArrayList<String> fit = new ArrayList<String>();
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

		
		   Set<String> get_fitness = target_subset.keySet();

		  
		   
			for (String target : get_fitness) {
               String [] temporal_fitness = target.split(" ");	
				
				String target_path_;

				String path_status;
				
				target_path_ =      temporal_fitness[0];
				
				path_status =       temporal_fitness[1];
	
			     double Unmatched_branch_node_a_ = 0.0;
				 double Unmatched_branch_node_b_ = 0.0;
						// traversed_violation = strLine_non.substring(strLine_non.length() - 5);

						int index = 0;
						// double unwinding2 = 0.0;
						for (int i = 0; i < 10; i++) {
							index++;
							if (final_vertex_path.charAt(i) != target_path_.charAt(i)) {

								if (target_path_.charAt(i) == 'a') {
									
									
									Unmatched_branch_node_a_ += fitness_matrix[0][index];

								}
								
								
							   if (target_path_.charAt(i) == 'b') {
									
									
									Unmatched_branch_node_b_ += fitness_matrix[1][index];

								}
								
							}
								
						}
						double nonsimple_unmatched2 = 0.0;
						nonsimple_unmatched2 = Unmatched_branch_node_a_ + Unmatched_branch_node_b_;
						fit.add(target_path_+ " " + path_status +" " + nonsimple_unmatched2);
						
						//fitness vector for test case
						

	        }
	
		return fit;  //fitness vector
	}
	
    
	
//	Iterator<Map.Entry<String, Boolean>> iter_simple_ = temporary_path_holder.entrySet().iterator();

	//while (iter_simple_.hasNext()) {

	//	Map.Entry<String, Boolean> entry = iter_simple_.next();

		//strLine_non = entry.getKey();

		//if (!paths.get(strLine_non)) {

			//double Unmatched_branch_node_a_ = 0;
			///double Unmatched_branch_node_b_ = 0;
			//double Unmatched_branch_node_c_ = 0;

			//for (int p = 0; p < 7; p++) {

				//if (final_vertex_path.charAt(p) != strLine_non.charAt(p)) {

					//if (strLine_non.charAt(p) == 'a') {

						//Unmatched_branch_node_a_ += fitness_matrix[0][p];

					//}

					//if (strLine_non.charAt(p) == 'b') {

						//Unmatched_branch_node_b_ += fitness_matrix[1][p];

					//}

					//if (strLine_non.charAt(p) == 'c') {

					//	Unmatched_branch_node_c_ += fitness_matrix[2][p];

					//}

				//}

			//}
			//double nonsimple_unmatched2 = 0.0;
			//nonsimple_unmatched2 = Unmatched_branch_node_a_ + Unmatched_branch_node_b_
			//		+ Unmatched_branch_node_c_;

			//fit.add(nonsimple_unmatched2);
		//}
//
	//}


//return fit;
//
//}
	
	
	
	
	static void enumeratePaths(int k, char[] set, char[] str, int index) {
		if (index == k) {
			// System.out.println(new String(str));
			//id++;
			paths.put(new String(str) , false); // write all resulting paths to map..
			//path_ids.put(id, new String(str));  
			//+replaced_status.put(new String(str), false);
			//replaced_status_2.put(id + new String(str), false);
			//coverage_status_1.put(new String(str), false);
			//coverage_status_2.put(id + new String(str), false);
			//Set<String> keyz = paths.keySet();
		}
		else {
			for (int i = 0; i < set.length; i++) {
				str[index] = set[i];
				enumeratePaths(k, set, str, index + 1); // recursive procedure to enumerate the paths.
			}
		}
	}

	public static void back_Up() {
	
		    temporay_paths.putAll(paths); 
	
}

	
	/*public static void init_Template() throws IOException { // read paths from map to files according to template ids..
															// assign identical paths to templates

		FileOutputStream fos_all = new FileOutputStream(allpaths);
		BufferedWriter bw_all = new BufferedWriter(new OutputStreamWriter(fos_all));

		Set<String> keyz = paths.keySet();

		for (String key : keyz) {

			bw_all.write(key); // write to file
			bw_all.newLine();

			
		}

		bw_all.close();
	

	}*/

	
/*static LinkedHashMap<String, Boolean> paths = new LinkedHashMap<String, Boolean>();
    static LinkedHashMap<String, Boolean> replaced_status = new LinkedHashMap<String, Boolean>();
	static LinkedHashMap<Integer, String> path_ids = new LinkedHashMap<Integer, String>();
	static LinkedHashMap<String, Boolean> coverage_status_1 = new LinkedHashMap<String, Boolean>();
	static LinkedHashMap<String, Boolean> coverage_status_2 = new LinkedHashMap<String, Boolean>();*/
	
	
	
	public static void reset_All() {
		// clear all the maps for next run and update them
		paths.clear();
	//	pathcounter.clear();
		evaluated_status.clear();
		//dynamic_access.clear();

	

	}

	static double getAverage(int[] array, int num) {
		int sum = 0;
		for (int i = 0; i < num; i++) {
			sum += array[i];
		}
		return (double) (sum / num);
	}

	// ��׼��
/*	static double getStandardDevition(int[] array, int num) {
		double sum = 0;
		for (int i = 0; i < num; i++) {
			sum += Math.sqrt(((double) array[i] - getAverage(array, num)) * (array[i] - getAverage(array, num)));
		}
		return (sum / (num - 1));
	}
*/
	
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
		
		Set<String> temporal = target_subset.keySet();
		
		for (String target : temporal) {
			//String [] temporal_reader = target.split(" ");	
			
			//int target_id;

			//String get_path;
			
		//	target_id = Integer.parseInt(temporal_reader[0]);
			
			//get_path = temporal_reader[1];
		
	 if (captured_path.equals(target)) {

					paths.remove(target);  //remove from set of uncovered paths
					//replaced_status.remove(target);
					target_subset.remove(target ); // remove from subset of uncovered paths
					obj_total++;
				}
			
			//if (paths.containsKey(target_id + "" + get_path )){
				
				//paths.remove(target_id + "" + get_path );
			//}
					
     if (captured_path.startsWith(template_1)) { // if the traversed path_id is equal to "the template id"
      
		  //save path and test case
	    // List<String> group_1 = new ArrayList<String>();
			   
			 //   group_1.add (captured_path);

				for (int s = 0; s < R; s++) {

							solution_1[group_1_count][s] = offspring[num][s];

					}
			    
				 group_1_count++;
		}

		else if (captured_path.startsWith(template_2)) { // if the traversed path_id is equal to "the template
	
			//   group_2.add (captured_path);
				for (int s = 0; s < R; s++) {

					solution_2[group_2_count][s] = offspring[num][s];

			}
	    
		           group_2_count++;
		}

		else if (captured_path.startsWith(template_3)) { // if the traversed path_id is equal to "the template
																// id"
			// group_3.add (captured_path);
			 for (int s = 0; s < R; s++) {

					solution_3[group_3_count][s] = offspring[num][s];

			}
	    
		           group_3_count++;
		}

		else if (captured_path.startsWith(template_4)) { // if the traversed path_id is equal to "the template
																// id"
			// group_4.add (captured_path);
			 for (int s = 0; s < R; s++) {

					solution_4[group_4_count][s] = offspring[num][s];

			}
	    
		           group_4_count++;

		}


		else if (captured_path.startsWith(template_5)) { // if the traversed path_id is equal to "the template
																// id"
			 //group_5.add (captured_path);
			 for (int s = 0; s < R; s++) {

					solution_5[group_5_count][s] = offspring[num][s];

			}
	    
		           group_5_count++;
		}

		else if (captured_path.startsWith(template_6)) { // if the traversed path_id is equal to "the template
		
			// group_6.add (captured_path);
			 for (int s = 0; s < R; s++) {

					solution_6[group_6_count][s] = offspring[num][s];

			}
	    
		           group_6_count++;
		}

		else if (captured_path.startsWith(template_7)) { // if the traversed path_id is equal to "the template
																// id"
			// group_7.add (captured_path);
			 for (int s = 0; s < R; s++) {

					solution_7[group_7_count][s] = offspring[num][s];

			}
	    
		           group_7_count++;
		}

		else if (captured_path.startsWith(template_8)) { // if the traversed path_id is equal to "the template
																// id"

			// group_8.add (captured_path);
			 
			 for (int s = 0; s < R; s++) {

					solution_8[group_8_count][s] = offspring[num][s];

			}
	    
		           group_8_count++;
		}

		else if (captured_path.startsWith(template_9)) { // if the traversed path_id is equal to "the template
																// id"
	
			// group_9.add (captured_path);
			 
			 for (int s = 0; s < R; s++) {

					solution_9[group_9_count][s] = offspring[num][s];

			}
	    
		           group_9_count++;
		}

		else if (captured_path.startsWith(template_10)) { // if the traversed path_id is equal to "the template
																// id"
		
			// group_10.add (captured_path);
			 
			 for (int s = 0; s < R; s++) {

					solution_10[group_10_count][s] = offspring[num][s];

			}
	    
		           group_10_count++;
		}

		else if (captured_path.startsWith(template_11)) { // if the traversed path_id is equal to "the template
																// id"
			// ******
			// group_11.add (captured_path);
			 for (int s = 0; s < R; s++) {

					solution_11[group_11_count][s] = offspring[num][s];

			}
	    
		           group_11_count++;
		}

		else if (captured_path.startsWith(template_12)) { // if the traversed path_id is equal to "the template
																// id"
																// ******
			// group_12.add (captured_path);
			 for (int s = 0; s < R; s++) {

					solution_12[group_12_count][s] = offspring[num][s];

			}
	    
		           group_12_count++;
		}

		else if (captured_path.startsWith(template_13)) { // if the traversed path_id is equal to "the template
																// id"
			// group_13.add (captured_path);
			 for (int s = 0; s < R; s++) {

					solution_13[group_13_count][s] = offspring[num][s];

			}
	    
		           group_13_count++;
		}

		else if (captured_path.startsWith(template_14)) { // if the traversed path_id is equal to "the template
																// id"

			// group_14.add (captured_path);

			 
			 for (int s = 0; s < R; s++) {

					solution_14[group_14_count][s] = offspring[num][s];

			}
	    
		           group_14_count++;
		}

		else if (captured_path.startsWith(template_15)) { // if the traversed path_id is equal to "the template
																// id"
			// group_15.add (captured_path);
			 for (int s = 0; s < R; s++) {

					solution_15[group_15_count][s] = offspring[num][s];

			}
	    
		           group_15_count++;
		}

		else if (captured_path.startsWith(template_16)) { // if the traversed path_id is equal to "the template
																// id"
			// group_16.add (captured_path);												// ******
			 for (int s = 0; s < R; s++) {

					solution_16[group_16_count][s] = offspring[num][s];

			}
	    
		           group_16_count++;

		}

		else if (captured_path.startsWith(template_17)) { // if the traversed path_id is equal to "the template
																// id"
			// group_17.add (captured_path);												// ******
			 for (int s = 0; s < R; s++) {

					solution_17[group_17_count][s] = offspring[num][s];

			}
	    
		           group_17_count++;
	
		}

		else if (captured_path.startsWith(template_18)) { // if the traversed path_id is equal to "the template
																// id"
			// ******
			// **************************************************************************//

			// group_18.add (captured_path);												// ******
			 for (int s = 0; s < R; s++) {

					solution_18[group_18_count][s] = offspring[num][s];

			}
	    
		           group_18_count++;
		}

		else if (captured_path.startsWith(template_19)) { // if the traversed path_id is equal to "the template
																// id"
			// ******
	
			// group_19.add (captured_path);												// ******
			 for (int s = 0; s < R; s++) {

					solution_19[group_19_count][s] = offspring[num][s];

			}
	    
		           group_19_count++;
		}

		// if( totalpathcounter ==TOTAL_PATHNUM ) { // if all paths are covered break
		// out of the loop
		// break ;
		// }

		else if (captured_path.startsWith(template_20)) { // if the traversed path_id is equal to "the template
																// id"
			// ******
			// **************************************************************************//
			// group_20.add (captured_path);												// ******

			 for (int s = 0; s < R; s++) {

					solution_20[group_20_count][s] = offspring[num][s];

			}
	    
		           group_20_count++;
		}

		// if( totalpathcounter ==TOTAL_PATHNUM ) { // if all paths are covered break
		// out of the loop
		// break ;
		// }

		else if (captured_path.startsWith(template_21)) { // if the traversed path_id is equal to "the template
																// id"
			// ******
			// **************************************************************************//
		//	 group_21.add (captured_path);	
			// }
			 for (int s = 0; s < R; s++) {

					solution_21[group_21_count][s] = offspring[num][s];

			}
	    
		           group_21_count++;
		}

		else if (captured_path.startsWith(template_22)) { // if the traversed path_id is equal to "the template
																// id"
			// ******
			// **************************************************************************//

		//	 group_22.add (captured_path);	
			 for (int s = 0; s < R; s++) {

					solution_22[group_22_count][s] = offspring[num][s];

			}
	    
		           group_22_count++;
		}

		else if (captured_path.startsWith(template_23)) { // if the traversed path_id is equal to "the template
																// id"
			// ******
			// **************************************************************************//

			// group_23.add (captured_path);
			 
			 for (int s = 0; s < R; s++) {

					solution_23[group_23_count][s] = offspring[num][s];

			}
	    
		           group_23_count++;
		}

		else if (captured_path.startsWith(template_24)) { // if the traversed path_id is equal to "the template
																// id"
			// ******
			// **************************************************************************//
			// group_24.add (captured_path);	
			 for (int s = 0; s < R; s++) {

					solution_24[group_24_count][s] = offspring[num][s];

			}
	    
		           group_24_count++;
		}

		else if (captured_path.startsWith(template_25)) { // if the traversed path_id is equal to "the template
																// id"
			// ******
			// **************************************************************************//

		//	 group_25.add (captured_path);	
			 for (int s = 0; s < R; s++) {

					solution_25[group_25_count][s] = offspring[num][s];

			}
	    
		           group_25_count++;
		}

		else if (captured_path.startsWith(template_26)) { // if the traversed path_id is equal to "the template
																// id"
			// ******
			// **************************************************************************//
		//	 group_26.add (captured_path);	
			 for (int s = 0; s < R; s++) {

					solution_26[group_26_count][s] = offspring[num][s];

			}
	    
		           group_26_count++;
		}

		// if(totalpathcounter ==TOTAL_PATHNUM ) { // if all paths are covered break out
		// of the loop
		// break ;
		// }

		else if (captured_path.startsWith(template_27)) { // if the traversed path_id is equal to "the template
			// group_27.add (captured_path);													// id"
			 for (int s = 0; s < R; s++) {

					solution_27[group_27_count][s] = offspring[num][s];

			}
	    
		           group_27_count++;
		}

		else if (captured_path.startsWith(template_28)) { // if the traversed path_id is equal to "the template
																// id"
			// group_28.add (captured_path);	
			 for (int s = 0; s < R; s++) {

					solution_28[group_28_count][s] = offspring[num][s];

			}
	    
		           group_28_count++;
		}

		// if(totalpathcounter ==TOTAL_PATHNUM ) { // if all paths are covered break out
		// of the loop
		// break ;
		// }

		else if (captured_path.startsWith(template_29)) { // if the traversed path_id is equal to "the template
																// id"
			// group_29.add (captured_path);	
			 for (int s = 0; s < R; s++) {

					solution_29[group_29_count][s] = offspring[num][s];

			}
	    
		           group_29_count++;
		}

		// if(totalpathcounter ==TOTAL_PATHNUM ) { // if all paths are covered break out
		// of the loop
		// break ;
		// }

		else if (captured_path.startsWith(template_30)) { // if the traversed path_id is equal to "the template
																// id"
			// group_30.add (captured_path);
			 for (int s = 0; s < R; s++) {

					solution_30[group_30_count][s] = offspring[num][s];

			}
	    
		           group_30_count++;
		}

		else if (captured_path.startsWith(template_31)) { // if the traversed path_id is equal to "the template
																// id"
																// ******
			 //group_31.add (captured_path);

			 for (int s = 0; s < R; s++) {

					solution_31[group_31_count][s] = offspring[num][s];

			}
	    
		           group_31_count++;
		}

		if (captured_path.startsWith(template_32)) { // if the traversed path_id is equal to "the template id"

			// group_32.add (captured_path);
			 
			 for (int s = 0; s < R; s++) {

					solution_32[group_32_count][s] = offspring[num][s];

			}
	    
		           group_32_count++;
		}

		else if (captured_path.startsWith(template_33)) { // if the traversed path_id is equal to "the template
																// id"
			// group_33.add (captured_path);
			 for (int s = 0; s < R; s++) {

					solution_33[group_33_count][s] = offspring[num][s];

			}
	    
		           group_33_count++;
		}

		else if (captured_path.startsWith(template_34)) { // if the traversed path_id is equal to "the template
																// id"
			// group_34.add (captured_path);
			 for (int s = 0; s < R; s++) {

					solution_34[group_34_count][s] = offspring[num][s];

			}
	    
		           group_34_count++;
		}


		else if (captured_path.startsWith(template_35)) { // if the traversed path_id is equal to "the template
																// id"
			// ******
			// **************************************************************************//

			// group_35.add (captured_path);
			 for (int s = 0; s < R; s++) {

					solution_35[group_35_count][s] = offspring[num][s];

			}
	    
		           group_35_count++;
		}

		// if( totalpathcounter ==TOTAL_PATHNUM ) { // if all paths are covered break
		// out of the loop
		// break ;
		// }

		else if (captured_path.startsWith(template_36)) { // if the traversed path_id is equal to "the template
																// id"
			// group_36.add (captured_path);
			 for (int s = 0; s < R; s++) {

					solution_36[group_36_count][s] = offspring[num][s];

			}
	    
		           group_36_count++;
		}

		else if (captured_path.startsWith(template_37)) { // if the traversed path_id is equal to "the template
																// id"
			// ******
			// **************************************************************************//
			// group_37.add (captured_path);
			 for (int s = 0; s < R; s++) {

					solution_37[group_37_count][s] = offspring[num][s];

			}
	    
		           group_37_count++;
		}

		else if (captured_path.startsWith(template_38)) { // if the traversed path_id is equal to "the template
																// id"
			// ******
			// **************************************************************************//

			// group_38.add (captured_path);
			 for (int s = 0; s < R; s++) {

					solution_38[group_38_count][s] = offspring[num][s];

			}
	    
		           group_38_count++;
		}

		else if (captured_path.startsWith(template_39)) { // if the traversed path_id is equal to "the template
																// id"
			// ******
			// **************************************************************************//
			// group_39.add (captured_path);
			 for (int s = 0; s < R; s++) {

					solution_39[group_39_count][s] = offspring[num][s];

			}
	    
		           group_39_count++;
		}

		else if (captured_path.startsWith(template_40)) { // if the traversed path_id is equal to "the template
																// id"
			// ******
			// **************************************************************************//

			// group_40.add (captured_path);
			 for (int s = 0; s < R; s++) {

					solution_40[group_40_count][s] = offspring[num][s];

			}
	    
		           group_40_count++;
		}

		else if (captured_path.startsWith(template_41)) { // if the traversed path_id is equal to "the template
																// id"
			// ******
			// group_41.add (captured_path);
			 
			 for (int s = 0; s < R; s++) {

					solution_41[group_41_count][s] = offspring[num][s];

			}
	    
		           group_41_count++;
		}

		// if(totalpathcounter ==TOTAL_PATHNUM ) { // if all paths are covered break out
		// of the loop
		// break ;
		// }

		else if (captured_path.startsWith(template_42)) { // if the traversed path_id is equal to "the template
																// id"
			// group_42.add (captured_path);												// ******
			 for (int s = 0; s < R; s++) {

					solution_42[group_42_count][s] = offspring[num][s];

			}
	    
		           group_42_count++;

		}

		else if (captured_path.startsWith(template_43)) { // if the traversed path_id is equal to "the template
																// id"
	
			// group_43.add (captured_path);
			 
			 for (int s = 0; s < R; s++) {

					solution_43[group_43_count][s] = offspring[num][s];

			}
	    
		           group_43_count++;
		}

		// if(totalpathcounter ==TOTAL_PATHNUM ) { // if all paths are covered break out
		// of the loop
		// break ;
		// }

		else if (captured_path.startsWith(template_44)) { // if the traversed path_id is equal to "the template
																// id"
			// group_44.add (captured_path);
			 for (int s = 0; s < R; s++) {

					solution_44[group_44_count][s] = offspring[num][s];

			}
	    
		           group_44_count++;
		}

		// if(totalpathcounter ==TOTAL_PATHNUM ) { // if all paths are covered break out
		// of the loop
		// break ;
		// }

		else if (captured_path.startsWith(template_45)) { // if the traversed path_id is equal to "the template
																// id"
			// group_45.add (captured_path);
			 for (int s = 0; s < R; s++) {

					solution_45[group_45_count][s] = offspring[num][s];

			}
	    
		           group_45_count++;
			 
		}

		else if (captured_path.startsWith(template_46)) { // if the traversed path_id is equal to "the template
																// id"
																// ******

			// group_46.add (captured_path);
			 
			 for (int s = 0; s < R; s++) {

					solution_46[group_46_count][s] = offspring[num][s];

			}
	    
		           group_46_count++;
		}

		if (captured_path.startsWith(template_47)) { // if the traversed path_id is equal to "the template id"

			// group_47.add (captured_path);
			 for (int s = 0; s < R; s++) {

					solution_47[group_47_count][s] = offspring[num][s];

			}
	    
		           group_47_count++;
		}

		else if (captured_path.startsWith(template_48)) { // if the traversed path_id is equal to "the template
																// id"
			// ******
			// **************************************************************************//

			// group_48.add (captured_path);
			 for (int s = 0; s < R; s++) {

					solution_48[group_48_count][s] = offspring[num][s];

			}
	    
		           group_48_count++;
		}

		else if (captured_path.startsWith(template_49)) { // if the traversed path_id is equal to "the template
																// id"
			// ******
			// **************************************************************************//

			// group_49.add (captured_path);
			 for (int s = 0; s < R; s++) {

					solution_49[group_49_count][s] = offspring[num][s];

			}
	    
		           group_49_count++;

		}

		else if (captured_path.startsWith(template_50)) { // if the traversed path_id is equal to "the template
																// id"
			// ******
		
			// group_50.add (captured_path);
			 
			 for (int s = 0; s < R; s++) {

					solution_50[group_50_count][s] = offspring[num][s];

			}
	    
		           group_50_count++;
		}

		// if( totalpathcounter ==TOTAL_PATHNUM ) { // if all paths are covered break
		// out of the loop
		// break ;
		// }

		else if (captured_path.startsWith(template_51)) { // if the traversed path_id is equal to "the template
																// id"
			// group_51.add (captured_path);
			 for (int s = 0; s < R; s++) {

					solution_51[group_51_count][s] = offspring[num][s];

			}
	    
		           group_51_count++;

		}

		// if( totalpathcounter ==TOTAL_PATHNUM ) { // if all paths are covered break
		// out of the loop
		// break ;
		// }

		else if (captured_path.startsWith(template_52)) { // if the traversed path_id is equal to "the template
																// id"
			// ******
			// **************************************************************************//
			 //group_52.add (captured_path);
		
			// }
			 for (int s = 0; s < R; s++) {

					solution_52[group_52_count][s] = offspring[num][s];

			}
	    
		           group_52_count++;

		}

		else if (captured_path.startsWith(template_53)) { // if the traversed path_id is equal to "the template
																// id"
			// ******
		
			 //group_53.add (captured_path);
			 
			 for (int s = 0; s < R; s++) {

					solution_53[group_53_count][s] = offspring[num][s];

			}
	    
		           group_53_count++;
		}

		else if (captured_path.startsWith(template_54)) { // if the traversed path_id is equal to "the template
																// id"
			// ******
			
			 //group_54.add (captured_path);
			 
			 for (int s = 0; s < R; s++) {

					solution_54[group_54_count][s] = offspring[num][s];

			}
	    
		           group_54_count++;
		}

		else if (captured_path.startsWith(template_55)) { // if the traversed path_id is equal to "the template
																// id"
			 //group_55.add (captured_path);
			 for (int s = 0; s < R; s++) {

					solution_55[group_55_count][s] = offspring[num][s];

			}
	    
		           group_55_count++;

		}

		else if (captured_path.startsWith(template_56)) { // if the traversed path_id is equal to "the template
																// id"
			// ******
	
			//group_56.add (captured_path);
			
			for (int s = 0; s < R; s++) {

				solution_56[group_56_count][s] = offspring[num][s];

		}
 
	           group_56_count++;
		}

		else if (captured_path.startsWith(template_57)) { // if the traversed path_id is equal to "the template
																// id"
			//group_57.add (captured_path);
			for (int s = 0; s < R; s++) {

				solution_57[group_57_count][s] = offspring[num][s];

		}
 
	           group_57_count++;
			
		}

		// if(totalpathcounter ==TOTAL_PATHNUM ) { // if all paths are covered break out
		// of the loop
		// break ;
		// }

		else if (captured_path.startsWith(template_58)) { // if the traversed path_id is equal to "the template
																// id"
	
			//group_58.add (captured_path);
			for (int s = 0; s < R; s++) {

				solution_58[group_58_count][s] = offspring[num][s];

		}
 
	           group_58_count++;

		}

		else if (captured_path.startsWith(template_59)) { // if the traversed path_id is equal to "the template
																// id"
		//	group_59.add (captured_path);
			for (int s = 0; s < R; s++) {

				solution_59[group_59_count][s] = offspring[num][s];

		}
 
	           group_59_count++;

		}

		// if(totalpathcounter ==TOTAL_PATHNUM ) { // if all paths are covered break out
		// of the loop
		// break ;
		// }

		else if (captured_path.startsWith(template_60)) { // if the traversed path_id is equal to "the template
																// id"
		//	group_60.add (captured_path);
			for (int s = 0; s < R; s++) {

				solution_60[group_60_count][s] = offspring[num][s];

		}
 
	           group_60_count++;

		}

		// if(totalpathcounter ==TOTAL_PATHNUM ) { // if all paths are covered break out
		// of the loop
		// break ;
		// }

		else if (captured_path.startsWith(template_61)) { // if the traversed path_id is equal to "the template
		
		//	group_61.add (captured_path);
			
			for (int s = 0; s < R; s++) {

				solution_61[group_61_count][s] = offspring[num][s];

		}
 
	           group_61_count++;
			
		}

		else if (captured_path.startsWith(template_62)) { // if the traversed path_id is equal to "the template
																// id"
																// ******
			//group_62.add (captured_path);
			for (int s = 0; s < R; s++) {

				solution_62[group_62_count][s] = offspring[num][s];

		}
 
	           group_62_count++;
		}

		else if (captured_path.startsWith(template_63)) { // if the traversed path_id is equal to "the template id"

			//group_63.add (captured_path);
			for (int s = 0; s < R; s++) {

				solution_63[group_63_count][s] = offspring[num][s];

		}
 
	           group_63_count++;
		}

		else if (captured_path.startsWith(template_64)) { // if the traversed path_id is equal to "the template
																// id"
			//group_64.add (captured_path);
			for (int s = 0; s < R; s++) {

				solution_64[group_64_count][s] = offspring[num][s];

		}
 
	           group_64_count++;
		}

 
 }


 }

 
 
 static int[][] updatePopulation( String check_path_group) {
	
	int[][] temp_array_ = new int[1][R];
	 
	 
 if (check_path_group.startsWith(template_1)) { // if the traversed path_id is equal to "the template id"
	
	 
	int index_1  = (int)(Math.random()*((group_1_count  - 0)+1))+ 0;
	 
	 
	for (int s = 0; s < R; s++) {

		temp_array_[index_1][s] = solution_1[index_1][s];

	}

	 
	}

	else if (check_path_group.startsWith(template_2)) { // if the traversed path_id is equal to "the template

		int index_2 = (int)(Math.random()*((group_2_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_2][s] = solution_2[index_2][s];

		}
	     
	}

	else if (check_path_group.startsWith(template_3)) { // if the traversed path_id is equal to "the template
		int index_3  = (int)(Math.random()*((group_3_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_3][s] = solution_3[index_3][s];

		}												// id"
		
	}

	else if (check_path_group.startsWith(template_4)) { // if the traversed path_id is equal to "the template
															// id"
		int index_4  = (int)(Math.random()*((group_4_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_4][s] = solution_4[index_4][s];

		}

	}


	else if (check_path_group.startsWith(template_5)) { // if the traversed path_id is equal to "the template
															// id"
		int index_5  = (int)(Math.random()*((group_5_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_5][s] = solution_5[index_5][s];

		}
	}

	else if (check_path_group.startsWith(template_6)) { // if the traversed path_id is equal to "the template
		int index_6  = (int)(Math.random()*((group_6_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_6][s] = solution_6[index_6][s];

		}
		
	}

	else if (check_path_group.startsWith(template_7)) { // if the traversed path_id is equal to "the template
															// id"
		int index_7  = (int)(Math.random()*((group_7_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_7][s] = solution_7[index_7][s];

		}
	}

	else if (check_path_group.startsWith(template_8)) { // if the traversed path_id is equal to "the template
															// id"
		int index_8  = (int)(Math.random()*((group_8_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_8][s] = solution_8[index_8][s];

		}
	}

	else if (check_path_group.startsWith(template_9)) { // if the traversed path_id is equal to "the template
		int index_9 = (int)(Math.random()*((group_9_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_9][s] = solution_9[index_9][s];

		}											// id"

	}

	else if (check_path_group.startsWith(template_10)) { // if the traversed path_id is equal to "the template
		int index_10  = (int)(Math.random()*((group_10_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_10][s] = solution_10[index_10][s];

		}											// id"
	
	}

	else if (check_path_group.startsWith(template_11)) { // if the traversed path_id is equal to "the template
															// id"
		int index_11  = (int)(Math.random()*((group_11_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_11][s] = solution_1[index_11][s];

		}
	}

	else if (check_path_group.startsWith(template_12)) { // if the traversed path_id is equal to "the template
															// id"
		int index_12  = (int)(Math.random()*((group_12_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_12][s] = solution_12[index_12][s];

		}
	}

	else if (check_path_group.startsWith(template_13)) { // if the traversed path_id is equal to "the template
		int index_13  = (int)(Math.random()*((group_13_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_13][s] = solution_13[index_13][s];

		}
	}

	else if (check_path_group.startsWith(template_14)) { // if the traversed path_id is equal to "the template
		int index_14  = (int)(Math.random()*((group_14_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_14][s] = solution_14[index_14][s];

		}											// id"

	}

	else if (check_path_group.startsWith(template_15)) { // if the traversed path_id is equal to "the template
															// id"
		int index_15 = (int)(Math.random()*((group_15_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_15][s] = solution_15[index_15][s];

		}
	}

	else if (check_path_group.startsWith(template_16)) { // if the traversed path_id is equal to "the template
															// id"
		int index_16  = (int)(Math.random()*((group_16_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_16][s] = solution_16[index_16][s];

		}
	}

	else if (check_path_group.startsWith(template_17)) { // if the traversed path_id is equal to "the template
		int index_17  = (int)(Math.random()*((group_17_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_17][s] = solution_17[index_17][s];

		}
	}

	else if (check_path_group.startsWith(template_18)) { // if the traversed path_id is equal to "the template
															// id"
		int index_18  = (int)(Math.random()*((group_18_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_18][s] = solution_18[index_18][s];

		}
	}

	else if (check_path_group.startsWith(template_19)) { // if the traversed path_id is equal to "the template
															// id"
		int index_19  = (int)(Math.random()*((group_19_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_19][s] = solution_19[index_19][s];

		}
	}

	// if( totalpathcounter ==TOTAL_PATHNUM ) { // if all paths are covered break
	// out of the loop
	// break ;
	// }

	else if (check_path_group.startsWith(template_20)) { // if the traversed path_id is equal to "the template
		int index_20  = (int)(Math.random()*((group_20_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_20][s] = solution_1[index_20][s];

		}
	}

	// if( totalpathcounter ==TOTAL_PATHNUM ) { // if all paths are covered break
	// out of the loop
	// break ;
	// }

	else if (check_path_group.startsWith(template_21)) { // if the traversed path_id is equal to "the template
															// id"
		int index_21  = (int)(Math.random()*((group_21_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_21][s] = solution_21[index_21][s];

		}
	}

	else if (check_path_group.startsWith(template_22)) { // if the traversed path_id is equal to "the template
															// id"
		int index_22  = (int)(Math.random()*((group_22_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_22][s] = solution_22[index_22][s];

		}
	}

	else if (check_path_group.startsWith(template_23)) { // if the traversed path_id is equal to "the template
															// id"
		int index_23  = (int)(Math.random()*((group_23_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_23][s] = solution_23[index_23][s];

		}
	}

	else if (check_path_group.startsWith(template_24)) { // if the traversed path_id is equal to "the template
		int index_24  = (int)(Math.random()*((group_24_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_24][s] = solution_24[index_24][s];

		}											// id"
		
	}

	else if (check_path_group.startsWith(template_25)) { // if the traversed path_id is equal to "the template
		int index_25  = (int)(Math.random()*((group_25_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_25][s] = solution_25[index_25][s];

		}												// id"
		
	}

	else if (check_path_group.startsWith(template_26)) { // if the traversed path_id is equal to "the template
															// id"
		
		int index_26  = (int)(Math.random()*((group_26_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_26][s] = solution_26[index_26][s];

		}
		
		
	}

	// if(totalpathcounter ==TOTAL_PATHNUM ) { // if all paths are covered break out
	// of the loop
	// break ;
	// }

	else if (check_path_group.startsWith(template_27)) { // if the traversed path_id is equal to "the template
		int index_27  = (int)(Math.random()*((group_27_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_27][s] = solution_27[index_27][s];

		}
	}

	else if (check_path_group.startsWith(template_28)) { // if the traversed path_id is equal to "the template
		int index_28  = (int)(Math.random()*((group_28_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_28][s] = solution_28[index_28][s];

		}
	}

	// if(totalpathcounter ==TOTAL_PATHNUM ) { // if all paths are covered break out
	// of the loop
	// break ;
	// }

	else if (check_path_group.startsWith(template_29)) { // if the traversed path_id is equal to "the template
															// id"
		int index_29  = (int)(Math.random()*((group_29_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_29][s] = solution_29[index_29][s];

		}
	}

	// if(totalpathcounter ==TOTAL_PATHNUM ) { // if all paths are covered break out
	// of the loop
	// break ;
	// }

	else if (check_path_group.startsWith(template_30)) { // if the traversed path_id is equal to "the template
		int index_30  = (int)(Math.random()*((group_30_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_30][s] = solution_30[index_30][s];

		}												// id"
		
	}

	else if (check_path_group.startsWith(template_31)) { // if the traversed path_id is equal to "the template
															// id"
		int index_31  = (int)(Math.random()*((group_31_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_31][s] = solution_31[index_31][s];

		}												// ******
		
	}

	if (check_path_group.startsWith(template_32)) { // if the traversed path_id is equal to "the template id"
		int index_32  = (int)(Math.random()*((group_32_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_32][s] = solution_32[index_32][s];

		}
	}

	else if (check_path_group.startsWith(template_33)) { // if the traversed path_id is equal to "the template
		int index_33  = (int)(Math.random()*((group_33_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_33][s] = solution_33[index_33][s];

		}
	}

	else if (check_path_group.startsWith(template_34)) { // if the traversed path_id is equal to "the template
		int index_34  = (int)(Math.random()*((group_34_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_34][s] = solution_34[index_34][s];

		}
	}


	else if (check_path_group.startsWith(template_35)) { // if the traversed path_id is equal to "the template
		int index_35 = (int)(Math.random()*((group_35_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_35][s] = solution_35[index_35][s];

		}
	}

	// if( totalpathcounter ==TOTAL_PATHNUM ) { // if all paths are covered break
	// out of the loop
	// break ;
	// }

	else if (check_path_group.startsWith(template_36)) { // if the traversed path_id is equal to "the template
															// id"
		int index_36  = (int)(Math.random()*((group_36_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_36][s] = solution_36[index_36][s];

		}
	}

	else if (check_path_group.startsWith(template_37)) { // if the traversed path_id is equal to "the template
															// id"
		int index_37  = (int)(Math.random()*((group_37_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_37][s] = solution_37[index_37][s];

		}
	}

	else if (check_path_group.startsWith(template_38)) { // if the traversed path_id is equal to "the template
		int index_38 = (int)(Math.random()*((group_38_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_38][s] = solution_38[index_38][s];

		}												// id"
		
	}

	else if (check_path_group.startsWith(template_39)) { // if the traversed path_id is equal to "the template
		int index_39  = (int)(Math.random()*((group_39_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_39][s] = solution_39[index_39][s];

		}
	}

	else if (check_path_group.startsWith(template_40)) { // if the traversed path_id is equal to "the template
		int index_40  = (int)(Math.random()*((group_40_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_40][s] = solution_40[index_40][s];

		}												// id"
		
	}

	else if (check_path_group.startsWith(template_41)) { // if the traversed path_id is equal to "the template
		int index_41  = (int)(Math.random()*((group_41_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_41][s] = solution_41[index_41][s];

		}												// id"
		
	}

	// if(totalpathcounter ==TOTAL_PATHNUM ) { // if all paths are covered break out
	// of the loop
	// break ;
	// }

	else if (check_path_group.startsWith(template_42)) { // if the traversed path_id is equal to "the template
	
		int index_42  = (int)(Math.random()*((group_42_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_42][s] = solution_42[index_42][s];

		}
	}

	else if (check_path_group.startsWith(template_43)) { // if the traversed path_id is equal to "the template
															// id"
		int index_43  = (int)(Math.random()*((group_43_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_43][s] = solution_43[index_43][s];

		}
		
	}

	// if(totalpathcounter ==TOTAL_PATHNUM ) { // if all paths are covered break out
	// of the loop
	// break ;
	// }

	else if (check_path_group.startsWith(template_44)) { // if the traversed path_id is equal to "the template
		int index_44  = (int)(Math.random()*((group_44_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_44][s] = solution_44[index_44][s];

		}										// id"
		
	}

	// if(totalpathcounter ==TOTAL_PATHNUM ) { // if all paths are covered break out
	// of the loop
	// break ;
	// }

	else if (check_path_group.startsWith(template_45)) { // if the traversed path_id is equal to "the template
															// id"
		int index_45  = (int)(Math.random()*((group_45_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_45][s] = solution_45[index_45][s];

		}
		 
	}

	else if (check_path_group.startsWith(template_46)) { // if the traversed path_id is equal to "the template
															// id"
		int index_46  = (int)(Math.random()*((group_46_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_46][s] = solution_46[index_46][s];

		}											// ******

	}

	if (check_path_group.startsWith(template_47)) { // if the traversed path_id is equal to "the template id"
		int index_47  = (int)(Math.random()*((group_47_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_47][s] = solution_47[index_47][s];

		}
		
	}

	else if (check_path_group.startsWith(template_48)) { // if the traversed path_id is equal to "the template
		int index_48  = (int)(Math.random()*((group_48_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_48][s] = solution_48[index_48][s];

		}											// id"
		
	}

	else if (check_path_group.startsWith(template_49)) { // if the traversed path_id is equal to "the template
															// id"
		int index_49  = (int)(Math.random()*((group_49_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_49][s] = solution_49[index_49][s];

		}

	}

	else if (check_path_group.startsWith(template_50)) { // if the traversed path_id is equal to "the template
															// id"
		int index_50  = (int)(Math.random()*((group_50_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_50][s] = solution_50[index_50][s];

		}
	}

	// if( totalpathcounter ==TOTAL_PATHNUM ) { // if all paths are covered break
	// out of the loop
	// break ;
	// }

	else if (check_path_group.startsWith(template_51)) { // if the traversed path_id is equal to "the template
															// id"
		int index_51  = (int)(Math.random()*((group_51_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_51][s] = solution_51[index_51][s];

		}

	}

	// if( totalpathcounter ==TOTAL_PATHNUM ) { // if all paths are covered break
	// out of the loop
	// break ;
	// }

	else if (check_path_group.startsWith(template_52)) { // if the traversed path_id is equal to "the template
															// id"
		int index_52  = (int)(Math.random()*((group_52_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_52][s] = solution_52[index_52][s];

		}

	}

	else if (check_path_group.startsWith(template_53)) { // if the traversed path_id is equal to "the template
		int index_53  = (int)(Math.random()*((group_53_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_53][s] = solution_53[index_53][s];

		}												// id"
		
	}

	else if (check_path_group.startsWith(template_54)) { // if the traversed path_id is equal to "the template
															// id"
		int index_54  = (int)(Math.random()*((group_54_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_54][s] = solution_54[index_54][s];

		}
	}

	else if (check_path_group.startsWith(template_55)) { // if the traversed path_id is equal to "the template
															// id"
		int index_55  = (int)(Math.random()*((group_55_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_55][s] = solution_55[index_55][s];

		}

	}

	else if (check_path_group.startsWith(template_56)) { // if the traversed path_id is equal to "the template
		int index_56  = (int)(Math.random()*((group_56_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_56][s] = solution_56[index_56][s];

		}											// id"
	
	}

	else if (check_path_group.startsWith(template_57)) { // if the traversed path_id is equal to "the template
															// id"
		int index_57  = (int)(Math.random()*((group_57_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_57][s] = solution_57[index_57][s];

		}
	}

	// if(totalpathcounter ==TOTAL_PATHNUM ) { // if all paths are covered break out
	// of the loop
	// break ;
	// }

	else if (check_path_group.startsWith(template_58)) { // if the traversed path_id is equal to "the template
															// id"
		int index_58  = (int)(Math.random()*((group_58_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_58][s] = solution_58[index_58][s];

		}
	}

	else if (check_path_group.startsWith(template_59)) { // if the traversed path_id is equal to "the template
		int index_59  = (int)(Math.random()*((group_59_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_59][s] = solution_59[index_59][s];

		}
	}

	// if(totalpathcounter ==TOTAL_PATHNUM ) { // if all paths are covered break out
	// of the loop
	// break ;
	// }

	else if (check_path_group.startsWith(template_60)) { // if the traversed path_id is equal to "the template
		int index_60  = (int)(Math.random()*((group_60_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_60][s] = solution_60[index_60][s];

		}										// id"
	
	}

	// if(totalpathcounter ==TOTAL_PATHNUM ) { // if all paths are covered break out
	// of the loop
	// break ;
	// }

	else if (check_path_group.startsWith(template_61)) { // if the traversed path_id is equal to "the template
	
		int index_61  = (int)(Math.random()*((group_61_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_61][s] = solution_61[index_61][s];

		}
	}

	else if (check_path_group.startsWith(template_62)) { // if the traversed path_id is equal to "the template
															// id"
															// ******
		int index_62  = (int)(Math.random()*((group_62_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_62][s] = solution_62[index_62][s];

		}
	}

	else if (check_path_group.startsWith(template_63)) { // if the traversed path_id is equal to "the template id"

		int index_63  = (int)(Math.random()*((group_63_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_63][s] = solution_63[index_63][s];

		}
	}

	else if (check_path_group.startsWith(template_64)) { // if the traversed path_id is equal to "the template
															// id"
		int index_64  = (int)(Math.random()*((group_64_count  - 0)+1))+ 0;
		 
		 
		for (int s = 0; s < R; s++) {

			temp_array_[index_64][s] = solution_64[index_64][s];

		}
	}
	return temp_array_;
 
 
 
 
}

}
 
 
 
 
 
 
 
 
 































