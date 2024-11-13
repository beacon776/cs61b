package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns2=new AList<>();
        AList<Double> times2=new AList<>();
        AList<Integer> opCounts2 =new AList<>();

        for(int i=1;i<=8;i++){
            int N=pow(2,i-1)*1000;
            SLList<Integer> L2=new SLList<>();
            Stopwatch sw = new Stopwatch();
            for(int j=0;j<N;j++) L2.addLast(j);

            Ns2.addLast(N);
            double timeInSeconds = sw.elapsedTime();
            times2.addLast(timeInSeconds);
            int count=10000;
            opCounts2.addLast(count);
        }
        printTimingTable(Ns2,times2,opCounts2);
    }
    private static int pow (int a,int b){
        int resurt=1;
        while(b>0){
            b--;
            resurt*=a;
        }
        return resurt;
    }
}
