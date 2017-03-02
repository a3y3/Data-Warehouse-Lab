package kmeans;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class KMeans 
{
    static int k;
    static String[] values;
    static int numOfItems = 0;
    ArrayList<Integer> dataValues;
    ArrayList<Integer> centz;
    ArrayList<Integer> oldCentz;
    ArrayList<Integer> row;
    ArrayList<ArrayList<Integer>> cluster;
    Scanner sc = new Scanner(System.in);
    public KMeans(int k, int numOfItems)
    {
        this.k = k; this.numOfItems = numOfItems;
        dataValues = new ArrayList<>();
        oldCentz = new ArrayList<>();
        centz = new ArrayList<>();
        row = new ArrayList<>();
        cluster = new ArrayList<>();
        for(int i = 0; i < k; i++)
            cluster.add(new ArrayList<>());
        for(int i = 0; i < numOfItems; i++)
        {
            dataValues.add(Integer.parseInt(values[i]));
            if(i<k)
            {
                centz.add(dataValues.get(i));
                System.out.println("Centroid " + i+1 + " is: " + centz.get(i));
            }
        }
            //clustering
        int iteration = 1;
        do
        {
            //calculating distance and adding to cluster
            for(int item: dataValues)
            {
                for(int c : centz)
                {
                    row.add(abs(item - c));
                }
                cluster.get(row.indexOf(Collections.min(row))).add(item);
                row.removeAll(row);
            }
            //checking convergence
            for(int i=0;i<k;i++)
            {
                if(iteration == 1)
                {
                    oldCentz.add(centz.get(i));
                }
                else
                {
                    oldCentz.set(i, centz.get(i));
                }
                if(!cluster.get(i).isEmpty())
                {
                    centz.set(i, average(cluster.get(i)));
                }
            }
            if (!centz.equals(oldCentz)) {
                for (int i = 0; i < cluster.size(); i++) {
                    cluster.get(i).removeAll(cluster.get(i));
                }
            }
            iteration++;		
        }
        while(!centz.equals(oldCentz));
        for(int i=0;i<k;i++)
        {
            System.out.println("New centroid " + i+1 + ": " + centz.get(i));
            for(int item: cluster.get(i))
                System.out.print(item + " ");
            System.out.println();
        }
        System.out.println("Number of iterations: " + iteration);
    }
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the value of k.");
        k = sc.nextInt();
        String csvFile = "C:/Users/Ishita Bedi/workspace/K_means/src/orange.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
            values = line.split(cvsSplitBy);
                numOfItems = values.length;
                System.out.println(numOfItems);
            }
        } 
        catch (Exception e){
            e.printStackTrace();
        }
        new KMeans(k, numOfItems);
    }
    public static int average(ArrayList<Integer> list)
    {
        int av = 0;
        for(Integer item:list)
        {
            av += item;
        }
        av = av/list.size();    	
        return av;
    }
}