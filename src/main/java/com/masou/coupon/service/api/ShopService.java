package com.masou.coupon.service.api;

import com.masou.coupon.action.param.PageParam;
import com.masou.coupon.action.shopapi.vo.ShopVO;
import com.masou.coupon.action.shopapi.vo.UserShopVO;
import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.common.enums.ShopOwnerTypeEnum;
import com.masou.coupon.common.enums.ShopVerifyEnum;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.dao.ShopDao;
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
    private ShopDao shopDao;

    @Autowired
    private PhoneUtil phoneUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private UserTokenService userTokenService;


    @Autowired
    private ShopChiefService shopChiefService;


    public int deleteByPrimaryKey(Integer id) {
        return shopDao.deleteByPrimaryKey(id);
    }

    /**
     * 申请店铺
     *
     * @param record
     * @param uid
     * @return
     */
    @Transactional
    public Result apply(Shop record, Long uid) {
        if (!phoneUtil.isPhone(record.getPhone())) {
            throw new UserException("手机号不正确");
        }
        if (shopDao.selectByPhone(record.getPhone()) != null) {
            throw new UserException("手机号已经注册");
        }


        if (shopDao.insertSelective(record) == 1) {

            ShopChief shopChief = new ShopChief();
            shopChief.setShopId(record.getId());
            shopChief.setUid(uid);
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


        Shop shop = shopDao.selectByPhone(phone);

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
        return ResultHelper.genResultWithSuccess(buildList(shopDao.selectListByFilter(filter)));
    }

    public Result approve(Integer shopId, Integer shopVerifyType) {
        Shop shop = shopDao.selectByPrimaryKey(shopId);
        shop.setIsShopVerified(shopVerifyType.byteValue());
        if (shopDao.updateByPrimaryKeySelective(shop) == 1) {
            return ResultHelper.genResultWithSuccess();
        } else {
            throw new UserException("审核失败");
        }
    }

    public Shop selectByPrimaryKey(Integer id) {
        return shopDao.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Shop record) {
        return shopDao.updateByPrimaryKeySelective(record);
    }

    public List<ShopVO> buildList(List<Shop> list) {
        List<ShopVO> voList = new ArrayList<>();

        for (Shop e : list) {
            voList.add(ModelConvertUtil.convert(ShopVO.class, e));
        }

        return voList;

    }
}
