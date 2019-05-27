package com.example.punissementmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.view.View;
import android.widget.CalendarView;

import com.objetsjava.Punissement;

import java.util.Date;

public class CalendarActivity extends AppCompatActivity {
    private  static final String TAG = "CalendarActivity";
    private CalendarView mCalendarView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calendar);

        mCalendarView = (CalendarView) findViewById(R.id.calendarView);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {


            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String date = year + "/" + month + "/" + dayOfMonth;
                Log.d(TAG, "onSelectedDayChange: yyyy/mm/dd:" + date);
                Intent punissement = new Intent(CalendarActivity.this, PunissementActivity.class);
                punissement.putExtra("date", date);
                startActivity(punissement);
            }
        });
        }

    public boolean comparaisonDate (String date, Date datePunition){

            Date dateCalendrier = ApplicationManager.getInstance().ConvertirStringEnDate(date);

            if (dateCalendrier.compareTo(datePunition) == 0) {
                return true ;
            }
                return false;
        }
}
