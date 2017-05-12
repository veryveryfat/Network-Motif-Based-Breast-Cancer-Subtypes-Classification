import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FilterData {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner1 = new Scanner(new File("nodes.csv"), "latin1");
		Scanner scanner2 = new Scanner(new File("all_sample"), "latin1");
//		Scanner scanner1 = new Scanner(new File("label"), "latin1");
//		Scanner scanner2 = new Scanner(new File("raw.txt"), "latin1");
//		Scanner scanner1 = new Scanner(new File("label"), "latin1");
//		Scanner scanner2 = new Scanner(new File("raw.txt"), "latin1");
		
		BufferedWriter out0 = new BufferedWriter(new FileWriter("all_sample_relevant.csv"));
		
		BufferedWriter out1 = new BufferedWriter(new FileWriter("gene_order"));
		
		Set<String> nodes = new HashSet<>();

		
		while (scanner1 != null && scanner1.hasNextLine()) {
			System.out.println("1");
        	String line = scanner1.nextLine();
        	nodes.add(line.toLowerCase());
        }
		
		String header = scanner2.nextLine();
		//out0.write(header);
		System.out.println(nodes.size());
		
        while (scanner2 != null && scanner2.hasNextLine()) {
        	String line = scanner2.nextLine();
        	String gene = line.split("\t")[0].toLowerCase();

        	//System.out.println(number);
        	
        	if (!nodes.contains(gene)) {
        		continue;
        	}
        	
        	
        	String[] data = line.split("\t");
        	out1.write(gene);
        	out1.write("\n");
        	
        	out0.write(data[1]);        	
        	for (int i = 2; i < data.length; i++) {
        		out0.write("\t");
        		out0.write(data[i]);
        	}
        	out0.write("\n");
        }     
        
        out0.close();
        out1.close();
	}
	
}
