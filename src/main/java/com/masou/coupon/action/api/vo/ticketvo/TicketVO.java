package com.masou.coupon.action.api.vo.ticketvo;

import com.masou.coupon.data.models.TicketType;
import com.masou.coupon.data.models.TicketWithBLOBs;

/**
 * Created by jason on 2017/6/9.
 */
public class TicketVO {

    private TicketWithBLOBs ticket;

    private String _typeId;

    private String _status;

    private String _isRetaken;

    private String _isReUse;

    private String _colcMethod;

    //用户对券的使用情况
    private String utStatus;

    private String isTaken;

    private int taken;

    private int used;

    public TicketWithBLOBs getTicket() {
        return ticket;
    }

    public void setTicket(TicketWithBLOBs ticket) {
        this.ticket = ticket;
    }

    public String getUtStatus() {
        return utStatus;
    }

    public void setUtStatus(String utStatus) {
        this.utStatus = utStatus;
    }

    public String get_typeId() {
        return _typeId;
    }

    public void set_typeId(String _typeId) {
        this._typeId = _typeId;
    }

    public String get_status() {
        return _status;
    }

    public void set_status(String _status) {
        this._status = _status;
    }

    public String get_isRetaken() {
        return _isRetaken;
    }

    public void set_isRetaken(String _isRetaken) {
        this._isRetaken = _isRetaken;
    }

    public String get_isReUse() {
        return _isReUse;
    }

    public void set_isReUse(String _isReUse) {
        this._isReUse = _isReUse;
    }

    public String getIsTaken() {
        return isTaken;
    }

    public void setIsTaken(String isTaken) {
        this.isTaken = isTaken;
    }

    public int getTaken() {
        return taken;
    }

    public void setTaken(int taken) {
        this.taken = taken;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public String get_colcMethod() {
        return _colcMethod;
    }

    public void set_colcMethod(String _colcMethod) {
        this._colcMethod = _colcMethod;
    }
}
