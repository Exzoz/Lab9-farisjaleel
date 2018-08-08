Lab 9 Questions:

1. 
A)  Are the resulting word frequencies any different?
 No, they are the same as before. 
33278=the
18012=of
12843=and
12449=to
12438=a
9384=in
7788=was
6601=that
6199=he
5528=his


B) Is the time performance any different? If so, how would you rank the three implementations(in increasing order of time complexity)?

Times: 
HashMap - 825
TreeMap - 1163
MyHashMap - 137515

- The fastest is HashMap, then TreeMap, and finally MyHashMap.

2. How are the % and Math.floorMod different? Which works more reliably for computing a hash table index?

- Math.floorMod will always give us a positive value in comparison to % which will return a negative in the case when hash is negative so for our use case Math.floorMod is better. 

3. What is the time complexity of MyHashMap.size(), and how could you make it much more efficient?

- Time complexity is O(table size). If we introduce counter of elements, then complexity could be O(1).

4. How does this implementation compare to one where you would directly use your linked Node class from the earlier assignment?

- In my opinion the implementation of MyHashMap is was harder then iStack. 
