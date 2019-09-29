class Solution(object):
    def removeElement(self, nums, val):
        l = len(nums)
        i = 0
        while i < l:
            if nums[i] == val:
                nums[i] = nums[l-1]
                l -= 1
                i -= 1
            i += 1
        return l
