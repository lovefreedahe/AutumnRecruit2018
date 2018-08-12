public class Main {
    public static int help(boolean[][] a, int i, int j){
        int count = 0;
        for (int k = 0; k < a.length; k++){
            if (a[i][k]&&a[j][k]) count++;
        }
        return count;
    }
    public static int solve(boolean[][] a, int num){
        if (a.length <= 1) return -1;
        int max = -1;
        int index = -1;
        for (int i = 0; i < a.length; i++){
            if (i == num) continue;
            if (a[num][i]) continue;
            int temp = help(a, num, i);
            if (temp > max){
                max = temp;
                index = i;
            }
        }
        if (max == 0) return -1;
        return index;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int num = scanner.nextInt();
        scanner.nextLine();
        boolean[][] a = new boolean[N][N];
        for (int i = 0; i < N; i++){
           String input = scanner.nextLine();
           if (input!=null && input.length()!=0) {
               String[] strs = input.split(" ");
               for (int j = 0; j < strs.length; j++) {
                   a[i][Integer.parseInt(strs[j])] = true;
               }
           }else continue;
        }
        System.out.println(solve(a, num));
    }
} 