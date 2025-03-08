package com.qa.copperCrm.dataProvider;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class DataReader {
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode;

    public DataReader() {
        {
            try {
                jsonNode = objectMapper.readTree(new File("src/main/resources/jasonFiles/data.json"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void getData() {
        JsonNode a = jsonNode.get("abc");

    }
}
