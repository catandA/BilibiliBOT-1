# catandBOT_Shiro

<p align="center">
    <a href="https://github.com/howmanybots/onebot"><img src="https://img.shields.io/badge/OneBot-v11-blue?style=flat-square&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABABAMAAABYR2ztAAAAIVBMVEUAAAAAAAADAwMHBwceHh4UFBQNDQ0ZGRkoKCgvLy8iIiLWSdWYAAAAAXRSTlMAQObYZgAAAQVJREFUSMftlM0RgjAQhV+0ATYK6i1Xb+iMd0qgBEqgBEuwBOxU2QDKsjvojQPvkJ/ZL5sXkgWrFirK4MibYUdE3OR2nEpuKz1/q8CdNxNQgthZCXYVLjyoDQftaKuniHHWRnPh2GCUetR2/9HsMAXyUT4/3UHwtQT2AggSCGKeSAsFnxBIOuAggdh3AKTL7pDuCyABcMb0aQP7aM4AnAbc/wHwA5D2wDHTTe56gIIOUA/4YYV2e1sg713PXdZJAuncdZMAGkAukU9OAn40O849+0ornPwT93rphWF0mgAbauUrEOthlX8Zu7P5A6kZyKCJy75hhw1Mgr9RAUvX7A3csGqZegEdniCx30c3agAAAABJRU5ErkJggg==" alt=""></a>
</p>

这是基于[Shiro](https://github.com/MisakaTAT/Shiro/) 的QQ机器人, 开发用于指向性的视频创作推广引流。

想要快速在自己的服务器上运行它，可以参见[快速开始](https://github.com/catandA/BilibiliBOT-1/blob/main/QuickStart_zh_cn.md)

另外需要使用任意支持反向WebSocket的OneBot协议客户端,比如[cq-http](https://github.com/Mrs4s/go-cqhttp)

另外此项目也使用了来自 [spring-mirai-server](https://github.com/protobufbot/spring-mirai-server) 仓库的Bot日志代码

## 目前功能列表

- 定时推荐配置文件指定的视频(详见content.json)
- 根据”视频推荐“指令作视频推荐
- 自动解析聊天里面的哔哩哔哩直链分享并显示出完整信息

示例：
![](.README_images/9584a2c7.png)

## 技术框架

本项目使用Spring框架与[Shiro](https://github.com/MisakaTAT/Shiro/), 对 [哔哩哔哩的公开API](https://api.bilibili.com/x/web-interface/view?bvid=${bid}) 进行HTTPS请求, 获取到JSON格式数据之后反序列化读取，并进行QQ消息构造再传回前端发送数据

## 相关链接

- Shiro [https://github.com/MisakaTAT/Shiro/](https://github.com/MisakaTAT/Shiro/)
- onebot [https://github.com/botuniverse/onebot](https://github.com/botuniverse/onebot)
- spring-mirai-server [https://github.com/protobufbot/spring-mirai-server](https://github.com/protobufbot/spring-mirai-server)

# License

This product is licensed under the GNU General Public License version 3. The license is as published by the Free Software Foundation published at [https://www.gnu.org/licenses/gpl-3.0.html](https://www.gnu.org/licenses/gpl-3.0.html).

Alternatively, this product is licensed under the GNU Lesser General Public License version 3 for non-commercial use. The license is as published by the Free Software Foundation published at [https://www.gnu.org/licenses/lgpl-3.0.html](https://www.gnu.org/licenses/lgpl-3.0.html).

Feel free to contact us if you have any questions about licensing or want to use the library in a commercial closed source product.

