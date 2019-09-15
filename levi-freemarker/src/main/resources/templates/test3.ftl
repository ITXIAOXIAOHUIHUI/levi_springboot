<!DOCTYPE html>
<html>
<head>
    <meta charset="utf‐8">
    <title>Hello World!</title>
</head>
<body>
输出stu1的学生信息：<br/>
姓名：${stuMap['stu1'].name}<br/>
年龄：${stuMap['stu1'].age}<br/>
北京市昌平区建材城西路金燕龙办公楼一层 电话：400-618-90903、输出
1.3.1.4 if指令
if 指令即判断指令，是常用的FTL指令，freemarker在解析时遇到if会进行判断，条件为真则输出if中间的内容，否
则跳过内容不再输出。
1、数据模型：
使用list指令中测试数据模型。
2、模板：
输出stu1的学生信息：<br/>
姓名：${stuMap.stu1.name}<br/>
年龄：${stuMap.stu1.age}<br/>
遍历输出两个学生信息：<br/>
<table>
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>钱包</td>
    </tr>
    <#list stuMap?keys as k>
        <tr>
            <td>${k_index + 1}</td>
            <td>${stuMap[k].name}</td>
            <td>${stuMap[k].age}</td>
        </tr>
    </#list>
</table>
</body>
</html