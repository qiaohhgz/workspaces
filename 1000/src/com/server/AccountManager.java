package com.server;

import static com.server.DB.accounts;
import static com.server.DB.id;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.bean.Account;
import com.bean.AccountFilterBean;
import com.bean.AccountTabBean;
import com.bean.Page;
import com.bean.State;
import com.dao.AccountDao;

public class AccountManager {
	private AccountDao accountDao = new AccountDao();

	public List<Account> getAll() throws Exception {
		return accounts;
	}

	public AccountTabBean displayAccountList(State state) {
		// get page info
		Integer recordOffSet = 0;
		Integer pageSize = 25;
		Page page = state.getPage();
		if (page != null && page.getRecordOffset() != 0) {
			recordOffSet = page.getRecordOffset();
		}
		if (page != null && page.getRowsPerPage() != 0) {
			pageSize = page.getRowsPerPage();
		}

		// get filter info
		HashMap<String, String> filters = state.getFilter();
		Integer id = 0;
		String name = null;
		String pwd = null;
		Date fromStartDate = null;
		Date toStartDate = null;
		if (filters != null) {

		}
		AccountTabBean accountTabBean = null;

		Account ac = Account.create(state.getFilter());

		return accountTabBean;
	}

	private AccountFilterBean composeAccountFilterBean(Integer id, String name,
			String pwd, Date formStartDate, Date toStartDate) {
		AccountFilterBean afBean = new AccountFilterBean();

		return afBean;
	}

	public void addAccount(Account ac) throws Exception {
		ac.setId(id++);
		ac.setCreateOn(new Date());
		DB.accounts.add(ac);
	}

	public void deleteById(Integer id) throws Exception {
		accounts.remove(getAccountById(id));
	}

	public void deleteByArray(Integer[] ids) throws Exception {
		for (int i = 0; i < ids.length; i++) {
			deleteById(ids[i]);
		}
	}

	public void updateAccount(Account ac) throws Exception {
		Account account = getAccountById(ac.getId());
		if (account != null) {
			ac.setCreateOn(account.getCreateOn());
			accounts.remove(account);
			accounts.add(ac);
		}
	}

	public Account getAccountById(Integer id) throws Exception {
		Account account = null;
		for (Account ac : accounts) {
			if (ac.getId().equals(id)) {
				return ac;
			}
		}
		return account;
	}

	public void addTestData() throws Exception {
		DB.addTestData();
	}

	public void testThrwosException() throws Exception {
		throw new Exception("This is a Exception");
	}
}
