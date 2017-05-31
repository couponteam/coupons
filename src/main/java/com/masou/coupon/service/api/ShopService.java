package com.masou.coupon.service.api;

import com.masou.coupon.action.param.PageParam;
import com.masou.coupon.action.shopapi.vo.ShopVO;
import com.masou.coupon.action.shopapi.vo.UserShopVO;
import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.common.enums.ShopOwnerTypeEnum;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.dao.Shop2Dao;
import com.masou.coupon.data.filter.ShopFilter;
import com.masou.coupon.data.models.Shop;
import com.masou.coupon.data.models.ShopChief;
import com.masou.coupon.data.models.User;
import com.masou.coupon.exception.UserException;
import com.masou.coupon.utils.ModelConvertUtil;
import com.masou.coupon.utils.PhoneUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private ShopChiefService shopChiefService;


    public int deleteByPrimaryKey(Integer id) {
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
        if (shop2Dao.selectByPhone(record.getPhone()) != null) {
            throw new UserException("手机号已经注册");
        }

        User user = userService.selectByPhone(record.getPhone());
        if (user == null) {
            throw new UserException("请先注册再进行申请");
        }

        if (shop2Dao.insertSelective(record) == 1) {

            ShopChief shopChief = new ShopChief();
            shopChief.setShopId(record.getId());
            shopChief.setUid(user.getId());
            shopChief.setType(ShopOwnerTypeEnum.CHIEF_MANAGER.getRole());

            if (shopChiefService.insertSelective(shopChief) == 1) {
                return ResultHelper.genResultWithSuccess();

            } else {
                throw new UserException("申请失败");
            }
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

    public Result approve(Integer shopId, Integer shopVerifyType) {
        Shop shop = shop2Dao.selectByPrimaryKey(shopId);
        shop.setIsShopVerified(shopVerifyType.byteValue());
        if (shop2Dao.updateByPrimaryKeySelective(shop) == 1) {
            return ResultHelper.genResultWithSuccess();
        } else {
            throw new UserException("审核失败");
        }
    }

    public Shop selectByPrimaryKey(Integer id) {
        return shop2Dao.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Shop record) {
        return shop2Dao.updateByPrimaryKeySelective(record);
    }

    public List<ShopVO> buildList(List<Shop> list) {
        List<ShopVO> voList = new ArrayList<>();

        for (Shop e : list) {
            voList.add(ModelConvertUtil.convert(ShopVO.class, e));
        }

        return voList;

    }
}
