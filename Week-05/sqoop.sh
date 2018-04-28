#Password itmd521
sqoop import --connect jdbc:mysql://localhost/521 --username root -P  --table records --where "AIR_TEMP >= 380 and AIR_TEMP <= 420" -m 1
