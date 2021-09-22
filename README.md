# Nomics-J

<p align="center">
<b>Simple library for handling Nomics API</b><br>
This library is not officially maintained and is a 3rd party library maintained for fun and usage!<br><br>
  
  <b>
  Nomics is an API-first crypto-asset data company delivering professional-grade market data APIs to institutional crypto investors & exchanges. Nomics offer products & services that allow funds, fintech apps, & exchanges to access clean, normalized & gapless primary source trade & order book data.</b>
</p>

<p align="center">
  <img src="https://github.com/lenk/Nomics-J/raw/master/blob/NOMICS_RUPEE.png" />
</p>

# Features
Feature  | Supported
-------- | -------------
Ticker | &check;
Metadata  | &check;
Market History  | &check;
Volume History  | &check;
Markets | &check;
Rates | &check;
Rates History | &check;
Global Ticker | &#10060;	
Highlights | &#10060;	
Supply History | &#10060;	
Exchg. Highlihts | &#10060;	
Exchg. Ticker | &#10060;	
Exchg. Vol. History | &#10060;	
Exchg. Metadata | &#10060;	
Market Highlight | &#10060;	

# Samples

## Ticker
[sort] Price, volume, market cap, and rank [shortCurrency] across [intervals] converting the price value [toCurrency]<br>
Current prices are updated every 10 seconds

```kotlin 
    // Fetch ticker data for Cardano (ADA)
    val client = Client("your_api_key")
    val ticker = client.getTicker("ADA")
    
    for (result in ticker) {
        println("Current Price: " + result.price)
        println("24H change: " + result.ONE_DAY?.price_change)
    } 
 ```
 
 ```kotlin
    // Fetch ticker data for Cardano (ADA) in EUR or alternative conversion
    val client = Client("your_api_key")
    val ticker = client.getTicker("ADA", "EUR")
    
    for (result in ticker) {
        println("Current Price: " + result.price)
        println("24H change: " + result.ONE_DAY?.price_change)
    } 
 ```
 
## Metadata
The currencies endpoint returns all the [shortCurrency] and their metadata that Nomics supports.
 
```kotlin 
    // Fetch Metadata for Cardano (ADA)
    val client = Client("your_api_key")
    val meta = client.getMeta("ADA")
    
    for (data in meta) {
        println("Github: " + data.github_url)
        println("Name: " + data.name)
        println("id: " + data.id)
    } 
 ```
 
## Market History
MarketCap History is the total market cap in [toCurrency] for all crypto-assets at intervals between<br>
the requested [start] and [end] time period.

 
```kotlin 
    // Fetch Market History for Cardano (ADA)
    val client = Client("your_api_key")
    val market = client.getMarket(Date(), toCurrency = "ADA")
    
    for (history in market) {
        println("Time: " + history.timestamp)
        println("Cap: " + history.market_cap)
    } 
 ```
 
## Volume History
Volume History is the total volume for all crypto-assets in [toCurrency] at intervals between the requested [start] and [end] time period.<br>
For each entry, the volume field represents the sum of the spot_volume and derivative_volume fields.
 
```kotlin 
    // Fetch Market History for Cardano (ADA)
    val client = Client("your_api_key")
    val volume = client.getVolume(Date(), toCurrency = "ADA")
    
    for (history in volume) {
        println("Time: " + history.timestamp)
        println("Vol.: " + history.volume)
    } 
 ```
 
## Markets
The markets endpoint returns information on the [exchange]s and markets that Nomics supports,<br>
in addition to the Nomics currency identifiers for the [base] and [quote] currency.
 
```kotlin 
    // Fetch markets from Binance
    val client = Client("your_api_key")
    val markets = client.getMarkets("binance")
    
    for (market in markets) {
        println("Exchange: " + market.exchange)
        println("Market: " + market.market)
        println("Quote: " + market.quote)
        println("Base: " + market.base)

    } 
 ```
 
## Rates
The exchange rates endpoint returns the current exchange rates used by Nomics to convert prices from markets into USD.<br>
This contains Fiat currencies as well as a BTC and ETH quote prices.<br>
This endpoint helps normalize data across markets as well as to provide localization for users.
 
```kotlin 

    val client = Client("your_api_key")
    val rates = client.getRates()
    
    for (rate in rates) {
        println("Timestamp: " + rate.timestamp)
        println("Currency: " + rate.currency)
        println("Rate: " + market.rate)

    } 
 ```
 
 ## Rates History
Exchange rates for every point from [start] to [end] time range.<br>
This endpoint can be used with other history endpoints to convert values into a desired quote [currency].<br>

```kotlin 

    // Get rates history for Cardano (ADA)
    val client = Client("your_api_key")
    val rates = client.getRatesHistory("ADA", Date())
    
    for (rate in rates) {
        println("Timestamp: " + rate.timestamp)
        println("Rate: " + rate.rate)

    } 
 ```
