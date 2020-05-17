package com.wtxy.familyeducation.iview;


/**
 * @Author: yiwenhui
 * @Date: 2020/2/29
 * @Describe:
 */
public interface IPublishView extends IView{
    String getPubTitle();
    String getOtherTitle();
    String getLink();
    void onPublishSuccess();
}
