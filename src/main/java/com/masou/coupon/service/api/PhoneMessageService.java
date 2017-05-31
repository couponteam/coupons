package com.masou.coupon.service.api;


import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.dao.PhoneMessageDao;
import com.masou.coupon.data.models.PhoneMessage;
import com.masou.coupon.exception.UserException;
import com.masou.coupon.service.sms.SMSResult;
import com.masou.coupon.service.sms.SmsClientService;
import com.masou.coupon.utils.PhoneUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Paul on 2017/5/4.
 */
@Service
public class PhoneMessageService {


    @Value("${be.phone_message_per_day}")
    private Integer phoneMessagePerDay;

    @Autowired
    private PhoneMessageDao phoneMessageDao;


    @Autowired
    private PhoneUtil phoneUtil;


    @Autowired
    private SmsClientService smsClientService;


    public int insertSelective(PhoneMessage record) {
        return phoneMessageDao.insertSelective(record);
    }

    public PhoneMessage selectByPrimaryKey(Integer id) {
        return phoneMessageDao.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(PhoneMessage record) {
        return phoneMessageDao.updateByPrimaryKeySelective(record);
    }


    /**
     * 发送短信
     *
     * @param phone
     * @param verify
     * @param message
     * @param type
     * @return
     */
    public Result sendMessage(String phone, String verify, String message, Integer type) {

        return sendMessage(phone, verify, message, type, 1);

    }


    @Transactional
    public Result sendMessage(String phone, String verify, String message, Integer type, Integer source) {
        if (!phoneUtil.isPhone(phone)) {
            throw new UserException(ErrorCodeEnum.SYS_ERROR, "手机号不正确");
        }
        if (!verify.equals("")) {
            int count = phoneMessageDao.selectTodayMessageCount(phone);
            if (count >= phoneMessagePerDay) {
                throw new UserException(ErrorCodeEnum.SYS_ERROR, "短信已达上限");
            }

            Calendar _time = Calendar.getInstance();
            _time.add(Calendar.MINUTE, -2);
            PhoneMessage lastMsg = phoneMessageDao.selectLastByType(phone, type);
            if (lastMsg != null && lastMsg.getCreateAt().after(_time.getTime())) {
                throw new UserException(ErrorCodeEnum.SYS_ERROR, "发送太频繁");
            }
        }

        PhoneMessage msg = new PhoneMessage();
        msg.setContent(message);
        msg.setVerify(verify);
        msg.setPhoneNumber(phone);
        msg.setSource(source);
        msg.setType(type);
        Calendar nowTime = Calendar.getInstance();
        msg.setCreateAt(nowTime.getTime());

        nowTime.add(Calendar.MINUTE, 30);
        msg.setExpireAt(nowTime.getTime());

        phoneMessageDao.insertSelective(msg);

        SMSResult vo = smsClientService.sendMessage(phone, message);

        if (vo!=null) {
            return ResultHelper.genResultWithSuccess();

        }else {
            throw new UserException("短信发送失败");
        }

    }


    public boolean checkVerify(String phoneNumber, String verify, Integer type) {
        PhoneMessage message = phoneMessageDao.selectLastByType(phoneNumber, type);
        if (message != null) {
            message.setUsed(1);
            phoneMessageDao.updateByPrimaryKeySelective(message);
        }
        return message != null && message.getExpireAt().after(new Date()) && message.getVerify().trim().equals(verify.trim());

    }


    public String generateVerify() {
        return String.valueOf(100000 + (int) (Math.random() * 899999));
    }


}
