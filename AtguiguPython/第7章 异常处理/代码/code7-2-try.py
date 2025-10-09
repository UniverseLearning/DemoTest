try:
    n = int(input('请输入一个数字'))
    n = 5 / n
    print(n)
except ZeroDivisionError as e:
    print('除数不能为0')
    print('原始报错信息',e)
except:
    print('请输入一个数字')
else:
    print('运行没有被except语句捕获，执行else模块')
finally:
    print('无论如何，都要执行finally模块')

# try:
#     # NameError
#     # print(l)
#     # SyntaxError
#     # if 'he' == 'hi':
#
#     tp = (1, 3, 5)
#     tp[2]=4
#     # print(tp)
#     # tp.append(2)
#
# except KeyError as e:
#     print(e)
# except IndexError as e:
#     print(e)
# except:
#     print("异常了")