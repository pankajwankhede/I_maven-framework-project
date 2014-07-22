package net.aimeizi.spring.data.example.controller;

import java.util.List;

import javax.validation.Valid;

import net.aimeizi.spring.data.example.entities.Emp;
import net.aimeizi.spring.data.example.exception.EmpNotFound;
import net.aimeizi.spring.data.example.service.EmpService;
import net.aimeizi.spring.data.example.validation.EmpValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/emp")
public class EmpController {
	
	@Autowired
	private EmpService empService;
	
//	@Autowired
//	private EmpValidator empValidator;
	
//	@InitBinder
//	private void initBinder(WebDataBinder binder) {
//		binder.setValidator(empValidator);
//	}
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView newEmpPage() {
		ModelAndView mav = new ModelAndView("emp-new", "emp", new Emp());
		return mav;
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ModelAndView createNewEmp(@ModelAttribute @Valid Emp emp,
			BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors())
			return new ModelAndView("emp-new");
		
		ModelAndView mav = new ModelAndView();
		String message = "New emp "+emp.getEmpname()+" was successfully created.";
		
		empService.save(emp);
		mav.setViewName("redirect:/index.html");
				
		redirectAttributes.addFlashAttribute("message", message);	
		return mav;		
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView empListPage() {
		ModelAndView mav = new ModelAndView("emp-list");
		List<Emp> empList = empService.find();
		mav.addObject("empList", empList);
		return mav;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editEmpPage(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("emp-edit");
		Emp emp = empService.findOne(id);
		mav.addObject("emp", emp);
		return mav;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView editEmp(@ModelAttribute @Valid Emp emp,
			BindingResult result,
			@PathVariable Integer id,
			final RedirectAttributes redirectAttributes) throws EmpNotFound {
		emp.setEmpid(id);
		if (result.hasErrors())
			return new ModelAndView("emp-edit");
		
		ModelAndView mav = new ModelAndView("redirect:/index.html");
		String message = "Emp was successfully updated.";
		
		empService.update(emp);
		
		redirectAttributes.addFlashAttribute("message", message);	
		return mav;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteEmp(@PathVariable Integer id,
			final RedirectAttributes redirectAttributes) throws EmpNotFound {
		
		ModelAndView mav = new ModelAndView("redirect:/index.html");		
		
		Emp emp = empService.delete(empService.findOne(id));
		String message = "The emp "+emp.getEmpname()+" was successfully deleted.";
		
		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}
	
}
