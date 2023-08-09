package medicineNote.model.logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import medicineNote.model.domain.MedicineList;
import medicineNote.model.mapper.MedicineListMapper;
import medicineNote.model.mapper.TokenMapper;

public class SetTimer {
	private final String message;
	private final String token;
	private int a = 0;
	private int h = 0;
	private int y = 0;
	private int s = 0;
	
	public SetTimer(TokenMapper tm,MedicineListMapper mlm) {
		this.token = tm.find().getToken();
		this.message = new CreateMessage().createMessage(mlm);;
	}
	
	public void check(List<MedicineList> medicineList) throws ParseException{
		for(MedicineList medicine : medicineList) {
			if(medicine.getMedicineTime().contains("朝") && a == 0) {
				a++;
				setTimer("朝");
			}
			if(medicine.getMedicineTime().contains("昼") && h == 0) {
				h++;
				setTimer("昼");
			}
			if(medicine.getMedicineTime().contains("夜") && y == 0) {
				y++;
				setTimer("夜");
			}
			if(medicine.getMedicineTime().contains("就寝前") && s == 0) {
				s++;
				setTimer("就寝前");
			}
		}
	}
	
	public void setTimer(String timeframe) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Timer timer = new Timer(false);
		LocalDate todayDate = LocalDate.now();
		Date date = new Date();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				LineNotify lineNotify = new LineNotify(token);
		        if(lineNotify.notify(message)) {
		        	System.out.println("薬を飲むようにLineに通知しました");	
		        } else {
		        	System.out.println("通知に失敗しました");	
		        }
			}
		};
		if(timeframe.equals("朝") && date.before(sdf.parse(todayDate.toString() + " " + "07:00:00"))){
			timer.schedule(task, sdf.parse(todayDate.toString() + " " + "07:00:00"),(long)(24 * 60 * 60 * 1000));	
			System.out.println("朝にタイマーをセットしました");
		}
		if(timeframe.equals("昼") && date.before(sdf.parse(todayDate.toString() + " " + "11:30:00"))){
			timer.schedule(task, sdf.parse(todayDate.toString() + " " + "11:30:00"),(long)(24 * 60 * 60 * 1000));
			System.out.println("昼にタイマーをセットしました");	
		}
		if(timeframe.equals("夜") && date.before(sdf.parse(todayDate.toString() + " " + "19:00:00"))) {
			timer.schedule(task, sdf.parse(todayDate.toString() + " " + "15:00:00"),(long)(24 * 60 * 60 * 1000));	
			System.out.println("夜にタイマーをセットしました");
		}
		if(timeframe.equals("就寝前") && date.before(sdf.parse(todayDate.toString() + " " + "22:00:00"))) {
			timer.schedule(task, sdf.parse(todayDate.toString() + " " + "22:00:00"),(long)(24 * 60 * 60 * 1000));
			System.out.println("就寝前にタイマーをセットしました");
		}
	}
}
