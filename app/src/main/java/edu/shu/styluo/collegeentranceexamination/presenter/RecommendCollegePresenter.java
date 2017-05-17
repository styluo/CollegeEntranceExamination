package edu.shu.styluo.collegeentranceexamination.presenter;

import java.util.HashMap;
import java.util.Map;

import edu.shu.styluo.collegeentranceexamination.data.ParcelableEntity.QueryInfo;

/**
 * author: styluo
 * date: 2017/5/17 18:33
 * e-mail: shu_jiahuili@foxmail.com
 */

public class RecommendCollegePresenter implements RecommendCollegeContract.presenter {

    RecommendCollegeContract.view mView;
    Map<String, Integer> map = new HashMap<>();
    private final String[] PROVINCE_ARRAY = {"新疆", "黑龙江", "湖南", "浙江", "吉林", "北京", "四川", "安徽", "甘肃", "广西"
            , "贵州", "江苏", "上海", "江西", "云南", "河南", "广东", "重庆", "福建", "内蒙古", "辽宁", "山西", "湖北", "陕西", "河北"
            , "山东"};

    private final double[] FRACTIONAL_LINE_ARTS_2015 = {81, 84, 53, 41.7, 53, 49, 45, 46, -1, 54,
        60, 26.6, 16.3, 39, 65, 52, -1, 58, 58, 88, -1, 40, 43, 73, 59, -1};
    private final double[] FRACTIONAL_LINE_SCIENCE_2015 = {94, 110, 74, 50.92593, 55, 84, 53, 68, 64, 76,
    77, 39.0625, 28.75, 51, 73, 65, 41, 49, 88, 95, 76, 58, 72, 90, 73, 58};
    private final double LINE_ARTS_2015 = 500f; //文科分数线
    private final double LINE_SCIENCE_2015 = 500f; //理科分数线

    private final int SUCCESS = 1; //成功达到分数线
    private final int FAIL = 0; //未达到分数线
    private final int DEFECT = -1; //无相关数据

    private final int ARTS = 1; //文科
    private final int SCIENCE = 0; //理科

    private int isArts = 1; //当前选择文理，默认文科

    private final String[] ARTS_HOT_SUBJECT = {"会计学", "国际经济与贸易", "新闻学", "法学", "国际政治", "英语", "物流管理", "工商管理",
            "广告学", "中医学"};
    private final String[] SCIENCE_HOT_SUBJECT = {"数学与应用数学", "信息与计算科学", "应用物理学", "应用化学", "环境科学", "环境工程专业",
            "计算机科学与技术", "生物工程(生物科学)", "机械工程及自动化专业", "通信工程"};

    public RecommendCollegePresenter(RecommendCollegeContract.view view){
        mView = view;
        initProvinceId();
    }

    @Override
    public void start() {
    }

    @Override
    public void initData(QueryInfo queryInfo) {
        int position = map.get(queryInfo.getmProvince());
        double fractionalLine = 0f;

        if(queryInfo.getmSubject().equals("理科")){
            isArts = 0;
            fractionalLine = FRACTIONAL_LINE_SCIENCE_2015[position];
            judgeEntry(fractionalLine, Double.parseDouble(queryInfo.getmScore()), SCIENCE);
        } else{
            isArts = 1;
            fractionalLine = FRACTIONAL_LINE_ARTS_2015[position];
            judgeEntry(fractionalLine, Double.parseDouble(queryInfo.getmScore()), ARTS);
        }


    }

    /**
     * 直接加载相关数据，因为给的Excel格式问题，不直接导入数据库，直接代码处理相关数据
     */
    private void initProvinceId(){
        for(int i = 0; i < PROVINCE_ARRAY.length; i++){
            map.put(PROVINCE_ARRAY[i], i);
        }
    }

    /**
     * 根据文理分别判断是否达到相关录取条件
     */
    private void judgeEntry(double fractionalLine, double score, int subject){
        //无相关数据
        if(fractionalLine == -1){
            mView.initShow(DEFECT, null);
        }

        double line_2015 = 0f;

        if(subject == ARTS){
            line_2015 = LINE_ARTS_2015;
        } else {
            line_2015 = LINE_SCIENCE_2015;
        }

        if(score < (line_2015 + fractionalLine)){
            mView.initShow(FAIL, null);
        } else {
            if (isArts == ARTS) {
                mView.initShow(SUCCESS, ARTS_HOT_SUBJECT);
            } else {
                mView.initShow(SUCCESS, SCIENCE_HOT_SUBJECT);
            }
        }
    }
}
