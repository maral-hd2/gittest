import datetime 
import pandas
import urllib2
import json
import pymongo
import time 
import sys
import datetime
import pymongo
import collections
from bson.json_util import loads

client = pymongo.MongoClient('localhost', 27017)
db = client.fin
datelist = pandas.bdate_range(pandas.datetime.today(), periods=7, freq='B').tolist()

date = []

for i in range(0, len(datelist)):
	date.append(str(datelist[i]).split(" ", 1)[0])

print(date)

	#get data from bloomberg
def get_data(url):
	req = urllib2.Request(url)
	response = urllib2.urlopen(req)
	file = response.read()
	result = json.loads(file)
	return result

	
def fin_analysis(date):
	#query the mongodb database by a timestamp
	df = db.fin_eps_premarket.find({"_id" : date})
	document = None


	#sort through mongodb pre_market_eps database for stock tickers
	for i in df:
		document = i['ticker']
		for j in range(len(i['ticker'])):
			ticker = document[j].keys()


	#create the pandas dataframe that is going to be structured to hold the value investing analysis data 
	fin_analysis = pandas.DataFrame(columns=["ticker", "epsDate", "epsEstimate", "epsActual", "price",  "sector", "debt", "marketCap", "peRatio", "sharesOutstanding", "priceToSales", "priceToBook", "day5ChangePercent"], index=range(len(ticker)))

	fin_analysis = pandas.DataFrame(columns=["ticker", "epsEstimate", "price", "peRatio", "day5ChangePercent"], index=range(len(ticker)))



	#Pull the Eps data from the mongodb premarket eps data (actual and estimated)
	for i in range(len(ticker)):
		fin_analysis["epsEstimate"].loc[i] = document[0][ticker[i]]['eps'][0]['epsestimate']
		# fin_analysis["epsActual"].loc[i] = document[0][ticker[i]]['eps'][1]["epsactual"]
		# fin_analysis["epsDate"].loc[i] = document[0][ticker[i]]['eps'][2]["epsDate"]

	#Get quote data and organize it into a pandas dataframe 
	#example usage of data getting pulled by the IEX exchange
		#/stock/aapl/quote?displayPercent=true
		# "sector": "Technology",
		# "latestPrice": 158.73
		# "marketCap": 751627174400,
		# "peRatio": 16.86,

	for i in range(len(ticker)):
		try:
			fin_analysis['ticker'].loc[i] = ticker[i]
			url = "https://api.iextrading.com/1.0/stock/{}/quote?displayPercent=true".format(str(ticker[i]).lower()) 
			result = get_data(url)
			# fin_analysis["sector"].loc[i] = result["sector"]
			# fin_analysis["marketCap"].loc[i] = result["marketCap"]
			fin_analysis["peRatio"].loc[i] = result["peRatio"]


			fin_analysis["price"].loc[i] = result["latestPrice"]
		except:
			pass 


	#Get financials data and put into the pandas dataframe
	#Example return of the data:
		#/stock/aapl/financials
		# "cashFlow": 12523000000
		# "totalLiabilities": 200450000000
		# "sharesOutstanding": 5213840000


	# for i in range(len(ticker)):
	# 	try:
	# 		url = "https://api.iextrading.com/1.0/stock/{}/financials".format(str(ticker[i]).lower()) 
	# 		result = get_data(url)
	# 		fin_analysis["debt"].loc[i] = float(result["financials"][0]["totalAssets"]) - float(result["financials"][0]["totalLiabilities"])
	# 	except:
	# 		pass

	##Get stats data and put it into a pandas dataframe 
	#Example return of the data
	  # "companyName": "Apple Inc.",
	  # "marketcap": 760334287200
	  # "sharesOutstanding": 5213840000
	  # "day5ChangePercent": -0.005762605699968781  
	  # "priceToSales": 3.6668503,
	  # "priceToBook": 6.19,

	for i in range(len(ticker)):
		try:
			url = "https://api.iextrading.com/1.0/stock/{}/stats".format(str(ticker[i]).lower()) 
			result = get_data(url)
			print(result)
			# fin_analysis["priceToSales"].loc[i] = result["priceToSales"]
			# fin_analysis["priceToBook"].loc[i] = result["priceToBook"]
			# fin_analysis["sharesOutstanding"].loc[i] = result["sharesOutstanding"]
			fin_analysis["day5ChangePercent"].loc[i] = result["day5ChangePercent"]
		except:
			pass

	##filter the data based on the ten rules of investing by benjamin graham
	fin_analysis = fin_analysis[fin_analysis["epsEstimate"] > 0]
	fin_analysis = fin_analysis[fin_analysis["price"] < 50]
	fin_analysis = fin_analysis[fin_analysis["peRatio"] < 11]
	# fin_analysis = fin_analysis[fin_analysis["priceToBook"] < 10]
	# fin_analysis = fin_analysis[fin_analysis["priceToSales"] < 10]
	# fin_analysis = fin_analysis[fin_analysis["debt"] > 0]
	# fin_analysis = fin_analysis[fin_analysis["day5ChangePercent"] > 0]

	return fin_analysis

for i in range(len(date)):
	document = None
	d = {} 	
	document = fin_analysis(date[i])
	# print(document)
	print(document.dropna())
	records = document.to_dict('records')
	# print(records)
	# try:
	if(records != []):
		db.fin_analysis.update({"_id" : date[i]}, {"$push": records[0]}, upsert = True)
	# except:
	# 	pass

	# for j in range(len(document)):
	# 	d[document['ticker'].values[j]] = {'tiker_attributes' : [{'price' : [document['price'].values[i]]} ,  {'peRatio' : document['peRatio'].values[i]} , {'priceToBook' : document['priceToBook'].values[i]}, {'priceToSales' : document['priceToSales'].values[i]}, {'day5ChangePercent' : document['day5ChangePercent'].values[i]}, {'epsEstimate' : document['epsEstimate'].values[i]}, [{'epsEstimate' : document['epsEstimate'].values[i]}]]}
	# 	print d

		# return d


for i in range(len(date)):
	d = import_fin(date[i])
	db.fin_analysis.insert({"_id" : date[i]})
	db.fin_analysis.update({"_id" : date[i]}, {"$push": {'ticker' : d}})	

# print "finished"



