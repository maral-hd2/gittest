package org.jfree.chart.axis.junit;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import junit.framework.TestCase;

import org.jfree.chart.axis.SegmentedTimeline;
import org.jfree.util.Log;
import org.jfree.util.PrintStreamLogTarget;

/**
 * @author fredatwork
 */
public class SegmentedTimelineTests2 extends TestCase {

    /**
     * Constructor
     */
    public SegmentedTimelineTests2() {
        super();
        Log.getInstance().replaceTargets(new PrintStreamLogTarget());
    }

    /**
     * Test 1 checks 9am Friday 26 March 2004 converts to a timeline value and back again
     * correctly.  This is prior to Daylight Saving.
     */
    public void test1() {
            
        Log.info("Entering SegmentedTimelineTests2.test1()...");
        Calendar cal = Calendar.getInstance(Locale.UK);
        cal.set(Calendar.YEAR, 2004);
        cal.set(Calendar.MONTH, Calendar.MARCH);
        cal.set(Calendar.DAY_OF_MONTH, 26);
        cal.set(Calendar.HOUR_OF_DAY, 9);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date date = cal.getTime();
        Log.info("date = " + date + "[" + date.getTime() + "]");                  
                  
        SegmentedTimeline timeline = getTimeline();      

        long value = timeline.toTimelineValue(date);   
        Log.info("value = " + value);
      
        long ms = timeline.toMillisecond(value);
        Log.info("ms = " + ms);
      
        Calendar cal2 = Calendar.getInstance(Locale.UK);
        cal2.setTime(new Date(ms));
        Date reverted = cal2.getTime();
        Log.info("reverted = " + reverted + '[' + reverted.getTime() + "]");   
        Log.info("");
      
        assertTrue("test1", value == (900000 * 34) && date.getTime() == reverted.getTime());
 
    }

    /**
     * Test 2 checks 9.15am Friday 26 March 2004 converts to a timeline value and back again
     * correctly.  This is prior to Daylight Saving.
     */
    public void test2() {
            
        Log.info("Entering SegmentedTimelineTests2.test2()...");
        Calendar cal = Calendar.getInstance(Locale.UK);
        cal.set(Calendar.YEAR, 2004);
        cal.set(Calendar.MONTH, Calendar.MARCH);
        cal.set(Calendar.DAY_OF_MONTH, 26);
        cal.set(Calendar.HOUR_OF_DAY, 9);
        cal.set(Calendar.MINUTE, 15);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date date = cal.getTime();
        Log.info("date = " + date + "[" + date.getTime() + "]");                  
                  
        SegmentedTimeline timeline = getTimeline();      

        long value = timeline.toTimelineValue(date);   
        Log.info("value = " + value);
      
        long ms = timeline.toMillisecond(value);
        Log.info("ms = " + ms);
      
        Calendar cal2 = Calendar.getInstance(Locale.UK);
        cal2.setTime(new Date(ms));
        Date reverted = cal2.getTime();
        Log.info("reverted = " + reverted + '[' + reverted.getTime() + "]");   
        Log.info("");
      
        assertTrue(
            "test2", value == (900000 * 34 + 900000) && date.getTime() == reverted.getTime()
        );
     }

    /**
     * Test 3 checks 9.30am Friday 26 March 2004 converts to a timeline value and back again
     * correctly.  This is prior to Daylight Saving.
     */
    public void test3() {
            
        Log.info("Entering SegmentedTimelineTests2.test3()...");
        Calendar cal = Calendar.getInstance(Locale.UK);
        cal.set(Calendar.YEAR, 2004);
        cal.set(Calendar.MONTH, Calendar.MARCH);
        cal.set(Calendar.DAY_OF_MONTH, 26);
        cal.set(Calendar.HOUR_OF_DAY, 9);
        cal.set(Calendar.MINUTE, 30);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date date = cal.getTime();
        Log.info("date = " + date + "[" + date.getTime() + "]");                  
                  
        SegmentedTimeline timeline = getTimeline();      

        long value = timeline.toTimelineValue(date);   
        Log.info("value = " + value);
      
        long ms = timeline.toMillisecond(value);
        Log.info("ms = " + ms);
      
        Calendar cal2 = Calendar.getInstance(Locale.UK);
        cal2.setTime(new Date(ms));
        Date reverted = cal2.getTime();
        Log.info("reverted = " + reverted + '[' + reverted.getTime() + "]");   
        Log.info("");
      
        assertTrue(
            "test2", value == (900000 * 34 + 900000 * 2) && date.getTime() == reverted.getTime()
        );
    }

