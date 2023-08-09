package medicineNote.model.logic;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import medicineNote.model.domain.MedicineList;
import medicineNote.model.mapper.MedicineListMapper;

public class CreateMessage {
	private String message;
	
	public String createMessage (MedicineListMapper mlm) {
		message = getTimeFrame() + "です。" + getMedicineName(mlm) + "を飲んでください。";
		return message;
	}
	
	private String getTimeFrame () {
		String timeFrame = null;
		LocalTime nowTime = LocalTime.now();
		
		if(nowTime.isAfter(LocalTime.of(4, 0)) && nowTime.isBefore(LocalTime.of(10, 0)) ) {
			timeFrame = "朝";
		}
		if(nowTime.isAfter(LocalTime.of(10, 0)) && nowTime.isBefore(LocalTime.of(16, 0)) ) {
			timeFrame = "昼";
		}
		if(nowTime.isAfter(LocalTime.of(16, 0)) && nowTime.isBefore(LocalTime.of(21, 0)) ) {
			timeFrame = "夜";
		}
		if(nowTime.isAfter(LocalTime.of(21, 0)) && nowTime.isBefore(LocalTime.of(23, 0)) ) {
			timeFrame = "就寝前";
		} else {
			timeFrame ="就寝前";
		}
		return timeFrame;
	}
	
	private String getMedicineName(MedicineListMapper mlm) {
		String medicineName = null;
		List<String> medicineNameList = new ArrayList<>();
		List<MedicineList> medicineList = mlm.findByTimeFrame(getTimeFrame());
		
		for(int i = 0; medicineList.size() > i; i++) {
			medicineNameList.add(medicineList.get(i).getMedicineName());
		}
		
		medicineName = String.join(",", medicineNameList);
		return medicineName;
	}
}
