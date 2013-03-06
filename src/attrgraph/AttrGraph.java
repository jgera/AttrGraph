/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package attrgraph;

import edu.uci.ics.jung.io.GraphIOException;
import japa.parser.ParseException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author itü
 */
public class AttrGraph {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, ParseException, IOException, SQLException, ClassNotFoundException, GraphIOException {
       
        Parser classParser=new Parser();
        //DBOperations dbop = new DBOperations();
        
        String url;
        //url = "/depot/Work/Academic/Students/PhD/Atakan Aral/javaParser";
        //url = "C:/Users/itü/Desktop/Dropbox/Dropbox/AtakanAral/JavaParser/javaParser";
        //url="/home/atakan/javaParser";
        url = "C:/javaParser";
        String outputPath = "C:/ClusteringResults/";
        String outputFile = url.replace(url.substring(0, url.lastIndexOf("/")+1), "");
        outputFile = outputPath + outputFile + ".txt";
        //url = "C:/Users/Atakan/Desktop/eclipse workspace/Structure101Test/src/test";
        
        //url = "C:/Users/Atakan Aral/Documents/NetBeansProjects/HighRandIndexSample";
        //url = "C:/Users/Atakan Aral/Documents/NetBeansProjects/LowRandIndexSample";
        //url = "C:/Users/Æ/Desktop/Dropbox/Tolga Ovatman/JavaParser/javaParser";
        for(File f : FileUtils.listFiles(new File(url), new String[]{"java"}, true)){
            classParser.parse(f);
        }
        
        //classParser.printClassNames();
        //classParser.printMethodNames();
        //classParser.printAttributeNames();
        //classParser.printParameterNames();
        //classParser.printMethodCalls();
        //classParser.printMethodClasses();

        //Visualizer.visualizeGraph(Grapher.getCallGraph(classParser.getMethods(), classParser.getCalls()));
        
        //Visualizer.visualizeGraph(Grapher.getAccessGraph(classParser.getMethods(), classParser.getAttributes(), classParser.getAccesses()));

        //Visualizer.visualizeGraph(Grapher.getCoopMethodsGraph(classParser.getMethods(), classParser.getCalls()));
        //Visualizer.visualizeGraph(Grapher.getMethodLayoutGraph(classParser.getMethods()));
        //Visualizer.visualizeGraph(Grapher.getCoopAttrsGraph(classParser.getAttributes(),classParser.getCalls(),classParser.getAccesses()));
        //Visualizer.visualizeGraph(Grapher.getAttrLayoutGraph(classParser.getAttributes()));
        
        //System.out.println(classParser.getInternalCalls().size());
        //System.out.println(classParser.getCalls().size());
        
        Clusterer c = new Clusterer();
        
        File file = new File(outputFile);
        File dir = new File(outputPath);
        dir.mkdir();
        file.createNewFile();
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter out = new BufferedWriter(fw);
        
        
        //System.out.println("Coop Methods Graph\n\na<-");
        out.write("a<-");
        out.write(c.cluster(Grapher.getCoopMethodsGraph(classParser.getMethods(), classParser.getCalls())));

        //System.out.println("\n\nMethod Call Graph\n\nb<-");
        out.write("\nb<-");
        out.write(c.cluster(Grapher.getCallGraph(classParser.getMethods(), classParser.getCalls())));
        
        //System.out.println("\n\nInternal Method Call Graph\n\nc<-");
        out.write("\nc<-");
        out.write(c.cluster(Grapher.getCallGraph(classParser.getMethods(), classParser.getInternalCalls())));

        //System.out.println("\n\nMethod Layout Graph\n\nd<-");
        out.write("\nd<-");
        out.write(c.cluster(Grapher.getMethodLayoutGraph(classParser.getMethods())));
        out.write("\nadjustedRandIndex(a,b)");
        out.write("\nadjustedRandIndex(a,c)");
        out.write("\nadjustedRandIndex(a,d)");
        out.write("\nadjustedRandIndex(b,c)");
        out.write("\nadjustedRandIndex(b,d)");
        out.write("\nadjustedRandIndex(c,d)\n");
        
        //out.write(textToSave);
        out.close();
        
        System.out.println("R script is written to " + outputFile);
        
        //GraphIO.writeGraphML(Grapher.getCallGraph(classParser.getMethods(), classParser.getCalls()),"/depot/Work/Academic/Students/PhD/Atakan Aral/NetworkX/parser.graphml");
        //Visualizer.visualizeCallGraph(GraphIO.readGraphML("/depot/Work/Academic/Students/PhD/Atakan Aral/NetworkX/parser.graphml"));
        
        //classParser.printConflicts();
        
        //dbop.connectDB();
        //dbop.createAppsDB();
        //dbop.insertApp("javaParser");
        //dbop.createClassesDB();
        //classParser.saveClassNames("javaParser",dbop);
        //dbop.createMethodsDB();
        //dbop.createParametersDB();
        //classParser.saveMethodNames(dbop);
        //dbop.createAttributesDB();
        //classParser.saveAttributeNames(dbop);
        //dbop.createMethodCallsDB();
        //dbop.createMethodCallParametersDB();
        //classParser.saveMethodCallNames(dbop);
        
        //dbop.closeCon();
    }
    

        
}
