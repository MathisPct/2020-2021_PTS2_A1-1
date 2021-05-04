/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Metier;

import java.util.Date;

/**
 *
 * @author Eileen
 */
public class Activity {
    private ActivityStatus status;
    private ActivityType type;
    private Date startDate;
    private Date endDate;
    private String summary;
    private String details;

    public Activity(ActivityStatus status, ActivityType type, Date startDate, Date endDate, String summary, String details) {
        this.status = status;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.summary = summary;
        this.details = details;
    }
    
    
}
