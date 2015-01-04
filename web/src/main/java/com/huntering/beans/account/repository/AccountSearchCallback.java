package com.huntering.beans.account.repository;

import javax.persistence.Query;

import com.huntering.common.entity.search.Searchable;
import com.huntering.common.repository.callback.DefaultSearchCallback;

/**
 * 
 * @author Vincent Yao
 *
 */
public class AccountSearchCallback extends DefaultSearchCallback {

    public AccountSearchCallback() {
        super("v");
    }

    @Override
    public void prepareQL(StringBuilder ql, Searchable search) {
        super.prepareQL(ql, search);
    }

    @Override
    public void setValues(Query query, Searchable search) {
        super.setValues(query, search);
    }

}
