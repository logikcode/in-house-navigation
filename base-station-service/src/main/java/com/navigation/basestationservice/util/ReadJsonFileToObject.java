package com.navigation.basestationservice.util;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadJsonFileToObject {

    public JSONObject read()  {
        try {
            String fileDir = "base-station-service/src/main/resources/openapi/openapiresponse.json";
            String content = new String(Files.readAllBytes(Paths.get(fileDir)));
           return new JSONObject(content);
        } catch ( IOException ioException){
            System.out.println("EXCEPTION THROWN WHILE TRYING TO READ FILE");
        }

        return null;
    }
}
