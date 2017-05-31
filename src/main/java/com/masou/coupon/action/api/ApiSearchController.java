package com.masou.coupon.action.api;

import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.data.models.HotWord;
import com.masou.coupon.data.models.Shop;
import com.masou.coupon.exception.UserException;
import com.masou.coupon.service.api.BannerService;
import com.masou.coupon.service.api.SearchService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jason on 2017/5/16.
 */
@RestController
@RequestMapping("/api/search")
public class ApiSearchController {

    @Autowired
    private SearchService searchService;

    private Logger logger = LoggerFactory.getLogger(ApiSearchController.class);

    /**
     * 请求关键词查询
     *
     * @param keyword
     * @return
     */
    @ApiOperation("关键词查询")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Result search(@RequestParam("keyword") String keyword){

        List<Shop> shopList = searchService.search(keyword);

        if(shopList == null || shopList.size() < 1){
            return ResultHelper.genResult(ErrorCodeEnum.SEARCH_EMPTY_RESULT);
        }else{

        }

        return ResultHelper.genResult(ErrorCodeEnum.SYS_ERROR);
    }

    @ApiOperation("热词接口")
    @RequestMapping(value = "/hotword", method = RequestMethod.GET)
    public Result hotword(@RequestParam("limit") Integer limit){
        try {
            List<HotWord> hotword = searchService.hotword(limit);
            if(hotword != null && hotword.size() > 0){
                return ResultHelper.genResultWithSuccess(hotword);
            }
        }catch (UserException e){
            logger.error("查询热词失败："+e.getLocalizedMessage());
            return ResultHelper.genResult(ErrorCodeEnum.SEARCH_HOTWORD_FAILED);
        }
        return ResultHelper.genResult(ErrorCodeEnum.SEARCH_HOTWORD_FAILED);
    }
}
