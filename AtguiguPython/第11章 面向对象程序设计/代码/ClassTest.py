# #描述符类
# class revealAccess:
#     def __init__(self, initval = None, name = 'var'):
#         self.val = initval
#         self.name = name
#
#     def __get__(self, obj, objtype):
#         return self.val
#
#     def __set__(self, obj, val):
#         self.val = val
# class myClass:
#     x = revealAccess(10,'var "x"')
#     y = 5
#
# m = myClass()
# print(m.x)
#
# m.x = 99
#
# m1 = myClass()
# print(m1.x)
# print(m.x)



# class CLanguage:
#     #构造函数
#     def __init__(self,n):
#         self.__name = n
#     #设置 name 属性值的函数
#     def setname(self,n):
#         self.__name = n
#     #访问nema属性值的函数
#     def getname(self):
#         return self.__name
#     #删除name属性值的函数
#     def delname(self):
#         self.__name="xxx"
#     #为name 属性配置 property() 函数
#     name = property(getname, setname, delname, '指明出处')
# #调取说明文档的 2 种方式
# #print(CLanguage.name.__doc__)
# help(CLanguage.name)
# clang = CLanguage("C语言中文网")
# javalang = CLanguage("JAVA语言中文网")
# #调用 getname() 方法
# print(clang.name)
# print(javalang.name)
# #调用 setname() 方法
# clang.name="Python教程"
# print(clang.name)
# #调用 delname() 方法
# del clang.name
# print(clang.name)







# class Rect:
#     def __init__(self,area):
#         self.__area = area
#     @property
#     def area(self):
#         print("get---------------------")
#         return self.__area
#
#     @area.setter
#     def area(self, value):
#         print("set---------------------")
#         self.__area = value
#
#     @area.deleter
#     def area(self):
#         print("delete---------------------")
#         self.__area = 0
# rect = Rect(30)
# #直接通过方法名来访问 area 方法
# rect.area
# rect.area = 11
# del rect.area


# class CLanguage:
#     def setname(self, name):
#         if len(name) < 3:
#             raise ValueError('名称长度必须大于3！')
#         self.__name = name
#
#     def getname(self):
#         return self.__name
#
#     # 为 name 配置 setter 和 getter 方法
#     name = property(getname, setname)
#
#     def setadd(self, add):
#         if add.startswith("http://"):
#             self.__add = add
#         else:
#             raise ValueError('地址必须以 http:// 开头')
#
#     def getadd(self):
#         return self.__add
#
#     # 为 add 配置 setter 和 getter 方法
#     add = property(getadd, setadd)
#
#     # 定义个私有方法
#     def __display(self):
#         print(self.__name, self.__add)
# clang = CLanguage()
# #调用name的setname()方法
# clang.name = "C语言中文网"
# #调用add的setadd()方法
# clang.add = "http://c.biancheng.net"
# #直接调用隐藏的display()方法
# clang._CLanguage__display()



#
# class People:
#     def __init__(self,name):
#         self.name = name
#     def say(self):
#         print("我是人，名字为：",self.name)
# class Animal:
#     def __init__(self,food):
#         self.food = food
#     def display(self):
#         print("我是动物,我吃",self.food)
# class Person(People, Animal):
#     #自定义构造方法
#     def __init__(self,name,food):
#         #调用 People 类的构造方法
#         super().__init__(name)
#         super(Person,self).__init__(name) #执行效果和上一行相同
#         #People.__init__(self,name)#使用未绑定方法调用 People 类构造方法
#         #调用其它父类的构造方法，需手动给 self 传值
#         Animal.__init__(self,food)
# per = Person("zhangsan","熟食")
# per.say()
# per.display()


#
# class A:
#     def __init__(self):
#         print("A",end=" ")
#         super().__init__()
# class B:
#     def __init__(self):
#         print("B",end=" ")
#         super().__init__()
# class C(A,B):
#     def __init__(self):
#         print("C",end=" ")
#         A.__init__(self)
#         B.__init__(self)
# print("MRO:",[x.__name__ for x in C.__mro__])
# C()



