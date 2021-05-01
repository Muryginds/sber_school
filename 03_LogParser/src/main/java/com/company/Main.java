package com.company;

import com.company.utils.Event;
import com.company.utils.Status;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class Main {

  public static void main(String[] args) {
    LogParser parser = new LogParser();
    parser.readLogFromFile();
    parser.writeSuccessfulResultsToFile();

    try {
      SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
      Date before = format.parse("01.01.2021");
      Date after = format.parse("01.01.2012");
      int countUniqueIP = parser.getNumberOfUniqueIPs(after, before);
      System.out.println("Number of unique IPs: " + countUniqueIP);

      Set<String> setUniqueIP = parser.getUniqueIPs(after, before);
      System.out.println("Unique IPs: " + setUniqueIP);

      String userName = "Eduard Petrovich Morozko";
      Set<String> userUniqueIP = parser.getIPsForUser(userName, after, before);
      System.out.println("User \""+ userName +"\" unique IPs: " + userUniqueIP);

      Set<String> eventUniqueIP = parser.getIPsForEvent(Event.WRITE_MESSAGE, after, before);
      System.out.println("Event filtered IPs: " + eventUniqueIP);

      Set<String> statusUniqueIP = parser.getIPsForStatus(Status.OK, after, before);
      System.out.println("Status filtered IPs: " + statusUniqueIP);
    } catch (ParseException ex) {
      ex.printStackTrace();
    }
  }
}
