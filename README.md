# amap-mcp-119 火警
获得二等奖🏆的高德MCP消防救援应用，报警智能AI助手：精准报警位置、最佳逃生路线、最近就医；智慧消防应急响应：调动最快出警的消防队、和规划最佳的救援路径。 
## 快速开始
* 1、执行初始化db/init.sql 
* 2、修改配置：阿里云百炼OPEN_API_KEY、数据库连接信息和账号密码 、高德地图api key
````
spring.ai.openai.base-url=https://dashscope.aliyuncs.com/compatible-mode/
spring.ai.openai.chat.options.model=deepseek-r1
spring.ai.openai.api-key=${OPEN_API_KEY}
spring.ai.mcp.client.stdio.servers-configuration=classpath:/mcp-servers-config.json
amap.key=${AMAP_API_KEY}

spring.datasource.url=jdbc:mysql://${MYSQL_HOST_PORT}/fire-test?useSSL=false&verifyServerCertificate=false&allowPublicKeyRetrieval=true&characterEncoding=utf8&characterSetResults=utf8&autoReconnect=true&failOverReadOnly=false&serverTimezone=GMT%2B8
spring.datasource.username=root
spring.datasource.password=${MYSQL_PASSWORD}
````
````
{
    "mcpServers": {
        "amap-maps": {
            "command": "npx.cmd", 
            "args": [
                "-y",
                "@amap/amap-maps-mcp-server"
            ],
            "env": {
                "AMAP_MAPS_API_KEY": "高德地图API Key"
            }
        }
    }
}
````
* 3、直接启动AmapMcp119Application  
## 应用系统
#### 1、119报警智能AI助手  
访问地址:http://localhost:8080/index.html  
  ![图片描述](./src/main/resources/image/119.png)

#### 2、消防队响应系统   
访问地址:http://localhost:8080/fire-station/index.html  
  ![图片描述](./src/main/resources/image/station.png)
## 更多资讯
想了解更多资讯，获得更多资料，请关注公众号【有范编程笔记】  
![图片描述](./src/main/resources/image/fanjh.png)

