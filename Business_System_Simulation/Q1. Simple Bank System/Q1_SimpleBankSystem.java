class Bank {

    long[] balance;            // array storing balance of each account
    long n;                    // number of accounts
    
    public Bank(long[] balance) {
        this.balance = balance;      // save the balance array
        this.n = balance.length;     // store total number of accounts
    }
    
    public boolean transfer(int account1, int account2, long money) {
        // if (isInvalidAccount(account1) || isInvalidAccount(account2) || balance[account1-1] < money) return false;
        // above code was direct checking and updating (commented out)

        // balance[account1-1] -= money;
        // balance[account2-1] += money;

        // return true;

        if (isInvalidAccount(account2)) return false;   // check if receiver account is invalid
        
        // withdraw from account1 and deposit into account2
        return withdraw(account1, money) && deposit(account2, money);
    }
    
    public boolean deposit(int account, long money) {
        if (isInvalidAccount(account)) return false;    // check if account is invalid

        balance[account-1] += money;                    // add money to account

        return true;                                    // deposit success
    }
    
    public boolean withdraw(int account, long money) {
        if (isInvalidAccount(account) || balance[account-1] < money) return false;
        // check if account is invalid or does not have enough balance

        balance[account-1] -= money;                    // subtract money from account

        return true;                                    // withdrawal success
    }

    private boolean isInvalidAccount(int account) {
        if (account < 1 || account > n) return true;    // account number must be between 1 and n
        else return false;                              // otherwise account is valid
    }
}

/**
 * Your Bank object will be instantiated and called as such:
 * Bank obj = new Bank(balance);              // create bank object
 * boolean param_1 = obj.transfer(account1,account2,money); // transfer money
 * boolean param_2 = obj.deposit(account,money);            // deposit money
 * boolean param_3 = obj.withdraw(account,money);           // withdraw money
 */
