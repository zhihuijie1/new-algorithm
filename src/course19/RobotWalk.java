package algorithmbasic.class19;

/*
假设有排成一行的N个位置记为1~N，N一定大于或等于2
开始时机器人在其中的M位置上(M一定是1~N中的一个)
如果机器人来到1位置，那么下一步只能往右来到2位置；
如果机器人来到N位置，那么下一步只能往左来到N-1位置；
如果机器人来到中间位置，那么下一步可以往左走或者往右走；
规定机器人必须走K步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
给定四个参数 N、M、K、P，返回方法数
 */
public class RobotWalk {

    //N:一共有N个位置记为1-N
    //start：机器人当前所在的位置
    //aim：目标位置
    //k：一共可以走多少步。
    public static int ways1(int N, int start, int aim, int K) {
        return process1(start, aim, K, N);
    }

    // process方法的解释：现在在cur位置，目标位置是aim，还剩rest步可走，一共有N个位置（1-N）
    // 机器人从当前的cur位置开始走，走rest步之后，到达目标位置aim的方法数一共是多少。
    private static int process1(int cur, int aim, int rest, int N) {
        //现在还剩0步可走。
        if (rest == 0) {
            return cur == aim ? 1 : 0;
        }
        //如果当前的位置在最左侧，只能往右行走。
        if (cur == 1) {
            return process1(2, aim, rest-1, N);
        }
        //如果当前的位置在最右侧只能往左走。
        if (cur == N) {
            return process1(N - 1, aim, rest-1, N);
        }

        //当前的位置不在边缘，在中间，那既可以向左也可以向右走。
        return process1(cur + 1, aim, rest-1, N) + process1(cur - 1, aim, rest-1, N);
    }



    private static int process2(int cur, int aim, int rest, int N) {

    }

}












