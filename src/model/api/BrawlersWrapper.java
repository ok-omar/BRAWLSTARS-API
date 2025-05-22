package model.api;

import com.google.gson.Gson;
import model.classes.Brawler;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class BrawlersWrapper {
    private List<Brawler> list;
    private List<Brawler> items;



    public static BrawlersWrapper fromOfficialJsonFile(String filePath) throws IOException {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, BrawlersWrapper.class);
        }
    }

    public static BrawlersWrapper fromUnofficialJsonString(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, BrawlersWrapper.class);
    }

    public List<Brawler> getOfficialBrawlers() {
        return items;
    }
    public List<Brawler> getUnofficialBrawlers() {
        return list;
    }
}