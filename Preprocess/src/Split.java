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

public class Split {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//A_motif_1_P0P A_motif_1_X0X A_motif_2_PP0 2_XX0 motif3_P0p motif3_X0x
		Scanner scanner1 = new Scanner(new File("label"), "latin1");
		//all_sample_relevant_T
		Scanner scanner2 = new Scanner(new File("Z_all_sample_relevant.csv"), "latin1");
		
		Map<String, Integer> gene_pos = new HashMap<> ();
		
		BufferedWriter out0 = new BufferedWriter(new FileWriter("./Data/Z_all_sample_relevant_train.csv"));
		BufferedWriter out1 = new BufferedWriter(new FileWriter("./Data/Z_all_sample_relevant_train_label"));
		BufferedWriter out2 = new BufferedWriter(new FileWriter("./Data/Z_all_sample_relevant_test.csv"));
		BufferedWriter out3 = new BufferedWriter(new FileWriter("./Data/Z_all_sample_relevant_test_label"));
		
		List<String> label = new ArrayList<>();
		while (scanner1 != null && scanner1.hasNextLine()) {
        	String line = scanner1.nextLine();
        	label.add(line);
        }
		
		int laTotal = 210; //344 - 134;
		int lbTotal = 114; //458 -344
		int bsTotal = 81;
		int h2Total = 53;
		int nTotal = 8;
		
		int laCount = 0;
		int lbCount = 0;
		int bsCount = 0;
		int h2Count = 0;
		int nCount = 0;
		
		int numLine = 0;
		while (scanner2 != null && scanner2.hasNextLine()) {
        	String line = scanner2.nextLine();
        	String tag = label.get(numLine);
    		if (tag.equals("LumA")) {
    			laCount++;
    			if (laCount < laTotal * 0.8) {
    				out0.write(line);
    				out0.write("\n");
    				out1.write(tag);
    				out1.write("\n");
    			} else {
    				out2.write(line);
    				out2.write("\n");
    				out3.write(tag);
    				out3.write("\n");
    			}
    		}
    		if (tag.equals("LumB")) {
    			lbCount++;
    			if (lbCount < lbTotal * 0.8) {
    				out0.write(line);
    				out0.write("\n");
    				out1.write(tag);
    				out1.write("\n");
    			} else {
    				out2.write(line);
    				out2.write("\n");
    				out3.write(tag);
    				out3.write("\n");
    			}
    		}
    		if (tag.equals("Basal")) {
    			bsCount++;
    			if (bsCount < bsTotal * 0.8) {
    				out0.write(line);
    				out0.write("\n");
    				out1.write(tag);
    				out1.write("\n");
    			} else {
    				out2.write(line);
    				out2.write("\n");
    				out3.write(tag);
    				out3.write("\n");
    			}
    		}
    		if (tag.equals("Her2")) {
    			h2Count++;
    			if (h2Count < h2Total * 0.8) {
    				out0.write(line);
    				out0.write("\n");
    				out1.write(tag);
    				out1.write("\n");
    			} else {
    				out2.write(line);
    				out2.write("\n");
    				out3.write(tag);
    				out3.write("\n");
    			}
    		}
    		if (tag.equals("Normal")) {
    			nCount++;
    			if (nCount < nTotal * 0.8) {
    				out0.write(line);
    				out0.write("\n");
    				out1.write(tag);
    				out1.write("\n");
    			} else {
    				out2.write(line);
    				out2.write("\n");
    				out3.write(tag);
    				out3.write("\n");
    			}
    		}
    		numLine++;
        }
		out0.close();
		out1.close();
		out2.close();
		out3.close();
	}

}
