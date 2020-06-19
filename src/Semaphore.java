import java.util.LinkedList;
import java.util.Queue;

public class Semaphore {
	public static int Read=1;
	public static int Print=1;
	public static int Take=1;
	public static int Write;
	public static Queue<Process> ReadProcesses;
	public static Queue<Process> PrintProcesses ;
	public static Queue<Process> TakeProcesses;
	public static Queue<Process> WriteProcesses;

	public Semaphore() {
		Read=1;
		Print=1;
		Take=1;
		Write=1;
		ReadProcesses= new LinkedList<Process>();
		PrintProcesses =new LinkedList<Process>();
		TakeProcesses= new LinkedList<Process>();
		WriteProcesses= new LinkedList<Process>();

	}

	@SuppressWarnings({ "deprecation", "static-access" })
	public  void semPrintPost() {		
		if(PrintProcesses.isEmpty()) {
			Print=1;
		}
		else {
			Process a= PrintProcesses.remove();
			
			OperatingSystem.Ready.add(a);
			
			System.out.println("TheStateOfTheProcess: "+a+" Was:"+a.status);
			
			a.setProcessState(a, ProcessState.Ready);
			
			System.out.println("TheStateOfTheProcess: "+a+" NowIs: "+a.status);
			
			a.resume();
			
			a.setProcessState(a, ProcessState.Running);
			
			System.out.println("TheStateOfTheProcess: "+a+" NowIs: "+a.status);
		}
	}

	@SuppressWarnings({ "deprecation", "static-access" })
	public  void semReadPost() {
		if(ReadProcesses.isEmpty())
			Read=1;
		else {
			Process a= ReadProcesses.remove();
			
			OperatingSystem.Ready.add(a);
			
			System.out.println("TheStateOfTheProcess: "+a+" Was: "+a.status);
			
			a.setProcessState(a, ProcessState.Ready);
			
			System.out.println("TheStateOfTheProcess: "+a+" NowIs: "+a.status);
			
			a.resume();
			
			a.setProcessState(a, ProcessState.Running);
			
			System.out.println("TheStateOfTheProcess: "+a+" NowIs: "+a.status);
		}
	}

	@SuppressWarnings({ "deprecation", "static-access" })
	public  void semWritePost() {
		if(WriteProcesses.isEmpty())
			Write=1;
		else {
			Process a= WriteProcesses.remove();
			
			OperatingSystem.Ready.add(a);
			
			System.out.println("TheStateOfTheProcess: "+a+" Was; "+a.status);
			
			a.setProcessState(a, ProcessState.Ready);
			
			System.out.println("TheStateOfTheProcess: "+a+" NowIs: "+a.status);
			
			a.resume();
			
			a.setProcessState(a, ProcessState.Running);
			
			System.out.println("TheStateOfTheProcess: "+a+" NowIs: "+a.status);
		}
	}

	@SuppressWarnings({ "deprecation", "static-access" })
	public  void semTakePost() {
		if(TakeProcesses.isEmpty())
			Take=1;
		else {
			Process a= TakeProcesses.remove();

			OperatingSystem.Ready.add(a);

			System.out.println("TheStateOfTheProcess: "+a+" Was: "+a.status);
			
			a.setProcessState(a, ProcessState.Ready);
			
			System.out.println("TheStateOfTheProcess: "+a+" NowIs: "+a.status);
			
			a.resume();
			
			a.setProcessState(a, ProcessState.Running);
			
			System.out.println("TheStateOfTheProcessNowIs: "+a+" "+a.status+" TheSemaIs: Take");

		}
	}

	@SuppressWarnings({ "deprecation", "static-access" })
	public void semPrintWait(Process p) {
		if(Print==1) {
			Print=0;
		}
		else {
			PrintProcesses.add(p);
			System.out.println("TheStateOfTheProcess: "+p+" Was: "+p.status);
			p.setProcessState(p,ProcessState.Waiting);
			System.out.println("TheStateOfTheProcess: "+p+" NowIs: "+p.status);
			p.suspend();
		}
	}

	@SuppressWarnings({ "deprecation", "static-access" })
	public  void semReadWait(Process p) {
		if(Read==1) {
			Read=0;
		}
		else {
			ReadProcesses.add(p);
			System.out.println("TheStateOfTheProcess: "+p+" Was: "+p.status);
			p.setProcessState(p,ProcessState.Waiting);
			System.out.println("TheStateOfTheProcess: "+p+" NowIs: "+p.status);
			p.suspend();
		}

	}


	@SuppressWarnings({ "deprecation", "static-access" })
	public  void semWriteWait(Process p) {
		if(Write==1)
			Write=0;
		else{
			WriteProcesses.add(p);
			System.out.println("TheStateOfTheProcess:  "+p+" Was:  "+p.status);
			p.setProcessState(p,ProcessState.Waiting);
			System.out.println("TheStateOfTheProcess: "+p+" NowIs: "+p.status);
			p.suspend();
		}
	}


	@SuppressWarnings({ "deprecation", "static-access" })
	public  void semTakeWait(Process p) {
		if(Take==1)
			Take=0;
		else{
			TakeProcesses.add(p);
			System.out.println("TheStateOfTheProcess:  "+p+" Was: "+p.status);
			p.setProcessState(p,ProcessState.Waiting);
			System.out.println("TheStateOfTheProcess:  "+p+" NowIs: "+p.status);
			p.suspend();
		}
	}
}
