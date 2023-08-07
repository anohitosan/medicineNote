package medicineNote.model.form;

import lombok.Data;

@Data
public class AddMedicineForm {
	private String medicineName;
	private int amount;
	private String[] medicineTime;
}
