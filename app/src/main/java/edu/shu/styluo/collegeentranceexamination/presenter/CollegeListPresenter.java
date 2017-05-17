package edu.shu.styluo.collegeentranceexamination.presenter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import edu.shu.styluo.collegeentranceexamination.data.local.entity.CollegeInfo;
import edu.shu.styluo.collegeentranceexamination.view.activity.CollegeListActivity;

/**
 * 数据库给我先给了一个Excel，我导入了Sql，现在给个txt，我决定直接文件读写，不再导入数据库进行读写操作,之后可能还会给其他文件格式的数据需要特殊处理
 * author: styluo
 * date: 2017/5/17 14:46
 * e-mail: shu_jiahuili@foxmail.com
 */

public class CollegeListPresenter implements CollegeListContract.presenter{

    CollegeListContract.view mView;
    List<CollegeInfo> collegeInfoList;

    public CollegeListPresenter(CollegeListContract.view view){
        mView = view;
    }

    @Override
    public void start() {
    }

    @Override
    public void initData() {
        try {
            InputStreamReader inputReader = new InputStreamReader( ((CollegeListActivity)mView).getAssets().open("tb_univ.txt") );
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line="";
            collegeInfoList = new ArrayList<CollegeInfo>();
            while((line = bufReader.readLine()) != null){
                String[] temp = line.split(" ");
                CollegeInfo collegeInfo = new CollegeInfo(Integer.parseInt(temp[0]), temp[1]);
                collegeInfoList.add(collegeInfo);
            }
            mView.initAdapter(collegeInfoList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refreshData() {
        collegeInfoList.clear();
        try {
            InputStreamReader inputReader = new InputStreamReader( ((CollegeListActivity)mView).getAssets().open("tb_univ.txt") );
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line="";
            collegeInfoList = new ArrayList<CollegeInfo>();
            while((line = bufReader.readLine()) != null){
                String[] temp = line.split(" ");
                CollegeInfo collegeInfo = new CollegeInfo(Integer.parseInt(temp[0]), temp[1]);
                collegeInfoList.add(collegeInfo);
            }
            mView.refershData(collegeInfoList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCollegeId(int position) {
        return collegeInfoList.get(position).getId();
    }
}
