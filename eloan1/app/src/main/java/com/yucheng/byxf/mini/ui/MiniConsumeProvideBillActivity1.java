package com.yucheng.byxf.mini.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.adapter.MiniConsumeProvideBillWeiChuAdapter;
import com.yucheng.byxf.mini.adapter.MiniConsumeProvideBillYiChuAdapter;
import com.yucheng.byxf.mini.message.XiaoJinYingMingXiresult;
import com.yucheng.byxf.mini.message.XiaoJinYingMingXiresult2;
import com.yucheng.byxf.mini.message.XiaoJinYingMingXiresult_Weichu_Response;
import com.yucheng.byxf.mini.message.XiaoJinYingZhangDanXiangQing;
import com.yucheng.byxf.mini.message.XiaoJinYingZhangDanXiangQing2;
import com.yucheng.byxf.mini.message.weichuzhangdan.Details;
import com.yucheng.byxf.mini.message.weichuzhangdan.Myresult;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.LogUtil;
import com.yucheng.byxf.mini.xiaojinying.XiaoJinYingQueryActivity;
import com.yucheng.byxf.mini.xiaojinying.XiaoJinYingZhangDanH_ListAdapter;

public class MiniConsumeProvideBillActivity1 extends BaseActivity {
    
    /************** Viewpager **************/
    /**
     * 存放Viewpager中的子项
     */
    private List<View> mPagersList = new ArrayList<View>();
    
    private ViewPager mViewPager;
    
    /**
     * viewPager的容器，页面改变后需要刷新
     */
    private RelativeLayout viewPagerContainer;
    
    private final int MPAGERSLISTREADY = 2;
    
    /*********************************/
    // 返回
    private ImageView backzhangdan;
    
    /**
     * 帐单明细
     */
    private ListView listView;
    
    private Myresult mMyresult;
    
    // 账单日期 提供查询
    // private TextView zhangdanriqi_text;
    
    // ListView上部的 提示
    private TextView zhangdanmingxiwenzi;
    
    // 上期账单金额
    private TextView shangqizhangdanjin;
    
    private static final Integer RET_CODE = 0;
    
    // 0--全部 1--指定日期
    private String zhangdanleixing = "0";
    
    public static int currentPosition = -1;
    
    public static int currentPositionTF = -1;
    
    private String loginErrorMessage;
    
    private MiniConsumeProvideBillYiChuAdapter nadapter;

    private MiniConsumeProvideBillWeiChuAdapter weichuAdapter;
    /**
     * 已出帐单
     */
    private List<XiaoJinYingMingXiresult2> mingxilist = new ArrayList<XiaoJinYingMingXiresult2>();
    
    /**
     * 未出出帐单数据
     */
    private List<Details> mWeichList = new ArrayList<Details>();
    
    /**
     * 纵向明细数据
     */
    private List<XiaoJinYingZhangDanXiangQing2> RealitionInfo99 = new ArrayList<XiaoJinYingZhangDanXiangQing2>();
    
    private String kaishi;
    
    private String jieshu;
    private String cardNo = "";
    // 布局容器
    // private LayoutInflater mInflater;
    
    XiaoJinYingZhangDanH_ListAdapter adapter;
    
