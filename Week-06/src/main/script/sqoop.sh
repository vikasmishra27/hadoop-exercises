# Password itmd521
# LIMIT index starts from 0 hence start is 999. We need to select remaining 4000 records hence offset is 4000
hadoop fs -rm -r week-06-sqoop
sqoop import --connect jdbc:mysql://localhost/521 --username root -P --target-dir week-06-sqoop --query 'select * from (select * from records LIMIT 999,4000) rec where rec.AIR_TEMP >= 20 and rec.AIR_TEMP <= 200 and $CONDITIONS' -m 1
