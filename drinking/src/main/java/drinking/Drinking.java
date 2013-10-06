package drinking;

import org.apache.log4j.Logger;

import java.text.MessageFormat;
import java.util.*;

public class Drinking {
	public static final Logger log = Logger.getLogger(Drinking.class);
	private int year = Calendar.getInstance().get(Calendar.YEAR);
	private int month = Calendar.getInstance().get(Calendar.MONTH);
	private int date = Calendar.getInstance().get(Calendar.DATE);
	public static final String[] msgs = { "经过一整夜的睡眠，身体开始缺水，起床之际先喝杯250CC的水，可帮助肾脏及肝脏解毒。别马上吃早餐，等待半小时让水融入每个细胞，进行新陈代谢后，再进食！（非常重要！身体排污靠喝水！）",
			"清晨从起床到办公室的过程，时间总是特别紧凑，情绪也较紧张，身体无形中会出现脱水现象，所以到了办公室后，先别急著泡咖啡，给自己一杯至少250CC的水！",
			"在冷气房里工作一段时间后，一定得趁起身动动的时候，再给自己一天里的第三杯水，补充流失的水分，有助於放松紧张的工作情绪！", "用完午餐半小时后，喝一些水，取代让你发胖的人工饮料，可以加强身体的消化功能，不仅对健康有益，也能助你维持身材。",
			"以一杯健康矿泉水代替午茶与咖啡等提神饮料吧！喝上一大杯水，除了补充在冷气房里流失的水份之外，还能帮助头脑清醒。", "下班离开办公室前，再喝一杯水。想要运用喝水减重的，可以多喝几杯，增加饱足感，待会吃晚餐时，自然不会暴饮暴食。",
			"睡前一至半小时再喝上一杯水，目标达成！今天已摄取2000CC水量了。不过别一口气喝太多，以免晚上得上洗手间影响睡眠品质。" };

	public Drinking() {
		log.info(MessageFormat.format("year:{0} month:{1} date:{2}", year, month, date));
		log.debug("begin");
		Timer timer = new Timer();
		timer.schedule(new MyTask(msgs[0]), getDate(year, month, date, 7, 0, 0));
		timer.schedule(new MyTask(msgs[1]), getDate(year, month, date, 9, 0, 0));
		timer.schedule(new MyTask(msgs[2]), getDate(year, month, date, 11, 0, 0));
		timer.schedule(new MyTask(msgs[3]), getDate(year, month, date, 12, 30, 0));
		timer.schedule(new MyTask(msgs[4]), getDate(year, month, date, 16, 0, 0));
		timer.schedule(new MyTask(msgs[5]), getDate(year, month, date, 18, 30, 0));
		timer.schedule(new MyTask(msgs[6]), getDate(year, month, date, 21, 0, 0));
		timer.schedule(new MyTask("休息一下准备吃饭"), getDate(year, month, date, 11, 40, 0));
		timer.schedule(new MyTask("休息下喝口水"), 1000 * 60 * 15, 1000 * 60 * 15);
	}

	public static Date getDate(int year, int month, int date, int hourOfDay, int minute, int second) {
		Calendar c = Calendar.getInstance();
		c.set(year, month, date, hourOfDay, minute, second);
		return c.getTime();
	}

	public static void main(String[] args) {
		new Drinking();
	}

	private class MyTask extends TimerTask {

		private String msg;

		public MyTask(String msg) {
			this.msg = msg;
		}

		public void run() {
			Map<String, String> feaMap = new HashMap<String, String>();
			feaMap.put("title", "欢迎使用本系统");
			feaMap.put("name", "");
			feaMap.put("feature", msg);
			feaMap.put("backgroundImage", "background.jpg");
			new VersionUtil(feaMap);
		}
	}
}