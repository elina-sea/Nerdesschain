# Nerdesschain

This repository is devoted to studying blockchain principles and learning how to implement own solution.
It will be progressing and gaining more and more features.

This work is done with the help of the following blog: [link](https://medium.com/programmers-blockchain/create-simple-blockchain-java-tutorial-from-scratch-6eeed3cb03fa)

---

### Here I will write about some topics,which are essential for understanding how blockchain works

---

## Proof-of-Work
[Implemented in `HashGenerator` class]

**Note:** This method was originally introduced to prevent service abuse (DoS, spam).
Cryptocurrency only inherited this method in own way. In preventing DoS for example, 
host has to solve a specific problem to be allowed t access services.

Proof-of-Work is a consensus algorithm in lots of cryptocurrencies, 
used to verify that the work done by a node (or miner) was valid.
### How it works
1. Each node (or miner) has to solve puzzles which require high computational performance. 
   Though puzzle is hard to solve, the result is easily verified.
2. After finding solution to a puzzle miner broadcasts new Block to the network 
   (to the ledger’s chain of blocks or blockchain itself).
3. All other miners verify that the solution for a puzzle is correct.
4. If validation successful node/miner who solved the puzzle gets the Block subsidy (aka reward).

### What is the puzzle
The goal of a puzzle is in guessing a pseudo random number usually called `nonce`. 
Nonce, when combined with the data provided in the block and passed through hash function (`HashGenerator`),
must produce a result that matches given conditions (which are to be verified by other nodes).

**Nonce is responsible for generating correct solution of a specific block (block hash)**. 
Which means, it's impossible to add a new block to the chain without finding a valid nonce.

The name ***"Proof-of-Work"*** comes from the fact that each block has a block hash that 
represents the work done by a node (miner).

#### Note
PoW isn't the only algorithm existing for this purpose, but for now the most used one.
