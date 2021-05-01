package com.company;

import com.company.utils.Event;
import com.company.utils.Status;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class LogRecord {

  private String ip;
  private String username;
  private LocalDateTime date;
  private Event event;
  private int taskNumber;
  private Status status;

  @Override
  public String toString() {

    String tNumber = (taskNumber == 0) ? "" : ", taskNumber=" + taskNumber;

    return "LogRecord{" +
        "ip='" + ip + '\'' +
        ", username='" + username + '\'' +
        ", date=" + date +
        ", event=" + event +
        tNumber +
        ", status=" + status +
        '}';
  }
}
