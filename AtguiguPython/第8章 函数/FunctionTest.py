def isGreater0(x):
    if x > 0:
        return True
    else:
        return 1
print(isGreater0(5))
print(isGreater0(0))
print(bool(1))

import modele as m
dict1 = {"1":2}
m.total(**dict1)
m.total(aaaa=2)

# 导入sys、os两个模块
import sys,os
# 使用模块名作为前缀来访问模块中的成员
print(sys.argv[0])
# os模块的sep变量代表平台上的路径分隔符
print(os.sep)


# 导入sys模块的argv,winver成员
from sys import argv, winver
# 使用导入成员的语法，直接使用成员名访问
print(argv[0])
print(winver)