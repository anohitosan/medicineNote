package medicineNote.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import medicineNote.model.domain.MedicineList;
import medicineNote.model.form.AddMedicineForm;
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
	
	@GetMapping("/")
	public String index(Model m) {
		List<MedicineList> medicineList = mlm.find();
		SetTimer st = new SetTimer(tm.find().getToken());
		try {
			st.check(medicineList);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("パース例外が発生しました");
		}
		m.addAttribute("medicineList",medicineList);
		return "index";
	}
	
	@GetMapping("/addMedicine")
	public String addMedicine(AddMedicineForm amf, Model m) {
		String medicineTime = String.join(",", amf.getMedicineTime());
		mlm.addMedicine(amf.getMedicineName(), amf.getAmount(),medicineTime);
		List<MedicineList> medicineList = mlm.find();
		m.addAttribute("medicineList",medicineList);
		return "redirect:/medicineNote/";
	}
}
