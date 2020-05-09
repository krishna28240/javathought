package io.javathoughts.coronavirustracter.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.javathoughts.coronavirustracter.model.LocationStates;
@Service
public class CoronaVirunsDataService {
	
	public static String TOTAL_CASES_DATA_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";	
	public static String TOTAL_DEATH_DATA_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";	
    public static String TOTAL_RECOVERED_DATA_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";	
	private List<LocationStates> locStates=new ArrayList<>();
	private List<LocationStates> deathLocStates=new ArrayList<>();
	private List<LocationStates> recoveredLocStates=new ArrayList<>();
	
	public List<LocationStates> getRecoveredLocStates() {
		return recoveredLocStates;
	}

	public List<LocationStates> getDeathLocStates() {
		return deathLocStates;
	}

	public List<LocationStates> getLocStates() {
		return locStates;
	}

	@PostConstruct
	//@Scheduled(cron = "* * * * * *")
	public void fetchTotalCases() throws InterruptedException, IOException {
		List<LocationStates> newStates =new ArrayList<LocationStates>();
		
		HttpClient httpClient=HttpClient.newHttpClient();		
		HttpRequest request=HttpRequest.newBuilder()
				.uri(URI.create(TOTAL_CASES_DATA_URL)).build();
		HttpResponse<String> httpResponse=httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		StringReader reader=new StringReader(httpResponse.body());
		//System.out.println("test:="+httpResponse.body());
		//String resposeString=html2text(httpResponse.body());
		//System.out.println(resposeString);
		Iterable<CSVRecord> virusRecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
		
		for (CSVRecord record : virusRecords) {
			LocationStates lState =new LocationStates();
			///System.out.println("for.."+record.);
		    String state = record.get("Province/State");
		    String country = record.get("Country/Region");
		    String totalCases=record.get(record.size()-1);
		    String diffFromPrevDay=record.get(record.size()-2);
		    lState.setState(state);
			lState.setCountry(country);
			lState.setTotalCases(Integer.parseInt(totalCases));
			lState.setDiffFromPrevDay(Integer.parseInt(totalCases)-Integer.parseInt(diffFromPrevDay));
			newStates.add(lState);
			//System.out.println(state+"-"+country+":"+totalCases+":"+diffFromPrevDay);
		}
		this.locStates=newStates;
	}
	@PostConstruct
	//@Scheduled(cron = "* * * * * *")
	public void fetchDeathCases() throws InterruptedException, IOException {
		List<LocationStates> newStates =new ArrayList<LocationStates>();
		
		HttpClient httpClient=HttpClient.newHttpClient();		
		HttpRequest request=HttpRequest.newBuilder()
				.uri(URI.create(TOTAL_DEATH_DATA_URL)).build();
		HttpResponse<String> httpResponse=httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		StringReader reader=new StringReader(httpResponse.body());
		//System.out.println("test:="+httpResponse.body());
		//String resposeString=html2text(httpResponse.body());
		//System.out.println(resposeString);
		Iterable<CSVRecord> virusRecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
		
		for (CSVRecord record : virusRecords) {
			LocationStates lState =new LocationStates();
			///System.out.println("for.."+record.);
		    String state = record.get("Province/State");
		    String country = record.get("Country/Region");
		    String totalCases=record.get(record.size()-1);
		    String diffFromPrevDay=record.get(record.size()-2);
		    lState.setState(state);
			lState.setCountry(country);
			lState.setTotalCases(Integer.parseInt(totalCases));
			lState.setDiffFromPrevDay(Integer.parseInt(totalCases)-Integer.parseInt(diffFromPrevDay));
			newStates.add(lState);
			//System.out.println(state+"-"+country+":"+totalCases+":"+diffFromPrevDay);
		}
		this.deathLocStates=newStates;
	}
	
	@PostConstruct
	//@Scheduled(cron = "* * * * * *")
	public void fetchRecoveredCases() throws InterruptedException, IOException {
		List<LocationStates> newStates =new ArrayList<LocationStates>();
		
		HttpClient httpClient=HttpClient.newHttpClient();		
		HttpRequest request=HttpRequest.newBuilder()
				.uri(URI.create(TOTAL_RECOVERED_DATA_URL)).build();
		HttpResponse<String> httpResponse=httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		StringReader reader=new StringReader(httpResponse.body());
		//System.out.println("test:="+httpResponse.body());
		//String resposeString=html2text(httpResponse.body());
		//System.out.println(resposeString);
		Iterable<CSVRecord> virusRecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
		
		for (CSVRecord record : virusRecords) {
			LocationStates lState =new LocationStates();
			///System.out.println("for.."+record.);
		    String state = record.get("Province/State");
		    String country = record.get("Country/Region");
		    String totalCases=record.get(record.size()-1);
		    String diffFromPrevDay=record.get(record.size()-2);
		    lState.setState(state);
			lState.setCountry(country);
			lState.setTotalCases(Integer.parseInt(totalCases));
			lState.setDiffFromPrevDay(Integer.parseInt(totalCases)-Integer.parseInt(diffFromPrevDay));
			newStates.add(lState);
			//System.out.println(state+"-"+country+":"+totalCases+":"+diffFromPrevDay);
		}
		this.recoveredLocStates=newStates;
	}
}
