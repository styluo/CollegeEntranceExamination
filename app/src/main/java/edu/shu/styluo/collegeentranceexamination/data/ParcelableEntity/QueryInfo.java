package edu.shu.styluo.collegeentranceexamination.data.ParcelableEntity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 序列化专业推荐中查询实体类
 * author: styluo
 * date: 2017/5/17 18:15
 * e-mail: shu_jiahuili@foxmail.com
 */

public class QueryInfo implements Parcelable {
    String mScore;
    String mProvince;
    String mSubject;

    public QueryInfo(String score, String province, String subject) {
        super();
        mScore = score;
        mProvince = province;
        mSubject = subject;
    }

    public QueryInfo(Parcel in){
        mScore = in.readString();
        mProvince = in.readString();
        mSubject = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mScore);
        dest.writeString(mProvince);
        dest.writeString(mSubject);
    }

    public static final Parcelable.Creator<QueryInfo> CREATOR = new Creator<QueryInfo>() {
        @Override
        public QueryInfo createFromParcel(Parcel source) {
            return new QueryInfo(source);
        }

        @Override
        public QueryInfo[] newArray(int size) {
            return new QueryInfo[0];
        }
    };

    public String getmScore() {
        return mScore;
    }

    public String getmProvince() {
        return mProvince;
    }

    public String getmSubject() {
        return mSubject;
    }

    public void setmScore(String mScore) {
        this.mScore = mScore;
    }

    public void setmProvince(String mProvince) {
        this.mProvince = mProvince;
    }

    public void setmSubject(String mSubject) {
        this.mSubject = mSubject;
    }
}
