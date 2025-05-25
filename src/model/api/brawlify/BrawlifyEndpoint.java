package model.api.brawlify;
import com.google.gson.Gson;
import model.DAO.DBConnection;
import model.classes.Brawler;

import javax.net.ssl.*;
import java.security.cert.X509Certificate;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class BrawlifyEndpoint {
    public static String fetchBrawlify(){
        String api_url = "https://api.brawlify.com/v1/brawlers";
        try {
            URL url = new URL(api_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setRequestProperty("Accept", "application/json");



            // Verificar si la resposta es correcte
            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {

                // Prepare to read the response stream from the API
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                // Read each line from the stream
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                // close the stream when there are no more lines
                in.close();


                // Save to file (optional)
                // saveToFile("src/model/api/brawlify/brawlers-brawlify.json", response.toString());

                // return the string
                return response.toString();


            } else {
                System.out.println("Request failed with error code: " + responseCode);
            }

        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException: " + e);
            e.printStackTrace();
        } catch (ProtocolException e) {
            System.out.println("ProtocolException: " + e);
        } catch (IOException e) {
            System.out.println("IOExeption: " + e);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return null;
    }

    public static Brawler fetchBrawler(int braweler_id){
        String api_url = String.format("https://api.brawlify.com/v1/brawlers/%d", braweler_id);
        try {
            URL url = new URL(api_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setRequestProperty("Accept", "application/json");



            // Verificar si la resposta es correcte
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {

                // Prepare to read the response stream from the API
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                // Read each line from the stream
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                // close the stream when there are no more lines
                in.close();


                // Save to file (optional)
                // saveToFile("src/model/api/brawlify/brawlers-brawlify.json", response.toString());

                // return the string
                Gson gson = new Gson();
                return gson.fromJson(response.toString(), Brawler.class);


            } else {
                System.out.println("Request failed: " + responseCode);
            }

        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException: " + e);
            e.printStackTrace();
        } catch (ProtocolException e) {
            System.out.println("ProtocolException: " + e);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOExeption: " + e);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void saveToFile(String fileName, String content) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
            System.out.printf("JSON saved to %s%n", fileName);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
