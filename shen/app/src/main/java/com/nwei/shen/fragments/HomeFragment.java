package com.nwei.shen.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.ABaseTransformer;
import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.ToxicBakery.viewpager.transforms.BackgroundToForegroundTransformer;
import com.ToxicBakery.viewpager.transforms.CubeInTransformer;
import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.ToxicBakery.viewpager.transforms.DepthPageTransformer;
import com.ToxicBakery.viewpager.transforms.FlipHorizontalTransformer;
import com.ToxicBakery.viewpager.transforms.FlipVerticalTransformer;
import com.ToxicBakery.viewpager.transforms.ForegroundToBackgroundTransformer;
import com.ToxicBakery.viewpager.transforms.RotateDownTransformer;
import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.ToxicBakery.viewpager.transforms.StackTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomInTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomOutTranformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.jn.chart.charts.LineChart;
import com.jn.chart.data.Entry;
import com.jn.chart.manager.LineChartManager;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nwei.R;
import com.nwei.holder.NetworkImageHolderView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by admin on 2018/2/6.
 */

public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener, ViewPager.OnPageChangeListener, OnItemClickListener {

    private LineChart mLineChart;

    Context mContext;
    Context mApplicationContext;
    View view;
    private ConvenientBanner convenientBanner; // 顶部广告控件
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    private List<String> networkImages;

    private String[] images = {
            "http://shenbaocaifu.oss-cn-shenzhen.aliyuncs.com/image/shenbaocaifu-img/temp1.jpg",
            "http://shenbaocaifu.oss-cn-shenzhen.aliyuncs.com/image/shenbaocaifu-img/temp2.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg",
            "http://img3.imgtn.bdimg.com/it/u=1656941036,2072297851&fm=27&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=3446672618,225431101&fm=27&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=1983071532,955198593&fm=27&gp=0.jpg"
    };

    private ListView listView;
    private ArrayAdapter transformerArrayAdapter;
    private ArrayList<String> transformerList = new ArrayList<String>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       this.mContext = getActivity();
       view = inflater.inflate(R.layout.fragment_home,container,false);
        mLineChart = view.findViewById(R.id.lineChart);

       //设置图标描述
        mLineChart.setDescription("全省移网");
        //设置 x 轴的数据
        ArrayList<String> xValues = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            xValues.add("" +i);
        }

        //设置 Y 轴数据
        ArrayList<Entry> yValue = new ArrayList<>();
        yValue.add(new Entry(13,1));
        yValue.add(new Entry(6,2));
        yValue.add(new Entry(3,3));
        yValue.add(new Entry(7,4));
        yValue.add(new Entry(2,5));
        yValue.add(new Entry(5,6));
        yValue.add(new Entry(12,7));

        //设置折现的名称
        LineChartManager.setLineName("当月值");

        //创建一条折线的图表
//        LineChartManager.initSingleLineChart(mContext,mLineChart,xValues,yValue);

        //设置第二条折现y轴数据
        ArrayList<Entry> yValue1 = new ArrayList<>();
        yValue1.add(new Entry(17, 1));
        yValue1.add(new Entry(3, 2));
        yValue1.add(new Entry(5, 3));
        yValue1.add(new Entry(4, 4));
        yValue1.add(new Entry(3, 5));
        yValue1.add(new Entry(7, 6));
        yValue1.add(new Entry(10, 7));

        LineChartManager.setLineName1("上月值");

        //创建两条折线图的图标
        LineChartManager.initDoubleLineChart(mContext,mLineChart,xValues,yValue,yValue1);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();
        init();
