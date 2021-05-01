package com.company;

import com.company.utils.Event;
import com.company.utils.Status;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogParser implements IPQuery {

  private static final String LOG_PATH = "03_LogParser/src/main/resources/log.log";
  private static final String RESULTS_FILE_PATH = "03_LogParser/src/main/resources/results.txt";
  private static final Pattern LOG_PARSER_PATTERN = Pattern.
      compile("([\\d.]+)\\t([\\w\\s]+)\\t([\\d.:\\s]+)\\t([\\w]+)\\s?([\\d]+)?\\t([\\w]+)");
  private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
  private static List<LogRecord> result = new ArrayList<>();

  public void writeSuccessfulResultsToFile() {
    Stream<CharSequence> resultsStream = result.stream().filter(r -> (r.getEvent().equals(Event.DONE_TASK)
        || r.getEvent().equals(Event.SOLVE_TASK)) && r.getStatus().equals(Status.OK))
        .map(LogRecord::getUsername).distinct().map(s -> s);
    try {
      Files.write(Paths.get(RESULTS_FILE_PATH), resultsStream::iterator);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public void readLogFromFile() {
    try (Stream<String> stream = Files.lines(Paths.get(LOG_PATH))){
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

  @Override
  public int getNumberOfUniqueIPs(Date after, Date before) {
    LocalDateTime afterLDT = after.toInstant().atZone(ZoneId.systemDefault())
        .toLocalDateTime();
    LocalDateTime beforeLDT = before.toInstant().atZone(ZoneId.systemDefault())
        .toLocalDateTime();

    return (int)result.stream().filter(r -> r.getDate().isBefore(beforeLDT) && r.getDate().isAfter(afterLDT))
        .map(LogRecord::getIp).distinct().count();
  }

  @Override
  public Set<String> getUniqueIPs(Date after, Date before) {
    LocalDateTime afterLDT = after.toInstant().atZone(ZoneId.systemDefault())
        .toLocalDateTime();
    LocalDateTime beforeLDT = before.toInstant().atZone(ZoneId.systemDefault())
        .toLocalDateTime();

    return result.stream().filter(r -> r.getDate().isBefore(beforeLDT) && r.getDate().isAfter(afterLDT))
        .map(LogRecord::getIp).collect(Collectors.toSet());
  }

  @Override
  public Set<String> getIPsForUser(String user, Date after, Date before) {
    LocalDateTime afterLDT = after.toInstant().atZone(ZoneId.systemDefault())
        .toLocalDateTime();
    LocalDateTime beforeLDT = before.toInstant().atZone(ZoneId.systemDefault())
        .toLocalDateTime();

    return result.stream().filter(r -> r.getDate().isBefore(beforeLDT) && r.getDate().isAfter(afterLDT)
        && r.getUsername().equals(user))
        .map(LogRecord::getIp).collect(Collectors.toSet());
  }

  @Override
  public Set<String> getIPsForEvent(Event event, Date after, Date before) {
    LocalDateTime afterLDT = after.toInstant().atZone(ZoneId.systemDefault())
        .toLocalDateTime();
    LocalDateTime beforeLDT = before.toInstant().atZone(ZoneId.systemDefault())
        .toLocalDateTime();

    return result.stream().filter(r -> r.getDate().isBefore(beforeLDT) && r.getDate().isAfter(afterLDT)
        && r.getEvent().equals(event))
        .map(LogRecord::getIp).collect(Collectors.toSet());
  }

  @Override
  public Set<String> getIPsForStatus(Status status, Date after, Date before) {
    LocalDateTime afterLDT = after.toInstant().atZone(ZoneId.systemDefault())
        .toLocalDateTime();
    LocalDateTime beforeLDT = before.toInstant().atZone(ZoneId.systemDefault())
        .toLocalDateTime();

    return result.stream().filter(r -> r.getDate().isBefore(beforeLDT) && r.getDate().isAfter(afterLDT)
        && r.getStatus().equals(status))
        .map(LogRecord::getIp).collect(Collectors.toSet());
  }
}