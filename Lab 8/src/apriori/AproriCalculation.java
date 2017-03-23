package apriori;

import java.io.*;
import java.util.*;

class AprioriCalculation
{
    Vector<String> candidates = new Vector<String>();
    String configFile = "config.txt";
    String transactFile = "transact.txt";
    int numItems;
    int numTransactions;
    double minSup;
    int i;
    int j;
    String checkArray[]; //array used to read database transaction
    String itemSep = " "; //database item delimiter
    
    //generate the apriori itemsets
    public void aprioriProcess()
    {
        int itemsetNumber = 0;
        getConfig(); //get configuation
        
        System.out.println("Apriori algorithm has started\n");
        pressAnyKeyToContinue();
        
        do
        {
            //increase the itemset being generated
            itemsetNumber++;
            
            //generate the candidates
            generateCandidates(itemsetNumber);
            
            if(!candidates.isEmpty())
            {
                //determine and display frequent itemsets if candidate set is not null
                findFrequentItemsets(itemsetNumber);
                
                System.out.println("Frequent " + itemsetNumber + "-itemsets computed");
                System.out.print("L[" + itemsetNumber + "]: ");
                System.out.println(candidates);
                pressAnyKeyToContinue();
            }
        }while(candidates.size() != 0); //if there are 0 candidates, then the algorithm will end
        
        System.out.println("Apriori algorithm complete");
    }
    
    //get configuration
    public void getConfig()
    {
        System.out.println("Current configuration (read from file):");
        System.out.println("Regular transaction file with '" + itemSep + "' as delimiter");
        System.out.println("Configuration file: " + configFile);
        System.out.println("Transaction database file: " + transactFile);
        
        try
        {
            FileInputStream fin = new FileInputStream(configFile);
            BufferedReader buf = new BufferedReader(new InputStreamReader(fin));
            numItems = Integer.valueOf(buf.readLine());
            numTransactions = Integer.valueOf(buf.readLine());
            minSup = Double.valueOf(buf.readLine());
            
            System.out.print("\nInput configuration: " + numItems + " items, " + numTransactions + " transactions, ");
            System.out.println("minimum support = " + minSup + "%");
            System.out.println();
            minSup/=100.0;
            
            checkArray = new String[numItems];
            for(i = 0; i<checkArray.length; i++)
            {
                checkArray[i] = "1"; //all values initialized to 1
            }
        }
        catch(IOException ie)
        {
            System.out.println(ie);
        }
        pressAnyKeyToContinue();
    }
    
    //generate C[n]
    public void generateCandidates(int n)
    {
        Vector<String> tempCandidates = new Vector<String>();
        String str1, str2;
        StringTokenizer st1, st2;
        int s;
        
        if(n == 1) //if 1-itemset, candidates are just the numbers
        {
            for(i = 1; i<=numItems; i++)
            {
                tempCandidates.add(Integer.toString(i));
            }
        }
        else if(n == 2) //if 2-itemset, candidates are just all combination of 1-temsets
        {
            for(i = 0; i<candidates.size(); i++)
            {
                st1 = new StringTokenizer(candidates.get(i));
                str1 = st1.nextToken();
                for(j = i+1; j<candidates.size(); j++)
                {
                    st2 = new StringTokenizer(candidates.elementAt(j));
                    str2 = st2.nextToken();
                    tempCandidates.add(str1 + " " + str2);
                }
            }
        }
        else
        {
            for(i = 0; i<candidates.size(); i++)
            {
                for(j = i+1; j<candidates.size(); j++)
                {
                    str1 = new String();
                    str2 = new String();
                    
                    st1 = new StringTokenizer(candidates.get(i));
                    st2 = new StringTokenizer(candidates.get(j));
                    
                    for(s = 0; s<n-2; s++)
                    {
                        str1 = str1 + " " + st1.nextToken();
                        str2 = str2 + " " + st2.nextToken();
                    }
                    
                    //if they have the same n-2 tokens, condition satisfied
                    //perform apriori join
                    if(str2.compareToIgnoreCase(str1) == 0)
                    {
                        tempCandidates.add((str1 + " " + st1.nextToken() + " " + st2.nextToken()).trim());
                    }
                }
            }
        }
        
        //clear old candidates
        candidates.clear();
        //set new candidates
        candidates = new Vector<String>(tempCandidates);
        tempCandidates.clear();
    }

    //generate L[n]
    public void findFrequentItemsets(int n)
    {
        FileInputStream fin;
        BufferedReader buf;
        
        Vector<String> frequentCandidates = new Vector<String>();
        StringTokenizer st, stFile; //tokenizer for candidate and transaction
        boolean match; //flag whether the transaction has all the items in an itemset
        boolean trans[] = new boolean[numItems]; //array to hold transaction
        int count[] = new int[candidates.size()]; //number of successful matches
        int c;
        
        try
        {
            fin = new FileInputStream(transactFile);
            buf = new BufferedReader(new InputStreamReader(fin));

            //for each transaction
            for(i = 0; i<numTransactions; i++)
            {
                stFile = new StringTokenizer(buf.readLine(), itemSep); //read a line from the file to the tokenizer
                //put the contents of that line into the transaction array
                for(j = 0; j<numItems; j++)
                {
                    trans[j] = (stFile.nextToken().compareToIgnoreCase(checkArray[j]) == 0);
                    //if read value matches checkArray value (1), read value (value in the transaction) is also 1
                    //else, read value (value in the transaction) is 0                    
                }
                
                //check each candidate
                for(c = 0; c<candidates.size(); c++)
                {
                    match = false; //reset match to false
                    //tokenize the candidate so that we know what items need to be present for a match
                    st = new StringTokenizer(candidates.get(c));
                    //check each item in the itemset to see if it is present in the transaction
                    while(st.hasMoreTokens())
                    {
                        match = (trans[Integer.valueOf(st.nextToken())-1]);
                        if(!match) //if it is not present in the transaction stop checking
                        {
                            break;
                        }
                    }
                    if(match) //if it is a match, increase the count
                    {
                        count[c]++;
                    }
                }
            }
            for(i = 0; i<candidates.size(); i++)
            {
                //if the count is greater than the support, add to frequent candidates
                if((count[i]/(double)numTransactions) >= minSup)
                {
                    frequentCandidates.add(candidates.get(i));
                }
            }
        }
        catch(IOException ie)
        {
            System.out.println(ie);
        }
        
        //clear old candidates
        candidates.clear();
        //new candidates are old frequent candidates
        candidates = new Vector<String>(frequentCandidates);
        frequentCandidates.clear();
    }
    
    //press any key to continue
    public void pressAnyKeyToContinue()
    {
        System.out.println("Press any key to continue...");
        try
        {
            System.in.read();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }  
    }
}