//        TextView companyIntro = getView().findViewById(R.id.intro);
//        String companyIntroStr = "参宝财富是一家专业化投资平台。公司业务涵盖实业投资、项目投资、投资管理等，与银行、信托、基金、第三方财富公司等机构有长期合作关系，在金融、制造、房地产、酒店、商贸等领域获取了丰厚的投资回报。依托强大的股东资源，参宝财富从项目源头甄选实力强大、经营稳健、信誉良好的融资方，凭借专业化的运营团队，严谨健全的风控体系，为其提供便捷、高效、低成本的融资服务，为投资者提供安全、可靠、透明的投资渠道，实现投融资信息的自由匹配和多方共赢。";
//        companyIntro.setText("\u3000\u3000" + companyIntroStr);


    }





    /**
     * 初始化轮播图视图
     */
    private void initViews() {
        convenientBanner = getView().findViewById(R.id.convenientBanner);

        try {
            Class cls = Class.forName("com.ToxicBakery.viewpager.transforms." + BackgroundToForegroundTransformer.class.getSimpleName());
            ABaseTransformer transforemer= (ABaseTransformer)cls.newInstance();
            convenientBanner.getViewPager().setPageTransformer(true,transforemer);

            //部分3D特效需要调整滑动速度
//            if(transforemerName.equals("StackTransformer")){
//                convenientBanner.setScrollDuration(1200);
//            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        initImageLoader();
//        loadTestDatas();

        //轮播图加载网络图片
        sliderLoadNetworkImages();

    }


    /**
     * 轮播图加载网络图片
     */
    private void sliderLoadNetworkImages(){
        networkImages= Arrays.asList(images);
        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        },networkImages);
    }


    /**
     * 加入测试Views
     */
    private void loadTestDatas() {
        //本地图片集合
        for (int position = 0; position < 7; position++)
            localImages.add(getResId("ic_test_" + position, R.drawable.class));

//        //各种翻页效果
        transformerList.add(DefaultTransformer.class.getSimpleName());
        transformerList.add(AccordionTransformer.class.getSimpleName());
        transformerList.add(BackgroundToForegroundTransformer.class.getSimpleName());
        transformerList.add(CubeInTransformer.class.getSimpleName());
        transformerList.add(CubeOutTransformer.class.getSimpleName());
        transformerList.add(DepthPageTransformer.class.getSimpleName());
        transformerList.add(FlipHorizontalTransformer.class.getSimpleName());
        transformerList.add(FlipVerticalTransformer.class.getSimpleName());
        transformerList.add(ForegroundToBackgroundTransformer.class.getSimpleName());
        transformerList.add(RotateDownTransformer.class.getSimpleName());
        transformerList.add(RotateUpTransformer.class.getSimpleName());
        transformerList.add(StackTransformer.class.getSimpleName());
        transformerList.add(ZoomInTransformer.class.getSimpleName());
        transformerList.add(ZoomOutTranformer.class.getSimpleName());

        transformerArrayAdapter.notifyDataSetChanged();
    }


    /**
     * 通过文件名获取资源id 例子：getResId("icon", R.drawable.class);
     *
     * @param variableName
     * @param c
     * @return
     */
    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


    /**
     * 初始化网络图片缓存库
     */
    private void initImageLoader(){
        //网络图片例子,结合常用的图片缓存库UIL,你可以根据需求自己换其他网络图片库
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
                showImageForEmptyUri(R.drawable.ic_default_adimage)
                .cacheInMemory(true).cacheOnDisk(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getContext()).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();

        ImageLoader.getInstance().init(config);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mApplicationContext = getActivity().getApplicationContext();
    }

    @Override
    public void onResume() {
        super.onResume();
        //开始自动翻页
        convenientBanner.startTurning(3000);
    }

    @Override
    public void onPause() {
        super.onPause();
        //停止翻页
        convenientBanner.stopTurning();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String transforemerName = transformerList.get(position);
        try {
            Class cls = Class.forName("com.ToxicBakery.viewpager.transforms." + transforemerName);
            ABaseTransformer transforemer= (ABaseTransformer)cls.newInstance();
            convenientBanner.getViewPager().setPageTransformer(true,transforemer);

            //部分3D特效需要调整滑动速度
            if(transforemerName.equals("StackTransformer")){
                convenientBanner.setScrollDuration(1200);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Toast.makeText(mContext,"监听到翻到第"+position+"了",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(mContext,"点击了第"+position+"个",Toast.LENGTH_SHORT).show();
    }
}
