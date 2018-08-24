package com.nero.simpledateselector;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.widget.DatePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/*Created by cs07 on 7/2/18.*/

@SuppressWarnings("all")
public class DateSelector {

    private Activity activity;
    private OnDateSelectedListener callback;

    public DateSelector(Activity activityParam){
        activity=activityParam;
        if(activityParam instanceof OnDateSelectedListener){
        callback=(OnDateSelectedListener)activity;
        }else {
            throw new HostNotFound("Did you implement OnDateSelectedListener interface in your calling class?");
        }
    }

    public DateSelector(android.app.Fragment fragment){
        if(fragment.getActivity()!=null){
        activity=fragment.getActivity();
        }else {
            throw new ContextNullException("Fragment's host activity is null");
        }
        if(fragment instanceof OnDateSelectedListener){
        callback=(OnDateSelectedListener)fragment;
        }else {
            throw new HostNotFound("Did you implement OnDateSelectedListener interface in your calling class?");
        }
    }

    public DateSelector(android.support.v4.app.Fragment fragment){
        if(fragment.getActivity()!=null){
            activity=fragment.getActivity();
        }else {
            throw new ContextNullException("Fragment's host activity is null");
        }
        if(fragment instanceof OnDateSelectedListener){
            callback=(OnDateSelectedListener)fragment;
        }else {
            throw new HostNotFound("Did you implement OnDateSelectedListener interface in your calling class?");
        }
    }

    public void chooseDate(final String datePattern,final Locale locale, final int requestCode,boolean disableFutureDate){

        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener onDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat sdf = new SimpleDateFormat(datePattern,locale);
                String formattedDate=sdf.format(myCalendar.getTime());
                callback.onDateSelected(formattedDate,requestCode);
            }
        };

        DatePickerDialog dialog=new DatePickerDialog(activity,onDateSetListener,myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));

        if (disableFutureDate) {
            dialog.getDatePicker().setMaxDate(myCalendar.getTimeInMillis());
        }
        dialog.show();

    }

    public void chooseDate(String maxTime,String maxTimePattern,final Locale locale,final String datePattern,final int requestCode){

        long timeInMilliseconds=0;

        SimpleDateFormat sdf = new SimpleDateFormat(maxTimePattern,locale);
        try {
            Date mDate = sdf.parse(maxTime);
            timeInMilliseconds = mDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener onDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat sdf = new SimpleDateFormat(datePattern,locale);
                String formattedDate=sdf.format(myCalendar.getTime());
                callback.onDateSelected(formattedDate,requestCode);
            }
        };

        DatePickerDialog dialog=new DatePickerDialog(activity,onDateSetListener,myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));
        dialog.getDatePicker().setMaxDate(timeInMilliseconds);
        dialog.show();

    }

    public void chooseDate(String minTime,String minTimePattern,final String datePattern,final int requestCode,final Locale locale,boolean disableFutureDate){

        long timeInMilliseconds=0;

        SimpleDateFormat sdf = new SimpleDateFormat(minTimePattern,locale);
        try {
            Date mDate = sdf.parse(minTime);
            timeInMilliseconds = mDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener onDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat sdf = new SimpleDateFormat(datePattern,locale);
                String formattedDate=sdf.format(myCalendar.getTime());
                callback.onDateSelected(formattedDate,requestCode);
            }
        };

        DatePickerDialog dialog=new DatePickerDialog(activity,onDateSetListener,myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));
        dialog.getDatePicker().setMinDate(timeInMilliseconds);
        if(disableFutureDate){
        dialog.getDatePicker().setMaxDate(myCalendar.getTimeInMillis());
        }
        dialog.show();

    }

    public void chooseDate(String minTime,String maxTime,final String datePattern, final Locale locale, final int requestCode){

        long timeInMillisMin=0;long timeInMillisMax=0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",locale);
        try {
            Date minDate = sdf.parse(minTime);
            timeInMillisMin = minDate.getTime();

            Date maxDate = sdf.parse(maxTime);
            timeInMillisMin = maxDate.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener onDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat sdf = new SimpleDateFormat(datePattern,locale);
                String formattedDate=sdf.format(myCalendar.getTime());
                callback.onDateSelected(formattedDate,requestCode);
            }
        };

        DatePickerDialog dialog=new DatePickerDialog(activity,onDateSetListener,myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));
        dialog.getDatePicker().setMaxDate(timeInMillisMax);
        dialog.getDatePicker().setMinDate(timeInMillisMin);
        dialog.show();

    }

}