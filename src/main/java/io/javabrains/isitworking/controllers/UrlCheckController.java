package io.javabrains.isitworking.controllers;

import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {

    private String IS_SITE_UP = "Site is up!";
    private String SITE_IS_DOWN = "Site is down!";
    private String INCORRECT_URL = "URL is incorrect!";

    @GetMapping("/checkUrlStatus")
    public String checkUrlStatusMessage(@RequestParam String url) {
        String returnMessage = "";
        try {
            URL urlObj = new URL(url);
            System.out.println("URL: " + urlObj);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCodeCategory = conn.getResponseCode();
            if (responseCodeCategory != 200 || responseCodeCategory != 300) {
                System.out.println("Response Code: " + responseCodeCategory);
                returnMessage = SITE_IS_DOWN;
            } else {
                returnMessage = IS_SITE_UP;
            }

        } catch (Exception e) {
            returnMessage = INCORRECT_URL;
        } catch (Error e) {
            returnMessage = SITE_IS_DOWN;
        }

        return returnMessage;
    }

    public void setIS_SITE_UP(String url) {
        this.IS_SITE_UP = url;
    }

}
