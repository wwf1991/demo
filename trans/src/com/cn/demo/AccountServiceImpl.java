package com.cn.demo;

public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;
    
    
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }


    @Override
    public void transfer(String out, String in, Double money) {
        
        accountDao.outMoney(out, money);
        System.out.println("---------------------");
        accountDao.inMoney(in, money);
    }

}
