//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.sql.Array;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
     if(args.length!=1){
         System.out.print("Nu a-ti introdus doar valoarea lui n. \n");
     }
     int n=Integer.parseInt(args[0]);

     ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
     for(int i=0; i<n; i++)
     {
         ArrayList<Integer> linie = new ArrayList<>();
         for(int j=0; j<n; j++){
          linie.add(0);
         }
         matrix.add(linie);
     }

     for(int i=1; i<n; i++){
         matrix.get(0).set(i, 1);
         matrix.get(i).set(0, 1);
         if(i <n-1){
             matrix.get(i).set(i+1, 1);
             matrix.get(i+1).set(i, 1);
         } else {
             matrix.get(i).set(1, 1);
             matrix.get(1).set(i, 1);
         }
     }

     for(int i=0; i<n; i++) {
         for(int j=0; j<n; j++){
             System.out.print(matrix.get(i).get(j) + " ");
         }
         System.out.println();
     }

     int Cycle_Count = Cycles(matrix);
     System.out.print("Numarul de cicluri este " + Cycle_Count + " n^2 - 3n + 3 =" + (Math.pow(matrix.size(),2)-3*matrix.size() + 3));

    }
    public static int Cycles(ArrayList<ArrayList<Integer>> matrix){
        int n=matrix.size();
        int cycles=0;

        for(int i=0; i<n; i++){
            boolean[] visited = new boolean[n];
            cycles+=search_Cycles(matrix, visited, i, i , 1);
        }
        return cycles;
    }
    public static int search_Cycles(ArrayList<ArrayList<Integer>> matrix, boolean[] visited, int start, int current, int length){
        int n=matrix.size();
        int cycles=0;
        visited[current]= true;

        for(int i=0; i<n; i++){
            if(matrix.get(current).get(i)==1)
                cycles++;
            else
                if(!visited[i]){
                    cycles+=search_Cycles(matrix,visited, start, i, length+1);
                }
        }
        visited[current]=false;
        return cycles;
    }
}