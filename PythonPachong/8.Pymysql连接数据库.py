import pymysql

db = pymysql.connect(host='172.16.33.29',user='admin',password='w#sxp6G*mcYRYxU^',database='fast_machine',port=3901,charset='utf8')
cursor = db.cursor()
# sql语句执性，列表元组
info_list = [(2),(3)]
sql = 'insert into user (id) values(%s)'
cursor.executemany(sql,info_list)
db.commit()
# 关闭
cursor.close()
db.close()