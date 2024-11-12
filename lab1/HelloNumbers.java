public class HelloNumbers {
    public static void main(String[] args) {
        int x = 1;
        int total = 0;
        while (x <= 10) {
            System.out.print(total + " ");
            total = total + x;
            x = x + 1;
        }
	}
}
public static class Walrus{
    public int weight;
    public double  tuskSize;
    public Walrus(int w, double ts){
        weight = w;
        tuskSize = ts;
    }
}
public static void main(String[] args){
    Walrus walrus = new Walrus(3500,10.5);
    int x = 9;
    doStuff(Walrus,x);
    System.out.println(walrus);
    System.out.println(x);
}
public static void doStuff(Walrus W,int x){
    W.weight = W.weight - 100;
    x = x - 5;
}