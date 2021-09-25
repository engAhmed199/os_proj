/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

/**
 *
 * @author alial
 */
public class RRC {
    public float avgctime;
    public float avgwtime;
    public String pName[];
    public int aTime[];
    public int wTime[];
    public int com[];
    public int burst[];
    public int nop;
    public String gantChart[];
    public void roundRobin(String p[], int a[],int b[], int n) 
    { 
        // result of average times 
        int res = 0; 
        int resc = 0; 
  
        // for sequence storage 
        String seq = new String(); 
  
        // copy the burst array and arrival array 
        // for not effecting the actual array
        this.nop=p.length;
        
        int res_b[] = new int[nop]; 
        int res_a[] = new int[nop]; 
        this.pName=new String[nop];
        this.aTime=new int [nop];
        this.wTime=new int[nop];
        this.com=new int[nop];
        this.burst=new int [nop];
        for (int i = 0; i < nop; i++) { 
            res_b[i] = b[i]; 
            res_a[i] = a[i]; 
        } 
  
        // critical time of system 
        int t = 0; 
  
        // to store the waiting time 
        int w[] = new int[nop]; 
  
        // to store the Completion time 
        int comp[] = new int[nop]; 
  
        while (true) { 
            boolean flag = true; 
            for (int i = 0; i < nop; i++) { 
  
                
                // arrival is not on zero 
  
                // check that if there come before qtime 
                if (res_a[i] <= t) { 
                    if (res_a[i] <= n) { 
                        if (res_b[i] > 0) { 
                            flag = false; 
                            if (res_b[i] > n) { 
  
                                // make decrease the b time 
                                t = t + n; 
                                res_b[i] = res_b[i] - n; 
                                res_a[i] = res_a[i] + n; 
                                seq += "->" + p[i]; 
                            } 
                            else { 
  
                                // for last time 
                                t = t + res_b[i]; 
  
                                // store comp time 
                                comp[i] = t - a[i]; 
  
                                // store wait time 
                                w[i] = t - b[i] - a[i]; 
                                res_b[i] = 0; 
  
                                // add sequence 
                                seq += "->" + p[i]; 
                            } 
                        } 
                    } 
                    else if (res_a[i] > n) { 
  
                        // is any have less arrival time 
                        // the coming process then execute them 
                        for (int j = 0; j < nop; j++) { 
  
                            // compare 
                            if (res_a[j] < res_a[i]) { 
                                if (res_b[j] > 0) { 
                                    flag = false; 
                                    if (res_b[j] > n) { 
                                        t = t + n; 
                                        res_b[j] = res_b[j] - n; 
                                        res_a[j] = res_a[j] + n; 
                                        seq += "->" + p[j]; 
                                    } 
                                    else { 
                                        t = t + res_b[j]; 
                                        comp[j] = t - a[j]; 
                                        w[j] = t - b[j] - a[j]; 
                                        res_b[j] = 0; 
                                        seq += "->" + p[j]; 
                                    } 
                                } 
                            } 
                        } 
  
                        // now the previous porcess according to 
                        // its is process 
                        if (res_b[i] > 0) { 
                            flag = false; 
  
                            // Check for greaters 
                            if (res_b[i] > n) { 
                                t = t + n; 
                                res_b[i] = res_b[i] - n; 
                                res_a[i] = res_a[i] + n; 
                                seq += "->" + p[i]; 
                            } 
                            else { 
                                t = t + res_b[i]; 
                                comp[i] = t - a[i]; 
                                w[i] = t - b[i] - a[i]; 
                                res_b[i] = 0; 
                                seq += "->" + p[i]; 
                            } 
                        } 
                    } 
                } 
  
                // if no process is come on thse critical 
                else if (res_a[i] > t) { 
                    t++; 
                    i--; 
                } 
            } 
            // for exit the while loop 
            if (flag) { 
                break; 
            } 
        } 
  
//        System.out.println("name of process  arrival time  burst time  compilation time  waiting time"); 
        for (int i = 0; i < nop; i++) { 
            this.aTime[i]=a[i];
            this.pName[i]=p[i];
            this.wTime[i]=w[i];
            this.com[i]=comp[i];
            this.burst[i]=b[i];
//            System.out.println(" " + p[i] + "           "+ a[i]+"          "+b[i]+"         "+ comp[i] 
//                               + "           " + w[i]); 
  
            res = res + w[i]; 
            resc = resc + comp[i]; 
        } 
      
          
        
        this.avgwtime=(float)res / nop;
        this.avgctime=(float)resc/ nop;
        this.gantChart=seq.split("->");
        System.out.println("Sequence is like that " + seq); 

//         System.out.println("Average waiting time is "
//                           + (float)res / nop);
//        System.out.println("Average compilation  time is "
//                           + (float)resc / nop); 
    }
}

