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
import pandas as pd
import time
import requests
from lxml.html import fromstring




def get_proxies():
    url = 'https://free-proxy-list.net/'
    response = requests.get(url)
    parser = fromstring(response.text)
    proxies = set()
    for i in parser.xpath('//tbody/tr')[:10]:
        if i.xpath('.//td[7][contains(text(),"yes")]'):
            #Grabbing IP and corresponding PORT
            proxy = ":".join([i.xpath('.//td[1]/text()')[0], i.xpath('.//td[2]/text()')[0]])
            proxies.add(proxy)
    return proxies


proxies = get_proxies()

datelist = pd.bdate_range(pd.datetime.today(), periods=14, freq='B').tolist()

date = []

for i in range(0, len(datelist)):
	date.append(str(datelist[i]).split(" ", 1)[0])

print date

user_agent = 	'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0'
accept="text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"
# x-content-security-policy	upgrade-insecure-requests
Connection	= "keep-alive"

Insecure = '1'
def get_bloomberg_earnings(date):
	d = {}

	url = "https://www.bloomberg.com/markets/api/calendar/earnings/US?locale=en&date={}".format(date)

	print(url)

	req = urllib2.Request(url,headers={'Upgrade-Insecure-Requests': Insecure , 'Connection': Connection	, 'User-Agent': user_agent, 'Referer' : "https://www.bloomberg.com/tosv2.html?vid=&uuid=003dc040-e0a6-11e8-ad10-a179d138b29a&url=L21hcmtldHMvYXBpL2NhbGVuZGFyL2Vhcm5pbmdzL1VTP2xvY2FsZT1lbiZkYXRlPTIwMTgtMTEtMDg="})

	try:
		response = urllib2.urlopen(req)
	

		file = response.read()

		print file

		result = json.loads(file)
	except:
		pass


	try:
		print len(result['events'])

		for i in range(len(result['events'])):
			d[str(result['events'][i]['company']['ticker']).split(":", 1)[0]] = {'eps' : [{'epsestimate' : result['events'][int(i)]['eps']['estimate']} ,  {'epsactual' :  result['events'][int(i)]['eps']['actual']} , {'epsDate' : str(result['events'][int(i)]['eventTime']['date'])}]}
				

		client = pymongo.MongoClient('localhost', 27017)

		db = client.fin
		# df = pandas.DataFrame(list(db.fin.find()))
		##create a an if statement that parses out data only inserts if the eps data exists and there was good earnings



		# db.fin_eps_premarket.update({"_id" : date}, { unique: True })
		if(list(db.fin_eps_premarket.find({"_id": date})) == []):
			db.fin_eps_premarket.update({"_id" : date}, {"$push": {'ticker' : d}}, upsert=True)
		else:		
			db.fin_eps_premarket.update({"_id" : date}, {"$set": {'ticker.0' : d}})
	except:
		pass



for i in range(len(date)):
	time.sleep(5)
	get_bloomberg_earnings(date[i])
