package demo2;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;

//定义下载从start到end的内容的线程
class DownThread extends Thread {
	// 定义字节数组（取水的竹筒）的长度
	private final int BUFF_LEN = 32;
	// 定义下载的起始点
	private long start;
	// 定义下载的结束点
	private long end;
	// 下载资源对应的输入流
	private InputStream is;
	// 将下载到的字节输出到raf中
	private RandomAccessFile raf;

	// 构造器，传入输入流，输出流和下载起始点、结束点
	public DownThread(long start, long end, InputStream is, RandomAccessFile raf) {
		// 输出该线程负责下载的字节位置
		System.out.println(start + " B---->" + end + " B");
		this.start = start;
		this.end = end;
		this.is = is;
		this.raf = raf;
	}

	public void run() {
		try {
			is.skip(start);
			raf.seek(start);
			// 定义读取输入流内容的的缓存数组（竹筒）
			byte[] buff = new byte[BUFF_LEN];
			// 本线程负责下载资源的大小
			long contentLen = end - start;
			// 定义最多需要读取几次就可以完成本线程的下载
			long times = contentLen / BUFF_LEN + 4;
			// 实际读取的字节数
			int hasRead = 0;
			for (int i = 0; i < times; i++) {
				hasRead = is.read(buff);
				// 如果读取的字节数小于0，则退出循环！
				if (hasRead < 0) {
					break;
				}
				raf.write(buff, 0, hasRead);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// 使用finally块来关闭当前线程的输入流、输出流
		finally {
			try {
				if (is != null) {
					is.close();
				}
				if (raf != null) {
					raf.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}

public class MutilDown {

	public static MutilDown newInstance() {
		return new MutilDown();
	}

	public void download(String urlStr, String fileName) {
		final String URL = urlStr;
		final int DOWN_THREAD_NUM = 4;
		final String OUT_FILE_NAME = fileName;
		InputStream[] isArr = new InputStream[DOWN_THREAD_NUM];
		RandomAccessFile[] outArr = new RandomAccessFile[DOWN_THREAD_NUM];
		try {
			// 创建一个URL对象
			URL url = new URL(URL);
			// 以此URL对象打开第一个输入流
			isArr[0] = url.openStream();
			long fileLen = getFileLength(url);
			System.out.println("网络资源的大小" + fileLen + " B");
			// 以输出文件名创建第一个RandomAccessFile输出流
			outArr[0] = new RandomAccessFile(OUT_FILE_NAME, "rw");
			// 创建一个与下载资源相同大小的空文件
			for (int i = 0; i < fileLen; i++) {
				outArr[0].write(0);
			}
			// 每线程应该下载的字节数
			long numPerThred = fileLen / DOWN_THREAD_NUM;
			// 整个下载资源整除后剩下的余数
			long left = fileLen % DOWN_THREAD_NUM;
			for (int i = 0; i < DOWN_THREAD_NUM; i++) {
				// 为每个线程打开一个输入流、一个RandomAccessFile对象，
				// 让每个线程分别负责下载资源的不同部分。
				if (i != 0) {
					// 以URL打开多个输入流
					isArr[i] = url.openStream();
					// 以指定输出文件创建多个RandomAccessFile对象
					outArr[i] = new RandomAccessFile(OUT_FILE_NAME, "rw");
				}
				// 分别启动多个线程来下载网络资源
				if (i == DOWN_THREAD_NUM - 1) {
					// 最后一个线程下载指定numPerThred+left个字节
					new DownThread(i * numPerThred, (i + 1) * numPerThred + left, isArr[i], outArr[i]).start();
				} else {
					// 每个线程负责下载一定的numPerThred个字节
					new DownThread(i * numPerThred, (i + 1) * numPerThred, isArr[i], outArr[i]).start();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.getProperties().put("http.proxySet", "true");
		System.getProperties().put("http.proxyHost", "172.20.230.5");
		System.getProperties().put("http.proxyPort", "3128");
		System.getProperties().put("https.proxySet", "true");
		System.getProperties().put("https.proxyHost", "172.20.230.5");
		System.getProperties().put("https.proxyPort", "3128");

		String urlStr = "http://pic2.bengou.com/actor/wqwz008082012.jpg";
		String fileName = "aa.jpg";
		newInstance().download(urlStr, fileName);
	}

	// 定义获取指定网络资源的长度的方法
	public long getFileLength(URL url) throws Exception {
		long length = 0;
		// 打开该URL对应的URLConnection。
		URLConnection con = url.openConnection();
		// 获取连接URL资源的长度
		long size = con.getContentLength();
		length = size;
		return length;
	}
}
