package com.yucheng.byxf.mini.adapter;

import java.util.List;

import com.yucheng.byxf.mini.message.SuggestionMsgEntity;
import com.yucheng.byxf.mini.ui.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SuggestionAdapter extends BaseAdapter {

	
	//ListView视图的内容由IMsgViewType决定
		public static interface IMsgViewType
		{
			//对方发来的信息
			int IMVT_COM_MSG = 0;
			//自己发出的信息
			int IMVT_TO_MSG = 1;
		}
		
		
    private static final String TAG = SuggestionAdapter.class.getSimpleName();
    private List<SuggestionMsgEntity> data;
    private Context context;  
    private LayoutInflater mInflater;

    public SuggestionAdapter(Context context, List<SuggestionMsgEntity> data) {
        this.context = context;
        this.data = data;
        mInflater = LayoutInflater.from(context);
    }
	
	
	
	
	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO 自动生成的方法存根
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO 自动生成的方法存根
		 return position;
	}

    //获取项的类型
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		SuggestionMsgEntity entity = data.get(position);
	 	
	 	if (entity.getType().equals("1"))
	 	{
	 		return IMsgViewType.IMVT_COM_MSG;
	 	}else{
	 		return IMsgViewType.IMVT_TO_MSG;
	 	}
	 	
	}

	//获取项的类型数
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 2;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO 自动生成的方法存根
		SuggestionMsgEntity entity = data.get(position);
//    	boolean isComMsg = entity.getMsgType();
    		
    	ViewHolder viewHolder = null;	
	    if (convertView == null) 
	    {
	    	  if (entity.getType().equals("1"))
			  {
	    		  //如果是对方发来的消息，则显示的是左气泡 
				  convertView = mInflater.inflate(R.layout.chatting_item_msg_text_left, null);
			  }else{
				  //如果是自己发出的消息，则显示的是右气泡
				  convertView = mInflater.inflate(R.layout.chatting_item_msg_text_right, null);
			  }

	    	  viewHolder = new ViewHolder();
			  viewHolder.tvSendTime = (TextView) convertView.findViewById(R.id.tv_sendtime);
			  viewHolder.tvUserName = (TextView) convertView.findViewById(R.id.tv_username);
			  viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_chatcontent);
			//  viewHolder.isComMsg = isComMsg;
			  
			  convertView.setTag(viewHolder);
	    }else{
	        viewHolder = (ViewHolder) convertView.getTag();
	    }
	    
	    viewHolder.tvSendTime.setText(entity.getCreateTime());
	    viewHolder.tvUserName.setText(entity.getLaunchName());
	    viewHolder.tvContent.setText(entity.getContent());
	    
	    return convertView;
	}
    //通过ViewHolder显示项的内容
    static class ViewHolder { 
        public TextView tvSendTime;
        public TextView tvUserName;
        public TextView tvContent;
        public boolean isComMsg = true;
    }
}
