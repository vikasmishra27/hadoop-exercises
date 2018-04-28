#!/usr/bin/env python
#####################################################
# Script to pull records from 1985.txt and parse it #
# Parsed records are loaded to mysql DB             #
#####################################################

from __future__ import print_function
import mysql.connector
import re
import sys

# DB Connection string
dbConnection = mysql.connector.connect(user='root', database='521',password='itmd521')
cursor = dbConnection.cursor()

# Place holder for Insert statement for mysql DB

add_record = ("INSERT INTO records "
               "VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)")

#
# Open 1985.txt file in read mode. 
# Read each line of record and pull individual attributes based on schema.(Provided in schema directory
# Since sign + is not valid for decimal calculation, In AIR_TEMP removing leading + sign from attribute. 
# data_record is final combined record in alignment of insert statement.
# Insert statement is executed using cursor.executor
#
with open(str("1985.txt"), "r") as ins:
  for line in ins:
    val = line.strip()
    (filler1,USAF_WE_ST_ID,WBAN_WE_ST_ID,OBSE_DT,OBSE_TM,filler2,LATITUDE,LONGITUDE,filler3,ELEVATION,filler4,WIND_DIR,QUAL_CD1,filler5,SKY_CEIL_HT,QUAL_CD2,filler6,VISIBLE_DIST,QUAL_CD3,filler7,AIR_TEMP,QUAL_CD4,DEW_POINT_TEMP,QUAL_CD5,ATM_PRESS,QUAL_CD6) = (val[0:4],val[4:10],val[10:15],val[15:23],val[23:27],val[27:28],val[28:34],val[34:41],val[41:46],val[46:51],val[51:60],val[60:63],val[63:64],val[64:70],val[70:75],val[75:76],val[76:78],val[78:84],val[84:85],val[85:87],val[87:92],val[92:93],val[93:98],val[98:99],val[99:104],val[104:105])
    if ("+" in AIR_TEMP):
      AIR_TEMP = AIR_TEMP[1:5]
      data_record = (filler1,USAF_WE_ST_ID,WBAN_WE_ST_ID,OBSE_DT,OBSE_TM,filler2,LATITUDE,LONGITUDE,filler3,ELEVATION,filler4,WIND_DIR,QUAL_CD1,filler5,SKY_CEIL_HT,QUAL_CD2,filler6,VISIBLE_DIST,QUAL_CD3,filler7,AIR_TEMP,QUAL_CD4,DEW_POINT_TEMP,QUAL_CD5,ATM_PRESS,QUAL_CD6)
      cursor.execute(add_record,data_record)

dbConnection.commit()

cursor.close()
