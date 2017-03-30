import java.io.*;

/**
 * Created by student on 30/03/2017.
 */
public class Apriori {
    private String itemSep = " ";
    private String transactionFile = "transactions.txt";
    private int numItems;
    private int numTransactions;
    private double minSup;
    private int lines;
    private int transactionArray[][];
    int L1[];
    int C1[];
    int itemSetArray[];
    int itemSetCounter[];

    public static void main(String args[]) {
        Apriori apriori = new Apriori();
        apriori.readFile();
        apriori.transactionArray= new int[apriori.lines][apriori.numItems];
        System.out.println("Created transactionArray["+apriori.lines+"]["+apriori.numItems+"]");
        apriori.displayTransactions();
        apriori.generateItemSetArray();
        System.out.print("Items are:");
        for(int i = 0; i < apriori.numItems; i++){
            if(apriori.itemSetArray[i]!=0){
                System.out.print(apriori.itemSetArray[i]+"\t");
            }
        }
        System.out.println();
        apriori.generateL1();
        apriori.generateC1();
        apriori.generateL2();
    }

    public void readFile() {
        System.out.println("Reading file...");
        System.out.println("Regular transaction file with '" + itemSep + "' as delimter");
        try {
            FileInputStream fileInputStream = new FileInputStream(transactionFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            while (bufferedReader.readLine() != null) {
                lines++;
            }
            System.out.println("Found "+lines+" transactions");
            numItems = findNumberOfItemsets();
            numTransactions = lines;
            minSup = 0.2;

            System.out.println("Input configuration: " + numItems + " items, " + numTransactions + " transcations, " + minSup +" min support");
        }
        catch (IOException ioException) {
            System.out.println("" + ioException);
        }
    }
    public int findNumberOfItemsets(){
        return 5;
    }
    public void displayTransactions(){
        try{
            FileInputStream fileInputStream = new FileInputStream(transactionFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            int i = 0;
            while((line=bufferedReader.readLine())!=null){
                int columnCounter = 0;
                for(int j =0; j < line.length(); j++){
                    char ch = line.charAt(j);
                    if(ch != ' '){
                        transactionArray[i][columnCounter++] = ch-48;
                    }
                }
                i++;
            }
        }
        catch(IOException ioException){
            System.out.println("displayTransactions() threw a file exception");
        }

        for(int i = 0; i < transactionArray.length; i++){
            for(int j = 0; j < numItems; j++){
                System.out.print(transactionArray[i][j]+"\t");
            }
            System.out.println();
        }
    }
    public void generateItemSetArray(){
        itemSetArray = new int[numItems];
        for(int i = 0; i < numItems; i++){
            itemSetArray[i] = i+1;
        }
    }
    public void generateL1() {
        L1 = new int[numItems];
        itemSetCounter = new int[numItems +1];
        for(int i = 0; i <numItems; i++){
            int scanNumber = itemSetArray[i];
            for(int j = 0; j <transactionArray.length; j++){
                for(int k =0; k < numItems; k++){
                    if(scanNumber == transactionArray[j][k]){
                        itemSetCounter[scanNumber]++;
                    }
                }
            }
        }
        System.out.println("======L1======");
        for(int i = 1; i < numItems+1; i++){
            System.out.println((i)+":"+itemSetCounter[i]);
        }
    }

    public void generateC1(){
        System.out.println("======C1======");
        for(int i = 1; i <numItems+1; i++){
            if(itemSetCounter[i]!=0) {
                System.out.println((i) + ":" + itemSetCounter[i]);
            }
        }
        System.out.println("============");
    }

    public void generateL2(){
        for(int i = 1; i <numItems+1; i++){
            for(int j = 1; j< numItems+1; j++){
                if(itemSetCounter[i] != 0 && itemSetCounter[j] != 0 && i< j) {
                    System.out.println(i+","+j+""+countInL2(i, j));
                }


            }
        }
    }
    public int countInL2(int i, int j){
        int countI = 0;
        int countJ = 0;
        for(int x = 0; x < transactionArray.length; i++){
            for(int y = 0; y < numItems; y++){
                if(i == transactionArray[x][y]){
                    countI ++;
                }
            }
        }
        for(int x = 0; x < transactionArray.length; i++){
            for(int y = 0; y < numItems; y++){
                if(j == transactionArray[x][y]){
                    countJ ++;
                }
            }
        }
        return (Math.min(countI, countJ));
    }
}