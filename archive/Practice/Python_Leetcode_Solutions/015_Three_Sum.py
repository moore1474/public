#Exceeds Memory
class Solution(object):
    def threeSum(self, nums):
        three_sums = []
        
        # Build map with indices of all numbers
        num_map = {}
        for i, val in enumerate(nums):
            if val not in num_map: num_map[val] = []
            num_map[val].append(i)
        
        # Look for possible completion for every possible pair
        considered = {}
        for i in range(len(nums)):
            for j in range(i + 1, len(nums)):
                needed = 0 - (nums[i] + nums[j])
                triplet = [nums[i], nums[j], needed]
                triplet.sort()#Sorted for unique tuplet hash into considered map       
                if tuple(triplet) not in considered and needed in num_map:
                    indices_not_used = [x for x in num_map[needed] if x != i and x != j]
                    if len(indices_not_used)>0:
                        three_sums.append(triplet)
                considered[tuple(triplet)] = True
        
        return three_sums
    
#Time Limit Exceeded Solution
class Solution2(object):
    
    def threeSum(self, nums):
        three_sums = []
        
        # Look for possible completion for every possible pair
        added = {}
        for i in range(len(nums)):
            for j in range(i + 1, len(nums)):
                needed = 0 - (nums[i] + nums[j])
                triplet = [nums[i], nums[j], needed]
                triplet.sort()#Sorted for unique tuplet hash into considered map       
                if tuple(triplet) not in added:
                    list_minus_i_j = [nums[k] for k in range(len(nums)) if k!=i and k!=j]
                    if needed in list_minus_i_j:
                        three_sums.append(triplet)
                        added[tuple(triplet)] = True
        
        return three_sums

