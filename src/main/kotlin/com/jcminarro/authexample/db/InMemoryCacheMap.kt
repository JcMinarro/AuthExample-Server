package com.jcminarro.authexample.db

open class InMemoryCacheMap<T>(private val cacheTtlInMillis: Long = 60000) {

    private val cache: MutableMap<String, CacheEntry<T>>

    init {
        cache = HashMap<String, CacheEntry<T>>()
    }

    @Synchronized open operator fun get(id: String): T? {
        val value: T?
        val cacheEntry = cache[id]
        if (cacheEntry != null && cacheEntry.isValid(System.currentTimeMillis(), cacheTtlInMillis)) {
            value = cacheEntry.entry
        } else {
            cache.remove(id)
            value = null
        }
        return value
    }

    @Synchronized fun put(id: String, value: T) {
        cache.put(id, createCacheEntry(value))
    }

    @Synchronized fun remove(id: String) {
        cache.remove(id)
    }

    @Synchronized fun clear() {
        cache.clear()
    }

    private fun createCacheEntry(value: T): CacheEntry<T> {
        return CacheEntry(System.currentTimeMillis(), value)
    }

    private class CacheEntry<T>(private val time: Long, val entry: T) {

        fun isValid(nowInMillis: Long, ttlInMillis: Long): Boolean {
            return nowInMillis - time < ttlInMillis
        }
    }
}