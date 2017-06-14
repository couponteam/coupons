package com.masou.coupon.service.api;

import com.masou.coupon.action.api.vo.ShopResultVO;
import com.masou.coupon.action.api.vo.ShopapiVO;
import com.masou.coupon.action.api.vo.TicketVO;
import com.masou.coupon.action.param.PageParam;
import com.masou.coupon.action.shopapi.vo.ShopVO;
import com.masou.coupon.action.shopapi.vo.UserShopVO;
import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.common.enums.ShopOwnerTypeEnum;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.dao.Shop2Dao;
import com.masou.coupon.dao.api.ShopDao;
import com.masou.coupon.data.filter.LngAndLatParam;
import com.masou.coupon.data.filter.ShopFilter;
import com.masou.coupon.data.mappers.UserShopMapper;
import com.masou.coupon.data.models.*;
import com.masou.coupon.exception.UserException;
import com.masou.coupon.service.shopapi.TicketManagerService;
import com.masou.coupon.utils.ModelConvertUtil;
import com.masou.coupon.utils.PhoneUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Paul on 2017/5/20.
 */
@Service
public class ShopService {

    @Autowired
    private Shop2Dao shop2Dao;

    @Autowired
    private PhoneUtil phoneUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    private TicketManagerService ticketManagerService;

    @Autowired
    private UserShopMapper userShopMapper;

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private ShopChiefService shopChiefService;


    public int deleteByPrimaryKey(Long id) {
        return shop2Dao.deleteByPrimaryKey(id);
    }


    /**
     * 申请店铺
     *
     * @param record
     * @return
     */
    @Transactional
    public Result apply(Shop record) {
        if (!phoneUtil.isPhone(record.getPhone())) {
            throw new UserException("手机号不正确");
        }
//        if (shop2Dao.selectByPhone(record.getPhone()) != null) {
//            throw new UserException("手机号已经注册");
//        }

//        User user = userService.selectByPhone(record.getPhone());
//        if (user == null) {
//            throw new UserException("请先注册再进行申请");
//        }

        if (shop2Dao.insertSelective(record) == 1) {

//            ShopChief shopChief = new ShopChief();
//            shopChief.setShopId(record.getId());
//            shopChief.setUid(user.getId());
//            shopChief.setType(ShopOwnerTypeEnum.CHIEF_MANAGER.getRole());

//            if (shopChiefService.insertSelective(shopChief) == 1) {
                return ResultHelper.genResultWithSuccess();

//            } else {
//                throw new UserException("申请失败");
//            }
        } else {
            throw new UserException("申请失败");
        }

    }


    /**
     * 商家登陆
     *
     * @param token
     * @param phone
     * @param password
     * @return
     */
    public Result login(String token, String phone, String password) {
        User user = userService.selectByPhone(phone);
        if (user == null || !user.getPassword().equals(userService.encrypt(password))) {
            throw new UserException(ErrorCodeEnum.LOGIN_FAILED);
        }


        Shop shop = shop2Dao.selectByPhone(phone);

        userTokenService.updateByLogin(token, user.getId());
        UserShopVO vo = new UserShopVO();
        vo.setUid(user.getId());
        vo.setPhone(user.getPhone());
        vo.setUsername(user.getUsername());
        if (shop != null) {
            vo.setShopProfile(ModelConvertUtil.convert(ShopVO.class, shop));
            vo.setApplyShop(true);
        }

        return ResultHelper.genResultWithSuccess(vo);
    }

    /**
     * 商家退出
     *
     * @param token
     * @return
     */
    public Result logout(String token) {
        userTokenService.updateByLogout(token);
        return ResultHelper.genResultWithSuccess();
    }

    public Result selectShopList(PageParam pageParam, Integer verifyType) {

        ShopFilter filter = new ShopFilter();

        filter.setOffset(pageParam.getOffset());
        filter.setLimit(pageParam.getPageSize());
        filter.setVerified(verifyType);
        return ResultHelper.genResultWithSuccess(buildList(shop2Dao.selectListByFilter(filter)));
    }

    public Result approve(Long shopId, Integer shopVerifyType) {
        Shop shop = shop2Dao.selectByPrimaryKey(shopId);
        shop.setIsShopVerified(shopVerifyType.byteValue());
        if (shop2Dao.updateByPrimaryKeySelective(shop) == 1) {
            return ResultHelper.genResultWithSuccess();
        } else {
            throw new UserException("审核失败");
        }
    }

    public Shop selectByPrimaryKey(Long id) {
        return shop2Dao.selectByPrimaryKey(id);
    }

    public Result updateByPrimaryKeySelective(Shop record) {
        record.setIsShopVerified(null);
        record.setBusinessLicenseId(null);
        if (shop2Dao.updateByPrimaryKeySelective(record)==1){
            return ResultHelper.genResultWithSuccess();
        }else{
            throw new UserException("更新失败");
        }
    }

    public List<ShopVO> buildList(List<Shop> list) {
        List<ShopVO> voList = new ArrayList<>();

        for (Shop e : list) {
            voList.add(ModelConvertUtil.convert(ShopVO.class, e));
        }

        return voList;

    }

    /**
     * 用户关注店铺
     * @param uid
     * @param sid
     * @param status
     * @return
     */
    public int follow(Long uid, Long sid, Integer status){
        UserShop userShop = new UserShop();
        userShop.setId(uid);
        userShop.setShopId(sid);
        userShop.setStatus(new Byte(status + ""));

        //查询店铺是否存在
        Shop shop = shop2Dao.selectByPrimaryKey(sid);
        if(shop != null && shop.getShopName().length() > 0){
            return userShopMapper.insertSelective(userShop);
        }
        return 0;
    }

    /**
     * 根据uid获取用户关注的店铺列表
     * @param uid
     * @param page
     * @param pageSize
     * @return
     */
    public ShopResultVO list(Long uid,Integer page, Integer pageSize){

        ShopFilter shopFilter = new ShopFilter();
        shopFilter.setUid(uid);
        shopFilter.setOffset(page);
        shopFilter.setLimit(pageSize);

        ShopResultVO shopResultVO = new ShopResultVO();
        List<Shop> shopList = shopDao.findByUid(shopFilter);
        List<ShopapiVO> shopapiVOs = new ArrayList<>();
        if(shopList != null && shopList.size() > 0){
            for ( Shop shop : shopList ) {
                ShopapiVO shopapiVO = new ShopapiVO();
                shopapiVO.setShop(shop);

                //组装ticketvo
                List<TicketVO> ticketVOs = new ArrayList<>();
                List<TicketWithBLOBs> ticketWithBLOBses = shopDao.findBySid(shopFilter);
                for (TicketWithBLOBs ticket : ticketWithBLOBses) {
                    TicketVO ticketVo = new TicketVO();
                    ticketManagerService.fileTicketVO(ticketVo, ticket);
                    ticketVOs.add(ticketVo);
                }
                shopapiVO.setTicketVO(ticketVOs);
                shopapiVOs.add(shopapiVO);
            }
            shopResultVO.setShopVOList(shopapiVOs);
            shopResultVO.setTotal(shopapiVOs.size());
            return shopResultVO;
        }
        return null;
    }
}
