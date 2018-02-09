package com.nwei.test1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;

public class MainActivity extends AppCompatActivity {

    private RadioGroup mRadioGroup;
    private LineChart mLineChart;
    private TextView detailMinTimeTv, detailMaxTimeTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void addViewForGroup(final List<JsonData.HistoricalPrice> list) {
        for (int i = 0; i < list.size(); i++) {
            final RadioButton view = (RadioButton) LayoutInflater.from(MainActivity.this)
                    .inflate(R.layout.item_gr_add_but_layout, mRadioGroup, false);
            view.setId(i);
            view.setText(list.get(i).getTitle());
            if (i==0){
                view.performClick();
                radioGroupTextChange(list.get(0).getData(), list.get(0).getTitle());
                mLineCharWidget = new LineChartWidget(MainActivity.this,
                        list.get(0).getData(), mLineChart, setMinPrice(list.get(0).getData()));
            }
            mRadioGroup.addView(view);

        }
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button = (RadioButton) findViewById(checkedId);
                button.setText(list.get(checkedId).getTitle());

                for (int i = 0; i < list.size(); i++) {
                    if (button.getText().toString().equals(list.get(i).getTitle())) {
                        radioGroupTextChange(list.get(i).getData(), list.get(i).getTitle());
                        if (mLineCharWidget == null) {
                            mLineCharWidget = new LineChartWidget(MainActivity.this,
                                    list.get(i).getData(), mLineChart, setMinPrice(list.get(i).getData()));
                        } else {
                            mLineCharWidget.updateLineChar(list.get(i).getData(), setMinPrice(list.get(i).getData()));
                        }

                    }
                }
            }
        });
    }


    private void initLineChar() {
        List<JsonData.HistoricalPrice.HistoricalPriceData.DataList> datalist
                = removeDuplicteData(mHistoricalPrice.getData_list());
        //设置手势滑动事件
        mLineChar.setOnChartGestureListener(this);
        //设置数值选择监听
        mLineChar.setOnChartValueSelectedListener(this);
        //后台绘制
        mLineChar.setDrawGridBackground(false);
        //设置描述文本
        mLineChar.getDescription().setEnabled(false);
        mLineChar.setTouchEnabled(true); // 设置是否可以触摸
        mLineChar.setDragEnabled(true);// 是否可以拖拽
        mLineChar.setScaleXEnabled(true); //是否可以缩放 仅x轴
        mLineChar.setScaleYEnabled(true); //是否可以缩放 仅y轴
        mLineChar.setPinchZoom(true);  //设置x轴和y轴能否同时缩放。默认是否

        mLineChar.setDragDecelerationFrictionCoef(0.99f);
        mLineChar.getAxisRight().setEnabled(false);
        // 默认动画
        mLineChar.animateX(2500);

        setMakeList(removeDuplicteData(datalist));
        initMark(makeList, Long.valueOf(mHistoricalPrice.getStart_time()));
        initXAxis(datalist.size(), xAxisValuesStr);
        initYAxis();
        initLegend();
        setLineCharData(makeList);
    }

}
