package main;

public class Main {

	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		Laby laby1 = new Laby("rect_05.txt");
		laby1.solve();
		long end = System.currentTimeMillis();
		System.out.println("Elapsed Time in milli seconds: "+ (end-start));	
	}
}
