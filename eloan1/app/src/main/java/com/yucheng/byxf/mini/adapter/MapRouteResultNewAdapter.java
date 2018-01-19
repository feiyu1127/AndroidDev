package com.yucheng.byxf.mini.adapter;

import java.util.List;

import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRouteLine.DrivingStep;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRouteLine.TransitStep;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.WalkingRouteLine.WalkingStep;

import com.yucheng.byxf.mini.ui.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MapRouteResultNewAdapter extends BaseAdapter{
	private Context context;
	private int searchType;
	private List<DrivingRouteLine> listDrivingRouteResult;
	private List<TransitRouteLine> listTransitRouteResult;
	private List<WalkingRouteLine> listWalkingRouteResult;
	
	private String qiString;
	private String shiString;
	
	public  MapRouteResultNewAdapter(Context context, int searchType,
			List<DrivingRouteLine> listDrivingRouteResult,
			List<TransitRouteLine> listTransitRouteResult,
			List<WalkingRouteLine> listWalkingRouteResult
			,String qiString,String shiString) {
		super();
		this.context = context;
		this.searchType = searchType;
		this.listDrivingRouteResult = listDrivingRouteResult;
		this.listTransitRouteResult = listTransitRouteResult;
		this.listWalkingRouteResult = listWalkingRouteResult;
		this.qiString = qiString;
		this.shiString = shiString;
	}
	
	
	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		if (searchType == 0) {
			return listDrivingRouteResult.size();
		} else if (searchType == 1) {
			return listTransitRouteResult.size();
		} else if (searchType == 2) {
			return listWalkingRouteResult.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if (searchType == 0) {
			return listDrivingRouteResult.get(position);
		} else if (searchType == 1) {
			return listTransitRouteResult.get(position);
		} else if (searchType == 2) {
			return listWalkingRouteResult.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO 自动生成的方法存根
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO 自动生成的方法存根
		
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.map_route_listitem, null);
			holder = new ViewHolder();
			holder.number = (TextView) convertView.findViewById(R.id.number);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			holder.distance = (TextView) convertView
					.findViewById(R.id.distance);
			holder.walk_distance = (TextView) convertView
					.findViewById(R.id.walk_distance);
			holder.time = (TextView) convertView.findViewById(R.id.time);
			holder.image = (ImageView) convertView.findViewById(R.id.image);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.number.setText(position + 1 + "");
		if (searchType == 0) {
			// for (int j = 0; j <
			// listDrivingRouteResult.get(position).getNumRoutes(); j++) {
			// int number =
			// listDrivingRouteResult.get(position).getRoute(j).getNumSteps();
			// for (int k = 0; k < number; k++) {
			// System.out.println(listDrivingRouteResult.get(position).getRoute(j).getStep(k)
			// .getContent());
			// }
			// }
			holder.title.setText(qiString+"-"+shiString);
			int time = listDrivingRouteResult.get(position).getDuration() / 60;
			if (time < 60) {
				holder.time.setText(time + "分钟");
			}else {
				int min = time % 60;
				int hour = time / 60;
				holder.time.setText(hour+"小时"+min+"分");
			}
			holder.distance.setText((double) listDrivingRouteResult.get(
					position).getDistance()
					/ 1000 + "公里");
			holder.image.setVisibility(View.GONE);
		} else if (searchType == 1) {
			
			String disStr="";
//			for(int j=0;j<listTransitRouteResult.size();j++){
//				for(int z=0;z<listTransitRouteResult.get(j).getAllStep().size();z++){
//					if(!(listTransitRouteResult.get(j).getAllStep().get(z).getStepType()+"").equals("WAKLING")){
//					System.out.println("disStr"+disStr);
//					disStr=disStr+ listTransitRouteResult.get(j).getAllStep().get(z).getVehicleInfo().getTitle()+"  ";
//					}
//				}	
//			}
//			for(int z=0;z<listTransitRouteResult.get(0).getAllStep().size();z++){
//				if(!(listTransitRouteResult.get(0).getAllStep().get(z).getStepType()+"").equals("WAKLING")){
//				System.out.println("disStr"+disStr);
//				disStr=disStr+ listTransitRouteResult.get(0).getAllStep().get(z).getVehicleInfo().getTitle()+"  ";
//				}
//			}
		System.out.println("1="+listTransitRouteResult.get(position).getAllStep().size());
		System.out.println("1="+"");
				for(int z=0;z<listTransitRouteResult.get(position).getAllStep().size();z++){
					if(!(listTransitRouteResult.get(position).getAllStep().get(z).getStepType()+"").equals("WAKLING")){
						System.out.println("disStr"+disStr);
							disStr=disStr+("-"+listTransitRouteResult.get(position).getAllStep().get(z).getVehicleInfo().getTitle());
					}
				}	
			
			disStr=disStr.substring(1,disStr.length());
			System.out.println("公交名称"+disStr);
			
			holder.title.setText(disStr);
			//--
			int dis = 0;
			for (int j = 0; j < listTransitRouteResult.get(position)
					.getAllStep().size(); j++) {
				if (listTransitRouteResult.get(position).getAllStep().get(j).getStepType().ordinal() == 2) {
					dis = dis + listTransitRouteResult.get(position).getAllStep().get(j).getDistance();
				}
			}
			System.out.println("dis====>" + dis);
			if (dis > 1000) {
				double dou_dis = dis / 1000;
				holder.walk_distance.setText("步行" + dou_dis + "公里");
			} else {
				holder.walk_distance.setText("步行" + dis + "米");
			}
			int time = listTransitRouteResult.get(position).getDuration() / 60;
			if (time < 60) {
				holder.time.setText(time + "分钟");
			}else {
				int min = time % 60;
				int hour = time / 60;
				holder.time.setText(hour+"小时"+min+"分");
			}
			holder.distance.setText((double) listTransitRouteResult.get(
					position).getDistance()
					/ 1000 + "公里");
		} else if (searchType == 2) {
			holder.title.setText(qiString+"-"+shiString);
			int time = listWalkingRouteResult.get(position).getDuration() / 60;
			if (time < 60) {
				holder.time.setText(time + "分钟");
			}else {
				int min = time % 60;
				int hour = time / 60;
				holder.time.setText(hour+"小时"+min+"分");
			}

			holder.distance.setText((double) listWalkingRouteResult.get(
					position).getDistance()
					/ 1000 + "公里");
			holder.image.setVisibility(View.GONE);
		}

		return convertView;
	}

	class ViewHolder {
		TextView number;
		TextView title;
		TextView distance;
		TextView time;
		TextView walk_distance;
		ImageView image;
	}

}
