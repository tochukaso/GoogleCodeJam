
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.util.Collections.*;

import java.math.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class Google1C_b {
    
//    private static final String INPUT_PATH = "C:\\Users\\Yasu\\Downloads\\B-large.in";

  private static final String INPUT_PATH = "C:\\Users\\Yasu\\Downloads\\B-small-practice.in";

//    private static final String INPUT_PATH = "C:\\Users\\Yasu\\Downloads\\googleCodeJam\\q1_test.txt";
    
    private static final String OUTPUT_PATH = "C:\\Users\\Yasu\\Downloads\\googleCodeJam\\q1_test_answer.txt";

    int N;

    List<String> qList;
    List<String> answerList = new ArrayList<String>();
    
    public static void main(String[] args) {
        Google1C_b app = new Google1C_b();
        
        app.solve();
    }

    static final String WON = "Fegla Won";
    
    static final int DIV =  1000000007;
    
    String[] strArray;
    
    int strCnt;

    int sum;
    
    Map<String, Long> map;

    public void solve() { 
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(INPUT_PATH))));
            N = readInt();
            for (int testCase = 0; testCase < N; testCase++) {
                strCnt = readInt();
                strArray = readStrArray();
                answerList.add(String.valueOf(calc(strArray)));
            
            }
            writeFile(OUTPUT_PATH, answerList);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception igunore) {}
        }
    }

    static final int MOD = 1_000_000_007;

    public static long calc(String[] cars) {
        long result = 1;
        int n = cars.length;
    
        for (char ch = 'a'; ch <= 'z'; ch++) {
            //System.out.println(java.util.Arrays.toString(cars));
            int solocount = 0;
            int start = -1;
            int mid = -1;
            int end = -1;
            int solo = -1;
            for (int i=0; i<n; i++) {
                if (cars[i] == null) {
                }
                else if (cars[i].matches(""+ch+'+')) {
                    solocount++;
                    result *= solocount;
                    result %= MOD;
                    cars[i] = null;
                    solo = i;
                }
                else if (cars[i].matches(ch+"+[^"+ch+"]+")) {
                    if (start != -1) return 0;
                    start = i;
                }
                else if (cars[i].matches("[^"+ch+"]+"+ch+"+")) {
                    if (end != -1) return 0;
                    end = i;
                }
                else if (cars[i].matches("[^"+ch+"]+"+ch+"+[^"+ch+"]+")) {
                    if (mid != -1) return 0;
                    mid = i;
                }
                else if (cars[i].matches("[^"+ch+"]+")) {
                }
                else return 0;
            }
            //System.out.println(ch+" "+solocount+" "+start+" "+solo+" "+end+" "+result);
            if (mid == -1 && solocount == 0 && start == -1 && end == -1)
                continue;
            if (mid != -1 && (solocount > 0 || start != -1 || end != -1))
                return 0;
            if (mid != -1)
                continue;
            String repl = "";
            if (end != -1) {repl += cars[end]; cars[end] = null;}
            if (solo != -1) repl += ch;
            if (start != -1) {repl += cars[start]; cars[start] = null;}
            if (start != -1) {cars[start] = repl;}
            else if (solo != -1) {cars[solo] = repl;}
            else cars[end] = repl;
        }
        int left = 0;
        for (String s : cars) if (s != null) {
            left ++;
            result *= left; result %= MOD;
        }
        return result;
    }

    

    
    public void solve_b() { 
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(INPUT_PATH))));
            N = readInt();
            for (int testCase = 0; testCase < N; testCase++) {
                strCnt = readInt();
                strArray = readStrArray();
                sum = 0;
                
                int[] intArr = new int[strCnt];
                for (int i = 0; i < strCnt; i++) {
                    intArr[i] = i;
                }
                
                map = new HashMap<String, Long>();

                for (int i = 0; i < strCnt; i++) {
                    String before = strArray[i];
                    char beforeC = before.charAt(before.length() - 1);
                    for (int j = 0; j < strCnt; j++) {
                        if (i == j) continue;
                        
                        String next = strArray[j];
                        char nextC = next.charAt(0);

                        if (beforeC == nextC) {
                            String key = String.valueOf(before.charAt(0)) + String.valueOf(nextC);
                            
                            Long val = map.get(key);
                            if (val == null) val = 0l;
                            val++;
                            map.put(key, val);
                        }
                    }
                }

                boolean isMatch = true;
                Map<String, Long> margeMap = null;
                long roopCnt = 0;
                while(isMatch) {
                    margeMap = new HashMap<String, Long>();
                    isMatch = false;
                    Set<String> used = new HashSet<String>();
                    for (Map.Entry<String, Long> before : map.entrySet()) {

                        String beforeKey = before.getKey();
                        if (used.contains(beforeKey)) continue;

                        boolean isUpdate = false;
                        
                        long perm = before.getValue();
                        
                        for (Map.Entry<String, Long> next : map.entrySet()) {
                            String nextKey = next.getKey();
                            if (used.contains(nextKey)) continue;

                            if (beforeKey.equals(nextKey)) continue;
                            
                            if (beforeKey.charAt(beforeKey.length() - 1) == nextKey.charAt(0)) {
                                isUpdate = true;
                                isMatch = true;
                                long nextPerm = next.getValue();
                                margeMap.put(beforeKey + nextKey, perm * nextPerm);
                                used.add(beforeKey);
                                used.add(nextKey);
                            }
                        }
                        
                        if (!isUpdate) {
                            margeMap.put(beforeKey, perm);
                            used.add(beforeKey);
                        }
                    }
                    map = margeMap;
                }

                long groupCnt = map.size();
                long groupPerm = Math.min(groupCnt, 1);
                for (long i = groupCnt % DIV; i > 0; i --) {
                    groupPerm *= i;
                }
                
                for (Map.Entry<String, Long> next : map.entrySet()) {
                    for (long i = next.getValue(); i > 0; i --) {
                        groupPerm = groupPerm * i % DIV;
                    }
                }
                
//                generate(intArr, 0, strCnt - 1);
                
                System.out.println(groupPerm);
                answerList.add(String.valueOf(groupPerm));
            
            }
            writeFile(OUTPUT_PATH, answerList);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception igunore) {}
        }
    }
    
    private static final String FORMAT = "Case #{0}: {1}";

    public static void writeFile(String filePath, List<String> solve) {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(new File (filePath))));

            for (int i = 0; i < solve.size(); i++) {
                
                Object[] ans = new Object[2];
                
                ans[0] = String.valueOf(i + 1);
                ans[1] = solve.get(i);
                String write = MessageFormat.format(FORMAT, ans);
                
                bufferedWriter.write(write);
                bufferedWriter.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();
            } catch (Exception igunore) {
                igunore.printStackTrace();
            }
        }
    }

    BufferedReader br = null;

    private int readInt() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    private long readLong() throws IOException {
        return Long.parseLong(br.readLine());
    }

    private int[] readIntArray() throws IOException {
        String[] s = readStrArray();
        int cnt = s.length;
        int[] out = new int[cnt];
        for (int i = 0; i < cnt; i++) {
            out[i] = Integer.parseInt(s[i]);
        }
        return out;
    }

    private Integer[] convIntArray(int[] arg) {
        int len = arg.length;
        Integer[] res = new Integer[len];
        for (int i = 0; i < len; i++) {
            res[i] = arg[i];
        }
        return res;
    }
    
    private long[] readLongArray() throws IOException {
        String[] s = readStrArray();
        int cnt = s.length;
        long[] out = new long[cnt];
        for (int i = 0; i < cnt; i++) {
            out[i] = Long.parseLong(s[i]);
        }
        return out;
    }

    private String[] readStrArray() throws IOException {
        String[] s = br.readLine().split(" ");
        return s;
    }

    void generate(int[] p, int L, int R) {
        if (L == R) { // all numbers are set
            
            boolean[] b = new boolean[strCnt];
            
            for (int i = 0; i < strCnt - 1; i++) {
                String before = strArray[p[i]];
                String next = strArray[p[i + 1]];

                if (before.charAt(before.length() - 1) == next.charAt(0)) {
                    b[p[i]] = true;
                    b[p[i + 1]] = true;
                }
            }
            for (boolean c : b) {
                if (!c) {
                    return;
                }
            }
            
            sum ++;
            
//            System.out.println(deepToString(convIntArray(p)));
            //            System.out.print("OK :");

            for (int i = 0; i < strCnt; i++) {
//                System.out.print(strArray[p[i]] + ",");
            }
//            System.out.println();
        } else { // numbers at positions [0, L-1] are set, try to set L-th position
          for (int i = L; i <= R; i++) {
            int tmp = p[L]; p[L] = p[i]; p[i] = tmp;
            generate(p, L+1, R);
            tmp = p[L]; p[L] = p[i]; p[i] = tmp;
          }
        }
     }

    /***
     * Å‘åŒö–ñ”‚ð‹‚ß‚éŠÖ”
     * @param n1
     * @param n2
     * @return
     */
    private static long gcd(long n1, long n2) {
        return (n2 == 0)?n1:gcd(n2, n1%n2);
    }
    private static int gcd(int n1, int n2) {
        return (n2 == 0)?n1:gcd(n2, n1%n2);
    }

}

