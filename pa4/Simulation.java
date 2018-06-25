//------------------------------------------------------------------------------------
// Pa4
// Program: Simulation.java
// Author:  James Michael Vrionis
// ID    :  JVrionis
// Date  :  11-22-16
// Purpose: Implementation of a Queue ADT in Java based on a linked list data 
//			structure. You will use your Queue ADT to simulate a set of jobs 
//			performed by a set of processors, where there are more jobs than 
// 			processors, and therefore some jobs may have to wait in a queue.
//			determine (1) the total wait time, (2) the maximum wait time 
//			(3) the average wait time over all m jobs
//
//------------------------------------------------------------------------------------


import java.io.*;
import java.util.Scanner;

public class Simulation {
	public static void main(String[] args) throws IOException {

		
		Scanner sc = null;
		PrintWriter report = null;
		PrintWriter trace = null;
		Queue StorageC = new Queue();
		Queue Storage = new Queue();
		Queue finished = new Queue();
		Queue[] processorQueues = null;
		int m = 0;
		Job j = null;
		int time = 0;

		// Check command line arguments:
		try { 
			if (args.length != 1) {
				System.out.println("Usage: Simultation infile");
				System.exit(1);
			}

			// Input: 
			sc = new Scanner(new File(args[0]));
			report = new PrintWriter(new FileWriter(args[0] + ".rpt"));
			trace = new PrintWriter(new FileWriter(args[0] + ".trc"));
		} catch(FileNotFoundException e) {
			System.out.println("Caught Exception " + e);
			System.exit(1);
		}

		// Num of jobs: 
		m = numOfJobs(sc); 

		while(sc.hasNextLine()) {
			j = getJob(sc);
			StorageC.enqueue(j);
		}

		// Trace and Report: 
		trace.println("Trace file: " + (args[0] + ".trc"));
		trace.println(m + " Jobs:");
		trace.println(StorageC.toString());
		trace.println();

		report.println("Report file: " + (args[0] + ".rpt"));
		report.println(m + " Jobs:");
		report.println(StorageC.toString());
		report.println();
		report.println("***********************************************************");

		// Simulation loop from (1,m-1)
		// Will lessen proccess by one
		for(int n = 1; n < m; n++) { 
			int totalWait = 0;
			int maxWait = 0;
			double avgWait = 0.0;

			for(int i = 1; i < StorageC.length()+1; i++) {
				j = (Job)StorageC.dequeue();
				j.resetFinishTime();
				Storage.enqueue(j);
				StorageC.enqueue(j);
			}

			int processors = n;
			processorQueues = new Queue[n+2];
			processorQueues[0] = Storage;
			processorQueues[n+1] = finished;
			for(int i = 1; i < n+1; i++) {
				processorQueues[i] = new Queue();
			}

			trace.println("*****************************");
			if(processors == 1)
				trace.println(processors + " processor:");
			else
				trace.println(processors + " processors:");
			trace.println("*****************************");

			trace.println("time=" + time);
			trace.println("0: " + Storage.toString());
			for(int i = 1; i < processors+1; i++) {
				trace.println(i + ": " + processorQueues[i]);
			}

			// While jobs exist, true:
			while(finished.length() != m) {
				int compFinal = Integer.MAX_VALUE;
				int finalIndex = 1;
				int comp = -1;
				int length = -1;
				int finalLength = -1;
				Job compare = null;

				// Will run if storage is not empty:
				if (!Storage.isEmpty()) {
					compare = (Job)Storage.peek();
					compFinal = compare.getArrival();
					finalIndex = 0;
				}

				for(int i = 1; i < processors+1; i++) {
					if (processorQueues[i].length() != 0) {
						compare = (Job)processorQueues[i].peek();
						comp = compare.getFinish();
					}
					if (comp == -1) {
						//Do Nothing 
					} else if (comp < compFinal) {
						compFinal = comp;
						finalIndex = i;
					}
					time = compFinal;
				}

				if (finalIndex == 0) {
					int tempIndex = 1;
					finalLength = processorQueues[tempIndex].length();
					for (int i = 1; i < processors+1; i++) {
						length = processorQueues[i].length();
						if (length < finalLength) {
							finalLength = length;
							tempIndex = i;
						}
					}

					compare = (Job)Storage.dequeue();
					processorQueues[tempIndex].enqueue(compare);
					if (processorQueues[tempIndex].length() == 1) {
						compare = (Job)processorQueues[tempIndex].peek();
						compare.computeFinishTime(time);
					}
				} else {
					compare = (Job)processorQueues[finalIndex].dequeue();
					finished.enqueue(compare);
					int tempWait = compare.getWaitTime();
					if (tempWait > maxWait)
						maxWait = tempWait;
					totalWait += tempWait;

					if (processorQueues[finalIndex].length() >= 1) {
						compare = (Job)processorQueues[finalIndex].peek();
						compare.computeFinishTime(time);
					}
				}

				trace.println();
				trace.println("time=" + time);
				trace.println("0: " + Storage.toString());
				for(int i = 1; i < processors+1; i++)
					trace.println(i + ": " + processorQueues[i]);
			}

			avgWait = ((double)totalWait/m);
			avgWait = (double)Math.round(avgWait*100)/100;
			trace.println();
			if (processors == 1)
				report.println(processors + " processor: totalWait=" + totalWait + ", maxWait=" + maxWait + ", averageWait=" + avgWait);
			else
				report.println(processors + " processors: totalWait=" + totalWait + ", maxWait=" + maxWait + ", averageWait=" + avgWait);

			time = 0;
			finished.dequeueAll();
		}

		sc.close();
		report.close();
		trace.close();

	}

	// Returns number # of jobs currently waiting for completion
	public static int numOfJobs(Scanner sc) { 
		String s = sc.nextLine();
		int x = Integer.parseInt(s);
		return x;
	}
	// Given Function: 
	public static Job getJob(Scanner in) { 
		String[] s = in.nextLine().split(" ");
		int a = Integer.parseInt(s[0]);
		int d = Integer.parseInt(s[1]);
		return new Job(a, d);
	}
}

