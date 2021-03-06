package com.masou.coupon.service.shopapi;

import com.masou.coupon.action.erpapi.vo.ProvinceListVO;
import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.dao.ShopApiDao.ErpDicDao;
import com.masou.coupon.data.filter.AddressFilter;
import com.masou.coupon.data.models.Industry;
import com.masou.coupon.data.models.Province;
import com.masou.coupon.data.models.TicketType;
import com.masou.coupon.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2017/5/21.
 */
@Service
public class ErpDicService {

    @Autowired
    private ErpDicDao erpDicDao;

    /**
     * 查询券类型
     * @return
     */
    public List<TicketType> ticketType(){
        return erpDicDao.ticketType();
    }

    /**
     * 查询行业类型
     * @return
     */
    public List<Industry> industryType(){
        return erpDicDao.industryType();
    }

    /**
     * 查询行业类型
     * @return
     */
    public Result update(Integer id, String comment, Integer status, String icon){
        Industry industry = new Industry();
        industry.setId(id);
        industry.setStatus(status);
        industry.setComment(comment);
        industry.setIcon(icon);
        if (id != null && id > 0){
            System.out.println("update dic");
            if (erpDicDao.industryTypeUpdate(industry) > 0){
                return ResultHelper.genResultWithSuccess(erpDicDao.selectByPrimaryKey(industry.getId()));
            }
        }else{
            System.out.println("insert dic");
            if(erpDicDao.industryTypeInsert(industry) > 0){
                return ResultHelper.genResultWithSuccess();
            }
        }
        return ResultHelper.genResult(ErrorCodeEnum.SECTION_FAILED);
    }

    public Result ticketTypeUpdate(Integer id, String comment, Integer status,String icon){
        TicketType ticketType = new TicketType();
        ticketType.setId(id);
        ticketType.setStatus(status);
        ticketType.setComment(comment);
        ticketType.setIcon(icon);
        if (id != null && id > 0){
            if (erpDicDao.ticketTypeUpdate(ticketType) > 0){
                return ResultHelper.genResultWithSuccess(erpDicDao.selectByTicketType(id));
            }
        }else{
            if(erpDicDao.ticketTypeInsert(ticketType) > 0){
                return ResultHelper.genResultWithSuccess();
            }
        }
        return ResultHelper.genResult(ErrorCodeEnum.SECTION_FAILED);
    }

    /**
     * 查询省份城市
     * @return
     */
    public List<ProvinceListVO> districtType(){
        List<ProvinceListVO> distList = new ArrayList<ProvinceListVO>();
        try {
            List<Province> rs = erpDicDao.provinceType();
            if (rs != null && rs.size() > 0){
                for (Province p: rs) {
                    ProvinceListVO provinceVO = new ProvinceListVO();
                    provinceVO.setProvince(p);
                    provinceVO.setCityList(erpDicDao.cityType(p.getId()));
                    distList.add(provinceVO);
                }
            }
            return distList;
        }catch (Exception e){
            throw new UserException(ErrorCodeEnum.DIC_FAIL, "查询省市信息失败");
        }
    }


    public Result provinceList(AddressFilter ads){
        return ResultHelper.genResultWithSuccess(erpDicDao.provinceList(ads));
    }

    public Result cityList(AddressFilter ads){
        return ResultHelper.genResultWithSuccess(erpDicDao.cityList(ads));
    }

    public Result discList(AddressFilter ads){
        return ResultHelper.genResultWithSuccess(erpDicDao.discList(ads));
    }

}