# class commonBase:
#     def __init__(self):
#         print("commonBase")
#         super().__init__()
#
# class base1(commonBase):
#     def __init__(self):
#         print("base1")
#         super().__init__()
#
# class base2(commonBase):
#     def __init__(self):
#         print("base2")
#         super().__init__()
#
# class myClass(base1,base2):
#     def __init__(self,arg):
#         print("my base")
#         super().__init__(arg)
# myClass(10)



# #定义一个实例方法
# def say(self):
#     print("我要学 Python！")
# #使用 type() 函数创建类
# CLanguage = type("CLanguage",(object,),dict(say = say, name = "C语言中文网"))
# #创建一个 CLanguage 实例对象
# clangs = CLanguage()
# #调用 say() 方法和 name 属性
# clangs.say()
# print(clangs.name)



# class MyClass:
#   pass
# instance = MyClass()
# print(type(instance))
# print(type(MyClass))

# class demoClass:
#     instances_created = 0
#     def __new__(cls,*args,**kwargs):
#         print("__new__():",cls,args,kwargs)
#         instance = super().__new__(cls)
#         instance.number = cls.instances_created
#         cls.instances_created += 1
#         return instance
#     def __init__(self,attribute):
#         print("__init__():",self,attribute)
#         self.attribute = attribute
# test1 = demoClass("abc")
# test2 = demoClass("xyz")
# print(test1.number,test1.instances_created)
# print(test2.number,test2.instances_created)



# class CLanguage:
#     def __init__ (self,):
#         self.name = "C语言中文网"
#         self.add = "http://c.biancheng.net"
#     def say(self):
#         pass
# clangs = CLanguage()
# print(clangs.__dir__())
# print(clangs.__dict__)



# class CLanguage:
#     def __init__ (self):
#         self.name = "C语言中文网"
#         self.add = "http://c.biancheng.net"
#     def say(self):
#         print(self.name, "我正在学Python")
#
# clangs = CLanguage()
# print(getattr(clangs,"name"))
# print(getattr(clangs,"add"))
# www = getattr(clangs,"say")
# www()
# print(getattr(clangs,"display",'nodisplay'))

# class IntDic:
#     def __init__(self):
#         # 用于存储数据的字典
#         self.__date = {}
#
#     def __len__(self):
#         return len(list(self.__date.values()))
#
#     def __getitem__(self, key):
#         # 如果在self.__changed中找到已经修改后的数据
#         if key in self.__date:
#             return self.__date[key]
#         return None
#
#     def __setitem__(self, key, value):
#         # 判断value是否为整数
#         if not isinstance(value, int):
#             raise TypeError('必须是整数')
#         # 修改现有 key 对应的 value 值，或者直接添加
#         self.__date[key] = value
#
#     def __delitem__(self, key):
#         if key in self.__date: del self.__date[key]
#
#
# dic = IntDic()
# # 输出序列中元素的个数，调用 __len__() 方法
# print(len(dic))
# # 向序列中添加元素，调用 __setitem__() 方法
# dic['a'] = 1
# dic['b'] = 2
#
# print(len(dic))
# dic['a'] = 3
# dic['c'] = 4
# print(dic['a'])
# # 删除指定元素，调用 __delitem__() 方法
# del dic['a']
# print(dic['a'])
# print(len(dic))


class listDemo:
    def __init__(self):
        self.__date=[]
        self.__step = 0

    def __setitem__(self,key,value):
        self.__date.insert(key,value)
        self.__step += 1
    #是该类实例对象成为可调用对象
    def __call__(self):
        self.__step-=1
        return self.__date[self.__step]

mylist = listDemo()
mylist[0]=1
mylist[1]=2
mylist[2]=3
mylist[3]=4
mylist[4]=5
mylist[5]=6
print(mylist())
# #将 mylist 变为迭代器
# a = iter(mylist,1)
# print(a.__next__())
# print(a.__next__())





