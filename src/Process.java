//import java.util.concurrent.Semaphore;


public class Process extends Thread {

	public int processID;
	ProcessState status=ProcessState.New;	


	public Process(int m) {
		processID = m;
	}
	@Override
	public void run() {

		switch(processID)
		{
		case 1:process1();break;
		case 2:process2();break;
		case 3:process3();break;
		case 4:process4();break;
		case 5:process5();break;
		}

	}

	private void process1() {
		setProcessState(this,ProcessState.Running);
		
		OperatingSystem.S.semPrintWait(this);
		OperatingSystem.printText("Enter File Name to read data: ");

		OperatingSystem.S.semTakeWait(this);
		String FileName=OperatingSystem.TakeInput();
		
		
		OperatingSystem.S.semPrintPost();
        OperatingSystem.S.semTakePost();

		OperatingSystem.S.semReadWait(this);
		String Data=OperatingSystem.readFile(FileName);	
		OperatingSystem.S.semReadPost();

		OperatingSystem.S.semPrintWait(this);
		OperatingSystem.printText(Data);
		OperatingSystem.S.semPrintPost();


		setProcessState(this,ProcessState.Terminated);
		System.out.println("TheStateOfTheProcessNowIs: "+this+" "+this.status);

	}

	private void process2() {
		setProcessState(this,ProcessState.Running);

		OperatingSystem.S.semPrintWait(this);
		OperatingSystem.printText("Enter File Name to write to file : ");

		OperatingSystem.S.semTakeWait(this);
		String filename= OperatingSystem.TakeInput();
		
		OperatingSystem.S.semPrintPost();
		OperatingSystem.S.semTakePost();

		OperatingSystem.S.semPrintWait(this);
		OperatingSystem.printText("Enter Data: ");

		OperatingSystem.S.semTakeWait(this);
		String data= OperatingSystem.TakeInput();
		
    	OperatingSystem.S.semPrintPost();
		OperatingSystem.S.semTakePost();

		OperatingSystem.S.semWriteWait(this);
		OperatingSystem.writefile(filename,data);
		OperatingSystem.S.semWritePost();

		setProcessState(this,ProcessState.Terminated);	
		System.out.println("TheStateOfTheProcessNowIs: "+this+" "+this.status);

	}
	private void process3() {
		setProcessState(this,ProcessState.Running);

		OperatingSystem.S.semPrintWait(this);
		int x=0;
		while (x<301)
		{      
			OperatingSystem.printText(x+"\n");
			x++;
		}
		OperatingSystem.S.semPrintPost();
		setProcessState(this,ProcessState.Terminated);
		System.out.println("TheStateOfTheProcessNowIs: "+this+" "+this.status);

	}

	private void process4() {  
		setProcessState(this,ProcessState.Running);

		OperatingSystem.S.semPrintWait(this);
		int x=500;
		while (x<1001)
		{ 
			OperatingSystem.printText(x+"\n");
			x++;
		}	
		OperatingSystem.S.semPrintPost();
		setProcessState(this,ProcessState.Terminated);
		System.out.println("TheStateOfTheProcessNowIs: "+this+" "+this.status);

	}
	private void process5() {
		setProcessState(this,ProcessState.Running);

		OperatingSystem.S.semPrintWait(this);
		OperatingSystem.printText("Enter LowerBound: ");

		OperatingSystem.S.semTakeWait(this);
		String lower= OperatingSystem.TakeInput();
		
		OperatingSystem.S.semPrintPost();
		OperatingSystem.S.semTakePost();

		OperatingSystem.S.semPrintWait(this);
		OperatingSystem.printText("Enter UpperBound: ");

		OperatingSystem.S.semTakeWait(this);
		String upper= OperatingSystem.TakeInput();
		
		OperatingSystem.S.semPrintPost();
		OperatingSystem.S.semTakePost();

		int lowernbr=Integer.parseInt(lower);
		int uppernbr=Integer.parseInt(upper);
		String data="";

		while (lowernbr<=uppernbr)
		{
			data+=lowernbr++ +"\n";
		}	
		OperatingSystem.S.semWriteWait(this);
		OperatingSystem.writefile("P5.txt", data);
		OperatingSystem.S.semWritePost();
		setProcessState(this,ProcessState.Terminated);
		System.out.println("TheStateOfTheProcessNowIs: "+this+" "+this.status);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.processID+"";
	}

	public static void setProcessState(Process p, ProcessState s) {
		p.status=s;
		if (s == ProcessState.Terminated)
		{
			OperatingSystem.ProcessTable.remove(OperatingSystem.ProcessTable.indexOf(p));
		}
	}

	public static ProcessState getProcessState(Process p) {
		return p.status;
	}
}
