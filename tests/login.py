import requests


url = "http://localhost:8080/public"
login_data = {'username': 'dieu',
              'password': 'jesuishomo'}


def login():
    req = requests.post(url+'/login', data=login_data)
    print("Response code : {}, token : '{}' !".format(req.status_code, req.text))


def register():
    req = requests.post(url+'/register', data=login_data)
    print("Response code : {}, token : '{}' !".format(req.status_code, req.text))
