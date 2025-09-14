package interveiw.xiecheng2024chun;
import java.io.*;
import java.util.*;
// 前后缀和
// O(NlogN)。类似题 力扣.2602. 使数组元素全部相等的最少操作次数。 排序+前后缀分解。TreeMap被卡常，得用 常数更小的 直接对输入数组排序 的写法。
//
//遍历排序后的数组，像走台阶一样，在当前层，需要求出
//把左边矮的台阶补平的成本 lCost
//把右侧高的台阶削平的成本 rCost
//这两个的成本相加 lCost + rCost 就是所求。
//用 前后缀分解 可以O(1)把每个台阶的结果都出来，即
//在前一个lCost的基础上 再加上 左半边这层，左侧多补点
//在前一个rCost的基础上 再减去 右半边这层，右侧少削点。
public class Main3{
    public static void main (String[] args) throws IOException {
        //快读。
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(in.readLine());
        String[] sp = in.readLine().split("\\ ");
        int[] nums = new int[n]; //记录输入的数组，为了最后返回结果。
        int[] sortNums = new int[n]; //要把输入的数组排序下
        long sum = 0L; //记录输入数组的总和。
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(sp[i]);
            sortNums[i] = nums[i];
            sum = sum + nums[i];
        }

        Arrays.sort(sortNums); //要把输入的数组排序下

        //记录结果，key放元素值，value放对应结果。
        HashMap<Integer, Long> map = new HashMap<>();

        //第0个元素 没有左边，所以 不需要补平
        long lCost = 0L;
        //第0个元素右边 都需要把高的削平，需削去的体积是 全部 减去 同高的这一行，即下面这行的式子。
        long rCost = sum - (long)n * sortNums[0];
        map.put(sortNums[0], lCost + rCost); //把第0个的结果先放进map

        //分组循环
        int l = 0;
        for (int i = 1; i < n; i++) {
            if (sortNums[i] == sortNums[l]) continue; //一直相同则，则跳过。

            //到这就不相同了。 遇到不同的就要 告一段落来处理了
            //在前一个 lCost 的基础上 再加一层，即 左侧的成本要变大，加的这层 宽是 i，高是 sortNums[i] - sortNums[l]
            lCost += (long)i * (sortNums[i] - sortNums[l]);
            //在前一个 rCost 的基础上 再抽掉一层，即 右侧的成本会变小，抽掉的这层 宽是 n-i，高跟上面这行一样。
            rCost -= (long)(n - i) * (sortNums[i] - sortNums[l]);
            map.put(sortNums[i], lCost + rCost); //结果放到map里。

            l = i; //把当前i当作一个新开头。
        }

        for (int i : nums) out.println(map.get(i)); //输出结果。
        out.flush();
    }
}