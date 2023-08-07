package medicineNote.model.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MedicineList {
	private int id;
	private String medicineName;
	private int amount;
	private String medicineTime;
	private Timestamp createdAt;
	private Timestamp updateAt;
}
