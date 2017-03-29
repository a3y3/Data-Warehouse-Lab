import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Soham on 29-Mar-17.
 */
public class DynamicItemsetCounting {
    String itemSep = " ";
    String configFile = "config.txt";
    String transactionFile = "transact.txt";
    int numItems;
    int numTransactions;
    double minSup;
    public void dicProcess(){
        getConfig();
        System.out.println("Let the DIC begin");
        startDIC();
    }
    public void getConfig(){
        System.out.println("Configuration reading...");
        System.out.println("Regular transaction file with '"+ itemSep +"' as delimter");
        System.out.println("Configuration file:"+ configFile);
        System.out.println("Transaction database file:"+ transactionFile);

        try{
            FileInputStream fileInputStream = new FileInputStream(configFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            numItems = Integer.valueOf(bufferedReader.readLine());
            numTransactions = Integer.valueOf(bufferedReader.readLine());
            minSup = Double.valueOf(bufferedReader.readLine());

            System.out.println("Input configuration: " +numItems+ " items, "+ numTransactions+" transcations, "+ minSup+"% min support");
            minSup /= 100;


        }
        catch (IOException exception){
            System.out.println(exception);
        }

    }

    public void startDIC(){

    }
}
