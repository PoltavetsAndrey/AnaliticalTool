package com.poltavets;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Read {

    // Read strings from an input file
    public void reader(Collection<Line> lines) throws IOException, ParseException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream("input.txt"), StandardCharsets.UTF_8))){
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                lines.add(parserString(line));
            }
        }
    }
    // Create an object from an input string
    private Line parserString(String lineStr) throws ParseException {
        String[] elementsOfLine = lineStr.split("\\s");
        Line lineObj = new Line();
        lineObj.setTypeLine(elementsOfLine[0].toCharArray()[0]);

        String[] indicesOfService = elementsOfLine[1].split("\\.");
        lineObj.setIndicesService(indicesOfService);

        String[] indicesOfQuestion = elementsOfLine[2].split("\\.");
        lineObj.setIndicesQuestion(indicesOfQuestion);

        lineObj.setTypeResponse(elementsOfLine[3].toCharArray()[0]);

        String[] stringsDates = elementsOfLine[4].split("\\-");
        lineObj.setDateFrom(new SimpleDateFormat (
                "dd.MM.yyyy").parse(stringsDates[0]));

        if (lineObj.getTypeLine() == 'D' && stringsDates.length == 2) {
            lineObj.setDateTo(new SimpleDateFormat(
                    "dd.MM.yyyy").parse(stringsDates[1]));
        } else if (lineObj.getTypeLine() == 'C') {
            lineObj.setMinutesWaited(Integer.valueOf(elementsOfLine[5]));
        }
        return lineObj;
    }
}

