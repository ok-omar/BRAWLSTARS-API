package model.api.official;

import model.DAO.DBConnection;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class OfficialEndpoint {
    public static void fetchAndSaveBrawlersJson(int n){
        String api_url = "https://api.brawlstars.com/v1/brawlers";
        try {
            URL url = new URL(api_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Construir el Header
            connection.setRequestProperty("Authorization", "Bearer " + creds.getApiKey(0));

            // Verificar si la resposta es correcte
            int responseCode = connection.getResponseCode();
            if (responseCode == 403) {
                System.out.println("Request to BRAWLSTARS API failed with Error 403 (Forbidden), please update the API key and try again if your API has changed.");
                System.out.println("Current IP Adress: " + getPublicIP());
                DBConnection.closeCon(0);
                System.exit(1);
            }
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

                // Save to file
                saveToFile("src/model/api/official/brawlers-official.json", response.toString(), n);

            } else {
                System.out.println("Request failed with error code: " + responseCode);
                DBConnection.closeCon(0);
                System.exit(1);
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
            System.out.println("Exeption: " + e);
        }


    }
    public static void saveToFile(String fileName, String content, int n) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
            if (n == 1) System.out.printf("JSON saved to %s%n", fileName);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPublicIP() throws Exception {
        return new java.util.Scanner(new java.net.URL("https://api.ipify.org").openStream(), "UTF-8")
                .useDelimiter("\\A").next();
    }

}
