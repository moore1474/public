class Solution(object):
    def maxProfit(self, prices):
        if prices is None or len(prices)==0: return 0
        #Min, Max
        max_profits = [[0,0], [0,0]]
        latest = max_profits[0]
        #Find min-max ranges in array
        for i in range(len(prices)):            
            if prices[i] <= prices[latest[0]]:
                latest = [i, i]
                max_profits.append(latest)
            elif prices[i] > prices[latest[1]]:
                latest[1] = i        
        return max(map(lambda a: prices[a[1]]-prices[a[0]], max_profits))
    
print(Solution().maxProfit([7, 1, 5, 3, 6, 4]))#5
print(Solution().maxProfit([7, 6, 4, 3, 1]))#0
print(Solution().maxProfit([]))#0
print(Solution().maxProfit([2,4,1]))#2
print(Solution().maxProfit([4,7,2,1]))#3 