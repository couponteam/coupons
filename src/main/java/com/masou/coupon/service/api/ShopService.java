package com.masou.coupon.service.api;

import com.alibaba.fastjson.JSON;
import com.masou.coupon.action.api.vo.ShopResultVO;
import com.masou.coupon.action.api.vo.ShopapiVO;
import com.masou.coupon.action.api.vo.SverifyVO;
import com.masou.coupon.action.api.vo.ticketvo.TicketVO;
import com.masou.coupon.action.erpapi.vo.TicketPageParam;
import com.masou.coupon.common.constant.DicValue;
import com.masou.coupon.common.enums.StatusEnum;
import com.masou.coupon.data.filter.BaseFilter;
import com.masou.coupon.data.mappers.*;
import com.masou.coupon.data.param.PageParam;
import com.masou.coupon.action.shopapi.vo.ShopVO;
import com.masou.coupon.action.shopapi.vo.UserShopVO;
import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.dao.Shop2Dao;
import com.masou.coupon.dao.api.ShopDao;
import com.masou.coupon.data.filter.ShopFilter;
import com.masou.coupon.data.models.*;
import com.masou.coupon.exception.UserException;
import com.masou.coupon.service.shopapi.ShopManagerService;
import com.masou.coupon.service.shopapi.TicketManagerService;
import com.masou.coupon.service.sms.SMSResult;
import com.masou.coupon.service.sms.SmsClientService;
import com.masou.coupon.utils.ModelConvertUtil;
import com.masou.coupon.utils.PhoneUtil;
import com.qiniu.util.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private ShopService shopService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private TicketManagerService ticketManagerService;

    @Autowired
    private FollowTicketService followTicketService;

    @Autowired
    private UserShopMapper userShopMapper;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private SmsClientService smsClientService;
    @Autowired
    private TicketTypeMapper ticketTypeMapper;

    @Autowired
    private ShopManagerService shopManagerService;

    @Autowired
    private LogUserShopMapper logUserShopMapper;

    @Autowired
    private ShopDao shopDao;

    private Logger logger = LoggerFactory.getLogger(ShopService.class);


    public int deleteByPrimaryKey(Long id) {
        return shop2Dao.deleteByPrimaryKey(id);
    }


    public Result shopVerifyStatus(Long sid){

        if (sid != null && sid > 0){
            ShopFilter shopFilter = new ShopFilter();
            shopFilter.setSid(sid);

            Shop shop = shopDao.shopVerifyStatus(shopFilter);
            if(shop != null){
                SverifyVO sverifyVO = new SverifyVO();
                sverifyVO.setApplyShop(false);
                if (shop.getIsShopVerified() == StatusEnum.APPLY_SHOP_PASS.getStatus()){
                    sverifyVO.setApplyShop(true);
                }
                sverifyVO.setIndustryId(shop.getIndustryId());
                sverifyVO.setIsShopVerified(shop.getIsShopVerified());
                return ResultHelper.genResultWithSuccess(sverifyVO);
            }
        }
        return ResultHelper.genResult(ErrorCodeEnum.FAILED);
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

    public Integer ticketUnRead(BaseFilter baseFilter){
        return shopDao.ticketUnRead(baseFilter);
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

    public Result approve(Long shopId, Integer shopVerifyType, String comment) {
        Shop shop = shop2Dao.selectByPrimaryKey(shopId);
        shop.setIsShopVerified(shopVerifyType.byteValue());
        if(comment != null && comment.length() > 0){
            shop.setComment(comment);
        }
        if (shop2Dao.updateByPrimaryKeySelective(shop) == 1) {
            sendMsgOfVerify(shopId, shopVerifyType, comment);
            return ResultHelper.genResultWithSuccess();
        } else {
            throw new UserException("审核失败");
        }
    }

    private void sendMsgOfVerify(Long shopId, Integer shopVerifyType, String comment){
        String msg = "";
        switch(shopVerifyType){
            case (2):
                msg = "未通过审核，未通过原因 " +  comment;
                break;
            case (3):
                msg = "已通过审核";
                break;
        }
        Shop shop = shopDao.findByPrimaryId(shopId);

        StringBuffer content = new StringBuffer();

        content.append("您在 领汇券 APP上申请的店铺 " +  shop.getShopName() + " " +  msg + "。感谢您使用领汇圈！");

        SMSResult result = smsClientService.sendMessage(shop.getPhone(), content.toString());

        logger.info(JSON.toJSONString(result));

    }

    public Shop selectByPrimaryKey(Long id) {
        return shopManagerService.changeStatus2Char(shop2Dao.selectByPrimaryKey(id));
    }

    public Result updateByPrimaryKeySelective(Shop record) {
        record.setIsShopVerified(null);
        record.setBusinessLicenseId(null);
        if (shop2Dao.updateByPrimaryKeySelective(record)==1){
            System.out.println("SHOP MSG:" + JSON.toJSONString(record));
            Shop shop = shop2Dao.selectByPrimaryKey(record.getId());
            shopManagerService.changeStatus2Char(shop);
            return ResultHelper.genResultWithSuccess(shop);
        }else{
            throw new UserException("更新失败");
        }
    }

    public List<Shop> buildList(List<Shop> list) {
        List<Shop> voList = new ArrayList<>();

        for (Shop e : list) {
            Shop shop = shopManagerService.changeStatus2Char(e);
            voList.add(shop);
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
    public Result follow(Long uid, Long sid, Integer status){
        UserShop userShop = new UserShop();
        userShop.setUserId(uid);
        userShop.setShopId(sid);
        userShop.setStatus(new Byte(status + ""));

        //查询店铺是否存在
        Shop shop = shop2Dao.selectByPrimaryKey(sid);
        if(shop != null && shop.getShopName().length() > 0){
            UserShop rs = userShopMapper.selectByUidSid(userShop);
            if(status == 1){
                if(rs != null && rs.getId() > 0){
                    //如果数据库存在数据，并且状态不为1，说明之前关注过
                    if(rs.getStatus() != 1){
                       return updateFollow(userShop, rs.getId());
                    }
                    //数据库中存在数据
                    return ResultHelper.genResult(ErrorCodeEnum.FAILED.getCode(), "已关注过");
                }else
                    //插入到关注列表中,并且自动领取那张关注即领取的券
                    if(userShopMapper.insertSelective(userShop) > 0){
                        ShopFilter shopFilter = new ShopFilter();
                        shopFilter.setSid(sid);
                        //获取关注领取的券
                        TicketWithBLOBs ticket = ticketMapper.ticketByFollowShop(shopFilter);
                        if(ticket != null){
                            if(followTicketService.insertTicket(uid, ticket.getTicketId()) > 0){
                                return ResultHelper.genResultWithSuccess();
                            }
                        }
                        return ResultHelper.genResult(ErrorCodeEnum.FAILED);
                    }
                    return ResultHelper.genResultWithSuccess();
            }else if (status == 2){
                //如果为2，表示取消关注，则更新数据库
                if(rs != null && rs.getId() > 0){
                    return updateFollow(userShop, rs.getId());
                }
            }
        }
        return ResultHelper.genResult(ErrorCodeEnum.FAILED.getCode(), "关注失败");
    }

    //更新关注列表操作
    private Result updateFollow(UserShop userShop, Long id){
        userShop.setId(id);
        userShop.setShopId(null);
        userShop.setUserId(null);
        userShopMapper.updateByPrimaryKeySelective(userShop);
        return ResultHelper.genResultWithSuccess();
    }

    /**
     * 根据uid获取用户关注的店铺列表
     * @param uid
     * @param page
     * @param pageSize
     * @return
     */
    public ShopResultVO list(Long uid,Integer page, Integer pageSize,String keyword) {
        ShopFilter shopFilter = new ShopFilter();
        shopFilter.setUid(uid);
        shopFilter.setOffset(pageSize);
        shopFilter.setLimit(page);
        if(pageSize == null || pageSize <= 0){
            shopFilter.setOffset(PageParam.PAGESIZE_DEFAULT);
        }

        ShopResultVO shopResultVO = new ShopResultVO();
        List<Shop> shopList = null;
        //非关键词查询
        if (keyword == null || keyword.trim().length() <= 0) {
            //获取用户最近访问店铺的时间
            LogUserShop logUserShop = logUserShopMapper.selectByUid(uid);
            if (logUserShop != null) {
                shopFilter.setCreateTime(logUserShop.getCreateTime());
            }

            //查询数据库用户未读的店铺动态
            shopList = shopDao.findUnread(shopFilter);
            if (shopList == null || shopList.size() <= 0) {
                //如果查询到的未读数据为空，则继续查询关注店铺信息
                shopFilter.setCreateTime(null);
                shopList = shopDao.findUnread(shopFilter);
            } else if (shopList.size() < shopFilter.getOffset()) {
                shopFilter.setCreateTime(null);
                shopFilter.setOffset(shopFilter.getOffset() - shopList.size());
                List<Long> ids = new ArrayList<Long>();
                for (Shop shop : shopList) {
                    ids.add(shop.getId());
                }
                shopFilter.setIds(ids);
                List<Shop> shopAdd = shopDao.findUnread(shopFilter);
                if (shopAdd != null && shopAdd.size() > 0) {
                    shopList.addAll(shopAdd);
                }
            }
        } else {
            //关键词查询
            shopFilter.setKeyword(keyword);
            shopList = shopDao.findByUid(shopFilter);
        }

        List<ShopapiVO> shopapiVOs = new ArrayList<>();
        if (shopList != null && shopList.size() > 0) {
            for (Shop shop : shopList) {
                ShopapiVO shopapiVO = new ShopapiVO();
                shop.setUread(ticketService.unReadCount(uid));

                //获取当前店铺所拥有的券类型
                shopFilter.setSid(shop.getId());
                List<TicketWithBLOBs> uts = ticketMapper.selectShopTicketType(shopFilter);
                List<TicketType> tts = new ArrayList<TicketType>();
                if(uts != null && uts.size() > 0){
                    for (TicketWithBLOBs tb: uts) {
                        tb.getTicketType().setTicketName(tb.getTicketName());
                        tts.add(tb.getTicketType());
                    }
                    shopapiVO.setTicketTypes(tts);
                }
                shop.setUread(isUnRead(uid, shop.getId()));

                if(shop.getTicket() != null && shop.getTicket().getTicketId()!= null && shop.getTicket().getTicketId().length() > 1){
                    shop.getTicket().setTicketType(
                            ticketTypeMapper.selectByPrimaryKey(
                                    Integer.parseInt(shop.getTicket().getTypeId() + "")));

                    //组装ticketvo
                    List<TicketVO> ticketVOs = new ArrayList<>();
                    List<TicketWithBLOBs> ticketWithBLOBses = shopDao.findBySid(shopFilter);
                    for (TicketWithBLOBs ticket : ticketWithBLOBses) {
                        TicketVO ticketVo = new TicketVO();
                        ticketManagerService.fileTicketVO(ticketVo, ticket);
                        ticketVOs.add(ticketVo);
                    }
                    shopapiVO.setTicketVO(ticketVOs);
                }

                shopapiVO.setShop(shopManagerService.changeStatus2Char(shop));
                shopapiVOs.add(shopapiVO);
            }
            shopResultVO.setShopVOList(shopapiVOs);
            shopResultVO.setTotal(shopapiVOs.size());
            return shopResultVO;
        }
        return null;
    }


    /**
     * 查询数据库中是否有未读消息
     * @param uid
     * @return
     */
    private int  isUnRead(Long uid, Long sid){
        System.out.println("Enter unread method:" + uid + "      sid:" + sid);
        if(uid != null && uid > 0){
            ShopFilter shopFilter = new ShopFilter();
            shopFilter.setUid(uid);
            shopFilter.setSid(sid);

            LogUserShop logUserShop = logUserShopMapper.selectByUidSid(shopFilter);
            if (logUserShop != null) {
                BaseFilter baseFilter = new BaseFilter();
                baseFilter.setUid(uid);
                baseFilter.setToday(logUserShop.getCreateTime());
                return shopService.ticketUnRead(baseFilter);
            }else{
                //如果当前log中没有数据，说明没有用户访问shop记录，有新数据
                TicketPageParam ticketPageParam = new TicketPageParam();
                ticketPageParam.setShop_id(sid);

                return ticketMapper.selectCount(ticketPageParam);
            }
        }
        return 0;
    }

}
