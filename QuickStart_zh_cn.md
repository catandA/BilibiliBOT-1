# 快速开始

## 导入本项目

想要导入本项目,可以使用`git clone https://github.com/catandA/BilibiliBOT-1.git` 进行克隆
也可以直接下载ZIP, 将其解压之后在IDE中导入

## 运行本项目

此项目使用了Maven包管理器,在引入之后需要导入相关依赖,导入完成后打包成jar就可以进行初次运行测试
(或是直接运行release的jar包: 在有 `java` 的环境中执行 `java -jar BiliBiliBOT-1-X.X.X.jar` )

如果要配置自定义设置文件目录, 请使用JVM参数`-DconfigFile=<configPath>`

## JVM参数说明

### -DconfigFile

> 已知问题: 由于自动创建JSON没有写, 需要自己创建好这个Json, [格式参考](#配置文件说明)

设置配置文件路径, 不填则为 ./content.json

## 配置文件

> 具体格式: [Click here](README.md#配置文件说明)

运行后会检测当前目录的 `content.json` 文件,如果不存在则程序会自动退出
content里面存储有需要指定推荐的视频, 默认设置是每天早上八点自动推荐
targetGroups为目标群组(可以为多个)

## 连接客户端

本项目使用 `Shiro` 框架,兼容所有支持反向 `WebSocket` 的 `OneBot` 协议客户端


| 项目地址                                                                                        | 平台                                                            | 核心作者       | 备注                                                                                              |
| ------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------- | ---------------- | --------------------------------------------------------------------------------------------------- |
| [Yiwen-Chan/OneBot-YaYaopen in new window](https://github.com/Yiwen-Chan/OneBot-YaYa)           | [先驱open in new window](https://www.xianqubot.com/)            | kanri          |                                                                                                   |
| [richardchien/coolq-http-apiopen in new window](https://github.com/richardchien/coolq-http-api) | CKYU                                                            | richardchien   | 可在 Mirai 平台使用[mirai-nativeopen in new window](https://github.com/iTXTech/mirai-native) 加载 |
| [Mrs4s/go-cqhttpopen in new window](https://github.com/Mrs4s/go-cqhttp)                         | [MiraiGoopen in new window](https://github.com/Mrs4s/MiraiGo)   | Mrs4s          |                                                                                                   |
| [yyuueexxiinngg/cqhttp-miraiopen in new window](https://github.com/yyuueexxiinngg/cqhttp-mirai) | [Miraiopen in new window](https://github.com/mamoe/mirai)       | yyuueexxiinngg |                                                                                                   |
| [takayama-lily/onebotopen in new window](https://github.com/takayama-lily/onebot)               | [OICQopen in new window](https://github.com/takayama-lily/oicq) | takayama       |                                                                                                   |

这里以 `go-cqhttp` 为例，配置反向 `websocket` 连接到 `shiro`
初次运行 `go-cqhttp` 后，编辑 `go-cqhttp` 目录下的 `config.yml` 文件，并修改如下部分内容

```yaml
account: # 账号相关
uin: XXXXX # QQ账号
password: 'XXXXX' # 密码为空时使用扫码登录
```

```yaml
# 连接服务列表
servers:
  # 添加方式，同一连接方式可添加多个，具体配置说明请查看文档
  #- http: # http 通信
  #- ws:   # 正向 Websocket
  #- ws-reverse: # 反向 Websocket
  #- pprof: #性能分析服务器
  # 反向WS设置
  - ws-reverse:
      # 反向WS Universal 地址
      # 注意 设置了此项地址后下面两项将会被忽略
      universal: ws://127.0.0.1:5000/ws/shiro
```

之后运行 `go-cqhttp` ，若待登录成功后， `go-cqhttp` 应该会和 `catandBOT_Shiro` 建立连接

## 修改项目

`src/main/java/com/catand/bilibilibot1/plugin` 目录为目前此BOT的插件类的路径,可以以这些类作为基础模板进行修改后运行，关于Shiro的更多文档,请见
[重写事件](https://misakatat.github.io/shiro-docs/override_event/)
[注解事件](https://misakatat.github.io/shiro-docs/annotation_event/)
[API](https://misakatat.github.io/shiro-docs/action/)
