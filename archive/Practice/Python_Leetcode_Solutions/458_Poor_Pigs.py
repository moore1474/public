class Solution(object):
    def poorPigs(self, buckets, minutesToDie, minutesToTest):
        num_of_attempts = minutesToTest//minutesToDie
        return (buckets-1)//num_of_attempts
    
    
print(Solution().poorPigs(100, 15, 60))#5