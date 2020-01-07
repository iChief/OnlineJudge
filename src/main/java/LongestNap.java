import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Longest Nap (10191)
 * https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=1132
 *
 * @author Ahmed Schahadeh (UVa: iChief)
 */

class Main {

    static class Time implements Comparable<Time> {
        String startS;
        String endS;
        int startMin;
        int endMin;

        @Override
        public int compareTo(Time t) {
            return this.startMin-t.startMin;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        int dayCount=1;
        while ((s=br.readLine())!=null) {
            int timeCount=Integer.parseInt(s);
            Time [] time=new Time[timeCount+2];
            time[0]=new Time();
            time[0].startS="10:00";
            time[0].endS="10:00";
            time[0].startMin=600;
            time[0].endMin=600;
            for (int i=1;i<=timeCount;i++) {
                time[i]=new Time();
                StringTokenizer st=new StringTokenizer(br.readLine());
                time[i].startS=st.nextToken();
                int hrs=Integer.parseInt(time[i].startS.substring(0,2));
                int min=Integer.parseInt(time[i].startS.substring(3,5));
                time[i].startMin=hrs*60+min;
                time[i].endS=st.nextToken();
                hrs=Integer.parseInt(time[i].endS.substring(0,2));
                min=Integer.parseInt(time[i].endS.substring(3,5));
                time[i].endMin=hrs*60+min;
            }
            time[timeCount+1]=new Time();
            time[timeCount+1].startS="18:00";
            time[timeCount+1].endS="18:00";
            time[timeCount+1].startMin=1080;
            time[timeCount+1].endMin=1080;
            Arrays.sort(time);

            int lastEndTime=600;
            int maxDuration=0;
            Time lastMaxEnd=null;
            for (int i=1;i<time.length;i++) {
                int dur=time[i].startMin-time[i-1].endMin;
                if (dur>maxDuration) {
                    maxDuration=dur;
                    lastMaxEnd=time[i-1];
                }
            }

            StringBuilder sb=new StringBuilder();
            sb.append("Day #");
            sb.append(dayCount);
            sb.append(": the longest nap starts at ");
            assert(lastMaxEnd!=null);
            sb.append(lastMaxEnd.endS);
            sb.append(" and will last for ");
            if (maxDuration>=60) {
                sb.append(maxDuration/60);
                sb.append(" hours and ");
            }
            sb.append(maxDuration%60);
            sb.append(" minutes.");
            System.out.println(sb.toString());
            dayCount++;
        }
    }
}