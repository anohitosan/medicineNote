package medicineNote.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import medicineNote.model.domain.MedicineList;
import medicineNote.model.form.AddMedicineForm;
import medicineNote.model.json.MedicineNameJSON;
import medicineNote.model.logic.SetTimer;
import medicineNote.model.mapper.MedicineListMapper;
import medicineNote.model.mapper.TokenMapper;

@Controller
@RequestMapping("/medicineNote")
public class IndexController {
	
	@Autowired
	MedicineListMapper mlm;
	
	@Autowired
	TokenMapper tm;
	
	private Gson gson = new Gson();
	
	@GetMapping("/")
	public String index(Model m) {
		List<MedicineList> medicineList = mlm.find();
		SetTimer st = new SetTimer(tm,mlm);
		try {
			st.check(medicineList);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("例外が発生しました");
		}
		m.addAttribute("medicineList",medicineList);
		return "index";
	}
	
	@GetMapping("/addMedicine")
	public String addMedicine(AddMedicineForm amf) {
		String medicineTime = String.join(",", amf.getMedicineTime());
		mlm.addMedicine(amf.getMedicineName(), amf.getAmount(),medicineTime);
		return "redirect:/medicineNote/";
	}
	
	
	@PostMapping("/deleteMedicine")
	public String deleteMedicine(@RequestBody String medicineName) {
		MedicineNameJSON mn = gson.fromJson(medicineName, MedicineNameJSON.class);
		mlm.deleteByMedicineName(mn.getMedicineName());
		System.out.println(mn.getMedicineName());
		return "redirect:/medicineNote/";
	}
}
