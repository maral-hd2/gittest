

import pymongo
from collections import defaultdict
import pandas
# import datetime

# print datetime.datetime.now().strftime('%Y-%m-%d %H:%M')

client = pymongo.MongoClient('localhost', 27017)

db = client.fin
df = pandas.DataFrame(list(db.fin_eps_premarket.find()))

print(df)
# db.fin_eps_premarket.delete_many({})
# db.fin_analysis.delete_many({})
# print df['_id']


datelist = pandas.bdate_range(pandas.datetime.today(), periods=7, freq='B').tolist()

date = []

for i in range(0, len(datelist)):
	date.append(str(datelist[i]).split(" ", 1)[0])




for i in range(len(date)):
	
	df = db.fin_analysis.find({"_id" : date[i]})
	document = None


	#sort through mongodb pre_market_eps database for stock tickers
	for i in df:
		print(i.keys())
		ticker = i['ticker'][0]
		# for j in range(len(i['ticker'])):
		# 	ticker = document[j].keys()

		# return ticker

# print(len(x[-7:]))

x = [1,2,3,4,5,6,7]

# print(len(x))


y = [1,2,3,4,5,6,7,8]

for i in range(len(x)):
	for j in range(len(y)):
		print("this is a test url {}, ticker {}".format(j,i))

# for stuff in df['_id']:
# 	db.fin_eps_premarket.update({"_id": stuff},
# 	{"epsestimate": '1'})
# 	