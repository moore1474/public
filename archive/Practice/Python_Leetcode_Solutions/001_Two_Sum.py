#Trivial Solution 500ms
class Solution(object):
    def twoSum(self, nums, target):
        for i in range(len(nums)):
             for j in range(i+1, len(nums)):
                 if nums[i] + nums[j] == target: return [i, j]
                                                        
#Faster 65ms
class Solution2(object):
    def twoSum(self, nums, target):
        
        #Create Numbers to Indices Map
        num_to_index_map = {num:[] for num in nums}
        for index, item in enumerate(nums): 
            num_to_index_map[item].append(index)
        
        #Try to complete sum using each number given
        for i in range(len(nums)):
            
            #Find number needed to complete sum with given number
            needed = target - nums[i]
            if needed in num_to_index_map:
                
                #Can't use same number twice, so i index is same
                # as number we're on, iterate through to see if 
                #number appears at another spot in given list.
                j = None
                for index in num_to_index_map[needed]: 
                    if index != i: 
                        j = index
                        break
                
                #Found index of a number to complete sum
                if j != None: 
                    return [i, j]
                

print(Solution2().twoSum([2, 7, 11, 15], 9))