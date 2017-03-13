package ro.horiacalin.istud.PresentationLayer.Controller;

import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ro.horiacalin.istud.BusinessLayer.Interfaces.CallbackDefaultNetwork;
import ro.horiacalin.istud.BusinessLayer.Managers.ApiManager;
import ro.horiacalin.istud.BusinessLayer.Managers.ToolsManager;
import ro.horiacalin.istud.BusinessLayer.Pojo.Scheduale;
import ro.horiacalin.istud.Constants;
import ro.horiacalin.istud.PresentationLayer.Adapters.RecyclerViewAdapterEventsCalendar;
import ro.horiacalin.istud.R;


public class FragmentCalendar extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private CompactCalendarView compactCalendarView;
    private RecyclerView eventsRecyclerView;
    private ImageButton calendarBackButton, calendarForwardButton;
    private TextView calendarMonthTextview;
    private RecyclerViewAdapterEventsCalendar adapterEventsCalendar;
    private List<Event> eventList = new ArrayList<>();
    private List<Event> bookingsFromMap = new ArrayList<>();
    private Date dateClicked;
    public FragmentCalendar() {
        // Required empty public constructor
    }


    public static FragmentCalendar newInstance(String param1) {
        FragmentCalendar fragment = new FragmentCalendar();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
        compactCalendarView = (CompactCalendarView) rootView.findViewById(R.id.calendarView);
        eventsRecyclerView = (RecyclerView) rootView.findViewById(R.id.eventsRecyclerView);
        calendarBackButton = (ImageButton) rootView.findViewById(R.id.calendarBackButton);
        calendarForwardButton = (ImageButton) rootView.findViewById(R.id.calendarForwardButton);
        calendarMonthTextview = (TextView) rootView.findViewById(R.id.calendarMonthTextView);
        adapterEventsCalendar = new RecyclerViewAdapterEventsCalendar(getActivity().getApplicationContext(), bookingsFromMap);

        eventsRecyclerView.setNestedScrollingEnabled(false);
        eventsRecyclerView.setAdapter(adapterEventsCalendar);
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);


        final Date firsDayCurrentMonth = compactCalendarView.getFirstDayOfCurrentMonth();
        calendarMonthTextview.setText(DateFormat.format("MMMM", firsDayCurrentMonth));
        compactCalendarView.setCurrentDayBackgroundColor(R.color.etti);
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                bookingsFromMap = compactCalendarView.getEvents(dateClicked);
                adapterEventsCalendar.setmDataset(bookingsFromMap);


            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                calendarMonthTextview.setText(DateFormat.format("MMMM", firstDayOfNewMonth));

            }
        });

        compactCalendarView.setSelected(true);

        if(eventList.size()<=0) {
            getScheduale();
        }else{

            compactCalendarView.addEvents(eventList);
            eventList.clear();
            getScheduale();

        }


        compactCalendarView.setUseThreeLetterAbbreviation(true);
        compactCalendarView.shouldDrawIndicatorsBelowSelectedDays(true);
        compactCalendarView.requestFocus();

        return rootView;
    }

    private void getScheduale() {
        ApiManager.getInstance().getScheduale(ToolsManager.getInstance().getUser(getActivity().getApplicationContext()).getId(), getActivity().getApplicationContext(), new CallbackDefaultNetwork() {
            @Override
            public void success(Object object) {
                List<Scheduale> schedualeList = (List<Scheduale>) object;
                eventList.clear();
                for (Scheduale s : schedualeList) {
                    try {
                        Date simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy").parse(s.getStartDate());
                        Event e = null;
                        while (simpleDateFormat.before(new SimpleDateFormat("dd-MM-yyyy").parse(Constants.END_SEMN_2))) {
                            e = new Event(Color.RED, simpleDateFormat.getTime(), s);
                            eventList.add(e);
                            switch (s.getFrequency()) {
                                case Constants.COURSE_FREQ_WEEKLY:
                                    simpleDateFormat.setTime(simpleDateFormat.getTime() + Constants.MILLISECONDS_IN_ONE_WEEK);
                                    break;
                                case Constants.COURSE_FREQ_EVEN:
                                case Constants.COURSE_FREQ_ODD:
                                    simpleDateFormat.setTime(simpleDateFormat.getTime() + Constants.MILLISECONDS_IN_TWO_WEEK);
                                    break;
                                case Constants.COURSE_FREQ_MONTHLY:
                                    simpleDateFormat.setTime(simpleDateFormat.getTime() + Constants.COURSE_FREQ_MONTHLY);
                                    break;
                            }
                        }


                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                compactCalendarView.addEvents(eventList);

            }

            @Override
            public void fail(String message) {

            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


}
