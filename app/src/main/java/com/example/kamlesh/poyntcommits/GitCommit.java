package com.example.kamlesh.poyntcommits;

import java.time.LocalDateTime;
import java.text.DateFormat;
import java.util.Date;

public class GitCommit {
    private String id;
    private String name;
    private Date commitDate;
    //private LocalDateTime commitDate;

    public GitCommit(String id, String name, Date commitDate) {
        this.id = id;
        this.name = name;
        this.commitDate = commitDate;
    }

    public String getId() {
        // Show short version of the id, same as seen on the github site
        return id.substring(0, 7);
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return commitDate;
    }

    public String getDateAsString() {
        // short version of date
        return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(commitDate);
        //return commitDate.toString();
    }
}

