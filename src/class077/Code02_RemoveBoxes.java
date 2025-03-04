package class077;

// 移除盒子
// 给出一些不同颜色的盒子boxes，盒子的颜色由不同的正数表示
// 你将经过若干轮操作去去掉盒子，直到所有的盒子都去掉为止
// 每一轮你可以移除具有相同颜色的连续 k 个盒子（k >= 1）
// 这样一轮之后你将得到 k * k 个积分
// 返回你能获得的最大积分总和
// 测试链接 : https://leetcode.cn/problems/remove-boxes/
public class Code02_RemoveBoxes {

	public static int removeBoxes(int[] boxes) {
		int n = boxes.length;
		int[][][] dp = new int[n][n][n];
		return f(boxes, 0, n - 1, 0, dp);
	}

	public static int f(int[] boxes, int l, int r, int k, int[][][] dp) {
		if (l > r) {
			return 0;
		}
		if (dp[l][r][k] > 0) {
			return dp[l][r][k];
		}
		int s = l;
		while (s + 1 <= r && boxes[l] == boxes[s + 1]) {
			s++;
		}
		int cnt = k + s - l + 1;
		int ans = cnt * cnt + f(boxes, s + 1, r, 0, dp);
		for (int m = s + 2; m <= r; m++) {
			if (boxes[l] == boxes[m] && boxes[m - 1] != boxes[m]) {
				ans = Math.max(ans, f(boxes, s + 1, m - 1, 0, dp) + f(boxes, m, r, cnt, dp));
			}
		}
		dp[l][r][k] = ans;
		return ans;
	}

}
