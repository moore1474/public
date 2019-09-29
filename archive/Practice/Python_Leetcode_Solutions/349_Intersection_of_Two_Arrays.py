class Solution(object):
    def intersection(self, nums1, nums2):
        in_both = {}
        nums1_hash = {}
        for num in nums1:  nums1_hash[num]=None
        for num in nums2: 
            if num in nums1_hash: in_both[num]=None
        return in_both.keys()
        
print(Solution().intersection([1,2,2,1], [2,2]))   