    /**
     * Test 4 checks 9.30am Friday 26 March 2004 (+ 1 millisecond) converts to a timeline 
     * value and back again correctly.  This is prior to Daylight Saving.
     */
    public void test4() {
            
        Log.info("Entering SegmentedTimelineTests2.test4()...");
        Calendar cal = Calendar.getInstance(Locale.UK);
        cal.set(Calendar.YEAR, 2004);
        cal.set(Calendar.MONTH, Calendar.MARCH);
        cal.set(Calendar.DAY_OF_MONTH, 26);
        cal.set(Calendar.HOUR_OF_DAY, 9);
        cal.set(Calendar.MINUTE, 30);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 1);
        Date date = cal.getTime();
        Log.info("date = " + date + "[" + date.getTime() + "]");                  
                  
        SegmentedTimeline timeline = this.getTimeline();      

        long value = timeline.toTimelineValue(date);   
        Log.info("value = " + value);
      
        long ms = timeline.toMillisecond(value);
        Log.info("ms = " + ms);
      
        Calendar cal2 = Calendar.getInstance(Locale.UK);
        cal2.setTime(new Date(ms));
        Date reverted = cal2.getTime();
        Log.info("reverted = " + reverted + '[' + reverted.getTime() + "]");   
        Log.info("");
      
