class Solution(object):
    def maxProfit(self, prices):
        profit = 0
        for i in range(len(prices)-1):
            if prices[i]<prices[i+1]:
                profit += prices[i+1] - prices[i]
        return profit
   
print(Solution().maxProfit([1, 10, 100, 1, 500, 3, 2, 1, 700]))#1,297