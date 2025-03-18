
// In a world where encoded messages are sent secretly, your task is to decode them. 
// The encoding follows a specific pattern:

// You are given a string s that consists of lowercase English letters and encoded segments. 
// The encoded segments follow this rule:

// k[encodedString] where encodedString is a sequence of lowercase English letters enclosed in square brackets, 
// and k is a positive integer indicating how many times the encodedString should be repeated.


// You need to implement a function that takes this encoded string as input and returns the fully decoded version of it.

// Input
// A single string s, where s consists of digits, square brackets [ ], and lowercase English letters.

// Output
// Return a string that represents the decoded message.

// Examples
// Example 1:
// Input = 1[b]
// Output = b

// Example 2:
// Input = 3[b2[ca]]
// Output = bcacabcacabcaca


// Explanation:
// Inner substring 2[ca] breakdown into caca.
// it becomes 3[bcaca]
// 3[bcaca] becomes bcacabcacabcaca which is final result

// Example 3:
// Input = 2[abc]3[cd]ef
// Output = abcabccdcdcdef