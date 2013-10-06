package com.dao;

import java.util.List;

import com.bean.Account;
import com.bean.AccountFilterBean;

public class AccountDao {
	public List<Account> getAccounts(final Integer recordOffSet,
			final Integer numberPrePage, final String orderBy,
			final AccountFilterBean filterBean) {
		return null;
	}

	public int getAccountsCount() {
		return 0;
	}

	public String getWhereClause(AccountFilterBean filterBean) {
		String whereStr = "where (1 = 1) ";
		if (filterBean == null) {
			return whereStr;
		}
		return whereStr;
	}
}
