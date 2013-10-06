package com.jim.bookstack.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class PageBean<T> {
	protected Logger log = Logger.getLogger(PageBean.class);
	private int num = 2;//前后可以看到的页码数
	private int row = 10;// 显示行数
	private int current;// 当前页
	private int total;// 总页数
	private int count;// 总数据数
	private int back;// 上一页
	private int next;// 下一页
	private boolean isCurrent;// 当前是否是第一页
	private boolean isFinally;// 当前是否是最后一页
	private boolean isBack;// 有没有上一页
	private boolean isNext;// 有没有下一页
	private List<T> result;// 当前页结果集
	private List<Integer> nums;// 页码

	public PageBean() {
	}

	public PageBean(int current, int count, List<T> result) {
		this.current = current;
		this.count = count;
		this.result = result;
	}

	/**
	 * 开始计算,并初始化需要的数据
	 */
	public void init() {
		total = count % row == 0 ? count / row : count / row + 1;
		current = current <= 1 ? 1 : current;
		current = current >= total ? total : current;
		isBack = !(current == 1);
		isNext = !(current == total);
		back = isBack ? current - 1 : current;
		next = isNext ? current + 1 : current;
		isCurrent = current == 1;
		isFinally = current == total;

		nums = new ArrayList<Integer>();
		int b = current - num <= 1 ? 1 : current - num;
		int e = current + num >= total ? total : current + num;
		log.debug("b:" + b);
		log.debug("e:" + e);
		for (int i = b; i <= e; i++) {
			nums.add(i);
			log.debug("nums " + i);
		}
		//log.debug(toString());
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("total:" + total + "\n");
		sb.append("current:" + current + "\n");
		sb.append("isBack:" + isBack + "\n");
		sb.append("isNext:" + isNext + "\n");
		sb.append("back:" + back + "\n");
		sb.append("next:" + next + "\n");
		sb.append("isCurrent:" + isCurrent + "\n");
		sb.append("isFinally:" + isFinally + "\n");
		return sb.toString();
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getBack() {
		return back;
	}

	public void setBack(int back) {
		this.back = back;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public boolean getIsCurrent() {
		return isCurrent;
	}

	public void setCurrent(boolean isCurrent) {
		this.isCurrent = isCurrent;
	}

	public boolean getIsFinally() {
		return isFinally;
	}

	public void setFinally(boolean isFinally) {
		this.isFinally = isFinally;
	}

	public boolean getIsBack() {
		return isBack;
	}

	public void setBack(boolean isBack) {
		this.isBack = isBack;
	}

	public boolean getIsNext() {
		return isNext;
	}

	public void setNext(boolean isNext) {
		this.isNext = isNext;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public List<Integer> getNums() {
		return nums;
	}

	public void setNums(List<Integer> nums) {
		this.nums = nums;
	}
}
