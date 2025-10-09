#导入parse模块
from urllib import parse


#构建查询字符串字典
query_string = {
'wd' : '爬虫'
}
#调用parse模块的urlencode()进行编码
result = parse.urlencode(query_string)
#使用format函数格式化字符串，拼接url地址
url = 'http://www.baidu.com/s?{}'.format(result)
print(url)



#注意url的书写格式，和 urlencode存在不同
url = 'http://www.baidu.com/s?wd={}'
word = input('请输入要搜索的内容:')
#quote()只能对字符串进行编码
query_string = parse.quote(word)
print(url.format(query_string))



string = '%E7%88%AC%E8%99%AB'
result = parse.unquote(string)
print(result)



# 1、字符串相加
baseurl = 'http://www.baidu.com/s?'
params='wd=%E7%88%AC%E8%99%AB'
url = baseurl + params
# 2、字符串格式化（占位符）
params='wd=%E7%88%AC%E8%99%AB'
url = 'http://www.baidu.com/s?%s'% params
# 3、format()方法
url = 'http://www.baidu.com/s?{}'
params='wd=%E7%88%AC%E8%99%AB'
url = url.format(params)