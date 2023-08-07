package medicineNote.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import medicineNote.model.domain.MedicineList;

@Mapper
public interface MedicineListMapper {

	@Select(value="SELECT * FROM medicine_list")
	List<MedicineList> find();
	
	@Insert(value="INSERT INTO medicine_list(medicine_name,amount,medicine_time) VALUES (#{medicineName},#{amount},#{medicineTime})")
	int addMedicine(String medicineName,int amount, String medicineTime);
}
