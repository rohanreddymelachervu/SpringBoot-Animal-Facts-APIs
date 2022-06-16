package com.rohan.chatment.assignment.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;

import com.rohan.chatment.assignment.Animal;
import com.rohan.chatment.assignment.repository.AccessLogRepository;
import com.rohan.chatment.assignment.repository.AnimalTraitsRepository;
import com.rohan.chatment.assignment.repository.CatTraitsRepository;
import com.rohan.chatment.assignment.repository.DogTraitsRepository;
import com.rohan.chatment.assignment.domain.AccessLog;
import com.rohan.chatment.assignment.domain.AnimalTraits;
import com.rohan.chatment.assignment.domain.CatTraits;

@RestController
public class MyController {
	Logger logger=LoggerFactory.getLogger(MyController.class);
	@Value("${chatment.access.log.apikey}")
	private String accessAPIKey;
	private final AnimalTraitsRepository animalRepo;
	private final AccessLogRepository accessRepo;
	private final DogTraitsRepository dogRepo;
	private final CatTraitsRepository catRepo;
	public MyController(AnimalTraitsRepository animalRepo, AccessLogRepository accessRepo,DogTraitsRepository dogRepo, CatTraitsRepository catRepo){
		this.animalRepo = animalRepo;
		this.accessRepo = accessRepo;
		this.dogRepo  = dogRepo;
		this.catRepo = catRepo;
	}
	@GetMapping("/home")
	public String home() {
		return ("<h1> This is home </h1>");
	}
	@GetMapping("/home/cat/getAllFacts")
	public List<AnimalTraits> getCatFacts(HttpServletRequest request){
//		logAccessInfo(request);
		logger.info("Access Info inserted");
		return animalRepo.findAll();
	}
	
	
	@GetMapping("/access/log")
	public List<AccessLog> getAccessLog(HttpServletRequest request){
		logger.info("The access key is: " + accessAPIKey);
		return accessRepo.findAll();
	}
	
	@PostMapping("/getFact")
	public String getFact(@RequestBody Animal animal, HttpServletRequest request) {
		int catRecordsCount = (int)catRepo.count(), dogRecordsCount = (int)dogRepo.count(), min=1;
		int catRandomRecord = (int)Math.floor(Math.random()*(catRecordsCount-min+1)+min);
		int dogRandomRecord = (int)Math.floor(Math.random()*(dogRecordsCount-min+1)+min);
		String req = animal.getAnimalName().toLowerCase();
			switch (req) {
			case "cat":
				 String temp1 = catRepo.findById(catRandomRecord).get().getFact();
				 logAccessInfo(request,req,temp1);
				 return temp1;
			case "dog":
				 String temp2 = dogRepo.findById(dogRandomRecord).get().getFact();		
				 logAccessInfo(request,req,temp2);
				 return temp2;
			default:
				 String temp3 = "This animal is not supported currently for facts";
				 logAccessInfo(request,req,temp3);
				 return temp3;
			}
	}
	
	private void logAccessInfo(HttpServletRequest request,String req,String factReturned) {
		String ipAddress = request.getRemoteAddr();
		LocalDateTime ldt=LocalDateTime.now();
		AccessLog al = new AccessLog();
		al.setAnimalType(req);
		al.setFactReturned(factReturned);
		al.setIP_Address(ipAddress);
		al.setTimeStamp(ldt);
		accessRepo.save(al);
	}
}
