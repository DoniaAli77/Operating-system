import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class OperatingSystem {

	public static Semaphore S =new Semaphore();
	public static Queue<Process> Ready=new LinkedList<Process>();

	public static ArrayList<Thread> ProcessTable;

	// public static int activeProcess= 0;
	// system calls:
	// 1- Read from File
	@SuppressWarnings("unused")
	public static String readFile(String name) {
		String Data = "";
		File file = new File(name);
		try {
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				Data += scan.nextLine() + "\n";
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return Data;
	}

	// 2- Write into file
	@SuppressWarnings("unused")
	public static void writefile(String name, String data) {	
		try {
			BufferedWriter BW = new BufferedWriter(new FileWriter(name));
			BW.write(data);
			BW.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	// 3- print to console
	@SuppressWarnings("unused")
	public static void printText(String text) {
		System.out.println(text);
	}

	// 4- take input

	@SuppressWarnings("unused")
	public static String TakeInput() {
		Scanner in = new Scanner(System.in);
		String data = in.nextLine();

		return data;

	}

	private static void createProcess(int processID) {
		Process p = new Process(processID);
		ProcessTable.add(p);
		Process.setProcessState(p, ProcessState.Ready);
		System.out.println("TheStateOfTheProcessNowIs: "+p+" "+p.status);
		Ready.add(p);
		p.start();// to run without scheduler to test semaphore 
	}

	public static void main(String[] args) {
		ProcessTable = new ArrayList<Thread>();
		//createProcess(1);
		//createProcess(2);
		createProcess(3);
		createProcess(4);
		//createProcess(5);

			//Scheduler();//to run with schedular 
	}
	@SuppressWarnings("static-access")
	public static void Scheduler() {
		int n=0;
		Process p=Ready.peek();
		while (Ready.size()!=0) {
			if(p.status.equals(ProcessState.Ready)&n==0) {
				p.setProcessState(p, ProcessState.Running);
				p.start();
				Ready.remove(p);
				n++;
			}
			else if(p.status.equals(ProcessState.Running)){
				while(!(p.status.equals(ProcessState.Terminated))) {

				}
				if (p.status.equals(ProcessState.Terminated)) {
					n=0;
					p=Ready.peek();
				}
			}		
		}
	}
	public static Queue<Process> getReady() {
		return Ready;
	}


}
