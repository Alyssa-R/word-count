report specs: 

##Implementation Summary
The design that we have implemented, when given a directory of text files, will calculate the word counts of 
these files by "passing out" close-to-equal numbers of files to each thread up front. Each thread then counts 
the words of the files in a hashtable (where the key is a given word and the value is the number of times
that word appears). After each thread processes its designated files, it merges its accumulated data
into the 'master' hashtable, which collects the results of all threads. Once all threads have merged into 
this table and returned to main program execution thread, a single output file is generated from this master
hashtable, so that the final file contains all the data.

##Challenges

[]How did you solve each challenge in task 2? Be specific, add pseudo code if you need to.

####Input Challenges   
###### Splitting data across multiple threads:

   
..*  in single thread, our program took in entire directory of suitable files to read,
		 this approach inspired us to give each thread a similar list of files to read and report upon
		 allowed us to move forward with dependably accurate word- count code while solving trickier issues, 
		 having confidence in this algorithm allowed us to isolate and solve nastier bugs as they arose

..* we toyed with the idea of a queue or stack of "unread" files, which any available thread would 'pop'
a new file from when ready, but we decided to split the files up front and give each thread their own unread
 list.

Advantages of this: no synchronization/ mutual exclusion issues with list of files, impossible for file to 
be dropped in a mutex double-popping race

Disadvantages of this: may be slower because it's possible for one thread to get bigger/heavier files, 
it takes a long time while other threads have already completed
		
		 
####Output Challenges
######Single output file:  (Hey Hannah I'm not sure these sentences make sense)
The constraint of outputting data to the same file encouraged a lot of thought about I/O and restricting 
threads' access to the final file. However, we found that if we wait until writing to a file to combine data 
from different input files, further challenges arose with combining duplicate words found. Instead of modifying
the hash tables after sending their data out to a text file, we decided to compile the data from all 
the threads into a "master" hash table, which will be output to a text file after all the threads have
finished and entered their information.

To ensure accurate results in the master hashtable, we added two parts to our design.
The first is our `merge` method, which adds another hashtable into the results of the master, adding a new
key/value pair if the word had not been previously found or adding the new value to the existing key's value
if the key was already in master. The other part of our design ensures that no two threads attempt to alter
the master hashtable at the same time. Because we only alter this hashtable as shared content between threads,
we trimmed our critical section down to only the merging of the 'instance' hashtables with the master, 
so threads can run as much code as possible before potentially being blocked by semaphores.

####Semaphores
###### `mutex`
We used mutex to permit only one thread accessing `master` hashtable at a time. Immediately before merging,
the thread must `acquire` mutex, and it's released immediately after `merge` returns, 

###### `allDone`
The allDone semaphore guarantees that all threads have terminated and submitted their data before
generating an output file from master, finalizing the output process.
`example code except i'm not totally sure how this semaphore works`


..* chose to merge hash tables between threads to consilidate data efficiently with no repeat keys,
 accurate count, don't have to reread/ re-generate any files	
 	
how to merge hash tables?
		
3. why hashtable?
	
4. generate file from hashtable?
	
5. how to make only one hashtable / merging?
	
	
		
[] Did you face any other challenges? How did you solve them?

[] Compare the running time of your code in task 1 to that of thread 2 with a single thread. Explain
your results.

[] Calculate the running time of task 2, using a varying number of threads. Start with a single
thread, and keep increasing the number of threads till the running time doesn’t change
(significantly).

[] Create a plot to represent the varying running time with increased number of threads. Explain
your results. 
