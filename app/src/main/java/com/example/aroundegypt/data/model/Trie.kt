package com.example.aroundegypt.data.model

class Trie {
    private val root = TrieNode()

    fun insert(word: String) {
        var node = root
        for (char in word) {
            node = node.children.computeIfAbsent(char) { TrieNode() }
        }
        node.isEndOfWord = true
    }

    fun search(prefix: String): List<String> {
        val results = mutableListOf<String>()
        var node = root
        for (char in prefix) {
            node = node.children[char] ?: return emptyList()
        }
        collectAllWords(node, prefix, results)
        return results
    }

    private fun collectAllWords(node: TrieNode, prefix: String, results: MutableList<String>) {
        if (node.isEndOfWord) results.add(prefix)
        for ((char, childNode) in node.children) {
            collectAllWords(childNode, prefix + char, results)
        }
    }
}

