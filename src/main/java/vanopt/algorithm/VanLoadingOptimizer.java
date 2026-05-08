package vanopt.algorithm;
import vanopt.dto.Shipment;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
@Component
public class VanLoadingOptimizer {

    public List<Shipment> optimize(int maxVolume, List<Shipment> shipments){
        List<Shipment> lst = new ArrayList<>();
        int[][] dp = new int[shipments.size() + 1][maxVolume + 1];
        for(int i = 1; i <= shipments.size(); i++){
            for(int j =0; j <= maxVolume; j++){
                if(shipments.get(i - 1).getVolume() <= j){
                    int revenue = dp[i - 1][j];
                    int includedRevenue = shipments.get(i - 1).getRevenue() +
                            dp[i - 1][j - shipments.get(i - 1).getVolume()];
                    dp[i][j] = Math.max(revenue,includedRevenue);
                }else{
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        int i = shipments.size();
        int j = maxVolume;

        while(i > 0 && j > 0){
            if(dp[i][j] != dp[i - 1][j]){
                lst.add(shipments.get(i - 1));
                j = j - shipments.get(i - 1).getVolume();
            }
            i--;
        }
        return lst;
    }
}
