package com.cn.demo;

 interface AccountDao {

    public void outMoney(String out, Double money);
    
    public void inMoney(String in, Double money);
}
