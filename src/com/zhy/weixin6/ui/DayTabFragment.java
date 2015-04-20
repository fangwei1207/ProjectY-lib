package com.zhy.weixin6.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.SlidingDrawer.OnDrawerScrollListener;
import android.widget.TextView;

import com.zhy.weixin6.AddPlanActivity;
import com.zhy.weixin6.caledar.util.DateUtil;
import com.zhy.weixin6.calendar.doim.CalendarViewBuilder;
import com.zhy.weixin6.calendar.doim.CustomDate;
import com.zhy.weixin6.calendar.widget.CalendarView;
import com.zhy.weixin6.calendar.widget.CalendarView.CallBack;
import com.zhy.weixin6.calendar.widget.CalendarViewPagerLisenter;
import com.zhy.weixin6.calendar.widget.CircleTextView;
import com.zhy.weixin6.calendar.widget.CustomViewPagerAdapter;

public class DayTabFragment extends Fragment implements OnClickListener,CallBack{
	private ViewPager viewPager;
	private CalendarView[] views;
	private TextView showYearView;
	private TextView showMonthView;
	private TextView showWeekView;
	private TextView monthCalendarView;
	private TextView weekCalendarView;
	private CalendarViewBuilder builder = new CalendarViewBuilder();
	private SlidingDrawer mSlidingDrawer;
	private View mContentPager;
	private CustomDate mClickDate;
	private CircleTextView mNowCircleView;
	private CircleTextView mAddCircleView;
	public static final String MAIN_ACTIVITY_CLICK_DATE = "main_click_date";
	
	
	Context mContext = null;

	public DayTabFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mContext = ((MainActivity) getActivity()).getContext();

		View view = inflater.inflate(R.layout.activity_mdaytab, container, false);
		return view;

	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		findViewbyId(view);
	}
	private void findViewbyId(View view) {
		viewPager = (ViewPager) view.findViewById(R.id.viewpager);
		showMonthView = (TextView)view.findViewById(R.id.show_month_view);
		showYearView = (TextView)view.findViewById(R.id.show_year_view);
		showWeekView = (TextView)view.findViewById(R.id.show_week_view);
		views = builder.createMassCalendarViews(mContext, 5, this);
		monthCalendarView = (TextView)view.findViewById(R.id.month_calendar_button);
		weekCalendarView = (TextView)view.findViewById(R.id.week_calendar_button);
		mContentPager = view.findViewById(R.id.contentPager);
		mSlidingDrawer = (SlidingDrawer)view.findViewById(R.id.sildingDrawer);
		mNowCircleView = (CircleTextView)view.findViewById(R.id.now_circle_view);
		mAddCircleView = (CircleTextView)view.findViewById(R.id.add_circle_view);
		monthCalendarView.setOnClickListener(this);
		weekCalendarView.setOnClickListener(this);
		mNowCircleView.setOnClickListener(this);
		mAddCircleView.setOnClickListener(this);
		setViewPager();
		setOnDrawListener();
	}


	private void setViewPager() {
		CustomViewPagerAdapter<CalendarView> viewPagerAdapter = new CustomViewPagerAdapter<CalendarView>(views);
		viewPager.setAdapter(viewPagerAdapter);
		viewPager.setCurrentItem(498); 
		viewPager.setOnPageChangeListener(new CalendarViewPagerLisenter(viewPagerAdapter));
	}

	private void setOnDrawListener() {
		mSlidingDrawer.setOnDrawerOpenListener(new OnDrawerOpenListener() {
			
			@Override
			public void onDrawerOpened() {
				builder.swtichCalendarViewsStyle(CalendarView.WEEK_STYLE);
			}
		});
		mSlidingDrawer.setOnDrawerScrollListener(new OnDrawerScrollListener() {
			
			@Override
			public void onScrollStarted() {
				builder.swtichCalendarViewsStyle(CalendarView.MONTH_STYLE);
			}
			
			@Override
			public void onScrollEnded() {
			}
		});
	}


 public void setShowDateViewText(int year ,int month){
	 showYearView.setText(year+"");
	 showMonthView.setText(month+"月");
	 showWeekView.setText(DateUtil.weekName[DateUtil.getWeekDay()-1]);
 }

 	@Override
 	public void onClick(View v) {
 		switch (v.getId()) {
		case R.id.month_calendar_button:
			swtichBackgroundForButton(true);
			builder.swtichCalendarViewsStyle(CalendarView.MONTH_STYLE);
 			mSlidingDrawer.close();
			break;
		case R.id.week_calendar_button:
			swtichBackgroundForButton(false);
			mSlidingDrawer.open();
			break;
		case R.id.now_circle_view:
			builder.backTodayCalendarViews();
			break;
		case R.id.add_circle_view:
			Intent i = new Intent(mContext,AddPlanActivity.class);
			Bundle mBundle = new Bundle();  
	        mBundle.putSerializable(MAIN_ACTIVITY_CLICK_DATE,mClickDate);
	        i.putExtras(mBundle);  
			startActivity(i);
//			mContext.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
 		}
 	}
 	
 	private void swtichBackgroundForButton(boolean isMonth){
 		if(isMonth){
 			monthCalendarView.setBackgroundResource(R.drawable.press_left_text_bg);
 			weekCalendarView.setBackgroundColor(Color.TRANSPARENT);
 		}else{
 			weekCalendarView.setBackgroundResource(R.drawable.press_right_text_bg);
 			monthCalendarView.setBackgroundColor(Color.TRANSPARENT);
 		}
 	}


	@Override
	public void onMesureCellHeight(int cellSpace) {
		mSlidingDrawer.getLayoutParams().height = mContentPager.getHeight() - cellSpace;
	}

	@Override
	public void clickDate(CustomDate date) {
		mClickDate = date;
		//Toast.makeText(this, date.year+"-"+date.month+"-"+date.day, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void changeDate(CustomDate date) {
		setShowDateViewText(date.year,date.month);
	}

}
