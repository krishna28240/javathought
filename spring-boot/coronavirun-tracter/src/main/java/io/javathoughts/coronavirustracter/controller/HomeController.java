package io.javathoughts.coronavirustracter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.javathoughts.coronavirustracter.service.CoronaVirunsDataService;

@Controller
public class HomeController {
	
	@Autowired
	private CoronaVirunsDataService coronaVirusDataService;
	
	@GetMapping("/")
	public String home(Model model) {
		int totalCases=coronaVirusDataService.getLocStates().stream().mapToInt(stat->stat.getTotalCases()).sum();
		model.addAttribute("locationStates",coronaVirusDataService.getLocStates());
		model.addAttribute("totalCases",totalCases);
		return "home";
	}
	
	@GetMapping("/death")
	public String deathCases(Model model) {
		int totalDeathCases=coronaVirusDataService.getDeathLocStates().stream().mapToInt(stat->stat.getTotalCases()).sum();
		model.addAttribute("deathLocationStates",coronaVirusDataService.getDeathLocStates());
		model.addAttribute("deathTotalCases",totalDeathCases);
		return "death";
	}
	@GetMapping("/recovered")
	public String recoveredCases(Model model) {
		int totalDeathCases=coronaVirusDataService.getRecoveredLocStates().stream().mapToInt(stat->stat.getTotalCases()).sum();
		model.addAttribute("recoveredLocationStates",coronaVirusDataService.getRecoveredLocStates());
		model.addAttribute("recoveredTotalCases",totalDeathCases);
		return "recovered";
	}
}
