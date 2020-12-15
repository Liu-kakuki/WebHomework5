## WebHomeWork5

2018211466		刘远荣

### 项目目录

<img src="https://cdn.jsdelivr.net/gh/Liu-kakuki/Image/20201215134517.png" alt="image-20201215134509825" style="zoom: 80%;" />

### thymeleaf和h2的配置

<img src="https://cdn.jsdelivr.net/gh/Liu-kakuki/Image/20201215134933.png" alt="image-20201215134933217" style="zoom:80%;" />

### 登录界面http://127.0.0.1:8080/login

<img src="https://cdn.jsdelivr.net/gh/Liu-kakuki/Image/20201215134607.png" alt="image-20201215134607504" style="zoom:80%;" />

登陆成功

<img src="https://cdn.jsdelivr.net/gh/Liu-kakuki/Image/20201215134629.png" alt="image-20201215134629795" style="zoom:50%;" />



同时进到数据库后台

![image-20201215134812467](https://cdn.jsdelivr.net/gh/Liu-kakuki/Image/20201215134812.png)

此时，用户列表和数据库都是空的

![image-20201215135102595](https://cdn.jsdelivr.net/gh/Liu-kakuki/Image/20201215135102.png)

![image-20201215135118096](https://cdn.jsdelivr.net/gh/Liu-kakuki/Image/20201215135118.png)

然后我们添加一个用户信息，用户输完电话号码后AJAX会发起请求判断该号码是否存在并予以提示

![image-20201215135255021](https://cdn.jsdelivr.net/gh/Liu-kakuki/Image/20201215135255.png)

数据已经添加成功

![image-20201215135416181](https://cdn.jsdelivr.net/gh/Liu-kakuki/Image/20201215135416.png)

![image-20201215135424599](https://cdn.jsdelivr.net/gh/Liu-kakuki/Image/20201215135424.png)

如果我们再添加一个相同的电话号码，AJAX会提醒我们号码已经存在

![image-20201215135705611](https://cdn.jsdelivr.net/gh/Liu-kakuki/Image/20201215135705.png)

修改和删除功能也能正常运行
