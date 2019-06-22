package com.example.easytravel;



import java.util.ArrayList;
import java.util.regex.Pattern;

public class FlightsIndexedSearch {

    public ArrayList<AirportDataModel> doSearch(String text,
                                                ArrayList<AirportDataModel> dataSet) {

        ArrayList<AirportDataModel> targetDataset = new ArrayList<>();

        for (int index = 0; index < dataSet.size(); index++) {
            AirportDataModel currItem = dataSet.get(index);

            boolean dest = Pattern.compile(".*" + text + ".*",
                    Pattern.CASE_INSENSITIVE).matcher(currItem.m_Destination).matches();

            boolean flight = Pattern.compile(".*" + text + ".*",
                    Pattern.CASE_INSENSITIVE).matcher(currItem.m_Airlines.m_Flight).matches();

            boolean status = Pattern.compile(".*" + text + ".*",
                    Pattern.CASE_INSENSITIVE).matcher(currItem.m_Status).matches();

            if (dest != false || flight != false || status != false) {
                targetDataset.add(currItem);
            }
        }

        return targetDataset;
    }
}