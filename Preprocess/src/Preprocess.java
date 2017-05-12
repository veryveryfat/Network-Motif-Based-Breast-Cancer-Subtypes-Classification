
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Preprocess {
	
	public static void main(String[] args) throws IOException {
		
		Scanner scanner1 = new Scanner(new File("label"), "latin1");
		Scanner scanner2 = new Scanner(new File("raw.txt"), "latin1");
		
		BufferedWriter out0 = new BufferedWriter(new FileWriter("all_sample"));
		BufferedWriter out1 = new BufferedWriter(new FileWriter("lumA"));
		BufferedWriter out2 = new BufferedWriter(new FileWriter("lumB"));
		BufferedWriter out3 = new BufferedWriter(new FileWriter("Basal"));
		BufferedWriter out4 = new BufferedWriter(new FileWriter("Her2"));

		
		List<String> label = new ArrayList<>();
		while (scanner1 != null && scanner1.hasNextLine()) {
        	String line = scanner1.nextLine();
        	label.add(line);
        }
		
		System.out.println(label.size());
		
		
		String header = scanner2.nextLine();
		out0.write(header);
		
		String[] headerRef = header.split("\t");
		String first = headerRef[0];
		
       	out1.write(first);
    	out2.write(first);
    	out3.write(first);
    	out4.write(first);
    	
    	for (int i = 1; i < headerRef.length; i++) {
    		if (label.get(i-1).equals("LumA")) {
    			out1.write("\t");
    			out1.write(headerRef[i]);
    		}
    		if (label.get(i-1).equals("LumB")) {
    			out2.write("\t");
    			out2.write(headerRef[i]);
    		}
    		if (label.get(i-1).equals("Basal")) {
    			out3.write("\t");
    			out3.write(headerRef[i]);
    		}
    		if (label.get(i-1).equals("Her2")) {
    			out4.write("\t");
    			out4.write(headerRef[i]);
    		}
    	}

		
        while (scanner2 != null && scanner2.hasNextLine()) {
        	String line = scanner2.nextLine();
        	String[] data = line.split("\t");
        	int number = line.split("\t+").length;
        	//System.out.println(number);
        	
        	if (number != 467) {
        		continue;
        	}
        	out0.write("\n");
    		out1.write("\n");
    		out2.write("\n");
    		out3.write("\n");
    		out4.write("\n");
    		
        	out0.write(line);
        	
        	String gene = data[0];        	
        	out1.write(gene);
        	out2.write(gene);
        	out3.write(gene);
        	out4.write(gene);
        	
        	for (int i = 1; i < data.length; i++) {
        		if (label.get(i-1).equals("LumA")) {
        			out1.write("\t");
        			out1.write(data[i]);
        		}
        		if (label.get(i-1).equals("LumB")) {
        			out2.write("\t");
        			out2.write(data[i]);
        		}
        		if (label.get(i-1).equals("Basal")) {
        			out3.write("\t");
        			out3.write(data[i]);
        		}
        		if (label.get(i-1).equals("Her2")) {
        			out4.write("\t");
        			out4.write(data[i]);
        		}
        	}
        }
        
        scanner1.close();
        scanner2.close();
        
        out0.close();
        out1.close();
        out2.close();
        out3.close();
        out4.close();
	}
	
	
}
