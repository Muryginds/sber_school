package com.company;

import com.company.utils.Event;
import com.company.utils.Status;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogParser {

  private static final String LOG_PATH = "03_LogParser/src/main/resources/log.log";
  private static final Pattern LOG_PARSER_PATTERN = Pattern.
      compile("([\\d.]+)\\t([\\w\\s]+)\\t([\\d.:\\s]+)\\t([\\w]+)\\s?([\\d]+)?\\t([\\w]+)");
  private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

  public static void main(String[] args) {
    List<LogRecord> logList = readLogFromFile();
    writeSuccessfulResultsToFile(logList);
  }

  private static void writeSuccessfulResultsToFile(List<LogRecord> logList) {
    logList.stream().filter(r -> (r.getEvent().equals(Event.DONE_TASK)
        || r.getEvent().equals(Event.SOLVE_TASK)) && r.getStatus().equals(Status.OK)).forEach(System.out::println);
  }

  private static List<LogRecord> readLogFromFile() {
    List<LogRecord> result = new ArrayList<>();
    File file = new File(LOG_PATH);
    try (Stream<String> stream = Files.lines(Path.of(file.toURI()))){
      result = stream.flatMap(s -> LOG_PARSER_PATTERN.matcher(s).results().map(matchResult -> {
        LogRecord logRecord = new LogRecord();
        logRecord.setIp(matchResult.group(1));
        logRecord.setUsername(matchResult.group(2));
        logRecord.setDate(parseDateTime(matchResult.group(3)));
        Event event = Event.valueOf(matchResult.group(4));
        logRecord.setEvent(event);
        if(event.equals(Event.SOLVE_TASK) || event.equals(Event.DONE_TASK)){
          logRecord.setTaskNumber(Integer.parseInt(matchResult.group(5)));
        }
        logRecord.setStatus(Status.valueOf(matchResult.group(6)));
        return logRecord;
      })).collect(Collectors.toList());
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return result;
  }

  private static LocalDateTime parseDateTime(String input) {
    int index = input.indexOf(" ");
    String date = input.substring(0, index);
    date = Arrays.stream(date.split("\\.")).map(s -> (s.length() == 1) ? "0".concat(s) : s)
        .reduce((s1, s2) -> s1 + "." + s2).orElse(date);
    String time = input.substring(index + 1);
    time = Arrays.stream(time.split(":")).map(s -> (s.length() == 1) ? "0".concat(s) : s)
        .reduce((s1, s2) -> s1 + ":" + s2).orElse(time);
    return LocalDateTime.parse(date + " " + time, dateTimeFormatter);
  }
}
