package com.zhonghua.sdw.takkyuu.utils;

import com.drcosu.ndileber.tools.HSafe;
import com.zhonghua.sdw.takkyuu.data.enums.FloorEnum;
import com.zhonghua.sdw.takkyuu.data.enums.RubberEnum;
import com.zhonghua.sdw.takkyuu.data.model.BrandModel;
import com.zhonghua.sdw.takkyuu.data.model.FloorModel;
import com.zhonghua.sdw.takkyuu.data.model.RubberModel;
import com.zhonghua.sdw.takkyuu.data.source.SysRepository;

/**
 * Created by H2 on 2016/10/14.
 */
public class TakkyuuUtils {

    static SysRepository mDataSource = SysRepository.getInstance();

    public static String getFloor(Integer id) {
        if(id==null){
            return null;
        }
        FloorModel floorModel = mDataSource.getFlood(id);
        if(floorModel.getFloorbrand()!=null&&floorModel.getFloorname()!=null&&floorModel.getFloortype()!=null){
            BrandModel brandModel = mDataSource.getBrand(floorModel.getFloorbrand());
            if(brandModel.getBrandname()!=null){
                return brandModel.getBrandname()+" "+floorModel.getFloorname()+" "+ FloorEnum.getTheName(floorModel.getFloortype());
            }
        }
        return "无法获取您的底板信息，请您重新选择";
    }

    public static String getRubber(Integer id) {
        if(id==null){
            return null;
        }
        RubberModel rubberModel = mDataSource.getRubber(id);
        if(rubberModel.getRubberbrand()!=null&&rubberModel.getRubbername()!=null&&rubberModel.getRubbertype()!=null){
            BrandModel brandModel = mDataSource.getBrand(rubberModel.getRubberbrand());
            if(brandModel.getBrandname()!=null){
                return brandModel.getBrandname()+" "+rubberModel.getRubbername()+" "+ RubberEnum.getTheName(rubberModel.getRubbertype());
            }
        }
        return "无法获取您的胶皮信息，请您重新选择";
    }

}