    /****************************/
    /**
     * 头部ScrollView
     */
    // private MyScrollView mMyScrollView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mini_consume_provide_bill1);
        Intent intent = getIntent();
        cardNo = intent.getExtras().getString("cardNo");
        Date datenow = new Date();
        DateFormat yearFormat = new SimpleDateFormat("yyyy");
        DateFormat monthAndDayformat = new SimpleDateFormat("MM-dd");
        String year = yearFormat.format(datenow);
        String monthAndDay = monthAndDayformat.format(datenow);
        // 当前时间
        kaishi = year+"-"+monthAndDay;
        jieshu = (Integer.parseInt(year)+1) +"-"+monthAndDay;
        zhangdanleixing = "0";
        /*
         * mMyScrollView = (MyScrollView)findViewById(R.id.myscroll_view);
         * mMyScrollView.setPageChangedListener(new MyPageChangedListener()
         * {
         * @Override
         * public void moveToDest(int currid)
         * {
         * // onPagerChenge(currid);
         * }
         * });
         */
        backzhangdan = (ImageView)findViewById(R.id.backzhangdan);
        backzhangdan.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
        initview();
    }
    
    private void initview()
    {
        
        backzhangdan = (ImageView)findViewById(R.id.backzhangdan);
        // Listview上部的 提示
        zhangdanmingxiwenzi = (TextView)findViewById(R.id.zhangdanmingxiwenzi);
        shangqizhangdanjin = (TextView)findViewById(R.id.shangqizhangdanjin);
        
        listView = (ListView)findViewById(R.id.zhangdanmingxi);
        listView.setOnItemClickListener(new OnItemClickListener()
        {
            
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                
                // 选中项
                if (currentPosition == position)
                { // 如果重复点击 关闭
                    currentPosition = -1;
                }
                else
                {
                    currentPosition = position;
                }
                
                if (nadapter != null)
                { // 已出帐单适配器
                    nadapter.notifyDataSetChanged();
                }
                if (weichuAdapter != null)
                { // 未出帐单适配器
                    weichuAdapter.notifyDataSetChanged();
                }
                
            }
        });
        handler.sendEmptyMessage(1);
    }
    
    Handler handler = new Handler()
    {
        
        public void handleMessage(android.os.Message msg)
        {
            
            switch (msg.what)
            {
                case 1:
                    // 联网加载数据
                    initData();
                    
                    break;
                
                case MPAGERSLISTREADY:
                    
                    // 初始化viewpager
                    initViewPager();
                    
                    break;
                
                default:
                    break;
            }
            
        }
        
    };
    
    /**
     * 描述: 联网请求数据 </br>
     * 开发人员：Weiyb</br>
     * 创建时间：2015-7-19</br>
     */
    private void initData()
    {
        if (zhangdanleixing.equals("1"))
        {// 已选时间区间
         // 已出帐单查询
            new riqichaxunzhangdanAsyncTask().execute(cardNo, kaishi, jieshu);
            // "9999010000096118", kaishi, jieshu);
            
        }
        else
        { // 如时没有选择时间区间
        
            // 获取当前时间
            Date datenow = new Date();
            
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            // 当前时间
            String timenow = format.format(datenow);
            
            String[] array = timenow.split("-");
            // 根据当前时间计算出起始时间
            int day = Integer.parseInt(array[0]);
            day = day - 1;
            String olddate = "";
            // 起始时间
            olddate = day + "-" + array[1] + "-" + array[2];
            
            // 请求已出帐单信息
            
            new riqichaxunzhangdanAsyncTask().execute(cardNo,
                olddate,
                timenow);
            
        }
    }
    
    // 已出帐单查询接口
    class riqichaxunzhangdanAsyncTask extends AsyncTask<String, Object, Object>
    {
        
        @Override
        protected Object doInBackground(String... params)
        {
            // 请求已出帐单数据
            HttpHelper httpHelper = HttpHelper.getInstance(MiniConsumeProvideBillActivity1.this);
            List<NameValuePair> arg = new ArrayList<NameValuePair>();
            
            arg.add(new BasicNameValuePair("cardNo", params[0]));
            arg.add(new BasicNameValuePair("startBillDt", params[1]));
            arg.add(new BasicNameValuePair("endBillDt", params[2]));
            
            return httpHelper.post(ContantsAddress.shijianzhangdan, arg, XiaoJinYingMingXiresult.class);
        }
        
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            showProgressDialog();
        }
        
        @Override
        protected void onPostExecute(Object result)
        {
            dismissProgressDialog();
            super.onPostExecute(result);
            riqizhangdanchaxunResponse(result);
            
        }
    }
    
    // 未出帐单查询接口
    class riqichaxunzhangdanAsyncTask2 extends AsyncTask<String, Object, Object>
    {
        
        @Override
        protected Object doInBackground(String... params)
        {
            
            HttpHelper httpHelper = HttpHelper.getInstance(MiniConsumeProvideBillActivity1.this);
            List<NameValuePair> arg = new ArrayList<NameValuePair>();
            
            arg.add(new BasicNameValuePair("cardNo", params[0]));
            
            return httpHelper.post(ContantsAddress.shijianzhangdan_weichu,
                arg,
                XiaoJinYingMingXiresult_Weichu_Response.class);
        }
        
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            // showProgressDialog();
        }
        
        @Override
        protected void onPostExecute(Object result)
        {
            dismissProgressDialog();
            super.onPostExecute(result);
            riqizhangdanchaxunResponse2(result);
        }
    }
    
    /**
     * 描述:处理已出帐单返回结果 </br>
     * 开发人员：weiyb</br>
     * 创建时间：2015-7-7</br>
     * @param result
     */
    private void riqizhangdanchaxunResponse(Object result)
    {
        XiaoJinYingMingXiresult response = (XiaoJinYingMingXiresult)result;
        
        if (response == null)
        {
            loginErrorMessage = "服务器请求异常!";
            DialogUtil.createDialog(this, 1, loginErrorMessage);
            // mViewPager.setVisibility(View.GONE);
            zhangdanmingxiwenzi.setVisibility(View.VISIBLE);
            zhangdanmingxiwenzi.setText("暂无账单信息");
            listView.setVisibility(View.GONE);
            return;
        }
        else
        {
            if (RET_CODE == response.getCode())
            {
                
                mingxilist = response.getResult();
                
                // 获取未出帐单数据
                new riqichaxunzhangdanAsyncTask2().execute(cardNo);
                
                zhangdanmingxiwenzi.setVisibility(View.VISIBLE);
                listView.setVisibility(View.VISIBLE);
            }
            else
            {
                
                // 获取未出帐单数据
                new riqichaxunzhangdanAsyncTask2().execute(cardNo);
                zhangdanmingxiwenzi.setVisibility(View.VISIBLE);
                zhangdanmingxiwenzi.setText("暂无账单信息");
                listView.setVisibility(View.GONE);
            }
        }
    }
    
    /**
     * 描述:处理未出帐单返回结果 </br>
     * 开发人员：weiyb</br>
     * 创建时间：2015-7-7</br>
     * @param result
     */
    private void riqizhangdanchaxunResponse2(Object result)
    {
        
        XiaoJinYingMingXiresult_Weichu_Response response = (XiaoJinYingMingXiresult_Weichu_Response)result;
        
        if (response == null)
        {
            loginErrorMessage = "示出帐单服务器请求异常!";
            DialogUtil.createDialog(this, 1, loginErrorMessage);
            // mViewPager.setVisibility(View.GONE);
            zhangdanmingxiwenzi.setVisibility(View.VISIBLE);
            zhangdanmingxiwenzi.setText("暂无未出账单信息");
            listView.setVisibility(View.GONE);
            return;
        }
        else
        {
            if (RET_CODE == response.getCode())
            {
                
                mMyresult = response.getResult();
                
                mWeichList = mMyresult.getDetails();
                
                // initScrollView(mingxilist);
                
                initScrollView(mingxilist, mMyresult);
                
                // 未出帐单适配器
                weichuAdapter =
                    new MiniConsumeProvideBillWeiChuAdapter(mWeichList, MiniConsumeProvideBillActivity1.this);
                
                listView.setAdapter(weichuAdapter);
                
                zhangdanmingxiwenzi.setVisibility(View.VISIBLE);
                listView.setVisibility(View.VISIBLE);
            }
            else
            {
                
                Toast.makeText(MiniConsumeProvideBillActivity1.this, "提交失败" + response.getMsg(), Toast.LENGTH_LONG).show();
                // mViewPager.setVisibility(View.GONE);
                zhangdanmingxiwenzi.setVisibility(View.VISIBLE);
                zhangdanmingxiwenzi.setText("暂无账单信息");
                listView.setVisibility(View.GONE);
            }
        }
    }
    
    /**
     * 描述:处理装上部横向列表 </br>
     * 开发人员：weiyb</br>
     * 创建时间：2015-7-7</br>
     * @param mingxilist2
     */
    @SuppressLint("InflateParams")
    @SuppressWarnings("unused")
    private void initScrollView(List<XiaoJinYingMingXiresult2> mingxilist2, Myresult mMyresult)
    {
        
        if (mingxilist2 != null)
        {
            for (int i = 0; i < mingxilist2.size(); i++)
            {
                
                View mView = LayoutInflater.from(this).inflate(R.layout.xiaojinyin_zhangdan_item, null);
                TextView mzhangdanriqi = (TextView)mView.findViewById(R.id.zhangdanriqi);
                TextView zhangdanjinenum = (TextView)mView.findViewById(R.id.zhangdanjinenum);
                
                // 还款日数字
                TextView huakuanri = (TextView)mView.findViewById(R.id.huakuanri);
                // 还款日LiearLayout
                LinearLayout huakuanri_ll = (LinearLayout)mView.findViewById(R.id.huakuanri_ll);
                huakuanri_ll.setVisibility(View.VISIBLE);
                
                TextView zhuidijine = (TextView)mView.findViewById(R.id.zhuidijine);
                
                TextView zhangdanbishu = (TextView)mView.findViewById(R.id.zhangdanbishu);
                TextView zhangdanriqi = (TextView)mView.findViewById(R.id.zhangdanriqi);
                
                // 帐单金额
                
                if (mingxilist2.get(i).getCurBillAmt() != null)
                {
                    zhangdanjinenum.setText(mingxilist2.get(i).getCurBillAmt());
                }
                else
                {
                    zhangdanjinenum.setText("--");
                }
                
                // 账单日期-从几月几日到几月几日
                String zhandanqi = "";
                
                if (mingxilist2.get(i).getPsBillDt() != null)
                {
                    String[] array = mingxilist2.get(i).getPsBillDt().split("-");
                    
                    if (array.length >= 3)
                    {
                        // 根据当前时间计算出起始时间
                        int c_month = Integer.parseInt(array[1]);
                        // 上月
                        int p_month = 0;
                        if (c_month == 1)
                        {
                            // 上月
                            p_month = 12;
                        }
                        else
                        {
                            p_month = c_month - 1;
                        }
                        
                        zhandanqi =
                            "(" + String.valueOf(p_month) + "." + String.valueOf(array[2]) + "-"
                                + String.valueOf(c_month) + "." + array[2] + ")";
                    }
                    
                }
                
                // 帐单日期
                zhangdanriqi.setText(mingxilist2.get(i).getPsBillDt() + "\t账单" + zhandanqi);
                
                if (mingxilist2.get(i).getCashNum() != null)
                {
                    // 交易笔数
                    zhangdanbishu.setText(mingxilist2.get(i).getCashNum());
                }
                
                // 还款日
                if (mingxilist2.get(i).getPsDueDt() != null)
                {
                    huakuanri.setText(mingxilist2.get(i).getPsDueDt());
                }
                
                /*
                 * if (i == 0)
                 * {
                 * mView.setPadding(100, 0, 0, 0);
                 * }
                 */
                // 最低还款额
                
                if (mingxilist2.get(i).getMinAmt() != null)
                {
                    zhuidijine.setText(mingxilist2.get(i).getMinAmt() + "");
                }
                
                // mMyScrollView.addView(mView);
                
                mPagersList.add(mView);
                
            }
        }
        
        if (mMyresult != null)
        {
            addWeichu(mMyresult);
        }
        else
        {
            loginErrorMessage = "暂无账单信息!";
            DialogUtil.createDialog(this, 1, loginErrorMessage);
        }
        
    }
    
    /**
     * 描述:添加未出帐单项 </br>
     * 开发人员：weiyb</br>
     * 创建时间：2015-7-8</br>
     * @param mMyresult
     */
    @SuppressWarnings("unused")
    @SuppressLint("InflateParams")
    private void addWeichu(Myresult mMyresult)
    {
        
        View mView = LayoutInflater.from(this).inflate(R.layout.xiaojinyin_zhangdan_item, null);
        TextView zhangdanjinenum = (TextView)mView.findViewById(R.id.zhangdanjinenum);
        TextView zhangdanjine = (TextView)mView.findViewById(R.id.zhangdanjine);
        TextView zhangdanbishu = (TextView)mView.findViewById(R.id.zhangdanbishu);
        TextView zhangdanriqi = (TextView)mView.findViewById(R.id.zhangdanriqi);
        
        TextView zhangdanjinerenminbi = (TextView)mView.findViewById(R.id.zhangdanjinerenminbi);
        // 还款日LiearLayout
        LinearLayout huakuanri_ll = (LinearLayout)mView.findViewById(R.id.huakuanri_ll);
        huakuanri_ll.setVisibility(View.GONE);
        
        if (mingxilist.size() > 0)
        {
            shangqizhangdanjin.setText("¥ " + mingxilist.get(mingxilist.size() - 1).getCurAddAmt());
        }
        
        // 帐单金额
        
        if (mMyresult.getTotalAmt() != null)
        {
            zhangdanjinerenminbi.setText("¥ ");
            zhangdanjinenum.setText(mMyresult.getTotalAmt());
        }
        else
        {
            zhangdanjinerenminbi.setText("--");
            zhangdanjinenum.setText("--");
        }
        
        // 帐单日期
        zhangdanriqi.setText("未出帐单");
        // 帐单金额
        // zhangdanjine.setText("");
        // 交易笔数
        
        zhangdanbishu.setText(mWeichList.size() + "");
        /*
         * if (mMyScrollView.getChildCount() < 1)
         * { // 如果已出帐单没有
         * mView.setPadding(100, 0, 0, 0);
         * }
         * else
         * {
         * // 如果有已出帐单
         * mView.setPadding(0, 0, 100, 0);
         * }
         * //mMyScrollView.addView(mView);
         */
        zhangdanmingxiwenzi.setText("未出帐单明细");
        
        mPagersList.add(mView);
        // 发送消息处理 Viewpager
        handler.sendEmptyMessage(MPAGERSLISTREADY);
        
    }
    
    // 账单详情接口
    class zhangdanxiangqingResponseAsyncTask extends AsyncTask<String, Object, Object>
    {
        
        @Override
        protected Object doInBackground(String... params)
        {
            
            HttpHelper httpHelper = HttpHelper.getInstance(MiniConsumeProvideBillActivity1.this);
            List<NameValuePair> arg = new ArrayList<NameValuePair>();
            arg.add(new BasicNameValuePair("cardNo", params[0]));
            arg.add(new BasicNameValuePair("billDt", params[1]));
            return httpHelper.post(ContantsAddress.MINI_BILL, arg, XiaoJinYingZhangDanXiangQing.class);
        }
        
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            showProgressDialog();
        }
        
        @Override
        protected void onPostExecute(Object result)
        {
            dismissProgressDialog();
            super.onPostExecute(result);
            zhangdanxiangqingResponse(result);
            
            // getListItems(result);
        }
    }
    
    /**
     * 描述: 已出帐单详情 </br>
     * 开发人员：weiyb</br>
     * 创建时间：2015-7-7</br>
     * @param result
     */
    private void zhangdanxiangqingResponse(Object result)
    {
        XiaoJinYingZhangDanXiangQing response = (XiaoJinYingZhangDanXiangQing)result;
        if (response == null)
        {
            loginErrorMessage = "服务器请求异常!";
            DialogUtil.createDialog(this, 1, loginErrorMessage);
            // mViewPager.setVisibility(View.GONE);
            zhangdanmingxiwenzi.setVisibility(View.VISIBLE);
            zhangdanmingxiwenzi.setText("暂无账单信息");
            listView.setVisibility(View.GONE);
            
            return;
        }
        else
        {
            if (RET_CODE.equals(response.getCode()))
            {
                
                zhangdanmingxiwenzi.setVisibility(View.VISIBLE);
                listView.setVisibility(View.VISIBLE);
                
                RealitionInfo99 = response.getResult();
                zhangdanmingxiwenzi.setText("账单明细");
                nadapter = new MiniConsumeProvideBillYiChuAdapter(RealitionInfo99, MiniConsumeProvideBillActivity1.this);
                listView.setAdapter(nadapter);
            }
            else
            {
                Toast.makeText(MiniConsumeProvideBillActivity1.this, "提示：" + response.getMsg(), Toast.LENGTH_SHORT).show();
                // mViewPager.setVisibility(View.GONE);
                dismissProgressDialog();
                zhangdanmingxiwenzi.setVisibility(View.VISIBLE);
                zhangdanmingxiwenzi.setText("暂无账单信息");
                listView.setVisibility(View.GONE);
            }
        }
    }
    
    /***************************************************/
    
    private void initViewPager()
    {
        // 容器
        viewPagerContainer = (RelativeLayout)findViewById(R.id.pager_layout);
        
        mViewPager = (ViewPager)findViewById(R.id.ii_viewpager);
        
        mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
        
        mViewPager.setAdapter(new MyPagerAdapter());
        
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.page_margin));
        
        if (mPagersList.size() > 0)
        {
            mViewPager.setCurrentItem(mPagersList.size() - 1);
        }
        
    }
    
    private class MyPagerAdapter extends PagerAdapter
    {
        
        @Override
        public int getCount()
        {
            return mPagersList.size();
        }
        
        @Override
        public boolean isViewFromObject(View view, Object object)
        {
            return view == object;
        }
        
        public Object instantiateItem(ViewGroup container, int position)
        {
            container.addView(mPagersList.get(position));
            return mPagersList.get(position);
            
        }
        
        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {
            
            container.removeView(mPagersList.get(position));
        }
        
    }
    
    public class MyOnPageChangeListener implements OnPageChangeListener
    {
        
        @Override
        public void onPageSelected(int position)
        {
            // 处理页面改变时的逻辑
            onPagerChenge(position);
            
        }
        
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
        {
            // to refresh frameLayout 刷新 布局
            if (viewPagerContainer != null)
            {
                viewPagerContainer.invalidate();
            }
            
        }
        
        @Override
        public void onPageScrollStateChanged(int arg0)
        {
            LogUtil.i("viewpager", arg0 + "viewpager");
            
        }
    }
    
    /**
     * 描述:页面改变时调用 </br>
     * 开发人员：Weiyb</br>
     * 创建时间：2015-7-19</br>
     * @param position
     */
    private void onPagerChenge(int position)
    {
        // 联网请求数据，填充下部列表
        
        if (position > -1)
        {
            
            if (mingxilist != null)
            { // 已出帐单不为空的情况下
            
                if (position == mingxilist.size())
                { // 未出帐单
                
                    // 重置选中项
                    currentPosition = -1;
                    zhangdanmingxiwenzi.setText("帐单明细");
                    listView.setAdapter(weichuAdapter);
                    listView.setVisibility(View.VISIBLE);
                    weichuAdapter.notifyDataSetChanged();
                    
                    if (mingxilist.size() > 0)
                    {
                        shangqizhangdanjin.setText("¥ " + mingxilist.get(position - 1).getCurAddAmt());
                    }
                    
                }
                else if (position < mingxilist.size())
                {// 已出帐单
                
                    // 重置选中项
                    currentPosition = -1;
                    String st_date = mingxilist.get(position).getPsBillDt().toString();
                    new zhangdanxiangqingResponseAsyncTask().execute(cardNo, st_date);
                    
                    if (position >= 1)
                    {
                        
                        if (mingxilist.get(position - 1).getLastBillAmt() != null)
                        {
                            shangqizhangdanjin.setText("¥ " + mingxilist.get(position - 1).getLastBillAmt());
                        }
                        else
                        {
                            shangqizhangdanjin.setText("--");
                        }
                        
                    }
                    else
                    {
                        shangqizhangdanjin.setText("¥ " + "0");
                    }
                    
                }
                else if (position > mingxilist.size())
                {
                    
                    // 重置选中项
                    currentPosition = -1;
                    zhangdanmingxiwenzi.setText("帐单明细");
                    listView.setAdapter(weichuAdapter);
                    weichuAdapter.notifyDataSetChanged();
                    
                    if (position >= 1)
                    {
                        // shangqizhangdanjin.setText("¥ " + mingxilist.get(currid - 1).getLastBillAmt() + "");
                        
                        shangqizhangdanjin.setText("¥ " + "0");
                    }
                }
                
            }
            
        }
    }
    
    /*************************************************/
}
