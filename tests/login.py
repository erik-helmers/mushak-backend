import requests


url = "http://localhost:8081/"

login_data = {'name': 'dieu',
              'password': 'jesuishomo'}


def login():
    req = requests.post(url+'/song/256&', data=login_data)
    print("Response code : {}, token : '{}' !".format(req.status_code, req.text))


def register():
    req = requests.post(url+'/register', data=login_data)
    print("Response code : {}, token : '{}' !".format(req.status_code, req.text))

login()