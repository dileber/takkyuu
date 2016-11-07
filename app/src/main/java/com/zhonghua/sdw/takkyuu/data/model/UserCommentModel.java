package com.zhonghua.sdw.takkyuu.data.model;

import com.drcosu.ndileber.mvp.data.model.SModel;

/**
 * Created by shidawei on 2016/10/9.
 */

public class UserCommentModel extends SModel {
    private Integer _id;
    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }
    private Integer commentid;

    private String commentcontext;

    private Integer commentfromuserid;

    private Integer commenttouserid;

    private Integer commentstate;

    public Integer getCommentid() {
        return commentid;
    }

    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
    }

    public String getCommentcontext() {
        return commentcontext;
    }

    public void setCommentcontext(String commentcontext) {
        this.commentcontext = commentcontext == null ? null : commentcontext.trim();
    }

    public Integer getCommentfromuserid() {
        return commentfromuserid;
    }

    public void setCommentfromuserid(Integer commentfromuserid) {
        this.commentfromuserid = commentfromuserid;
    }

    public Integer getCommenttouserid() {
        return commenttouserid;
    }

    public void setCommenttouserid(Integer commenttouserid) {
        this.commenttouserid = commenttouserid;
    }

    public Integer getCommentstate() {
        return commentstate;
    }

    public void setCommentstate(Integer commentstate) {
        this.commentstate = commentstate;
    }
}