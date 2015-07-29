/* 
 * Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved.
 *
 * This file is part of Aspose.Tasks. The source code in this file
 * is only intended as a supplement to the documentation, and is provided
 * "as is", without warranty of any kind, either expressed or implied.
 */
 
package com.aspose.tasks.examples.calendars;

import com.aspose.tasks.*;
import com.aspose.tasks.examples.Utils;

public class GetWorkingHours
{
    public static void main(String[] args) throws Exception
    {
        // The path to the documents directory.
        String dataDir = Utils.getDataDir(GetWorkingHours.class);

        long OneSec = 10000000;//microsecond * 10
        long OneMin = 60 * OneSec;
        long OneHour = 60 * OneMin;

        Project project = new Project(dataDir + "BaselineTask.mpp");
        Task task = project.getRootTask().getChildren().getById(1);

        Calendar taskCalendar = task.get(Tsk.CALENDAR);

        java.util.Calendar calStartDate = java.util.Calendar.getInstance();
        calStartDate.setTime(task.get(Tsk.START));

        java.util.Calendar calEndDate = java.util.Calendar.getInstance();
        calEndDate.setTime(task.get(Tsk.FINISH));

        java.util.Calendar tempDate = java.util.Calendar.getInstance();
        tempDate = calStartDate;

        Resource resource = project.getResources().getById(1);
        Calendar resourceCalendar = resource.get(Rsc.CALENDAR);

        //TimeSpan timeSpan;
        long timeSpan = 0;

        //Get Duration in Minutes
        double durationInMins = 0;

        while (tempDate.before(calEndDate))
        {
            if (taskCalendar.isDayWorking(tempDate.getTime()) && resourceCalendar.isDayWorking(tempDate.getTime()))
            {
                timeSpan = (long) taskCalendar.getWorkingHours(tempDate.getTime());
                durationInMins = durationInMins + (timeSpan / OneMin);
            }
            tempDate.add(java.util.Calendar.DATE, 1);
        }
        tempDate.setTime(task.get(Tsk.START));

        //Get Duration in Hours
        double durationInHours = 0;

        while (tempDate.before(calEndDate))
        {
            if (taskCalendar.isDayWorking(tempDate.getTime()) && resourceCalendar.isDayWorking(tempDate.getTime()))
            {
                timeSpan = (long) taskCalendar.getWorkingHours(tempDate.getTime());
                durationInHours = durationInHours + (timeSpan / OneHour);
            }
            tempDate.add(java.util.Calendar.DATE, 1);
        }
        tempDate.setTime(task.get(Tsk.START));

        //Get Duration in Days
        double durationInDays = 0;

        while (tempDate.before(calEndDate))
        {
            if (taskCalendar.isDayWorking(tempDate.getTime()) && resourceCalendar.isDayWorking(tempDate.getTime()))
            {
                timeSpan = (long) taskCalendar.getWorkingHours(tempDate.getTime());
                if ((timeSpan / OneHour) > 0)
                    durationInDays = durationInDays + (timeSpan/OneHour/8.0);
            }
            tempDate.add(java.util.Calendar.DATE, 1);
        }
        tempDate = calStartDate;

        System.out.println("Duration in Minutes = " + durationInMins);
        System.out.println("Duration in Hours = " + durationInHours);
        System.out.println("Duration in Days = " + durationInDays);
        System.out.println();
    }
}




