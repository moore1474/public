class Solution(object):
    def merge(self, nums1, m, nums2, n):
        curr = m + n -1
        i, j = m -1, n-1
        while i >= 0 and j >= 0:
            if nums1[i] >= nums2[j]:
                nums1[curr] = nums1[i]
                i -= 1
            else:
                nums1[curr] = nums2[j]
                j -= 1
            curr -= 1
        while j >= 0:
            nums1[curr] = nums2[j]
            j -= 1 
            curr -= 1
            
nums1 = [1, 2, 3, 8, 9, 11, 0, 0, 0, 0, 0, 0]
nums2 = [4, 5, 6, 7, 10, 14]


Solution().merge(nums1, 6, nums2, 6)
print(nums1)

nums1 = [0]
nums2 = [1]


Solution().merge(nums1, 0, nums2, 1)
print(nums1)
