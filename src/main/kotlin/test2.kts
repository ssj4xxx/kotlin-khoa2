val sites = mapOf("pragprog" to "https://www.pragprog.com",
    "agiledeveloper" to "https://agiledeveloper.com")
println(sites.size)
println(sites["pragprog"])
for (entry in sites) {
    println("${entry.key} --- ${entry.value}")
}