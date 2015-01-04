package com.huntering.beans.account.repository;

import com.huntering.beans.account.entity.Account;
import com.huntering.common.repository.BaseRepository;
import com.huntering.common.repository.support.annotation.SearchableQuery;

@SearchableQuery(callbackClass = AccountSearchCallback.class)
public interface AccountRepository extends BaseRepository<Account, Long> {

    Account findByEmail(String email);

}
