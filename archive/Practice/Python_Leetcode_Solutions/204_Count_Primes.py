class Solution2(object):
    def countPrimes(self, n):
        if n <=2: return 0
        primes = [2]
        for i in range(3, n):
            prime = True
            for num in primes:
                if i%num == 0:
                    prime = False
                    break
            if prime: primes.append(i)
        return len(primes)
        
class Solution(object):
    def countPrimes(self, n):
        if n<=2: return 0
        sieve = {}
        for i in range(2, n, 1):
            if i not in sieve:
                for j in range(2*i, n, i):
                    sieve[j] = False
        return n - len(sieve)-2

#for x in range(5, 10, 1): print(x)
print(Solution().countPrimes(0))#0
print(Solution().countPrimes(1))#0
print(Solution().countPrimes(2))#0
print(Solution().countPrimes(3))#1
print(Solution().countPrimes(10000))#25