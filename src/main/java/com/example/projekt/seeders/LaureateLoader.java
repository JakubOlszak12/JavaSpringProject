package com.example.projekt.seeders;

import com.example.projekt.dao.ILaureateRepository;

import com.example.projekt.entities.Laureate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;


@Component
public class LaureateLoader implements ApplicationRunner {

    private ILaureateRepository laureateRepository;

    @Autowired
    public LaureateLoader(ILaureateRepository laureateRepository){
        this.laureateRepository = laureateRepository;
    }
    @SuppressWarnings("unchecked")
    public void run(ApplicationArguments args) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("src/main/java/com/example/projekt/seeders/laureates.json");
        Object obj = parser.parse(reader);
        JSONObject json = (JSONObject) obj;
        JSONArray array = (JSONArray) json.get("laureates");
        Laureate laureateToDatabase;

        for(int i=0;i< array.size();i++){
            JSONObject laureate = (JSONObject) array.get(i);

            if(laureate.get("givenName") != null && laureate.get("familyName") != null && laureate.get("id") != null){
                laureateToDatabase = new Laureate();
                JSONObject name = (JSONObject) laureate.get("givenName");
                JSONObject surname = (JSONObject) laureate.get("familyName");
                String givenName = (String)name.get("en");
                String familyName = (String) surname.get("en");
                String gender = (String) laureate.get("gender");
                laureateToDatabase.setGivenName(givenName);
                laureateToDatabase.setFamilyName(familyName);
                laureateToDatabase.setGender(gender);

                laureateToDatabase.setLaureateId(Integer.parseInt(laureate.get("id").toString()));
               // laureateToDatabase.setLaureateId(100);
                if(laureate.get("birth") != null){
                   JSONObject birth = (JSONObject) laureate.get("birth");
                   if(birth.get("date") != null){
                       LocalDate date = LocalDate.parse((String)birth.get("date"));
                       laureateToDatabase.setBirthDate(Date.valueOf(date));
                   }
                   if(birth.get("place") != null){
                       JSONObject place = (JSONObject) birth.get("place");
                       if(place.get("city") != null){
                           JSONObject city = (JSONObject) place.get("city");
                           String birthPlace = (String) city.get("en");
                           laureateToDatabase.setCountry(birthPlace);


                           //laureateToDatabase.setBirthDate(birth.get("date"));

                       }
                   }
                }
                if(laureate.get("wikipedia") != null){
                    JSONObject wikipedia = (JSONObject) laureate.get("wikipedia");
                    if(wikipedia.get("english") != null){
                    String address = (String) wikipedia.get("english");
                    laureateToDatabase.setWikipediaAddress(address);
                    }
                }
            //  System.out.println(laureateToDatabase.toString());
              laureateRepository.save(laureateToDatabase);
            }
        }
    }

}
