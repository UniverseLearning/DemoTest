import requests


class xxxSpider(object):
  def __init__(self):
    self.url = 'http://code.tarena.com.cn/AIDCode/aid1906/13Redis/'
    # 网站使用的用户名，密码
    self.auth = ('c语言中文网'.encode('utf-8'),'c.biancheng.net'.encode('utf-8'))

  def get_headers(self):
      headers = {'User-Agent':"Mozilla/5.0"}
      return headers

  def get_html(self,url):
      res = requests.get(url,headers=self.get_headers(),auth=self.auth)
      html = res.content
      return html

if __name__ == '__main__':
    xxx = xxxSpider()
    print(xxx.get_html(xxx.url))