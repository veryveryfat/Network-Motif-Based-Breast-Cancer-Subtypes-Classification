import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MotifExpression {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// motif1_P0P motif1_X0X motif2_PP0 motif2_XX0 motif3_P0p motif3_X0x
		Scanner scanner1 = new Scanner(new File("./motifs/motif3_X0x"), "latin1");
		Scanner scanner2 = new Scanner(new File("Z_all_sample_relevant.csv"), "latin1");
		Scanner scanner0 = new Scanner(new File("gene_order"), "latin1");
		
		Map<String, Integer> gene_pos = new HashMap<> ();
		
		BufferedWriter out0 = new BufferedWriter(new FileWriter("A_motif_3_X0x.csv"));
		
		int pos = 0;
		while (scanner0 != null && scanner0.hasNextLine()) {
        	String line = scanner0.nextLine().toLowerCase();
        	System.out.println(pos);
        	gene_pos.put(line, pos++);
        }
		
		List<Motif> motifs = new ArrayList<>();
		
		
		while (scanner1 != null && scanner1.hasNextLine()) {
        	String line = scanner1.nextLine().toLowerCase();
        	String[] nodes = line.split(";");
        	
        	Motif subGraph = new Motif();
        	
        	boolean valid = true;
        	for(int i = 0; i < nodes.length; i++ ) {
        		if (!gene_pos.containsKey(nodes[i])) {
        			System.out.println("error with " + line + ": missing data of" + nodes[i]);
        			valid = false;
        			break;
        		}
        		subGraph.setNode(i, gene_pos.get(nodes[i]));
        	}        	
        	if(valid) {
        		motifs.add(subGraph);
        	}
        }
		System.out.print("total valid subgraph for current motif:" + motifs.size());

		
		while (scanner2 != null && scanner2.hasNextLine()) {
        	String[] dataStr = scanner2.nextLine().split(",");
        	System.out.println(dataStr.length);
        	double[] data = new double[dataStr.length];
        	
        	for (int i = 0; i < dataStr.length; i++) {
        		data[i] = Double.parseDouble(dataStr[i]);
        	}
        	
        	
        	for (int i = 0; i < motifs.size(); i++) {
        		double[] a = new double[3];
        		double value = 0;
        		for (int j = 0; j < 3; j++) {
        			a[j] = data[motifs.get(i).getNodePos(j)];
        			System.out.print(a[j] + "\t");
        			value += a[j]/Math.sqrt(3);        			
        		}
        		System.out.print("active akj: " + value + "\t\t"); 
        		out0.write(Double.toString(value));
        		if(i != motifs.size() - 1) {
        			out0.write(",");
        		}
        	}
        	
        	out0.write("\n");      	
        }
		
		out0.close();
		System.out.println();
		System.out.print("total valid subgraph for current motif:" + motifs.size());
	}
	
	private static class Motif {
		int node1;
		int node2;
		int node3;
		
		public void setNode(int node, int pos) {
			if (node == 0) node1 = pos;
			if (node == 1) node2 = pos;
			if (node == 2) node3 = pos;
		}
		
		public int getNodePos(int node) {
			if (node == 0) return node1;
			if (node == 1) return node2;
			if (node == 2) return node3;
			
			return -1;
		}
	}
}
