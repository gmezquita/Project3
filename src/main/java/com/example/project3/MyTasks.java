package com.example.project3;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MyTasks {

    RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedRate = 3000)
    public void addVehicle() {

        System.out.println("addVehicle.........");
        String postURL = "http://localhost:8080/addVehicle";

        Vehicle newVehicle = new Vehicle(randomMakeModel(), randomYear(), randomPrice());

        restTemplate.postForObject(postURL, newVehicle, Vehicle.class);
    }

    @Scheduled(fixedRate = 4000)
    public void deleteVehicle() {

        System.out.println("deleteVehicle.........");
        int randomID = randomID();

        String postURL = "http://localhost:8080/deleteVehicle/" + randomID;

        restTemplate.delete(postURL);
    }

    @Scheduled(fixedRate = 5000)
    public void updateVehicle() {

        System.out.println("updateVehicle.........");
        int randomID = randomID();

        String postURL = "http://localhost:8080/updateVehicle";

        Vehicle updatedVehicle = new Vehicle(randomID, "NIGHTRIDER KITT", 1982, 1000000);

        restTemplate.put(postURL, updatedVehicle, Vehicle.class);
    }

    @Scheduled(cron="0 0 * * * *")
    public void getLatestVehicle() {

        System.out.println("getLatestVehicle.........");
        String getURL = "http://localhost:8080/getLatestVehicles";

        System.out.println("Last 10 Vehicles:");
        restTemplate.getForEntity(getURL, Vehicle.class);
    }

    public int randomID() {
        return RandomUtils.nextInt(1, 101);
    }

    public String randomMakeModel() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    public int randomYear() {
        return RandomUtils.nextInt(1987, 2016);
    }

    public double randomPrice() {
        return (double)RandomUtils.nextInt(15001, 45000);
    }
}
