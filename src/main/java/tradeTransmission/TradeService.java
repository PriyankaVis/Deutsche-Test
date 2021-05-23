package tradeTransmission;

import java.util.Date;
import java.util.HashMap;

/**
 * Service class to do operations on incoming trades after validating conditions
 * 
 * @author priyanka.vispute
 *
 */

public class TradeService {

    HashMap<String, Trade> allTradeMap = new HashMap<String, Trade>();

    // check if no trade Exists
    public boolean checkIfTradeEmpty() {
        return allTradeMap.isEmpty();
    }

    // Validate if the lower version is being received by the store it will reject the trade and throw an exception.
    // If the version is same it will override the existing record
    public void checkVersion(Trade t, int version) throws Exception {
        if (t.getVersion() < version) {
            throw new Exception(t.getVersion() + " is less than " + version);
        }
    }

    // Check if maturityDate
    public boolean isMaturedTrade(Date maturityDate, Date CurrentDate) {
        if (CurrentDate.compareTo(maturityDate) > 0) {
            return false;
        }
            
        return true;
    }

    // check whether trade is expired 
    public void checkExpiredTrade() {
        Date currentDate = new Date();

        for (String strKey : allTradeMap.keySet()) {
            if (currentDate.compareTo(allTradeMap.get(strKey).getMaturityDate()) > 0) {
                Trade t = allTradeMap.get(strKey);
                t.setExpired('Y');
                allTradeMap.replace(strKey, t);
            }
        }
    }

    // Insert Trade in Map after validating pre-conditions
    public void addTrade(Trade T) throws Exception {
        if (allTradeMap.containsKey(T.getTradeId())) {
            checkVersion(T, allTradeMap.get(T.getTradeId()).getVersion());

            if (isMaturedTrade(T.getMaturityDate(), allTradeMap.get(T.getTradeId()).getMaturityDate())) {
                allTradeMap.replace(T.getTradeId(), T);
                System.out.println(T.getTradeId() + " is added to the Store");
            } else {
                System.out.println("Not able to add " + T.getTradeId() + " in the store as maturity date is lower than current date");
            }
        } else {
            if (isMaturedTrade(T.getMaturityDate(), T.getCreatedDate())) {
                allTradeMap.put(T.getTradeId(), T);
                System.out.println(T.getTradeId() + " is added to the Store");
            } else {
                System.out.println("Not able to add " + T.getTradeId() + " in the store as maturity date is lower than current date");
            }
        }
    }

    // get trade with tradeId
    public Trade getTrade(String tId) throws Exception {
        if (allTradeMap.containsKey(tId))
            return allTradeMap.get(tId);
        throw new Exception("Trade with " + tId + " not Found");
    }

    // print All Trades in the store
    public void printTrade() {
        for (String tId : allTradeMap.keySet()) {
            System.out.println(allTradeMap.get(tId).toString());
        }
    }
}
