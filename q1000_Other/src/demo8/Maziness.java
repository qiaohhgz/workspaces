package demo8;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Maziness {

	private int M;// 行数
	private int N;// 列数
	private int[] visitMatrix;// 搜索是判断是否曾被访问过
	private int[][] colMatrix;// 保存要输出的的'|'矩阵
	private int[][] rowMatrix;// 保存要输出的的'_'矩阵
	private Random random;// 用来生成随机数，保证迷宫的复杂程度

	public Maziness(int M, int N) {
		this.M = M;
		this.N = N;
		visitMatrix = new int[M * N];
		colMatrix = new int[M][N + 1];
		rowMatrix = new int[M + 1][N];
		init(colMatrix, M, N + 1);
		init(rowMatrix, M + 1, N);
		for (int i = 0; i < M * N; i++)
			visitMatrix[i] = 0;
		random = new Random();
	}

	private void init(int matrix[][], int M, int N) {
		for (int i = 0; i < M; i++)
			for (int j = 0; j < N; j++)
				matrix[i][j] = 1;
	}

	// 返回num周围可用的邻居，即没被访问过，也没到达边缘
	private void availableNeigbers(ArrayList<Integer> list, int num) {
		int allNeigber[] = new int[4];
		if (num % N == 1) {
			allNeigber[0] = num - N;
			allNeigber[1] = num + N;
			allNeigber[2] = num + 1;
			allNeigber[3] = -1;
		} else if (num % N == 0) {
			allNeigber[0] = num - N;
			allNeigber[1] = num + N;
			allNeigber[2] = num - 1;
			allNeigber[3] = -1;
		} else {
			allNeigber[0] = num - N;
			allNeigber[1] = num + N;
			allNeigber[2] = num - 1;
			allNeigber[3] = num + 1;
		}
		for (int i = 0; i < 4; i++) {
			if (allNeigber[i] > 0 & allNeigber[i] <= M * N)
				if (visitMatrix[allNeigber[i] - 1] == 0)
					list.add(allNeigber[i]);
		}
	}

	// 返回随机选出的可用邻居
	private int neigber(int num) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		availableNeigbers(list, num);
		if (list.isEmpty())
			return -1;
		else {
			return (Integer) list.get(random.nextInt(list.size()));
		}
	}

	// 移除num1和num2之间的墙
	private void removeWall(int num1, int num2) {
		int x1 = (num1 + N - 1) / N - 1;
		int y1 = (num1 - 1) % N;
		if (Math.abs(num1 - num2) == 1) {
			if (num1 > num2)
				colMatrix[x1][y1] = 0;
			else
				colMatrix[x1][y1 + 1] = 0;
		} else {
			if (num1 > num2)
				rowMatrix[x1 - 1][y1] = 0;
			else
				rowMatrix[x1][y1] = 0;
		}
	}

	// 生成迷宫
	public void process() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int curr = (M * N) / 2;
		visitMatrix[curr - 1] = 1;
		list.add(curr);
		int tmp;
		while (!list.isEmpty()) {
			tmp = neigber(curr);
			if (tmp > 0) {
				visitMatrix[tmp - 1] = 1;
				removeWall(curr, tmp);
				curr = tmp;
				list.add(curr);
			} else
				curr = (Integer) list.remove(list.size() - 1);
		}
	}

	// 绘制迷宫，并输出到txt文件中
	public void draw(FileOutputStream fos) {
		try {
			fos.write(' ');
			fos.write(' ');
			for (int i = 0; i < N - 1; i++) {
				fos.write(' ');
				fos.write('_');
			}

			fos.write('\r');
			for (int i = 0; i < M; i++) {
				int j;
				for (j = 0; j < N; j++) {
					if (colMatrix[i][j] == 1)
						fos.write('|');
					else
						fos.write(' ');
					if (rowMatrix[i][j] == 1)
						fos.write('_');
					else
						fos.write(' ');
				}
				if (i != M - 1 || j != N) {
					fos.write('|');
					fos.write('\r');
				}
			}
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			FileOutputStream fos = new FileOutputStream("F://maze.txt");
			Maziness m = new Maziness(30, 60);
			m.process();
			m.draw(fos);

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
		}

	}
}