
def total(**kwargs):
    for k, v in kwargs.items():
        print(f'{k} = {v}')
        print(type(k),type(v))