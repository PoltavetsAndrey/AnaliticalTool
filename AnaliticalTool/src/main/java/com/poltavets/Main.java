package com.poltavets;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Main {

    Collection<Line> lines = new ArrayList<>();

    public static void main(String[] args) throws IOException, ParseException {
        Main main = new Main();
        main.run();
    }

    private void run() throws IOException, ParseException {
        Read read = new Read();
        read.reader(lines);
        calculateResponse(lines);
    }

    private void calculateResponse(Collection<Line> lines) throws FileNotFoundException {
        Collection<String> output = new ArrayList<>();
        // Verified Requests
        Collection<Line> linesForRemoving = new ArrayList<>();
        // Query search cycle
        for (Iterator<Line> iterator1 = lines.iterator(); iterator1.hasNext();) {
            Line condition = iterator1.next();
            if (condition.getTypeLine() == 'D' && !linesForRemoving.contains(condition)) {
                linesForRemoving.add(condition);
                int sumTime = 0;
                int counterNeedLines = 0;
                // Iterate over records to compare with query
                for (Iterator<Line> iterator2 = lines.iterator(); iterator2.hasNext();) {
                    Line checkline = iterator2.next();
                    if (satisfactionCheck(condition, checkline, linesForRemoving)) {
                        sumTime += checkline.getMinutesWaited();
                        counterNeedLines++;
                    }
                }
                if (counterNeedLines > 0) {
                    output.add(String.valueOf(sumTime / counterNeedLines));
                } else {
                    output.add("-");
                }
            }
        }
        write(output);
    }

    // Checking a record against a query
    private boolean satisfactionCheck(Line condition, Line checkline, Collection<Line> linesForRemoving) {
        boolean satisfaction = true;
        if (condition.getIndicesService().length > checkline.getIndicesService().length ||
                linesForRemoving.contains(checkline)) {
            satisfaction = false;
        } else {
            for (int i = 0; i < condition.getIndicesService().length; i++) {
                if (!condition.getIndicesService()[i].equals(checkline.getIndicesService()[i]) &&
                        !(condition.getIndicesService()[0].equals("*"))) {
                    satisfaction = false;
                    break;
                }
            }
        }
        if (!satisfaction ||
                condition.getIndicesQuestion().length > checkline.getIndicesQuestion().length) {
            satisfaction = false;
        } else {
            for (int i = 0; i < condition.getIndicesQuestion().length; i++) {
                if (!condition.getIndicesQuestion()[i].equals(checkline.getIndicesQuestion()[i]) &&
                        !(condition.getIndicesQuestion()[0].equals("*"))) {
                    satisfaction = false;
                    break;
                }
            }
        }
        if (condition.getTypeResponse() != checkline.getTypeResponse()) {
            satisfaction = false;
        }
        if (condition.getDateTo() != null) {
            if (condition.getDateFrom().after(checkline.getDateFrom())) {
                satisfaction = false;
            }
            if (!condition.getDateTo().after(checkline.getDateFrom())) {
                satisfaction = false;
            }
        } else {
            if (!condition.getDateFrom().equals(checkline.getDateFrom())) {
                satisfaction = false;
            }
        }
        return satisfaction;
    }

    // Output response to console and file
    private void write(Collection<String> collectionForOutput) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(
                new FileOutputStream("output.txt"))) {
            for (String line : collectionForOutput) {
                System.out.println(line);
                writer.println(line);
            }
        }
    }
}

