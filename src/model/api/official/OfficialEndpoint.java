package model.api.official;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class OfficialEndpoint {
    public static void fetchAndSaveBrawlersJson(){
        String api_url = "https://api.brawlstars.com/v1/brawlers";
        try {
            URL url = new URL(api_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Construir el Header
            connection.setRequestProperty("Authorization", "Bearer " + creds.getApiKey(0));

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

                // Save to file
                saveToFile("src/model/api/official/brawlers-official.json", response.toString());

                System.out.println("JSON saved to brawlers-official.json");

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
        }


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
