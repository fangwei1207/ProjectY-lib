//package com.zhy.weixin6.adpter;
//
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//
//import com.zhy.weixin6.ui.DayTabFragment;
//
//public class OnItemClickListenerImpl implements OnItemClickListener {
//	
//	private CalendarAdapter adapter = null;
//	private DayTabFragment activity = null;
//	public OnItemClickListenerImpl(CalendarAdapter adapter, DayTabFragment activity) {
//		this.adapter = adapter;
//		this.activity = activity;
//	}	
//	
//	public void onItemClick(AdapterView<?> gridView, View view, int position, long id) {
//		if (activity.currList.get(position).isThisMonth() == false) {
//			return;
//		}
//		adapter.setSelectedPosition(position);  
//		adapter.notifyDataSetInvalidated(); 
//		activity.lastSelected = activity.currList.get(position).getDate();
//	}
//	
//}
