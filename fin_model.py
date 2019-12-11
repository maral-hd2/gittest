import pandas as pd
import sklearn
from pandas import Series, DataFrame
from sklearn.linear_model import LogisticRegression
import numpy as np
from sklearn.linear_model import LinearRegression
# import module to calculate model perfomance metrics
from sklearn import metrics
from sklearn.model_selection import cross_val_score
from sklearn import  tree
from sklearn import preprocessing
import graphviz

train = pd.read_csv("final_features_numerical.csv")


min_max_scaler = preprocessing.MinMaxScaler()
np_scaled = min_max_scaler.fit_transform(train)

df_normalized = pd.DataFrame(np_scaled, columns=train.columns)

print(df_normalized)



target = pd.read_csv("targets.csv")

X = df_normalized.drop(["After"], axis=1) 


y = df_normalized["After"]

model = tree.DecisionTreeClassifier()

final = model.fit(X,y)
 
 # C:\Users\wfcla\Desktop\Fin\Back End
# predict = model.predict(target)

# dot_data = tree.export_graphviz(final, out_file=None) 
# graph = graphviz.Source(dot_data) 
# graph.render("Stock Prediction") 

print(cross_val_score(final, X, y, cv = 10, scoring="accuracy").mean())
