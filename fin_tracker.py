import datetime 
import pandas
import urllib2
import json
import pymongo
from fin_analysis import get_data
import time 
import sys
import datetime
import pymongo
import collections
from bson.json_util import loads

client = pymongo.MongoClient('localhost', 27017)

db = client.fin
df = pandas.DataFrame(list(db.fin_analysis.find()))

print df

def push_price()
	for i in range(len(df)):
		
		ticker = str(df['ticker'].values[i])

		try:
			get_data()

		except:
			pass
			price_df['price'].loc[i] = "Price not found"



# price_df = price_df[price_df["price"] < 10]

# price_dict = {}

# date_dict = {}

# price_df['time'] = datetime.datetime.now().strftime('%Y-%m-%d %H:%M')











# print iex_earnings