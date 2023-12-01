package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws IOException {

        URL uri = new URL("http://universities.hipolabs.com/search?country=Brazil");
        HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
        conn.setRequestMethod("GET");
        int resCode = conn.getResponseCode();

        if (resCode == HttpURLConnection.HTTP_OK) {
            InputStreamReader inputStreamReader = new InputStreamReader(conn.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuffer stringBuffer = new StringBuffer();

            String inputLine = bufferedReader.readLine();
            while ( inputLine != null) {
                stringBuffer.append(inputLine);
                inputLine = bufferedReader.readLine();
            }
            bufferedReader.close();


            ObjectMapper objectMapper = new ObjectMapper();
            List<Universidade> universidades =
                    objectMapper.readValue(stringBuffer.toString(), new TypeReference<List<Universidade>>(){});

            System.out.println(universidades);
        } else {
            System.out.println("GET request not worked");
        }



    }
}