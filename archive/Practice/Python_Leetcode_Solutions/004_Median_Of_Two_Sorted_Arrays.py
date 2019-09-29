class Solution(object):
    
    def find_highest_less_than_x(self, nums, start_pos, x):
        pass
    
    def findMedianSortedArrays(self, nums1, nums2):
        halfway_point = (len(nums1) + len(nums2))/2
        is_even = (len(nums1) + len(nums2))%2==0
        i_a = 0
        i_b = 0
        while i_a + i_b != int(halfway_point):
            highest = max(nums1[i_a], nums2[i_b])            
            for_nums = nums1 if highest==nums1[i_a] else nums2[i_b]
            i = i_a if highest==nums1[i_a] else i_b
            new_i = self.find_highest_less_than_x(for_nums, i, highest)
            if nums1 == for_nums: i_a = new_i +1
            else: i_b = new_i + 1
        
            