        assertTrue(
            "test4", value == (900000 * 34 + 900000 * 2 + 1) 
            && date.getTime() == reverted.getTime()
        );
    }

    /**
     * Test 5 checks 5.30pm Thursday 25 March 2004 converts to a timeline 
     * value and back again correctly.  As it is in the excluded segment, we expect
     * it to map to 9am, Friday 26 March 2004.  This is prior to Daylight Saving.
     */
    public void test5() {
            
        Log.info("Entering SegmentedTimelineTests2.test5()...");
        Calendar cal = Calendar.getInstance(Locale.UK);
        cal.set(Calendar.YEAR, 2004);
        cal.set(Calendar.MONTH, Calendar.MARCH);
        cal.set(Calendar.DAY_OF_MONTH, 25);
        cal.set(Calendar.HOUR_OF_DAY, 17);
        cal.set(Calendar.MINUTE, 30);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date date = cal.getTime();
        Log.info("date = " + date + "[" + date.getTime() + "]");                  
                  
        SegmentedTimeline timeline = this.getTimeline();      

        long value = timeline.toTimelineValue(date);   
        Log.info("value = " + value);
      
        long ms = timeline.toMillisecond(value);
        Log.info("ms = " + ms);
        
        Calendar cal2 = Calendar.getInstance(Locale.UK);
        cal2.setTime(new Date(ms));
        Date reverted = cal2.getTime();
        Log.info("reverted = " + reverted + '[' + reverted.getTime() + "]");   
        Log.info("");
      
        Calendar expectedReverted = Calendar.getInstance(Locale.UK);
        expectedReverted.set(Calendar.YEAR, 2004);
        expectedReverted.set(Calendar.MONTH, Calendar.MARCH);
        expectedReverted.set(Calendar.DAY_OF_MONTH, 26);
        expectedReverted.set(Calendar.HOUR_OF_DAY, 9);
        expectedReverted.set(Calendar.MINUTE, 0);
        expectedReverted.set(Calendar.SECOND, 0);
        expectedReverted.set(Calendar.MILLISECOND, 0);
      
        assertTrue(
            "test5", value == (900000 * 34) 
            && expectedReverted.getTime().getTime() == reverted.getTime()
        );
    }

    /**
     * Test 6 checks that 9am on Sunday 28 March 2004 converts to the timeline value and
     * back again correctly.  Note that Saturday and Sunday are excluded from the timeline, so
     * we expect the value to map to 9am on Monday 29 March 2004. This is during daylight saving.
     */
    public void test6() {
            
        Log.info("Entering SegmentedTimelineTests2.test6()...");
        Calendar cal = Calendar.getInstance(Locale.UK);
        cal.set(Calendar.YEAR, 2004);
        cal.set(Calendar.MONTH, Calendar.MARCH);
        cal.set(Calendar.DAY_OF_MONTH, 28);
        cal.set(Calendar.HOUR_OF_DAY, 9);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date date = cal.getTime();
        Log.info("date = " + date + "[" + date.getTime() + "]");                  
                  
        SegmentedTimeline timeline = getTimeline();      

        long value = timeline.toTimelineValue(date);   
        Log.info("value = " + value);
      
        long ms = timeline.toMillisecond(value);
        Log.info("ms = " + ms);
      
        Calendar cal2 = Calendar.getInstance(Locale.UK);
        cal2.setTime(new Date(ms));
        Date reverted = cal2.getTime();
        Log.info("reverted = " + reverted + '[' + reverted.getTime() + "]");   
        Log.info("");
      
        Calendar expectedReverted = Calendar.getInstance(Locale.UK);
        expectedReverted.set(Calendar.YEAR, 2004);
        expectedReverted.set(Calendar.MONTH, Calendar.MARCH);
        expectedReverted.set(Calendar.DAY_OF_MONTH, 29);
        expectedReverted.set(Calendar.HOUR_OF_DAY, 9);
        expectedReverted.set(Calendar.MINUTE, 0);
        expectedReverted.set(Calendar.SECOND, 0);
        expectedReverted.set(Calendar.MILLISECOND, 0);
      
        assertTrue(
            "test6", value == (900000 * 34 * 2) 
            && expectedReverted.getTime().getTime() == reverted.getTime()
        );
    }
             
    /**
     * Test 7 checks 9am Monday 29 March 2004 converts to a timeline value and back again
     * correctly.  This is during Daylight Saving.
     */
    public void test7() {
            
        Log.info("Entering SegmentedTimelineTests2.test7()...");
        Calendar cal = Calendar.getInstance(Locale.UK);
        cal.set(Calendar.YEAR, 2004);
        cal.set(Calendar.MONTH, Calendar.MARCH);
        cal.set(Calendar.DAY_OF_MONTH, 29);
        cal.set(Calendar.HOUR_OF_DAY, 9);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date date = cal.getTime();
        Log.info("date = " + date + "[" + date.getTime() + "]");                  
                  
        SegmentedTimeline timeline = getTimeline();      

        long value = timeline.toTimelineValue(date);   
        Log.info("value = " + value);
      
        long ms = timeline.toMillisecond(value);
        Log.info("ms = " + ms);
      
        Calendar cal2 = Calendar.getInstance(Locale.UK);
        cal2.setTime(new Date(ms));
        Date reverted = cal2.getTime();
        Log.info("reverted = " + reverted + '[' + reverted.getTime() + "]");   
        Log.info("");
      
        Calendar expectedReverted = Calendar.getInstance();
        expectedReverted.set(Calendar.YEAR, 2004);
        expectedReverted.set(Calendar.MONTH, Calendar.MARCH);
        expectedReverted.set(Calendar.DAY_OF_MONTH, 29);
        expectedReverted.set(Calendar.HOUR_OF_DAY, 9);
        expectedReverted.set(Calendar.MINUTE, 0);
        expectedReverted.set(Calendar.SECOND, 0);
        expectedReverted.set(Calendar.MILLISECOND, 0);
      
        assertTrue(
            "test7", value == (900000 * 34 * 2) 
            && expectedReverted.getTime().getTime() == reverted.getTime()
        );
    }

    /**
     * Test 8.
     */
    public void test8() {
            
        Log.info("Entering SegmentedTimelineTests2.test8()...");
        Calendar cal = Calendar.getInstance(Locale.UK);
        cal.set(Calendar.YEAR, 2004);
        cal.set(Calendar.MONTH, Calendar.MARCH);
        cal.set(Calendar.DAY_OF_MONTH, 29);
        cal.set(Calendar.HOUR_OF_DAY, 10);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date date = cal.getTime();
        Log.info("date = " + date + "[" + date.getTime() + "]");                  
                  
        SegmentedTimeline timeline = getTimeline();      
      
        // Add exception in included segments
        cal.set(Calendar.YEAR, 2004);
        cal.set(Calendar.MONTH, Calendar.MARCH);
        cal.set(Calendar.DAY_OF_MONTH, 29);
        cal.set(Calendar.HOUR_OF_DAY, 9);
        cal.set(Calendar.MINUTE, 15);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        timeline.addException(cal.getTime());
            
        long value = timeline.toTimelineValue(date);   
        Log.info("value = " + value);
      
        long ms = timeline.toMillisecond(value);
        Log.info("ms = " + ms);
      
        Calendar cal2 = Calendar.getInstance(Locale.UK);
        cal2.setTime(new Date(ms));
        Date reverted = cal2.getTime();
        Log.info("reverted = " + reverted + '[' + reverted.getTime() + "]");   
        Log.info("");
      
        Calendar expectedReverted = Calendar.getInstance();
        expectedReverted.set(Calendar.YEAR, 2004);
        expectedReverted.set(Calendar.MONTH, Calendar.MARCH);
        expectedReverted.set(Calendar.DAY_OF_MONTH, 29);
        expectedReverted.set(Calendar.HOUR_OF_DAY, 10);
        expectedReverted.set(Calendar.MINUTE, 0);
        expectedReverted.set(Calendar.SECOND, 0);
        expectedReverted.set(Calendar.MILLISECOND, 0);
      
        assertTrue(
            "test8", value == (900000 * 34 * 2 + 900000 * (4 - 1)) 
            && expectedReverted.getTime().getTime() == reverted.getTime()
        );
    }
   
    /**
     * Creates a timeline from 9am on Thursday 25 March 2004 to 5pm on 
     * Tuesday 30 March 2004, for the UK locale.
     * 
     * @return A timeline.
     */
    private SegmentedTimeline getTimeline() {
        Calendar cal = Calendar.getInstance(Locale.UK);
        cal.set(Calendar.YEAR, 2004);
        cal.set(Calendar.MONTH, Calendar.MARCH);
        cal.set(Calendar.DAY_OF_MONTH, 25);
        cal.set(Calendar.HOUR_OF_DAY, 9);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date from = cal.getTime();

        cal = Calendar.getInstance(Locale.UK);
        cal.set(Calendar.YEAR, 2004);
        cal.set(Calendar.MONTH, Calendar.MARCH);
        cal.set(Calendar.DAY_OF_MONTH, 30);
        cal.set(Calendar.HOUR_OF_DAY, 17);
        cal.set(Calendar.MINUTE, 30);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date to = cal.getTime();

        return getTimeline(from, to);
    }
   
    /**
     * Returns a segmented timeline for 15 minute segments, Monday to Friday only, between
     * 9am and 5pm.  The UK locale is used.
     * 
     * @param start  the start date.
     * @param end  the end date.
     * 
     * @return The timeline.
     */
    private SegmentedTimeline getTimeline(Date start, Date end) {
      
        Calendar cal = Calendar.getInstance(Locale.UK);
        cal.set(Calendar.YEAR, 1970);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 9);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date open = cal.getTime();

        cal = Calendar.getInstance(Locale.UK);
        cal.set(Calendar.YEAR, 1970);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 17);
        cal.set(Calendar.MINUTE, 30);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date close = cal.getTime();
                        
        SegmentedTimeline result = null;      
        // Create a segmented time line (segment size : 15 minutes)
        long quarterHourCount = (close.getTime() - open.getTime())
            / SegmentedTimeline.FIFTEEN_MINUTE_SEGMENT_SIZE;
        long totalQuarterHourCount = SegmentedTimeline.DAY_SEGMENT_SIZE 
            / SegmentedTimeline.FIFTEEN_MINUTE_SEGMENT_SIZE;
        result = new SegmentedTimeline(
            SegmentedTimeline.FIFTEEN_MINUTE_SEGMENT_SIZE,
            (int) quarterHourCount, (int) (totalQuarterHourCount - quarterHourCount)  
        );
        result.setAdjustForDaylightSaving(true);
        // Set start time
        result.setStartTime(start.getTime());
        // Saturday and Sundays are non business hours
        result.setBaseTimeline(SegmentedTimeline.newMondayThroughFridayTimeline());
        /* PUT exclusions in test */
        if (start != null && end != null) {
            result.addBaseTimelineExclusions(start.getTime(), end.getTime());
        }
          
        return result;   
    }
      
}
