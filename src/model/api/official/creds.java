package model.api.official;

public class creds {


    private static final String officialJsonPath = "src/model/api/official/brawlers-official.json";

    // Put your own Api Key in this variable
    private static final String apiKey = "";

    /**
     * Function that returns the API key to be used when calling the official Brawlstars API
     * @return returns the API key
     */
    public static String getApiKey(){
        return apiKey;
    }

    public static String getOfficialJsonPath() {
        return officialJsonPath;
    }


}
