package tradeTransmission;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

/**
 * Main class to read input from JSON file and add trades in hashmap
 * 
 * @author priyanka.vispute
 *
 */

public class TradeStoreMain {

    public static void main(String[] args) throws Exception {
        Gson gson = new Gson();
        TradeService tradeService = new TradeService();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");

        tradeService = readTrade(gson, tradeService);

        System.out.println("--------------------------------------------------");
        tradeService.printTrade();
        System.out.println("--------------------------------------------------");
        // Checking for all Expired Flag
        tradeService.getTrade("T2").setMaturityDate(sd.parse("20/09/2020"));
        tradeService.getTrade("T4").setMaturityDate(sd.parse("20/09/2020"));

        tradeService.checkExpiredTrade();
        System.out.println("--------------------------------------------------");
        tradeService.printTrade();
        System.out.println("--------------------------------------------------");
        
    }

    // Read input from json file and store trades 
    private static TradeService readTrade(Gson gson, TradeService tradeService) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\priyanka.vispute\\Documents\\trade.json"));
            List<Trade> tradeList = Arrays.asList(gson.fromJson(br, Trade[].class));
            
            for (Trade trade : tradeList) {
                tradeService.addTrade(trade);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return tradeService;
    }
}
