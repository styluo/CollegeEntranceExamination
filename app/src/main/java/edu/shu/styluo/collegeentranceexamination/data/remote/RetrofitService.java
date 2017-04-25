package edu.shu.styluo.collegeentranceexamination.data.remote;

import edu.shu.styluo.collegeentranceexamination.data.remote.entity.HotNews;
import edu.shu.styluo.collegeentranceexamination.data.remote.entity.NewsDetail;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Service,管理所有网络请求接口,后台接口不知道哪位同学写的，Json对象里面Json数组，Json数组里面Json对象，有意思?
 * 本地想优化就自定义Json解析了，时间关系，我也不写了，因为要定义出全局使用的Json解析，等有时间迭代版本了
 * author: styluo
 * date: 2017/4/25 11:49
 * e-mail: shu_jiahuili@foxmail.com
 */

public interface RetrofitService {

    //根据ID查询咨询
    @GET("/pro/news_queryNewsById")
    Observable<NewsDetail> getNewsDetailById(@Query("newsId") int newsId);

    //查询最新热门10条咨询
    @GET("/pro/news_queryNewsByHotAndDate")
    Observable<HotNews> getHotNews(@Query("hot") String hot);

}
